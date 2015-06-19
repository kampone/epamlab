package by.bsu.logic.entity;

import by.bsu.logic.exception.LogicException;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;
import by.bsu.restaurant.menu.MenuDAO;
import by.bsu.entity.menu.Menu;


public class MenuLogic {

    /**
     * @see Menu
     * @return Menu
     * @throws LogicException if some Connection or DAO problems
     */
    public static Menu takeMenuFromDB() throws LogicException {
        Menu menu = null;
        try (MenuDAO menuDAO = new MenuDAO()){
            menu = menuDAO.takeMenu();
        } catch (ConnectionException e) {
            throw new LogicException("Connection problem "+ e);
        } catch (DAOException e) {
            throw new LogicException("DAO problem "+ e);
        }
        return menu;
    }

}
