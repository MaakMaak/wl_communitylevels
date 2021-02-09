package ch.maak.wl.communitylevels.communitylevels.server.db;

import org.eclipse.scout.rt.server.jdbc.mysql.AbstractMySqlSqlService;

import ch.maak.wl.communitylevels.communitylevels.server.util.PropertyUtility;

public class MySqlSqlService extends AbstractMySqlSqlService {
	@Override
	protected String getConfiguredJdbcMappingName() {
		String host = "lx39.hoststar.hosting";
		return "jdbc:mysql://" + host + "/ch98612_Warlight?zeroDateTimeBehavior=convertToNull";
	}

	@Override
	protected String getConfiguredUsername() {
		return "ch98612_Warlight";
	}

	@Override
	protected String getConfiguredPassword() {
		return PropertyUtility.read("communitylevels.db.password");
	}
}
