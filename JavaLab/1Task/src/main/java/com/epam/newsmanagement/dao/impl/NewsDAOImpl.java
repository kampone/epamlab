/**
 * 
 */
package com.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class NewsDAOImpl implements NewsDAO {
	private static final String SQL_CREATE_NEW_NEWS_QUERY = "INSERT INTO news(title, short_text, full_text, creation_date, modification_date, news_id) VALUES (?,?,?,?,?,news_news_id_seq.nextval)";
	private static final String SQL_READ_NEWS_BY_ID_READ = "SELECT news_id, title, short_text, full_text, creation_date, modification_date FROM news WHERE news_id = ?";
	private static final String SQL_UPDATE_NEWS_BY_ID_QUERY = "UPDATE news SET title = ?, short_text = ?, full_text = ?, creation_date = ? , modification_date = ? WHERE news_id = ? ";
	private static final String SQL_DELETE_NEWS_BY_ID_READ = "DELETE FROM news WHERE news_id = ?";
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return return tag id if it is created
	 * @see com.epam.dao.NewsManagementDAO#create(com.epam.entity.NewsManagementEntity)
	 */
	@Override
	public long create(News entity) throws DAOException {
		long id = 0;
		if (entity != null) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;

			try {
				connection = DataSourceUtils.doGetConnection(dataSource);

				statement = connection.prepareStatement(
						SQL_CREATE_NEW_NEWS_QUERY, new String[] { "NEWS_ID" });
				String title = entity.getTitle();
				String shortText = entity.getShortText();
				String fullText = entity.getFullText();
				Timestamp creationDate = entity.getCreationDate();
				Date modificationDate = entity.getModificationDate();
				statement.setString(1, title);
				statement.setString(2, shortText);
				statement.setString(3, fullText);
				statement.setTimestamp(4, creationDate);
				statement.setDate(5, modificationDate);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.dao.NewsManagementDAO#read(long)
	 */
	@Override
	public News read(long id) throws DAOException {
		News news = new News();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DataSourceUtils.doGetConnection(dataSource);

			statement = connection.prepareStatement(SQL_READ_NEWS_BY_ID_READ);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				long idNews = resultSet.getLong(1);
				String title = resultSet.getString(2);
				String shortText = resultSet.getString(3);
				String fullText = resultSet.getString(4);
				Timestamp creationDate = resultSet.getTimestamp(5);
				Date modificationDate = resultSet.getDate(6);

				news.setId(idNews);
				news.setTitle(title);
				news.setShortText(shortText);
				news.setFullText(fullText);
				news.setCreationDate(creationDate);
				news.setModificationDate(modificationDate);

			}
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator()
					+ " Problem during reading comment ", e);

		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return news;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#update(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void update(News entity) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_UPDATE_NEWS_BY_ID_QUERY);
			statement.setString(1, entity.getTitle());
			statement.setString(2, entity.getShortText());
			statement.setString(3, entity.getFullText());
			statement.setTimestamp(4, entity.getCreationDate());
			statement.setDate(5, entity.getModificationDate());
			statement.setLong(6, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#delete(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void delete(News entity) throws DAOException {
		this.delete(entity.getId());
	}

	@Override
	public void delete(Long id) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DataSourceUtils.doGetConnection(dataSource);

			statement = connection.prepareStatement(SQL_DELETE_NEWS_BY_ID_READ);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator()
					+ " Problem during preparing statement ", e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

}
