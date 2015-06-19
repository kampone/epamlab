package by.bsu.service.manager;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Used to get system messages from property file
 * @see ResourceBundle
 */
public class MessageManager {

    private MessageManager() { }

    /**
     * @param key with this key Resource return message
     * @param locale method returns message with this Locale
     * @return message
     */
    public static String getProperty(String key, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("property.message", locale);
    return resourceBundle.getString(key);
    }

    /**
     * @param key with this key Resource return message
     * @param session HttpSession
     * @return message
     */
    public static String getProperty(String key, HttpSession session) {
        String sessionLocale = session.getAttribute("locale").toString();
        Locale locale = new Locale(sessionLocale);
        return getProperty(key, locale);
    }


}
