/**
 * 
 */
package com.epam.newsmanagement.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class CommentDAOImpl implements CommentDAO {
	private static final String SQL_CREATE_COMMENT_QUERY = "INSERT INTO comments c (c.creation_date, c.comment_text, c.news_id, c.comment_id) VALUES (SYSDATE, ?, ?, comments_comment_id_seq.nextval)";
	private static final String SQL_READ_COMMENT_BY_ID_QUERY = "SELECT c.comment_id, c.news_id, c.comment_text, c.creation_date FROM comments c WHERE c.comment_id = ? ";
	private static final String SQL_UPDATE_COMMENT_BY_ID_QUERY = "UPDATE comments c SET  c.comment_text = ?, c.news_id = ? WHERE c.comment_id = ? ";
	private static final String SQL_DELETE_COMMENT_BY_ID_QUERY = "DELETE FROM comments c WHERE c.comment_id = ?";
	private static final String SQL_DELETE_COMMENT_BY_NEWS_ID_QUERY = "DELETE FROM comments c WHERE c.news_id = ?";
	private static final String SQL_READ_COMMENTS_BY_NEWS_ID_QUERY = "SELECT c.comment_id, c.comment_text, c.creation_date FROM comments c WHERE c.news_id = ? ORDER BY c.creation_date DESC";
	
	private DataSource dataSource;


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Comment entity) throws DAOException {
		Long id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_CREATE_COMMENT_QUERY, new String[] { "COMMENT_ID" });
			String text = entity.getText();
			Long newsId = entity.getNews().getNewsId();
			statement.setString(1, text);
			statement.setLong(2, newsId);
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
	public Comment read(Long id) throws DAOException {
		Comment comment = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_READ_COMMENT_BY_ID_QUERY);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				comment = new Comment();
				Long commentId = resultSet.getLong(1);
				Long newsId = resultSet.getLong(2);
				String text = resultSet.getString(3);
				Timestamp creationDate = resultSet.getTimestamp(4);
				comment.setId(commentId);
				comment.setNewsId(newsId);
				comment.setText(text);
				comment.setCreationDate(creationDate);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return comment;
	}


	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Comment entity) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_UPDATE_COMMENT_BY_ID_QUERY);
			statement.setString(1, entity.getText());
			statement.setLong(2, entity.getNews());
			statement.setLong(3, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}

	}

	
	/**
	 * @see com.epam.newsmanagement.dao.CommentDAO#getCommentsByNewsId(java.lang.Long)
	 */
	@Override
	public List<Comment> getCommentsByNewsId(Long newsId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Comment> commentList = null;
		Comment comment = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_READ_COMMENTS_BY_NEWS_ID_QUERY);
			statement.setLong(1, newsId);
			resultSet = statement.executeQuery();
			commentList = new ArrayList<>();
			while (resultSet.next()) {
				comment = new Comment();
				Long commentId = resultSet.getLong(1);
				String text = resultSet.getString(2);
				Timestamp creationDate = resultSet.getTimestamp(3);
				comment.setId(commentId);
				comment.setNewsId(newsId);
				comment.setText(text);
				comment.setCreationDate(creationDate);
				commentList.add(comment);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return commentList;
	}


	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Comment entity) throws DAOException {
		this.delete(entity.getId());
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
			statement = connection.prepareStatement(SQL_DELETE_COMMENT_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}


	/**
	 * @see com.epam.newsmanagement.dao.CommentDAO#deleteCommentsByNewsId(java.lang.Long)
	 */
	@Override
	public void deleteCommentsByNewsId(Long newsId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_DELETE_COMMENT_BY_NEWS_ID_QUERY);
			statement.setLong(1, newsId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.CommentDAO#addCommentsForNews(java.util.List)
	 */
	@Override
	public void addCommentsForNews(List<Comment> commentList) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_CREATE_COMMENT_QUERY);
			for (Comment comment : commentList) {
				statement.setString(1, comment.getText());
				statement.setLong(2, comment.getNews());
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}		
	}

}
