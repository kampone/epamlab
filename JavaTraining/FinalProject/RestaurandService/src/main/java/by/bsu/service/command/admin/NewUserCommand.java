package by.bsu.service.command.admin;

import by.bsu.logic.exception.LogicException;
import by.bsu.logic.login.LoginChecker;
import by.bsu.logic.entity.UserLogic;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import by.bsu.service.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NewUserCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(NewUserCommand.class);
    private HttpServletRequest request;

    /**
     * Admin command: create user
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        this.request = request;
        HttpSession session = request.getSession();
        String page = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        int role = Integer.parseInt(request.getParameter("user_role"));
        try {
            if(password.equals(confirmPassword)) {
                if (LoginChecker.checkFormat(login) && LoginChecker.checkFormat(password)) {
                    if (UserLogic.createNewUser(login, password, role)) {
                        String message = MessageManager.getProperty("message.accountcreate", session);
                        request.setAttribute("infoMessage", message);
                        page = ConfigurationManager.getProperty("path.page.index");
                    } else {
                        request.setAttribute("errorMessage", "Account is not created, id or login exist ");//Локализация
                        page = ConfigurationManager.getProperty("path.page.new_user");

                    }
                } else {
                    String message = MessageManager.getProperty("message.loginformaterror", session);
                    request.setAttribute("errorMessage", message);
                    page = ConfigurationManager.getProperty("path.page.new_user");

                }
            }else{
                String message = MessageManager.getProperty("message.confirmpassworderror", session);
                request.setAttribute("errorMessage", message);
                page = ConfigurationManager.getProperty("path.page.new_user");
            }

        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
