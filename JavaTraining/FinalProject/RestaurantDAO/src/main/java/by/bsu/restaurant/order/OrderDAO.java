package by.bsu.restaurant.order;

import by.bsu.entity.dish.Dish;
import by.bsu.entity.order.Order;
import by.bsu.entity.order.OrderDetail;
import by.bsu.entity.order.StatusOrderDetail;
import by.bsu.restaurant.abstractdao.AbstractDAO;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO extends AbstractDAO {
    private static final Logger LOG = Logger.getLogger(OrderDAO.class);
    private static final String CREATE_NEW_ORDER = "INSERT INTO orders(time, users_login) VALUES (?,?)";
    private static final String CREATE_NEW_ORDERDETAIL = "INSERT INTO orderdetails(idOrder, idDish, number ,idStatus) VALUES (?,?,?,?)";
    private static final String TAKE_WAITER_ORDERS = "SELECT orders.idOrder, orders.time, orderdetails.idDetail, dishes.idDish, dishes.nameDish, dishes.description, dishes.price, orderdetails.number, statusorderdetails.status  \n" +
            "FROM orders \n" +
            "  JOIN orderdetails ON orders.idOrder = orderdetails.idOrder \n" +
            "  JOIN dishes ON dishes.idDish = orderdetails.idDish \n" +
            "  JOIN statusorderdetails ON statusorderdetails.idStatus = orderdetails.idStatus \n" +
            "WHERE users_login=? AND (orderdetails.idStatus = 1 OR orderdetails.idStatus = 2 OR orderdetails.idStatus = 3) ORDER BY orders.idOrder";
    private static final String TAKE_ORDERS_WITH_STATUS = "SELECT orders.idOrder, orders.time, orderdetails.idDetail, dishes.idDish, dishes.nameDish, dishes.description, dishes.price, orderdetails.number, statusorderdetails.status  \n" +
            "FROM orders " +
            "JOIN orderdetails ON orders.idOrder = orderdetails.idOrder\n" +
            "JOIN dishes ON dishes.idDish = orderdetails.idDish\n" +
            "JOIN statusorderdetails ON statusorderdetails.idStatus = orderdetails.idStatus\n" +
            "WHERE statusorderdetails.idStatus=? ORDER BY orders.idOrder";
    private static final String FIND_ORDER_WITH_ID = "SELECT orders.idOrder, orders.time, orderdetails.idDetail, dishes.idDish, dishes.nameDish, dishes.description, dishes.price, orderdetails.number, statusorderdetails.status  \n" +
            "FROM orders " +
            "JOIN orderdetails ON orders.idOrder = orderdetails.idOrder\n" +
            "JOIN dishes ON dishes.idDish = orderdetails.idDish\n" +
            "JOIN statusorderdetails ON statusorderdetails.idStatus = orderdetails.idStatus\n" +
            "WHERE orders.idOrder=? AND (orderdetails.idStatus = 1 OR orderdetails.idStatus = 2 OR orderdetails.idStatus = 3)";
    private static final String CONFIRM_ORDER = "UPDATE orderdetails SET idStatus=2 WHERE idStatus=1 AND idOrder=?";
    private static final String PAY_ORDER = "UPDATE orderdetails SET idStatus=4 WHERE idOrder=? AND (orderdetails.idStatus = 1 OR orderdetails.idStatus = 2 OR orderdetails.idStatus = 3)";
    private static final String COOK_DISH = "UPDATE orderdetails SET idStatus=3 WHERE idDetail=?";
    private static final String FIND_LAST_ID = "SELECT LAST_INSERT_ID() FROM orders";//only for MySQL
    private static final String CANCEL_ORDER = "UPDATE orderdetails SET idStatus=5 WHERE idOrder=?";//only for MySQL
    private static final String CANCEL_DISH = "UPDATE orderdetails SET idStatus=5 WHERE idDetail=?";//only for MySQL

    public OrderDAO() throws ConnectionException {
    }


    /**
     * @param status orders of this status will be returned
     * @return List of Orders with status
     * @throws DAOException when SQLException in DAOLayer
     */
    public List<Order> takeOrdersWithStatus(StatusOrderDetail status) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(TAKE_ORDERS_WITH_STATUS)) {
            statement.setInt(1, status.ordinal() + 1);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            Order order = null;

            while (resultSet.next()) {
                if (id != resultSet.getInt(1)) {
                    if (order != null) {
                        orders.add(order);
                    }
                    id = resultSet.getInt(1);
                    LOG.debug(id);
                    order = new Order();
                    order.setId(id);
                }
                if (id == resultSet.getInt(1)) {
                    OrderDetail detail = new OrderDetail();
                    detail.setId(resultSet.getInt(3));
                    Dish dish = new Dish();
                    dish.setId(resultSet.getInt(4));
                    dish.setName(resultSet.getString(5));
                    dish.setDescription(resultSet.getString(6));
                    dish.setPrice(resultSet.getDouble(7));
                    detail.setDish(dish);
                    detail.setNumber(resultSet.getInt(8));
                    detail.setStatus(StatusOrderDetail.valueOf(resultSet.getString(9).toUpperCase()));
                    order.addOrderDetail(detail);
                }
            }
            if (order != null) {
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
        return orders;
    }

    /**
     * @param login Orders of this waiter will returned
     * @return List of Orders
     * @throws DAOException when SQLException in DAOLayer
     */
    public List<Order> takeWaiterOrders(String login) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(TAKE_WAITER_ORDERS)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            Order order = null;
            while (resultSet.next()) {
                if (id != resultSet.getInt(1)) {
                    if (order != null) {
                        orders.add(order);
                    }
                    id = resultSet.getInt(1);
                    LOG.debug(id);
                    order = new Order();
                    order.setId(id);
                }
                if (id == resultSet.getInt(1)) {
                    OrderDetail detail = new OrderDetail();
                    detail.setId(resultSet.getInt(3));
                    Dish dish = new Dish();
                    dish.setId(resultSet.getInt(4));
                    dish.setName(resultSet.getString(5));
                    dish.setDescription(resultSet.getString(6));
                    dish.setPrice(resultSet.getDouble(7));
                    detail.setDish(dish);
                    detail.setNumber(resultSet.getInt(8));
                    detail.setStatus(StatusOrderDetail.valueOf(resultSet.getString(9).toUpperCase()));
                    order.addOrderDetail(detail);
                }
            }
            if (order != null) {
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
        return orders;
    }

    /**
     * @param order will write to database
     * @param login with login
     * @throws DAOException when SQLException in DAOLayer
     */
    public void wirteOrder(Order order, String login) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            Date date = new Date(order.getTime().getTimeInMillis());
            Time time = new Time(order.getTime().getTime().getTime());
            statement.setString(1, date.toString() + " " + time.toString());
            statement.setString(2, login);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
    }

    /**
     * @param idOrder foreign key in database
     * @param detail orderDetail that should be written
     * @param status status of orderDetails
     * @throws DAOException when SQLException in DAOLayer
     */
    public void wirteOrderDetail(int idOrder , OrderDetail detail, StatusOrderDetail status) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_NEW_ORDERDETAIL)) {
                statement.setInt(1, idOrder);
                statement.setInt(2, detail.getDish().getId());
                statement.setInt(3, detail.getNumber());
                statement.setInt(4, status.ordinal() + 1);
                statement.execute();
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
    }


    /**
     * Change status of Order
     * @param id order
     * @throws DAOException when SQLException in DAOLayer
     */
    public void confirmOrderWithId(Integer id) throws DAOException {
        execute(id, CONFIRM_ORDER);
    }
    /**
     * Change status of Order
     * @param id order
     * @throws DAOException when SQLException in DAOLayer
     */
    public void cookDishWithId(Integer id) throws DAOException {
        execute(id, COOK_DISH);
    }

    /**
     * @return int id last in database table orders
     * @throws DAOException when SQLException in DAOLayer
     */
    public int takeLastOrderId() throws DAOException {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(FIND_LAST_ID)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
        return id;
    }

    /**
     * @param id Id of order
     * @return Order with id
     * @throws DAOException when SQLException in DAOLayer
     */
    public Order takeOrdersWithId(int id) throws DAOException {
        Order order = new Order();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDER_WITH_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                Dish dish = new Dish(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDouble(7));
                OrderDetail detail = new OrderDetail();
                detail.setId(resultSet.getInt(3));
                detail.setNumber(resultSet.getInt(8));
                detail.setStatus(StatusOrderDetail.valueOf(resultSet.getString(9).toUpperCase()));
                detail.setDish(dish);
                order.addOrderDetail(detail);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
        return order;
    }

    /**
     * Change status of Order
     * @param id order
     * @throws DAOException when SQLException in DAOLayer
     */
    public void payedOrderWithId(int id) throws DAOException {
        execute(id, PAY_ORDER);
    }
    /**
     * Change status of Order
     * @param id order
     * @throws DAOException when SQLException in DAOLayer
     */
    public void cancelOrderWithId(int id) throws DAOException {
        execute(id, CANCEL_ORDER);
    }
    /**
     * Change status of Order
     * @param id order
     * @throws DAOException when SQLException in DAOLayer
     */
    public void cancelOrderDetailWithId(int id) throws DAOException {
        execute(id,CANCEL_DISH);
    }

    /**
     * @param id id order that will be changed
     * @param state statement will execute
     * @throws DAOException when SQLException in DAOLayer
     */
    public void execute(int id, String state) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(state)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
    }
}

