package by.bsu.service.command.common;

import by.bsu.logic.entity.MenuLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.entity.menu.Menu;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by note on 03.04.2015.
 */
public class DishMenuCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(DishMenuCommand.class);

    /**
     * Used to get page with all dishes from database
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request){
        String page = null;
        Menu menu = null;
        try {
            menu = MenuLogic.takeMenuFromDB();
            page = ConfigurationManager.getProperty("path.page.dishmenu");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        request.setAttribute("menu", menu);

        return page;
    }
}
