package by.bsu.service.command.waiter;

import by.bsu.entity.menu.Menu;
import by.bsu.entity.order.Order;
import by.bsu.logic.entity.MenuLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by note on 15.04.2015.
 */
public class OrderCreateCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(OrderCreateCommand.class);
    /**
     * Waiter command: create new order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            Menu menu = MenuLogic.takeMenuFromDB();
            Order order = new Order();
            HttpSession session = request.getSession();
            session.setAttribute("dishes", menu);
            session.setAttribute("order", order);
            page = ConfigurationManager.getProperty("path.page.edit_order");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
