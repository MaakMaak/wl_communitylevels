package ch.maak.wl.communitylevels.communitylevels.server.clotauth;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.platform.util.Assertions;

import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.IClotTokenValidateService;
import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;

public class ClotTokenValidateService implements IService, IClotTokenValidateService {

	@Override
	public WarzoneUserPrincipal validateToken(String token) {
		Assertions.assertNotNull(token);
		WarzoneUserPrincipal principal = BEANS.get(ClotTokenValidateResourceClient.class).validateToken(token);
		return principal;
	}
}
