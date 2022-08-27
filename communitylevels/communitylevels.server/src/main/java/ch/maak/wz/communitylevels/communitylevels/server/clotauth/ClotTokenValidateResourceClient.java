package ch.maak.wz.communitylevels.communitylevels.server.clotauth;

import ch.maak.wz.communitylevels.communitylevels.server.util.PropertyUtility;
import ch.maak.wz.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;
import org.apache.http.protocol.HTTP;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.rest.client.IRestResourceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Bean
public class ClotTokenValidateResourceClient implements IRestResourceClient {
  Logger LOG = LoggerFactory.getLogger(getClass());

  protected static final String RESOURCE_PATH = "ValidateInviteToken";

  protected WarzoneRestClientHelper helper() {
    return BEANS.get(WarzoneRestClientHelper.class);
  }

  public WarzoneUserPrincipal validateToken(String token) {
    WebTarget target = helper().target(RESOURCE_PATH).queryParam("Token", token);

    String mail = PropertyUtility.read("communitylevels.wz.mail");
    String apiToken = PropertyUtility.read("communitylevels.wz.api.token");
    try (Response response = target.request()
      .accept(MediaType.APPLICATION_JSON)
      .header(HTTP.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
      .post(Entity.text(String.format("Email=%s&APIToken=%s", mail, apiToken)))) {
      ClotTokenValidateDo validateDo = response.readEntity(ClotTokenValidateDo.class);
      return new WarzoneUserPrincipal(validateDo.getName(), token, validateDo.getClotPass());
    } catch (Exception e) {
      LOG.warn("Resource client failed", e);
    }
    return null;
  }

  protected RuntimeException transformCustomException(RuntimeException e, Response r) {
    if (r != null && r.hasEntity() && MediaType.TEXT_PLAIN_TYPE.equals(r.getMediaType())) {
      String message = r.readEntity(String.class);
      throw new VetoException(message);
    }
    return e;
  }
}
