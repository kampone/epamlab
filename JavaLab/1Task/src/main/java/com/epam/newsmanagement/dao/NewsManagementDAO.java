/**
 * 
 */
package com.epam.newsmanagement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.dbunit.dataset.DataSetUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski Interface to realize C.R.U.D. operations
 */
public interface NewsManagementDAO<T> {
	/**
	 * Create entity in database
	 * 
	 * @param entity
	 *            entity that should be created
	 * @return id of entity that is created in database
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	public long create(T entity) throws DAOException;

	/**
	 * Read entity from database
	 * 
	 * @param id
	 *            of entity that should be read
	 * @return Entity from database by id;
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	public T read(long id) throws DAOException;

	/**
	 * Update entity in database
	 * 
	 * @param entity
	 *            Entity that should be updated
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	public void update(T entity) throws DAOException;

	/**
	 * Delete entity from database
	 * 
	 * @param entity
	 *            Entity that should be deleted
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	public void delete(T entity) throws DAOException;

	/**
	 * Delete entity from database
	 * 
	 * @param id
	 *            of Entity that should be deleted
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	public void delete(Long id) throws DAOException;

	/**
	 * Default method that close connection, statement
	 * 
	 * @param connection
	 *            that should be closed
	 * @param statement
	 *            that should be closed
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	default void closeConnection(DataSource dataSource, Connection connection, Statement statement)
			throws DAOException {

		DataSourceUtils.releaseConnection(connection, dataSource);
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DAOException("Problem during close connection");
			}
		}

	}

	/**
	 * Default method that close connection, statement
	 * 
	 * @param connection
	 *            that should be closed
	 * @param statement
	 *            that should be closed
	 * @param resultSet
	 *            that should be closed
	 * @throws DAOException
	 *             if trouble with connection with database
	 */
	default void closeConnection(DataSource dataSource, Connection connection, Statement statement, ResultSet resultSet)
			throws DAOException {
		
		this.closeConnection(dataSource, connection, statement);
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException("Problem during close ResultSet");
			}
		}
	}
}
