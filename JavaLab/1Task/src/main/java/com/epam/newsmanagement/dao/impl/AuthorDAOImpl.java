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
import javax.xml.crypto.Data;

import org.springframework.jdbc.datasource.DataSourceUtils;

import oracle.sql.TIMESTAMP;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class AuthorDAOImpl implements AuthorDAO {
	private static final String SQL_CREATE_NEW_AUTHOR_QUERY = "INSERT INTO authors a (a.author_name, a.author_id) VALUES (?, authors_author_id_seq.nextval)";
	private static final String SQL_READ_AUTHOR_BY_ID_QUERY = "SELECT a.author_id, a.author_name, a.expired FROM authors a WHERE a.author_id = ?";
	private static final String SQL_UPDATE_AUTHOR_BY_ID_QUERY = "UPDATE authors a SET a.author_name = ? WHERE a.author_id = ? ";
	private static final String SQL_DELETE_AUTHOR_BY_ID_QUERY = "UPDATE authors a Set EXPIRED = SYSDATE WHERE a.AUTHOR_ID = ?";
	private static final String SQL_INSERT_NEWS_AUTHOR_QUERY = "INSERT INTO news_authors a (a.news_id, a.author_id) VALUES (?, ?)";
	private static final String SQL_DELETE_NEWS_AUTHOR_QUERY = "DELETE FROM news_authors a WHERE a.news_id = ?";
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#create(com.epam.entity.Author
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
				
				connection = DataSourceUtils.doGetConnection(dataSource);

				statement = connection.prepareStatement(
						SQL_CREATE_NEW_AUTHOR_QUERY,
						new String[] { "AUTHOR_ID" });
				String name = entity.getName();
				statement.setString(1, name);
				statement.executeUpdate();

				resultSet = statement.getGeneratedKeys();
				if (resultSet != null && resultSet.next()) {
					id = resultSet.getLong(1);
				} else {
					throw new DAOException(System.lineSeparator()
							+ " Problem during getting id ");
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				closeConnection(dataSource, connection, statement, resultSet);
			}
		}
		return id;
	}

	/**
	 * 
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

			connection = DataSourceUtils.doGetConnection(dataSource);

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
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return author;
	}

	/**
	 * 
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#update(com.epam.entity.Author
	 * )
	 */
	@Override
	public void update(Author entity) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DataSourceUtils.doGetConnection(dataSource);

			statement = connection
					.prepareStatement(SQL_UPDATE_AUTHOR_BY_ID_QUERY);
			statement.setString(1, entity.getName());
			statement.setLong(2, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}

	}

	/**
	 *
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#delete(com.epam.entity.Author
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

			connection = DataSourceUtils.doGetConnection(dataSource);

			statement = connection
					.prepareStatement(SQL_DELETE_AUTHOR_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}
	/**
	 *
	 * 
	 * @see
	 * com.epam.dao.AuthorDAO#attachAuthors(long,long)
	 */
	@Override
	public void attachAuthors(long idNews, long idAuthor) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {

			connection = DataSourceUtils.doGetConnection(dataSource);

			statement = connection
					.prepareStatement(SQL_INSERT_NEWS_AUTHOR_QUERY);
			statement.setLong(1, idNews);
			statement.setLong(2, idAuthor);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}
	/**
	 *
	 * 
	 * @see
	 * com.epam.dao.AuthorDAO#detachAuthor(long)
	 */
	@Override
	public void detachAuthor(long idNews) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {

			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection
					.prepareStatement(SQL_DELETE_NEWS_AUTHOR_QUERY);
			statement.setLong(1, idNews);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

}
