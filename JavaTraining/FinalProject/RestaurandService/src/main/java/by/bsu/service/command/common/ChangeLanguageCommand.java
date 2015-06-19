package by.bsu.service.command.common;

import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class ChangeLanguageCommand implements ActionCommand {
    /**
     * Put new value of locale to Session
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("locale", request.getParameter("locale_select"));
        return ConfigurationManager.getProperty("path.page.index");
    }
}
