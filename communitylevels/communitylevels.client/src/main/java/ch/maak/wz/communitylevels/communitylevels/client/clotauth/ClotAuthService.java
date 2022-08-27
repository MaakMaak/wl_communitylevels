package ch.maak.wz.communitylevels.communitylevels.client.clotauth;

import ch.maak.wz.communitylevels.communitylevels.shared.clotauth.IClotTokenValidateService;
import ch.maak.wz.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;

@Bean
public class ClotAuthService {

  public WarzoneUserPrincipal validateToken(String token) {
    return BEANS.get(IClotTokenValidateService.class).validateToken(token);
  }
}
