package by.bsu.restaurant.menu;

import by.bsu.restaurant.abstractdao.AbstractDAO;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;
import by.bsu.entity.dish.Dish;
import by.bsu.entity.menu.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by note on 27.03.2015.
 */
public class MenuDAO extends AbstractDAO {
    private static final String ALL_DISHES = "SELECT * FROM dishes";

    public MenuDAO() throws ConnectionException {
    }

    /**
     * @see Menu
     * @return Menu of Dish
     * @throws DAOException when SQLException in DAOLayer
     */
    public Menu takeMenu() throws DAOException {
        Menu menu = new Menu();
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery(ALL_DISHES);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    double price = resultSet.getDouble(4);
                    menu.add(new Dish(id, name, description, price));
                }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return menu;

    }
}