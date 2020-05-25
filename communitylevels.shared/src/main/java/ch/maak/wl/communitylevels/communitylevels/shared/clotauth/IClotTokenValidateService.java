package ch.maak.wl.communitylevels.communitylevels.shared.clotauth;

import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IClotTokenValidateService {

	WarzoneUserPrincipal validateToken(String token);

}
