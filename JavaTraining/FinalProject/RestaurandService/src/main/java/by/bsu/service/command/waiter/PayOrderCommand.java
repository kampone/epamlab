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

public class PayOrderCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(PayOrderCommand.class);
    /**
     * Waiter command: mark order status "Payed"
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            int idOrder =  Integer.valueOf(request.getParameter("idOrder"));
            OrderLogic.payedOrderWithId(idOrder);
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
