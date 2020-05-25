package ch.maak.wl.communitylevels.communitylevels.server.clotauth;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.rest.client.AbstractRestClientHelper;
import org.eclipse.scout.rt.rest.error.ErrorDo;
import org.eclipse.scout.rt.rest.error.ErrorResponse;
import org.glassfish.jersey.client.ClientConfig;

@Bean
public class WarzoneRestClientHelper extends AbstractRestClientHelper {
	@Override
	protected String getBaseUri() {
		return "https://www.warzone.com/API/";
	}

	@Override
	protected RuntimeException transformException(RuntimeException e, Response response) {
		if (response != null && response.hasEntity()) {
			ErrorDo error = response.readEntity(ErrorResponse.class).getError();
			throw new VetoException(error.getMessage()).withTitle(error.getTitle());
		}
		return e;
	}

	@Override
	protected Configuration createClientConfig() {
		ClientConfig config = new ClientConfig();
		return config;
	}
}