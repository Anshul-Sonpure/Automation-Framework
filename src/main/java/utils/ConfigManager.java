package utils;

import java.util.ResourceBundle;

public class ConfigManager {

	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	// Method to get a config value by key

	public static String getConfigValue(String key) {
		return resourceBundle.getString(key);
	}
}