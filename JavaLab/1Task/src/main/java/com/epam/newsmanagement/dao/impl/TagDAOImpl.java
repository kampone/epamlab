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

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class TagDAOImpl implements TagDAO {
	private static final String SQL_CREATE_NEW_TAG_QUERY = "INSERT INTO tags(TAG_NAME, TAG_ID) VALUES (?, TAGS_TAG_ID_SEQ.nextval)";
	private static final String SQL_READ_TAG_BY_ID_QUERY = "SELECT TAG_ID, TAG_NAME FROM tags WHERE TAG_ID = ? ";
	private static final String SQL_DELETE_TAG_BY_ID_QUERY = "DELETE FROM tags WHERE TAG_ID = ?";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.dao.NewsManagementDAO#update(com.epam.entity.NewsManagementEntity
	 * )
	 */
	@Override
	public void update(Tag entity) throws DAOException {
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

	@Override
	public void delete(Tag entity) throws DAOException {
		this.delete(entity.getId());
	}

}
