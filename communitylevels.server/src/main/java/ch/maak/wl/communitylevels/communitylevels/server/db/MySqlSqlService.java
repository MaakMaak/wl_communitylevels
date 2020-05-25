package ch.maak.wl.communitylevels.communitylevels.server.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.mysql.AbstractMySqlSqlService;

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
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("deploy.properties");
		if (is == null) {
			throw new ProcessingException("deploy.properties not found");
		}
		Properties p = new Properties();
		String pw = null;
		try {
			p.load(is);
			pw = p.getProperty("db.password");
		} catch (IOException e) {
			throw new ProcessingException("DB Password in deploy.properties not found");
		}
		if (StringUtility.isNullOrEmpty(pw)) {
			throw new ProcessingException("DB Password in deploy.properties not found");
		}
		return pw;
	}
}
