package by.bsu.restaurant.abstractdao;

import by.bsu.connectionpool.exception.ConnectionPoolException;
import by.bsu.connectionpool.pool.ConnectionPool;
import by.bsu.connectionpool.proxyconnection.ProxyConnection;
import by.bsu.restaurant.exception.ConnectionException;
import by.bsu.restaurant.exception.DAOException;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *   Abstract class (AutoCloseable)
 *   Each class in DAO Layer extends it
 *   @see ProxyConnection
 */
public abstract class AbstractDAO implements AutoCloseable{
    protected ProxyConnection connection;

    public AbstractDAO() throws ConnectionException {
        try {
            connection = ConnectionPool.getInstance().takeConnection();
        } catch (ConnectionPoolException e) {
            throw new ConnectionException(e);
        }
    }

    /**
     * return ProxyConnection to ConnectionPool
     */
    public void close(){
        ConnectionPool.getInstance().offerConnection(this.connection);
    }

}
