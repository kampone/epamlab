package by.bsu.service.manager;

import java.util.ResourceBundle;

/**
 * Create ResourceBundle to get message
 * @see ResourceBundle
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("property.config");
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
