package by.bsu.service.command.waiter;

import by.bsu.entity.order.Order;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ViewWaiterOrdersCommand implements ActionCommand {
    private final static Logger LOG = Logger.getLogger(ViewWaiterOrdersCommand.class);
    /**
     * Waiter command: show all waiter orders
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            HttpSession session = request.getSession();
            String login = (String) session.getAttribute("user");
            List<Order> orders = OrderLogic.takeWaiterOrders(login);
            request.setAttribute("orders", orders);
            page = ConfigurationManager.getProperty("path.page.waiter_orders");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
