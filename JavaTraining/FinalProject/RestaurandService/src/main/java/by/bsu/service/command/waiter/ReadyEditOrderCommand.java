package by.bsu.service.command.waiter;

import by.bsu.entity.order.Order;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import by.bsu.service.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ReadyEditOrderCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(ReadyEditOrderCommand.class);
    /**
     * Waiter command: used to finish edit order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        String login = (String) session.getAttribute("user");
        try {
            OrderLogic.writeOrder(order, login);
            request.setAttribute("infoMessage", MessageManager.getProperty("message.info.dish_edit_ready",session ));
            page = ConfigurationManager.getProperty("path.page.waiter_menu");
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
