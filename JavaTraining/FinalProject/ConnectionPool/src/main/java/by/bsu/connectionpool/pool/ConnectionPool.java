package by.bsu.connectionpool.pool;

import by.bsu.connectionpool.connectionfactory.ProxyConnectionFactory;
import by.bsu.connectionpool.exception.ConnectionPoolException;
import by.bsu.connectionpool.proxyconnection.ProxyConnection;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ConnectionPool is safe connection pool
 * @see ProxyConnection
 *
 */
public class ConnectionPool {
/*
 * Its unneccery comment
 */

    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final String DB_PROPERTIES = "property/database.properties";
    private ArrayBlockingQueue<ProxyConnection> connectionQueue;

    private ConnectionPool() throws ConnectionPoolException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(DB_PROPERTIES));
            int poolSize = Integer.valueOf(properties.getProperty("pool_size"));
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection = ProxyConnectionFactory.getInstance();
                connectionQueue.offer(connection);
            }
            LOG.info("Connection Pool is created");
        } catch (IOException e) {
            throw new ConnectionPoolException("Database properties file problem");
        }
    }

    /**
     * close all ProxyConnection in ConnectionPool
     * @throws ConnectionPoolException
     */
    public void closeConnections() throws ConnectionPoolException {
        for (ProxyConnection connection : connectionQueue) {
            try {
                connection.close();

            } catch (SQLException e) {
                throw new ConnectionPoolException();
            }
        }
        LOG.info("Connection pool is closed");
    }

    private static class ConnectionPoolHolder {
        private final static ConnectionPool INSTANCE;

        static {
            try {
                INSTANCE = new ConnectionPool();
            } catch (ConnectionPoolException e) {
                LOG.error(e + "Connection pool is not created");
                throw new ExceptionInInitializerError(e + "Connection pool is not created");
            }
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }


    /**
     * @return ProxyConnection
     * @throws ConnectionPoolException
     * @see ProxyConnection
     */
    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
        return connection;
    }


    /**
     * return ProxyConnection to Connection Pool
     * @param proxyConnection is ProxyConnection
     * @return true if ProxyConnection is offer to ConnectionPool
     */
    public boolean offerConnection(ProxyConnection proxyConnection) {
        return connectionQueue.offer(proxyConnection);
    }
}
