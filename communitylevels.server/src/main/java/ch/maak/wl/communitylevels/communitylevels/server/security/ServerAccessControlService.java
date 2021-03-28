package ch.maak.wl.communitylevels.communitylevels.server.security;

import java.security.AllPermission;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.security.DefaultPermissionCollection;
import org.eclipse.scout.rt.security.IPermissionCollection;
import org.eclipse.scout.rt.security.PermissionLevel;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;

import ch.maak.wl.communitylevels.communitylevels.shared.security.AccessControlService;

@Replace
public class ServerAccessControlService extends AccessControlService {

	@Override
	protected IPermissionCollection execLoadPermissions(String userId) {
		IPermissionCollection permissions = BEANS.get(DefaultPermissionCollection.class);
		permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"), PermissionLevel.ALL);

		permissions.add(new AllPermission());
		return permissions;
	}
}
