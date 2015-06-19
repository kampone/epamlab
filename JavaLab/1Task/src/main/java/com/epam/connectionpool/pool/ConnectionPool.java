package com.epam.connectionpool.pool;

import org.apache.log4j.Logger;

import com.epam.connectionpool.connectionfactory.ConnectionFactory;
import com.epam.connectionpool.exception.ConnectionPoolException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ConnectionPool is safe connection pool
 * @see Connection
 *
 */
public class ConnectionPool {


    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final String DB_PROPERTIES = "src/main/resources/property/database.properties";
    private ArrayBlockingQueue<Connection> connectionQueue;

    private ConnectionPool() throws ConnectionPoolException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(DB_PROPERTIES));
            int poolSize = Integer.valueOf(properties.getProperty("pool_size"));
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = ConnectionFactory.getInstance();
                connectionQueue.offer(connection);
            }
            LOG.info("Connection Pool is created");
        } catch (IOException e) {
            throw new ConnectionPoolException("Database properties file problem");
        }
    }

    /**
     * close all Connection in ConnectionPool
     * @throws ConnectionPoolException
     */
    public void closeConnections() throws ConnectionPoolException {
        for (Connection connection : connectionQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
            	LOG.error("Close connection trouble");
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
                throw new ExceptionInInitializerError(e + " Connection pool is not created");
            }
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }


    /**
     * @return Connection
     * @throws ConnectionPoolException
     * 
     */
    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
        	
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
        return connection;
    }


    /**
     * return Connection to Connection Pool
     * @param Connection
     * @return true if Connection is offer to ConnectionPool
     */
    public boolean offerConnection(Connection Connection) {
        return connectionQueue.offer(Connection);
    }
}
