package by.bsu.restaurant.user;

import by.bsu.restaurant.abstractdao.AbstractDAO;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;
import by.bsu.entity.user.User;
import by.bsu.entity.user.UserRole;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {
    private static final String FIND_USER_BY_LOGIN = "SELECT users.login,users.password,userroles.role FROM users JOIN userroles ON userroles.idRole = users.userroles_idRole WHERE login = ? ";
    private static final String CREATE_NEW_WAITER = "INSERT INTO users( login, password, userroles_idRole) VALUES (?,?,?)";
    private static final String FIND_ALL_WAITERS_AND_COOKS = "SELECT users.login,users.password,userroles.role FROM users JOIN userroles ON userroles.idRole = users.userroles_idRole WHERE userroles.idRole > 1 ";
    private static final String DELETE_USER_WITH_LOGIN = "DELETE FROM users WHERE login = ? ";

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    public UserDAO() throws ConnectionException {
    }


    /**
     * @see User
     * @param enterLogin of User
     * @return User with login
     * @throws DAOException when SQLException in DAOLayer
     */
    public User findUserByLogin(String enterLogin) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, enterLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString(1);
                String password = resultSet.getString(2);
                UserRole role = UserRole.valueOf(resultSet.getString(3).toUpperCase());
                user = new User(login, password, role);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return user;
    }

    /**
     * Create new User
     * @see User
     * @param login of User
     * @param password of User
     * @param role of User
     * @throws DAOException when SQLException in DAOLayer
     */
    public void createNewUser(String login, String password, int role) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_NEW_WAITER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, role);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
    }

    /**
     * @return List of Users with UserRole.WAITER and UserRole.COOK
     */
    public List<User> takeWaitersAndCooks() throws DAOException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_WAITERS_AND_COOKS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString(1);
                String password = resultSet.getString(2);
                UserRole role = UserRole.valueOf(resultSet.getString(3).toUpperCase());
                users.add(new User(login, password, role));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return users;
    }

    /**
     * Delete user from database
     * @param login of User
     * @throws DAOException when SQLException in DAOLayer
     */
    public void deleteUserWithLogin(String login) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_WITH_LOGIN)) {
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }

    }


}
