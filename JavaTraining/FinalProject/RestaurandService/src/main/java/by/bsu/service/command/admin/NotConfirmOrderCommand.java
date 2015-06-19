package by.bsu.service.command.admin;

import by.bsu.entity.order.Order;
import by.bsu.entity.order.StatusOrderDetail;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by note on 11.05.2015.
 */
public class NotConfirmOrderCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(NotConfirmOrderCommand.class);

    /**
     * Admin command: cancel order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Order> orders = null;
        int orderId = Integer.valueOf(request.getParameter("idOrder"));
        try {
            OrderLogic.cancelOrder(orderId);
            orders = OrderLogic.takeOrdersWithStatus(StatusOrderDetail.ORDERED);
            request.setAttribute("orders", orders);
            page = ConfigurationManager.getProperty("path.page.admin_orders");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
