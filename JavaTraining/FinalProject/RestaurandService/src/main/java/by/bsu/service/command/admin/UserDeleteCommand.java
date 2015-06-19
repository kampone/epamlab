package by.bsu.service.command.admin;

import by.bsu.logic.exception.LogicException;
import by.bsu.logic.entity.UserLogic;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by note on 19.04.2015.
 */
public class UserDeleteCommand implements ActionCommand {
    private final static Logger LOG = Logger.getLogger(UserDeleteCommand.class);

    /**
     * Admin command: return page to delete user
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            request.getSession().setAttribute("users", UserLogic.takeAllUsers());
            page = ConfigurationManager.getProperty("path.page.del_user");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
