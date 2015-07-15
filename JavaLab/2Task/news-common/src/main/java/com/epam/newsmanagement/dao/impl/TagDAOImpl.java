/**
 * 
 */
package com.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class TagDAOImpl implements TagDAO {

	private static final String SQL_CREATE_NEW_TAG_QUERY = "INSERT INTO tags t (t.tag_name, t.tag_id) VALUES (?, tags_tag_id_seq.nextval)";
	private static final String SQL_READ_TAG_BY_ID_QUERY = "SELECT t.tag_id, t.tag_name FROM tags t WHERE t.tag_id = ? ";
	private static final String SQL_UPDATE_TAG_BY_ID_QUERY = "UPDATE tags t SET t.tag_name = ? WHERE t.tag_id = ? ";
	private static final String SQL_DELETE_TAG_BY_ID_QUERY = "DELETE FROM tags t WHERE t.tag_id = ?";
	private static final String SQL_INSERT_NEWS_TAG_QUERY = "INSERT INTO news_tags nt (nt.news_id, nt.tag_id) VALUES (?, ?)";
	private static final String SQL_DELETE_NEWS_TAG_QUERY = "DELETE FROM news_tags nt WHERE nt.news_id = ?";
	private static final String SQL_READ_TAG_BY_NEWS_ID_QUERY = "SELECT t.tag_id, t.tag_name FROM tags t JOIN news_tags nt ON t.TAG_ID = nt.TAG_ID JOIN news n ON nt.NEWS_ID = n.NEWS_ID WHERE n.NEWS_ID=?";

	private DataSource dataSource;


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Tag entity) throws DAOException {
		Long id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_CREATE_NEW_TAG_QUERY, new String[] { "TAG_ID" });
			String name = entity.getName();
			statement.setString(1, name);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet != null && resultSet.next()) {
				id = resultSet.getLong(1);
			} else {
				throw new DAOException(System.lineSeparator() + " Problem during getting id ");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return id;
	}

	
	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
	 */
	@Override
	public Tag read(Long id) throws DAOException {
		Tag tag = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_READ_TAG_BY_ID_QUERY);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				tag = new Tag();
				Long tagId = resultSet.getLong(1);
				String name = resultSet.getString(2);
				tag.setId(tagId);
				tag.setName(name);

			}
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return tag;
	}

	
	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Tag entity) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_UPDATE_TAG_BY_ID_QUERY);
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
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_DELETE_TAG_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during preparing statement ", e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}


	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Tag entity) throws DAOException {
		this.delete(entity.getId());
	}


	/**
	 * @see com.epam.newsmanagement.dao.TagDAO#attachTagsToNews(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void attachTagsToNews(Long newsId, Long tagId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_INSERT_NEWS_TAG_QUERY);
			statement.setLong(1, newsId);
			statement.setLong(2, tagId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	
	/**
	 * @see com.epam.newsmanagement.dao.TagDAO#detachTagsFromNews(java.lang.Long)
	 */
	@Override
	public void detachTagsFromNews(Long newsId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_DELETE_NEWS_TAG_QUERY);
			statement.setLong(1, newsId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}

	}

	/**
	 * @see com.epam.newsmanagement.dao.TagDAO#attachListTagsToNews(java.lang.Long, java.util.List)
	 */
	@Override
	public void attachListTagsToNews(Long newsId, List<Long> tagIdList) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_INSERT_NEWS_TAG_QUERY);
			statement.setLong(1, newsId);
			for (Long tagId : tagIdList) {
				statement.setLong(2, tagId);
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}

	}

	/**
	 * @see com.epam.newsmanagement.dao.TagDAO#getNewsTags(java.lang.Long)
	 */
	@Override
	public List<Tag> getNewsTags(Long newsId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Tag> tagList = null;
		Tag tag = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_READ_TAG_BY_NEWS_ID_QUERY);
			statement.setLong(1, newsId);
			resultSet = statement.executeQuery();
			tagList = new ArrayList<>();
			while (resultSet.next()) {
				tag = new Tag();
				Long tagId = resultSet.getLong(1);
				String name = resultSet.getString(2);
				tag.setId(tagId);
				tag.setName(name);
				tagList.add(tag);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return tagList;
	}
}
