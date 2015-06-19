package by.bsu.service.command.common;


import by.bsu.logic.exception.LogicException;
import by.bsu.logic.login.LoginChecker;
import by.bsu.entity.user.User;
import by.bsu.logic.entity.UserLogic;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import by.bsu.service.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by note on 25.03.2015.
 */
public class LoginCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    /**
     * Check login and password
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request){
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();
        try {
            if(LoginChecker.checkFormat(login)&&LoginChecker.checkFormat(pass)) {
                if (LoginChecker.checkLogin(login, pass)) {
                    User user = UserLogic.takeUserByLogin(login);
                    session.setAttribute("userRole", user.getRole());
                    session.setAttribute("user", user.getLogin());
                    page = ConfigurationManager.getProperty("path.page.index");

                } else {
                    String message = MessageManager.getProperty("message.loginerror", session);
                    request.setAttribute("errorLoginPassMessage", message);
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            }else{
                String message = MessageManager.getProperty("message.loginformaterror", session);
                request.setAttribute("errorLoginPassMessage", message);
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);

        }
        return page;
    }




}
