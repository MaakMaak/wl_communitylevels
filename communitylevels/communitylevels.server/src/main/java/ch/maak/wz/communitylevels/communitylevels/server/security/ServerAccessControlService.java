package ch.maak.wz.communitylevels.communitylevels.server.security;

import ch.maak.wz.communitylevels.communitylevels.shared.security.AccessControlService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.security.AllPermissionCollection;
import org.eclipse.scout.rt.security.IPermissionCollection;

@Replace
public class ServerAccessControlService extends AccessControlService {

  @Override
  protected IPermissionCollection execLoadPermissions(String userId) {
    return BEANS.get(AllPermissionCollection.class);
  }
}
