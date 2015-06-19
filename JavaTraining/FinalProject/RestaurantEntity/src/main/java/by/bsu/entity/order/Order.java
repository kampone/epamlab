package by.bsu.entity.order;

import by.bsu.entity.dish.Dish;
import by.bsu.entity.restaurantentity.RestaurantEntity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by note on 26.03.2015.
 */
public class Order extends RestaurantEntity {
    private int id;
    private List<OrderDetail> orderDetails;
    private GregorianCalendar time;

    public Order() {
        time = new GregorianCalendar();
        orderDetails = new ArrayList<OrderDetail>();
    }

    public Order(int id, List<OrderDetail> orderDetails, GregorianCalendar time) {
        this.id = id;
        this.orderDetails = orderDetails;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public boolean hasADish(Dish dish){
        for (OrderDetail detail : orderDetails) {
            if (detail.getDish().equals(dish)) {
                return true;
            }
        }
        return false;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public boolean addOrderDetail(OrderDetail orderDetail){
        return orderDetails.add(orderDetail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order that = (Order) o;

        if (id != that.id) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public boolean hasId(){
        return !(id==0);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", time=" + time.getTime() +
                ", OrderDetails=" +orderDetails.toString() +
                '}';
    }
}
