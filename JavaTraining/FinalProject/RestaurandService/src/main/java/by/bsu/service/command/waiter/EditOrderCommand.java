package by.bsu.service.command.waiter;

import by.bsu.entity.menu.Menu;
import by.bsu.entity.order.Order;
import by.bsu.logic.entity.MenuLogic;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class EditOrderCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(EditOrderCommand.class);
    /**
     * Waiter command: page to edit this order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        LOG.debug(orderId);
        HttpSession session = request.getSession();
        try {
            Menu menu = MenuLogic.takeMenuFromDB();
            Order order = OrderLogic.takeOrdersWithId(orderId);
            LOG.debug(order.toString());
            session.setAttribute("order", order);
            session.setAttribute("dishes", menu);
            page = ConfigurationManager.getProperty("path.page.edit_order");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
