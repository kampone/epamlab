package by.bsu.restaurant.dish;

import by.bsu.entity.dish.Dish;
import by.bsu.restaurant.abstractdao.AbstractDAO;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by note on 26.03.2015.
 */
public class DishDAO extends AbstractDAO {
    private static final String DISH_WITH_ID = "SELECT * FROM dishes WHERE idDish=?";
    public DishDAO() throws ConnectionException {
    }

    /**
     * @param id int id
     * @return Dish with id
     * @throws DAOException when SQLException in DAOLayer
     */
    public Dish takeDishById(int id) throws DAOException {
        Dish dish = null;
        try (PreparedStatement statement = connection.prepareStatement(DISH_WITH_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_dish = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                dish = new Dish(id_dish, name, description, price);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL problem " + e);
        }
        return dish;
    }
}
