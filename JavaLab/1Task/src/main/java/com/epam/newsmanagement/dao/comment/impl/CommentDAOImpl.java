/**
 * 
 */
package com.epam.newsmanagement.dao.comment.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.epam.newsmanagement.dao.comment.CommentDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class CommentDAOImpl implements CommentDAO {
	private static final String CREATE_NEW_COMMENT = "INSERT INTO comments(CREATION_DATE, TEXT, COMMENT_ID) VALUES (?, ? , COMMENTS_COMMNET_ID_SEQ.nextval)";
	private static final String READ_COMMENT_BY_ID = "SELECT COMMENT_ID, CREATION_DATE, TEXT FROM comments WHERE COMMENT_ID = ? ";

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
				try {
					connection = dataSource.getConnection();
				} catch (SQLException e) {
					throw new DAOException("Problem during getting Connection"
							+ e);
				}

				try {
					statement = connection.prepareStatement(CREATE_NEW_COMMENT,
							new String[] { "COMMENT_ID" });
					entity.getText();
					Timestamp creationDate = entity.getCreationDate();
					String text = entity.getText();
					statement.setTimestamp(1, creationDate);
					statement.setString(2, text);
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
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				throw new DAOException("Problem during getting Connection" + e);
			}

			try {
				statement = connection.prepareStatement(READ_COMMENT_BY_ID);
				statement.setLong(1, id);
				resultSet = statement.executeQuery();
			} catch (SQLException e) {
				throw new DAOException("Problem during preparing statement" + e);
			}
			try {
				// COMMENT_ID, CREATION_DATE, TEXT FROM comments WHERE
				// COMMENT_ID = ? ";

				while (resultSet.next()) {
					long idComment = resultSet.getLong(1);
					Timestamp creationDate = resultSet.getTimestamp(2);
					String text = resultSet.getString(3);
					comment.setId(idComment);
					comment.setCreationDate(creationDate);
					comment.setText(text);
				}
			} catch (SQLException e) {
				throw new DAOException("Problem during reading comment" + e);
			}
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
	public void delete(Comment entity) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> takeCommentsByNewsId(long idNews) {
		// TODO Auto-generated method stub
		return null;
	}

}
