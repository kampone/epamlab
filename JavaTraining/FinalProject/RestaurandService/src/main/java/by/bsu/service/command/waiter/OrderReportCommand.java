package by.bsu.service.command.waiter;

import by.bsu.entity.order.Order;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.logic.report.OrderReporter;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class OrderReportCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(OrderReportCommand.class);
    /**
     * Waiter command: get order report
     * @param request HttpServletRequest
     * @return page address to generate for user
     */

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int idOrder = Integer.valueOf(request.getParameter("orderId"));
        try {
            Order order = OrderLogic.takeOrdersWithId(idOrder);
            double sum = OrderReporter.calculateSumOfOrder(order);
            request.setAttribute("order", order);
            request.setAttribute("sum", sum);
            page = ConfigurationManager.getProperty("path.page.order_report");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
