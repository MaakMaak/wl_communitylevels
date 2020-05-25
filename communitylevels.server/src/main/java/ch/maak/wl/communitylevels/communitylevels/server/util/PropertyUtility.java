package ch.maak.wl.communitylevels.communitylevels.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.StringUtility;

public final class PropertyUtility {

	public static String read(String key) {
		InputStream is = PropertyUtility.class.getClassLoader().getResourceAsStream("deploy.properties");
		if (is == null) {
			throw new ProcessingException("deploy.properties not found");
		}
		Properties p = new Properties();
		String property = null;
		try {
			p.load(is);
			property = p.getProperty(key);
		} catch (IOException e) {
			throw new ProcessingException("Could not load property {}", key);
		}
		if (StringUtility.isNullOrEmpty(property)) {
			throw new ProcessingException("Could not load property {}", key);
		}
		return property;
	}
}
