package by.bsu.entity.order;

import by.bsu.entity.dish.Dish;
import by.bsu.entity.restaurantentity.RestaurantEntity;

/**
 * Created by note on 26.03.2015.
 */
public class OrderDetail extends RestaurantEntity {
    private int id;
    private Dish dish;
    private int number;
    private StatusOrderDetail status;

    public OrderDetail() {
    }

    public OrderDetail(int id, Dish dish, int number, StatusOrderDetail status) {
        this.id = id;
        this.dish = dish;
        this.number = number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StatusOrderDetail getStatus() {
        return status;
    }

    public void setStatus(StatusOrderDetail status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;

        OrderDetail that = (OrderDetail) o;

        if (id != that.id) return false;
        if (number != that.number) return false;
        if (dish != null ? !dish.equals(that.dish) : that.dish != null) return false;
        if (status != that.status) return false;

        return true;
    }
    public boolean hasId(){
        return !(id==0);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", dish=" + dish.toString() +
                ", number=" + number +
                ", status=" + status +
                '}';
    }
}
