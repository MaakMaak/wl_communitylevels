package ch.maak.wl.communitylevels.communitylevels.server.clotauth;

import javax.ws.rs.core.Configuration;

import org.eclipse.scout.rt.rest.client.IRestClientConfigFactory;
import org.glassfish.jersey.client.ClientConfig;

public class RestClientConfigFactory implements IRestClientConfigFactory {

	@Override
	public Configuration createClientConfig() {
		ClientConfig config = new ClientConfig();
		return config;
	}
}
