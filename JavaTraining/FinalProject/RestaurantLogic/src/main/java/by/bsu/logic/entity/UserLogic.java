package by.bsu.logic.entity;

import by.bsu.entity.order.Order;
import by.bsu.entity.user.User;
import by.bsu.entity.user.UserRole;
import by.bsu.logic.entity.OrderLogic;
import by.bsu.logic.exception.LogicException;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;
import by.bsu.restaurant.user.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * Created by note on 19.04.2015.
 */
public class UserLogic {
    /**
     * @return List of Waiters and Cooks
     * @throws LogicException if some Connection or DAO problems
     */
    public static List<User> takeAllUsers() throws LogicException {
        try(UserDAO userDAO = new UserDAO()){
            return userDAO.takeWaitersAndCooks();
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem ", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem ", e);
        }

    }

    /**
     * @param login User Login
     * @return true if User deleted
     * @throws LogicException if some Connection or DAO problems
     */
    public static boolean deleteUserWithLogin(String login) throws LogicException {
        User user = takeUserByLogin(login);
        boolean flag = false;
        if (user.getRole() == UserRole.WAITER) {
            List<Order> orders = OrderLogic.takeWaiterOrders(login);
            if(!orders.isEmpty()){
                return flag;
            }
        }
        try (UserDAO userDAO = new UserDAO()) {
            userDAO.deleteUserWithLogin(login);
            flag = true;
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem ", e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem ", e);
        }
        return flag;
    }

    /**
     * @param login  User login
     * @param password User password
     * @param role User role
     * @return true if new User created
     * @throws LogicException if some Connection or DAO problems
     */
    public static boolean createNewUser(String login, String password, int role) throws LogicException {
        boolean flag = false;
        try (UserDAO userDAO = new UserDAO()) {
            if (userDAO.findUserByLogin(login) == null) {
                userDAO.createNewUser(login, DigestUtils.md5Hex(password), role);
                flag= true;
            }
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem " + e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem " + e);

        }
        return flag;
    }

    /**
     * @param login User Login
     * @return User with login
     * @throws LogicException if some Connection or DAO problems
     */
    public static User takeUserByLogin(String login) throws LogicException {
        User user = null;
        try (UserDAO userDAO = new UserDAO()){
            user = userDAO.findUserByLogin(login);

        } catch (ConnectionException e){
            throw new LogicException("Connection problem " + e);
        } catch ( DAOException e) {
            throw new LogicException("DAO problem" + e);
        }

        return user;
    }

}
