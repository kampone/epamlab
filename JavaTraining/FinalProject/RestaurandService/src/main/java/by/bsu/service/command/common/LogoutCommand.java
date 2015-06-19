package by.bsu.service.command;

import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by note on 25.03.2015.
 */
public class LogoutCommand implements ActionCommand {
    /**
     * Log out
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
