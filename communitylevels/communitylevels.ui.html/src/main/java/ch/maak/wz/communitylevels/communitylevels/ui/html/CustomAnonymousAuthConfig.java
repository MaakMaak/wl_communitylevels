package ch.maak.wz.communitylevels.communitylevels.ui.html;

import ch.maak.wz.communitylevels.communitylevels.client.util.SessionUtility;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.server.commons.authentication.AnonymousAccessController.AnonymousAuthConfig;

@Replace
public class CustomAnonymousAuthConfig extends AnonymousAuthConfig {

  @Override
  public String getUsername() {
    return SessionUtility.getRandomUserName();
  }
}
