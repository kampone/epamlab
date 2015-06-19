package by.bsu.logic.entity;

import by.bsu.entity.dish.Dish;
import by.bsu.entity.order.Order;
import by.bsu.entity.order.OrderDetail;
import by.bsu.entity.order.StatusOrderDetail;
import by.bsu.logic.exception.LogicException;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;
import by.bsu.restaurant.order.OrderDAO;

import java.util.List;


public class OrderLogic {
    /**
     * @param order Dish will add to this order
     * @param dish Dish that will be added
     */
    public static void addDish(Order order, Dish dish) {
        for (OrderDetail detail : order.getOrderDetails()) {
            if (detail.getDish().equals(dish) && detail.getStatus() == StatusOrderDetail.ORDERED) {
                int numberOfDishes = detail.getNumber();
                detail.setNumber(++numberOfDishes);
                return;
            }
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDish(dish);
        orderDetail.setNumber(1);
        orderDetail.setStatus(StatusOrderDetail.ORDERED);
        order.addOrderDetail(orderDetail);


    }

    /**
     * @param order Order that will be written
     * @param login Login of Order
     * @throws LogicException if some Connection or DAO problems
     */
    public static void writeOrder(Order order, String login) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            if (!order.hasId()) {
                orderDAO.wirteOrder(order, login);
                int i = orderDAO.takeLastOrderId();
                order.setId(i);
            }
            for (OrderDetail detail : order.getOrderDetails()) {
                if (!detail.hasId()) {
                    writeOrderDetail(order.getId(), detail, StatusOrderDetail.ORDERED);
                }
            }
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }

    /**
     * @param id Id Order
     * @param detail Detail to be written
     * @param status Of Detail
     * @throws LogicException if some Connection or DAO problems
     */
    public static void writeOrderDetail(int id, OrderDetail detail, StatusOrderDetail status) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            orderDAO.wirteOrderDetail(id, detail, status);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }

    /**
     * @param status Status of orders
     * @return List of Orders with status
     * @throws LogicException if some Connection or DAO problems
     */
    public static List<Order> takeOrdersWithStatus(StatusOrderDetail status) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            return orderDAO.takeOrdersWithStatus(status);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }

    /**
     * @param login Login of orders
     * @return List of Orders with waiter
     * @throws LogicException if some Connection or DAO problems
     */
    public static List<Order> takeWaiterOrders(String login) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            return orderDAO.takeWaiterOrders(login);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }

    /**
     * Change status of order
     * @param id If order
     * @throws LogicException if some Connection or DAO problems
     */
    public static void confirmOrder(Integer id) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            orderDAO.confirmOrderWithId(id);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }
    /**
     * Change status of order
     * @param id If order
     * @throws LogicException if some Connection or DAO problems
     */
    public static void cookDish(Integer id) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            orderDAO.cookDishWithId(id);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }

    /**
     * @param id id of order
     * @return List of Orders with id
     * @throws LogicException if some Connection or DAO problems
     */
    public static Order takeOrdersWithId(int id) throws LogicException {
        Order order = null;
        try (OrderDAO orderDAO = new OrderDAO()) {
            order = orderDAO.takeOrdersWithId(id);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
        return order;
    }

    /**
     * Change status of order
     * @param id If order
     * @throws LogicException if some Connection or DAO problems
     */
    public static void payedOrderWithId(int id) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            orderDAO.payedOrderWithId(id);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }
    /**
     * Change status of order
     * @param id If order
     * @throws LogicException if some Connection or DAO problems
     */
    public static void cancelOrder(int id) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            orderDAO.cancelOrderWithId(id);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }
    /**
     * Change status of orderDetails
     * @param id If order
     * @throws LogicException if some Connection or DAO problems
     */
    public static void cancelOrderDetail(int id) throws LogicException {
        try (OrderDAO orderDAO = new OrderDAO()) {
            orderDAO.cancelOrderDetailWithId(id);
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem", e);
        }
    }
}
