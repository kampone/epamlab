package by.bsu.service.command.admin;

import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by note on 16.04.2015.
 */
public class UserCreateCommand implements ActionCommand {
    /**
     * Admin command: return page to create new user
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.new_user");
    }
}
