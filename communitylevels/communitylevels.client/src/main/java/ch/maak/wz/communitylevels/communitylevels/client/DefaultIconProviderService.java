package ch.maak.wz.communitylevels.communitylevels.client;

import org.eclipse.scout.rt.client.services.common.icon.AbstractIconProviderService;

import java.net.URL;

public class DefaultIconProviderService extends AbstractIconProviderService {
  @Override
  protected URL findResource(String relativePath) {
    return ResourceBase.class.getResource("icons/" + relativePath);
  }
}
