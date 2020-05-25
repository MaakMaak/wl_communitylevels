package ch.maak.wl.communitylevels.communitylevels.client.util;

import java.util.Optional;

import ch.maak.wl.communitylevels.communitylevels.client.ClientSession;
import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;

public final class SessionUtility {

	public static Optional<WarzoneUserPrincipal> getWarzoneUserPrincipal() {
		return ClientSession.get()
				.getSubject()
				.getPrincipals()
				.stream()
				.filter(p -> p instanceof WarzoneUserPrincipal)
				.map(p -> (WarzoneUserPrincipal) p)
				.findFirst();
	}
}
