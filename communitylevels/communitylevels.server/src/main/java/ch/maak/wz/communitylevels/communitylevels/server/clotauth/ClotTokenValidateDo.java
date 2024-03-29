package ch.maak.wz.communitylevels.communitylevels.server.clotauth;

import org.eclipse.scout.rt.dataobject.AttributeName;
import org.eclipse.scout.rt.dataobject.DoEntity;
import org.eclipse.scout.rt.dataobject.DoValue;

import java.io.Serializable;

public class ClotTokenValidateDo extends DoEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @AttributeName("name")
  public DoValue<String> name() {
    return doValue("name");
  }

  @AttributeName("clotpass")
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
