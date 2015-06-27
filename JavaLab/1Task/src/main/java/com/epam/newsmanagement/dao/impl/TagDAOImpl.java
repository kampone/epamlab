/**
 * 
 */
package com.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class TagDAOImpl implements TagDAO {
	
	private static final String SQL_CREATE_NEW_TAG_QUERY = "INSERT INTO tags(tag_name, tag_id) VALUES (?, tags_tag_id_seq.nextval)";
	private static final String SQL_READ_TAG_BY_ID_QUERY = "SELECT tag_id, tag_name FROM tags WHERE tag_id = ? ";
	private static final String SQL_UPDATE_TAG_BY_ID_QUERY = "UPDATE tags SET tag_name = ? WHERE tag_id = ? ";
	private static final String SQL_DELETE_TAG_BY_ID_QUERY = "DELETE FROM tags WHERE tag_id = ?";
	private static final String SQL_INSERT_NEWS_TAG_QUERY = "INSERT INTO news_tags(news_id, tag_id) VALUES (?, ?)";
	private static final String SQL_DELETE_NEWS_TAG_QUERY = "DELETE FROM news_tags WHERE news_id = ?";

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
	public long create(Tag entity) throws DAOException {
		long id = 0;
		if (entity != null) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;

			try {
				connection = dataSource.getConnection();

				statement = connection.prepareStatement(
						SQL_CREATE_NEW_TAG_QUERY, new String[] { "TAG_ID" });
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
				throw new DAOException(System.lineSeparator()
						+ " Problem during getting id ", e);

			} finally {
				closeConnection(connection, statement, resultSet);
			}
		}
		return id;
	}

	/**
	 * 
	 * @see com.epam.dao.NewsManagementDAO#read(long)
	 */
	@Override
	public Tag read(long id) throws DAOException {
		Tag tag = new Tag();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();

			statement = connection.prepareStatement(SQL_READ_TAG_BY_ID_QUERY);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				long idTag = resultSet.getLong(1);
				String name = resultSet.getString(2);

				tag.setId(idTag);
				tag.setName(name);

			}
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator()
					+ " Problem during reading tag ", e);

		} finally {
			closeConnection(connection, statement, resultSet);
		}
		return tag;
	}

	/**
	 * 
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#update(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void update(Tag entity) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();

			statement = connection.prepareStatement(SQL_UPDATE_TAG_BY_ID_QUERY);
			statement.setString(1, entity.getName());
			statement.setLong(2, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#delete(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void delete(Long id) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();

			statement = connection.prepareStatement(SQL_DELETE_TAG_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator()
					+ " Problem during preparing statement ", e);

		} finally {
			closeConnection(connection, statement);
		}
	}
	/**
	 *
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#delete(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void delete(Tag entity) throws DAOException {
		this.delete(entity.getId());
	}
	/**
	 *
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#attachTags(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void attachTags(long idNews, long idTag) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {

			connection = dataSource.getConnection();

			statement = connection
					.prepareStatement(SQL_INSERT_NEWS_TAG_QUERY);
			statement.setLong(1, idNews);
			statement.setLong(2, idTag);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement);
		}		
	}
	
	/**
	 *
	 * 
	 * @see com.epam.dao.NewsManagementDAO#detachTags(java.lang.Long)
	 */
	@Override
	public void detachTags(long idNews) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {

			connection = dataSource.getConnection();
			statement = connection
					.prepareStatement(SQL_DELETE_NEWS_TAG_QUERY);
			statement.setLong(1, idNews);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement);
		}
		
	}

}
