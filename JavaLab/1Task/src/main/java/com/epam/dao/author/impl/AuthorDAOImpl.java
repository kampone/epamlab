/**
 * 
 */
package com.epam.dao.author.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.epam.dao.author.AuthorDAO;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Author;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class AuthorDAOImpl implements AuthorDAO {
	private static final String CREATE_NEW_AUTHOR = "INSERT INTO authors(AUTHOR_NAME) VALUES (?)";
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
				try {
					connection = dataSource.getConnection();
				} catch (SQLException e) {
					throw new DAOException("Problem during getting Connection"
							+ e);
				}

				try {
					statement = connection.prepareStatement(CREATE_NEW_AUTHOR,
							new String[] { "AUTHOR_ID" });
					String name = entity.getName();
					statement.setString(1, name);
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
	public Author read(long id) throws DAOException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

	}

}
