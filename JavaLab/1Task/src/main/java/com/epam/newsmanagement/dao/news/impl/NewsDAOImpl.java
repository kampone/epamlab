/**
 * 
 */
package com.epam.newsmanagement.dao.news.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.dao.news.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Tag;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class NewsDAOImpl implements NewsDAO {
	private static final String CREATE_NEW_NEWS = "INSERT INTO news(TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES (?,?,?,?,?)";
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
				try {
					connection = dataSource.getConnection();
				} catch (SQLException e) {
					throw new DAOException("Problem during getting Connection"
							+ e);
				}

				try {
					statement = connection.prepareStatement(CREATE_NEW_NEWS,
							new String[] { "NEWS_ID" });
					String title = entity.getTitle();
					String shortText = entity.getShortText();
					String fullText = entity.getFullText();
					Timestamp creationDate = entity.getCreationDate();
					String modificationDate = entity.getModificationDate();
					statement.setString(1, title);
					statement.setString(2, shortText);
					statement.setString(3, fullText);
					statement.setTimestamp(4, creationDate);
					statement.setString(5, modificationDate);
					statement.executeUpdate();
				} catch (SQLException e) {
					throw new DAOException("Problem during preparing statement"
							+ e);
				}

				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet != null && resultSet.next()) {
						id = resultSet.getLong(1);
					} else {
						throw new DAOException("Problem during getting id");
					}
				} catch (SQLException e) {
					throw new DAOException("Problem during getting id" + e);
				}
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
	public News read(long id) throws DAOException {
		return null;
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
	public void delete(News entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.dao.news.NewsDAO#addNewsTags(long, long)
	 */
	@Override
	public void addNewsTags(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.dao.news.NewsDAO#addNewsAuthors(long, long)
	 */
	@Override
	public void addNewsAuthors(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.dao.news.NewsDAO#deleteNewsTags(long, long)
	 */
	@Override
	public void deleteNewsTags(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.dao.news.NewsDAO#deleteNewsAuthors(long, long)
	 */
	@Override
	public void deleteNewsAuthors(long idNews, long idTag) throws DAOException {
		// TODO Auto-generated method stub

	}

}
