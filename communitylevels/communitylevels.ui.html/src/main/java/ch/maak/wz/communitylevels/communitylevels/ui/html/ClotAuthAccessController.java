package ch.maak.wz.communitylevels.communitylevels.ui.html;

import ch.maak.wz.communitylevels.communitylevels.client.clotauth.ClotAuthService;
import ch.maak.wz.communitylevels.communitylevels.client.util.SessionUtility;
import ch.maak.wz.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.PlatformException;
import org.eclipse.scout.rt.platform.security.SecurityUtility;
import org.eclipse.scout.rt.platform.util.Base64Utility;
import org.eclipse.scout.rt.platform.util.ObjectUtility;
import org.eclipse.scout.rt.server.commons.authentication.CookieAccessController;
import org.eclipse.scout.rt.server.commons.authentication.CookieAccessController.MaxAgeProperty;
import org.eclipse.scout.rt.server.commons.authentication.CookieAccessController.NameProperty;
import org.eclipse.scout.rt.server.commons.authentication.IAccessController;
import org.eclipse.scout.rt.server.commons.authentication.ServletFilterHelper;
import org.eclipse.scout.rt.shared.SharedConfigProperties.AuthTokenPrivateKeyProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Principal;


public class ClotAuthAccessController implements IAccessController {
  private static final Logger LOG = LoggerFactory.getLogger(ClotAuthAccessController.class);

  private static final String SESSION_ATTRIBUTE_COOKIE_SENT = CookieAccessController.class.getName() + "#cookieSent";

  private String m_cookieName;
  private long m_maxAge;
  private byte[] m_signKey;

  public ClotAuthAccessController initController() {
    return this;
  }

  @PostConstruct
  protected void init() {
    m_cookieName = CONFIG.getPropertyValue(NameProperty.class);
    m_maxAge = CONFIG.getPropertyValue(MaxAgeProperty.class);
    m_signKey = CONFIG.getPropertyValue(AuthTokenPrivateKeyProperty.class);
    if (m_signKey == null) {
      throw new PlatformException("Missing config.properties entry used for signing auth data: '{}'", BEANS.get(AuthTokenPrivateKeyProperty.class).getKey());
    }
  }

  @Override
  public boolean handle(final HttpServletRequest req, final HttpServletResponse resp, final FilterChain chain) throws IOException, ServletException {

    switch (getTarget(req)) {
      case "/login":
      case "/logout":
        clearPrincipalOnCookie(resp);
        return false;
      case "/clotauth":
        return authorize(req, resp, chain);
    }

    Principal principal = BEANS.get(ServletFilterHelper.class).getPrincipalOnSession(req);
    if (principal == null) {
      principal = loadPrincipalFromCookie(req);
      if (principal != null) {
        BEANS.get(ServletFilterHelper.class).putPrincipalOnSession(req, principal);
        return false;
      }
    }

    return false;
  }

  protected boolean authorize(final HttpServletRequest req, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {
    String clotpass = req.getParameter("clotpass");
    String token = req.getParameter("token");


    WarzoneUserPrincipal principal = ClientRunContexts.copyCurrent(true)
      .withSubject(SessionUtility.getDefaultSubject())
      .call(() -> BEANS.get(ClotAuthService.class).validateToken(token));

    if (principal == null || ObjectUtility.notEquals(clotpass, principal.getClotPass())) {
      LOG.warn("Authentication failed {}", response);
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
      return true;
    }

    // OWASP: force a new HTTP session to be created.
    final HttpSession session = req.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    // Put authenticated principal onto (new) HTTP session
    BEANS.get(ServletFilterHelper.class).putPrincipalOnSession(req, principal);
    storePrincipalToCookie(req, response, principal);
    BEANS.get(ServletFilterHelper.class).redirectTo(req, response, "/");
    return true;
  }

  protected String getTarget(final HttpServletRequest request) {
    final String pathInfo = request.getPathInfo();
    if (pathInfo != null) {
      return pathInfo;
    }

    final String requestURI = request.getRequestURI();
    return requestURI.substring(requestURI.lastIndexOf('/'));
  }

  /**
   * @return signed value in the format <code>base64(signature):value</code>
   */
  protected String signValue(String value) {
    try {
      byte[] sig = SecurityUtility.createMac(m_signKey, value.getBytes(StandardCharsets.UTF_8));
      return Base64Utility.encode(sig) + ":" + value;
    } catch (Exception e) {
      throw new PlatformException("Failed signing value '{}'", value, e);
    }
  }

  /**
   * @param signedValue value in the format <code>base64(signature):value</code>
   * @return the verified value extracted from the signedValue
   */
  protected String verifyValue(String signedValue) {
    if (signedValue != null && signedValue.indexOf(':') > -1) {
      String value = signedValue.substring(signedValue.indexOf(':') + 1);
      if (signValue(value).equals(signedValue)) {
        return value;
      }
    }
    return null;
  }

  protected Principal loadPrincipalFromCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie c : cookies) {
        if (m_cookieName.equals(c.getName())) {
          Principal p = getDeserialized(verifyValue(c.getValue()));
          if (p != null) {
            LOG.info("Load signed cookie '{}' for '{}'", m_cookieName, p.getName());
            HttpSession session = request.getSession(false);
            if (session != null) {
              session.setAttribute(SESSION_ATTRIBUTE_COOKIE_SENT, Boolean.TRUE);
            }
            return p;
          }
        }
      }
    }
    return null;
  }

  private Principal getDeserialized(String ser) {
    WarzoneUserPrincipal principal = null;
    try {
      InputStream stream = new ByteArrayInputStream(Base64Utility.decode(ser));
      ObjectInputStream ois = new ObjectInputStream(stream);
      principal = (WarzoneUserPrincipal) ois.readObject();
      ois.close();
    } catch (Exception e) {
      LOG.warn("Deserialization failed", e);

    }

    return principal;
  }

  private String getSerialized(Principal principal) {
    String ser = null;
    try {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      ObjectOutputStream so = new ObjectOutputStream(bo);
      so.writeObject(principal);
      so.flush();
      ser = Base64Utility.encode(bo.toByteArray());
    } catch (Exception e) {
      LOG.warn("Serialization failed", e);
    }

    return ser;

  }

  protected void storePrincipalToCookie(HttpServletRequest req, HttpServletResponse resp, Principal p) {
    HttpSession session = req.getSession(false);
    if (session == null) {
      return;
    }
    if (Boolean.TRUE.equals(session.getAttribute(SESSION_ATTRIBUTE_COOKIE_SENT))) {
      return;
    }
    session.setAttribute(SESSION_ATTRIBUTE_COOKIE_SENT, Boolean.TRUE);

    String signedValue = signValue(getSerialized(p));
    LOG.info("Store signed cookie '{}' for '{}'", m_cookieName, p.getName());
    Cookie myCookie = new Cookie(m_cookieName, signedValue);
    myCookie.setMaxAge((int) m_maxAge);
    resp.addCookie(myCookie);
  }

  protected void clearPrincipalOnCookie(HttpServletResponse resp) {
    LOG.info("Remove cookie '{}'", m_cookieName);
    Cookie myCookie = new Cookie(m_cookieName, "");
    myCookie.setMaxAge(0);// delete it
    resp.addCookie(myCookie);
  }

  @Override
  public void destroy() {
    // NOOP
  }

}
