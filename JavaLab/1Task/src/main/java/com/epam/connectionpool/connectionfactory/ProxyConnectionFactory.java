package com.epam.connectionpool.connectionfactory;

import com.epam.connectionpool.exception.ConnectionPoolException;
import com.epam.connectionpool.proxyconnection.ProxyConnection;

import oracle.jdbc.driver.OracleDriver;

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
				DriverManager.registerDriver(new OracleDriver());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

	}
	private static final Logger LOG = Logger.getLogger(ProxyConnectionFactory.class);
	private static final String DB_PROPERTIES = "src/main/resources/property/database.properties";

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
			connection = DriverManager.getConnection(
					properties.getProperty("db_url"), "NewsManagement", "root");
		} catch (IOException | SQLException e) {
			throw new ConnectionPoolException(e);
		}
		return new ProxyConnection(connection);
	}
}
