package by.bsu.logic.report;

import by.bsu.entity.order.Order;
import by.bsu.entity.order.OrderDetail;

/**
 * Created by note on 09.05.2015.
 */
public class OrderReporter {

    /**
     * @param order Order sum of what needed
     * @return sum of dishes from order
     */
    public static double calculateSumOfOrder(Order order) {
        Double sum = 0d;
        for (OrderDetail detail : order.getOrderDetails()) {
            sum += detail.getDish().getPrice() * detail.getNumber();
        }
        return Math.rint(sum*100)/100;
    }
}
