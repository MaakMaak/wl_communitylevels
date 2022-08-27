package ch.maak.wz.communitylevels.communitylevels.ui.html;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.commons.authentication.AnonymousAccessController;
import org.eclipse.scout.rt.server.commons.authentication.ServletFilterHelper;
import org.eclipse.scout.rt.server.commons.authentication.TrivialAccessController;
import org.eclipse.scout.rt.server.commons.authentication.TrivialAccessController.TrivialAuthConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is the main servlet filter for the HTML UI.
 */
public class UiServletFilter implements Filter {

  private ClotAuthAccessController m_clotAuthAccessController;
  private TrivialAccessController m_trivialAccessController;
  private AnonymousAccessController m_anonymousAccessController;

	@Override
	public void init(FilterConfig filterConfig) {
    m_clotAuthAccessController = BEANS.get(ClotAuthAccessController.class).initController();
    m_trivialAccessController = BEANS.get(TrivialAccessController.class).init(new TrivialAuthConfig()
				.withExclusionFilter(filterConfig.getInitParameter("filter-exclude")).withLoginPageInstalled(true));
    m_anonymousAccessController = BEANS.get(AnonymousAccessController.class).init(new CustomAnonymousAuthConfig());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse resp = (HttpServletResponse) response;

		if (m_clotAuthAccessController.handle(req, resp, chain)) {
			return;
		}

		if (m_trivialAccessController.handle(req, resp, chain)) {
			return;
		}

		if (m_anonymousAccessController.handle(req, resp, chain)) {
			return;
		}

		BEANS.get(ServletFilterHelper.class).forwardToLoginForm(req, resp);
	}

	@Override
	public void destroy() {
    m_clotAuthAccessController.destroy();
    m_trivialAccessController.destroy();
    m_anonymousAccessController.destroy();
	}
}
