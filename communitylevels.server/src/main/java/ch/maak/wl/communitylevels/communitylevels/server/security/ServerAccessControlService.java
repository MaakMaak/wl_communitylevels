package ch.maak.wl.communitylevels.communitylevels.server.security;

import java.security.AllPermission;
import java.security.Permissions;

import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;

import ch.maak.wl.communitylevels.communitylevels.shared.security.AccessControlService;

@Replace
public class ServerAccessControlService extends AccessControlService {

	@Override
	protected Permissions execLoadPermissions(String userId) {
		Permissions permissions = new Permissions();
		permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"));

		permissions.add(new AllPermission());
		return permissions;
	}
}
