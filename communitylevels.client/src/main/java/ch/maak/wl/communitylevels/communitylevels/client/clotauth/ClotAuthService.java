package ch.maak.wl.communitylevels.communitylevels.client.clotauth;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;

import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.IClotTokenValidateService;
import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;

@Bean
public class ClotAuthService {

	public WarzoneUserPrincipal validateToken(String token) {
		WarzoneUserPrincipal principal = BEANS.get(IClotTokenValidateService.class).validateToken(token);
		return principal;
	}
}
