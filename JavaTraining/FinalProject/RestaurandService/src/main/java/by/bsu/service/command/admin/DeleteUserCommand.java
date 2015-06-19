package by.bsu.service.command.admin;

import by.bsu.logic.exception.LogicException;
import by.bsu.logic.entity.UserLogic;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import by.bsu.service.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by note on 19.04.2015.
 */
public class DeleteUserCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    /**
     * Admin command: delete user
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = null;
        try {
            if(UserLogic.deleteUserWithLogin(request.getParameter("del_user_login"))){
                String message = MessageManager.getProperty("message.info.delete_user", session);
                session.setAttribute("infoMessage", message);
            }else{
                String message = MessageManager.getProperty("message.delete_user_error", session);
                session.setAttribute("infoMessage", message);
            }
            page = ConfigurationManager.getProperty("path.page.index");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
