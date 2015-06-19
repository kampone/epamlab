package by.bsu.service.command.cook;

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
 * Created by note on 24.04.2015.
 */
public class ViewCookOrdersCommand implements ActionCommand {
    private final static Logger LOG = Logger.getLogger(ViewCookOrdersCommand.class);

    /**
     * Cook command: add all dishes to cook
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            List<Order> orders = OrderLogic.takeOrdersWithStatus(StatusOrderDetail.CONFIRMED);
            request.setAttribute("orders", orders);
            page = ConfigurationManager.getProperty("path.page.cook_orders");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
