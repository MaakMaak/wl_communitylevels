package ch.maak.wl.communitylevels.communitylevels.server.db;

import org.eclipse.scout.rt.server.jdbc.mysql.AbstractMySqlSqlService;

import ch.maak.wl.communitylevels.communitylevels.server.util.PropertyUtility;

public class MySqlSqlService extends AbstractMySqlSqlService {
	@Override
	protected String getConfiguredJdbcMappingName() {
		String host = "login-144.hoststar.ch";
		return "jdbc:mysql://" + host + "/Warlight?zeroDateTimeBehavior=convertToNull";
	}

	@Override
	protected String getConfiguredUsername() {
		return "Warlight";
	}

	@Override
	protected String getConfiguredPassword() {
		return PropertyUtility.read("communitylevels.db.password");
	}
}
