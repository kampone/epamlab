package by.bsu.connectionpool.connectionfactory;

import by.bsu.connectionpool.exception.ConnectionPoolException;
import by.bsu.connectionpool.proxyconnection.ProxyConnection;
import com.mysql.jdbc.Driver;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ProxyConnectionFactory need to create ProxyConnection
 */
public class ProxyConnectionFactory {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("Database driver is not registered");
        }
    }
    private static final Logger LOG = Logger.getLogger(ProxyConnectionFactory.class);
    private static final String DB_PROPERTIES = "property/database.properties";

    /**
     * @return ProxyConnection
     * @throws ConnectionPoolException
     * @see ProxyConnection
     */
    public static ProxyConnection getInstance() throws ConnectionPoolException {
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(new FileReader(DB_PROPERTIES));
            connection = DriverManager.getConnection(properties.getProperty("db_url"),properties);
        } catch (IOException |SQLException e) {
            throw new ConnectionPoolException(e);
        }
        return new ProxyConnection(connection);
    }
}
