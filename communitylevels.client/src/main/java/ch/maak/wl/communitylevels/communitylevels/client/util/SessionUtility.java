package ch.maak.wl.communitylevels.communitylevels.client.util;

import java.util.Optional;

import javax.security.auth.Subject;

import org.eclipse.scout.rt.platform.security.SimplePrincipal;

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

	public static Subject getDefaultSubject() {
		final Subject subject = new Subject();
		subject.getPrincipals().add(new SimplePrincipal("access-check-user"));
		subject.setReadOnly();
		return subject;
	}
}
