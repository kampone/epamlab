package by.bsu.service.command.common;

import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by note on 25.03.2015.
 */
public class EmptyCommand implements ActionCommand {
    /**
     * If ActionFactory cant define command return default page
     * @see ActionCommand
     * @see by.bsu.service.action.ActionFactory
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
