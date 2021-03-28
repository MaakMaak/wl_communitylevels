package ch.maak.wl.communitylevels.communitylevels.server.clotauth;

import org.eclipse.scout.rt.dataobject.DoEntity;
import org.eclipse.scout.rt.dataobject.DoValue;
import org.eclipse.scout.rt.dataobject.TypeName;

@TypeName("ClotTokenValidate")
public class ClotTokenValidateDo extends DoEntity {

	public DoValue<String> name() {
		return doValue("name");
	}

	public DoValue<String> clotpass() {
		return doValue("clotpass");
	}

	public ClotTokenValidateDo withName(String value) {
		name().set(value);
		return this;
	}

	public ClotTokenValidateDo withClotPass(String value) {
		clotpass().set(value);
		return this;
	}

	public String getName() {
		return name().get();
	}

	public String getClotPass() {
		return clotpass().get();
	}
}
