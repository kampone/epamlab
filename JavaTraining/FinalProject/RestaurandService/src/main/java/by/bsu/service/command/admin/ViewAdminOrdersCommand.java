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
 *
 */
public class ViewAdminOrdersCommand implements ActionCommand {
    private final static Logger LOG = Logger.getLogger(ViewAdminOrdersCommand.class);

    /**
     * Admin command: return page to confirm or cancel order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Order> orders = null;
        try {
            orders = OrderLogic.takeOrdersWithStatus(StatusOrderDetail.ORDERED);
            request.setAttribute("orders", orders);
            page = ConfigurationManager.getProperty("path.page.admin_orders");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }

        return page;
    }
}
