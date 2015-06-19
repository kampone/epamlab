package by.bsu.service.command.waiter;

import by.bsu.entity.dish.Dish;
import by.bsu.entity.order.Order;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.entity.DishLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddDishCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(AddDishCommand.class);

    /**
     * Waiter command: Add dish to Order
     * @see Order
     * @param request HttpServletRequest
     * @return page address to generate for user
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String dish_id = request.getParameter("dish_id");
        Order order = (Order) session.getAttribute("order");
        try {
            Dish dish = DishLogic.takeDishById(Integer.parseInt(dish_id));
            OrderLogic.addDish(order, dish);
            page = ConfigurationManager.getProperty("path.page.edit_order");

        } catch (LogicException e) {
            page = exeptionHelp(request, e, LOG);

        }
        return page;
    }
}
