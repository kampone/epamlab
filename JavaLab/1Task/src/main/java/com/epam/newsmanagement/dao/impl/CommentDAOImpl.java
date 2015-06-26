/**
 * 
 */
package com.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class CommentDAOImpl implements CommentDAO {
	private static final String SQL_CREATE_NEW_COMMENT_QUERY = "INSERT INTO comments(CREATION_DATE, COMMENT_TEXT, NEWS_ID, COMMENT_ID) VALUES (?, ?, ?, COMMENTS_COMMENT_ID_SEQ.nextval)";
	private static final String SQL_READ_COMMENT_BY_ID_QUERY = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE FROM comments WHERE COMMENT_ID = ? ";
	private static final String SQL_DELETE_COMMENT_BY_ID_QUERY = "DELETE FROM comments WHERE COMMENT_ID = ?";

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
	public long create(Comment entity) throws DAOException {
		long id = 0;
		if (entity != null) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;

			try {

				connection = dataSource.getConnection();

				statement = connection.prepareStatement(
						SQL_CREATE_NEW_COMMENT_QUERY,
						new String[] { "COMMENT_ID" });

				Timestamp creationDate = entity.getCreationDate();
				String text = entity.getText();
				long idNews = entity.getIdNews();
				statement.setTimestamp(1, creationDate);
				statement.setString(2, text);
				statement.setLong(3, idNews);
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
				closeConnection(connection, statement, resultSet);
			}
		}
		return id;
	}

	/**
	 * 
	 * 
	*/
	@Override
	public Comment read(long id) throws DAOException {
		Comment comment = new Comment();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {

			connection = dataSource.getConnection();

			statement = connection
					.prepareStatement(SQL_READ_COMMENT_BY_ID_QUERY);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();


			while (resultSet.next()) {

				long idComment = resultSet.getLong(1);
				long idNews = resultSet.getLong(2);
				String text = resultSet.getString(3);
				Timestamp creationDate = resultSet.getTimestamp(4);

				comment.setId(idComment);
				comment.setIdNews(idNews);
				comment.setText(text);
				comment.setCreationDate(creationDate);

			}
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement, resultSet);
		}
		return comment;
	}

	@Override
	public void update(Comment entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> takeCommentsByNewsId(long idNews) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Comment entity) throws DAOException {
		this.delete(entity.getId());
	}

	@Override
	public void delete(Long id) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();

			statement = connection
					.prepareStatement(SQL_DELETE_COMMENT_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			closeConnection(connection, statement);
		}
	}

}
