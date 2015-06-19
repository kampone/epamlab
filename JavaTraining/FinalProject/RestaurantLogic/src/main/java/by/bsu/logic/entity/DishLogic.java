package by.bsu.logic.entity;

import by.bsu.entity.dish.Dish;
import by.bsu.logic.exception.LogicException;
import by.bsu.restaurant.dish.DishDAO;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;


public class DishLogic {

    /**
     * @param id Id of dish
     * @return Dish with id
     * @throws LogicException if some Connection or DAO problems
     */
    public static Dish takeDishById(int id) throws LogicException {
            try(DishDAO dishDAO = new DishDAO()){
                return dishDAO.takeDishById(id);
            } catch (ConnectionException e) {
                throw new LogicException("Connection problem ", e);
            } catch (DAOException e) {
                throw new LogicException("DAO problem ", e);
            }

        }

}
