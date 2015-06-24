/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.newsmanagement.entity.NewsManagementEntity;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski Interface to realize C.R.U.D. operations
 */
public interface NewsManagementDAO<T> {

	public long create(T entity) throws DAOException;

	public T read(long id) throws DAOException;

	public void update(T entity) throws DAOException;

	public void delete(T entity) throws DAOException;

	default void closeConnection(Connection connection, Statement statement)
			throws DAOException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Problem during close connection");
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DAOException("Problem during close connection");
			}
		}
	}

	default void closeConnection(Connection connection, Statement statement,
			ResultSet resultSet) throws DAOException {
		this.closeConnection(connection, statement);
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException("Problem during close ResultSet");
			}
		}
	}
}
