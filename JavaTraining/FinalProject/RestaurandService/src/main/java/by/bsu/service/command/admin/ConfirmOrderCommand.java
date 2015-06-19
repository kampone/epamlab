package by.bsu.service.command.admin;

import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


/**
 * Implementation of ActionCommand
 * @see ActionCommand
 *
 */
public class ConfirmOrderCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(ConfirmOrderCommand.class);

    /**
     * Takes idOrder from jsp page and change status of this Order
     * @see by.bsu.entity.order.Order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Integer id = Integer.valueOf(request.getParameter("idOrder"));
        try {
            OrderLogic.confirmOrder(id);
            page =  new ViewAdminOrdersCommand().execute(request);
        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);
        }
        return page;
    }
}
