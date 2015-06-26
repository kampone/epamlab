/**
 * 
 */
package com.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class AuthorDAOImpl implements AuthorDAO {
	private static final String SQL_CREATE_NEW_AUTHOR_QUERY = "INSERT INTO authors(AUTHOR_NAME) VALUES (?)";
	private static final String SQL_READ_AUTHOR_BY_ID_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM authors WHERE AUTHOR_ID = ?";
	private static final String SQL_DELETE_AUTHOR_BY_ID_QUERY = "DELETE FROM authors WHERE AUTHOR_ID = ?";
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#create(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public long create(Author entity) throws DAOException {
		long id = 0;
		if (entity != null) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;

			try {
				connection = dataSource.getConnection();

				statement = connection.prepareStatement(
						SQL_CREATE_NEW_AUTHOR_QUERY,
						new String[] { "AUTHOR_ID" });
				String name = entity.getName();
				statement.setString(1, name);
				statement.executeUpdate();

				resultSet = statement.getGeneratedKeys();
				if (resultSet != null && resultSet.next()) {
					id = resultSet.getLong(1);
				}else {
					throw new DAOException(System.lineSeparator()
							+ " Problem during getting id ");
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				closeConnection(connection, statement, resultSet);
			}
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.dao.NewsManagementDAO#read(long)
	 */
	@Override
	public Author read(long id) throws DAOException {
		Author author = new Author();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {

			connection = dataSource.getConnection();

			statement = connection
					.prepareStatement(SQL_READ_AUTHOR_BY_ID_QUERY);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long idAuthor = resultSet.getLong(1);
				String name = resultSet.getString(2);
				Timestamp expired = resultSet.getTimestamp(3);
				author.setId(idAuthor);
				author.setName(name);
				author.setExpired(expired);
			}
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement, resultSet);
		}
		return author;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#update(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void update(Author entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#delete(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void delete(Author entity) throws DAOException {
		this.delete(entity.getId());
	}

	@Override
	public void delete(Long id) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;
		try {

			connection = dataSource.getConnection();

			statement = connection
					.prepareStatement(SQL_DELETE_AUTHOR_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement);
		}
	}

}
