package ch.maak.wl.communitylevels.communitylevels.ui.html;

import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.server.commons.authentication.AnonymousAccessController.AnonymousAuthConfig;

import ch.maak.wl.communitylevels.communitylevels.client.util.SessionUtility;

@Replace
public class CustomAnonymousAuthConfig extends AnonymousAuthConfig {

	@Override
	public String getUsername() {
		return SessionUtility.getRandomUserName();
	}
}
