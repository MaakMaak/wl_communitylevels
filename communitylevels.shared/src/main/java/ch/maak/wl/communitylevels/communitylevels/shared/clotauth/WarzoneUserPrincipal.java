package ch.maak.wl.communitylevels.communitylevels.shared.clotauth;

import java.io.Serializable;
import java.security.Principal;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

@Contract(threading = ThreadingBehavior.IMMUTABLE)
public final class WarzoneUserPrincipal implements Principal, Serializable {

	private static final long serialVersionUID = -2266305184969850467L;

	private final String m_userName;
	private final String m_userId;
	private final String m_clotPass;

	public WarzoneUserPrincipal(final String userName, final String userId, String clotPass) {
		super();
		Args.notNull(userName, "User name");
		Args.notNull(userId, "User id");
		Args.notNull(clotPass, "clot pass");
		this.m_userName = userName;
		this.m_userId = userId;
		this.m_clotPass = clotPass;
	}

	@Override
	public String getName() {
		return m_userName;
	}

	public String getUserId() {
		return m_userId;
	}

	public String getClotPass() {
		return m_clotPass;
	}
}