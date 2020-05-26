package ch.maak.wl.communitylevels.communitylevels.ui.html;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.commons.authentication.AnonymousAccessController;
import org.eclipse.scout.rt.server.commons.authentication.ServletFilterHelper;
import org.eclipse.scout.rt.server.commons.authentication.TrivialAccessController;
import org.eclipse.scout.rt.server.commons.authentication.TrivialAccessController.TrivialAuthConfig;

/**
 * This is the main servlet filter for the HTML UI.
 */
public class UiServletFilter implements Filter {

	private AnonymousAccessController m_anonymousAccessController;
	private TrivialAccessController m_trivialAccessController;
	private ClotAuthAccessController m_clotAuthAccessController;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		m_trivialAccessController = BEANS.get(TrivialAccessController.class).init(new TrivialAuthConfig()
				.withExclusionFilter(filterConfig.getInitParameter("filter-exclude")).withLoginPageInstalled(true));
		m_anonymousAccessController = BEANS.get(AnonymousAccessController.class).init(new CustomAnonymousAuthConfig());
		m_clotAuthAccessController = BEANS.get(ClotAuthAccessController.class).initController();
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
		m_trivialAccessController.destroy();
		m_anonymousAccessController.destroy();
		m_clotAuthAccessController.destroy();
	}
}
