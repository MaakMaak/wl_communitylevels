package ch.maak.wl.communitylevels.communitylevels.server.clotauth;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.rest.client.IRestResourceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.maak.wl.communitylevels.communitylevels.server.util.PropertyUtility;
import ch.maak.wl.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;

@Bean
public class ClotTokenValidateResourceClient implements IRestResourceClient {
	Logger LOG = LoggerFactory.getLogger(getClass());

	protected static final String RESOURCE_PATH = "ValidateInviteToken";

	protected WarzoneRestClientHelper helper() {
		return BEANS.get(WarzoneRestClientHelper.class);
	}

	public WarzoneUserPrincipal validateToken(String token) {
		WebTarget target = helper().target(RESOURCE_PATH).queryParam("Token", token);
		WarzoneUserPrincipal principal = null;
		try {
			String mail = PropertyUtility.read("communitylevels.wz.mail");
			String apiToken = PropertyUtility.read("communitylevels.wz.api.token");
			Response response = target.request()
					.accept(MediaType.APPLICATION_JSON)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.post(Entity.text(String.format("Email=%s&APIToken=%s", mail, apiToken)));
			ClotTokenValidateDo validateDo = response.readEntity(ClotTokenValidateDo.class);
			principal = new WarzoneUserPrincipal(validateDo.getName(), token, validateDo.getClotPass());
		} catch (Exception e) {
			LOG.info("Resource client failed", e);
		}

		return principal;
	}

	protected RuntimeException transformCustomException(RuntimeException e, Response r) {
		if (r != null && r.hasEntity() && MediaType.TEXT_PLAIN_TYPE.equals(r.getMediaType())) {
			String message = r.readEntity(String.class);
			throw new VetoException(message);
		}
		return e;
	}
}
