package by.bsu.service.command.common;

import by.bsu.entity.user.UserRole;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class AuthorizationCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE = "ru_RU";

    /**
     * Authorization Command
     * Get role from Session and return page of main menu for this role
     * @see UserRole
     * @see HttpSession
     *
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute("userRole");
        String page = null;
        if(session.getAttribute("userRole") == null){
            role = UserRole.GUEST;
            session.setAttribute("userRole", role);
        }
        if(session.getAttribute("locale") == null){
            session.setAttribute("locale", DEFAULT_LOCALE);
        }
        switch (role){
            case GUEST:
                page = ConfigurationManager.getProperty("path.page.login");
                break;
            case WAITER:
                page = ConfigurationManager.getProperty("path.page.waiter_menu");
                break;
            case ADMINISTRATOR:
                page = ConfigurationManager.getProperty("path.page.admin_menu");
                break;
            case COOK:
                page = ConfigurationManager.getProperty("path.page.cook_menu");
                break;
        }
       return page;
    }
}
