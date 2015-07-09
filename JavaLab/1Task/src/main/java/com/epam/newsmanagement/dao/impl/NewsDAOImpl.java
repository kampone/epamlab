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
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.SearchCriteria;
import com.epam.newsmanagement.exception.DAOException;

/**
 * @author Uladzislau_Kaminski
 *
 */
public class NewsDAOImpl implements NewsDAO {
	private static final String SQL_CREATE_NEW_NEWS_QUERY = "INSERT INTO news n (n.title, n.short_text, n.full_text, n.creation_date, n.modification_date, n.news_id) VALUES (?,?,?,SYSDATE,SYSDATE,news_news_id_seq.nextval)";
	private static final String SQL_READ_NEWS_BY_ID_QUERY = "SELECT n.news_id, n.title, n.short_text, n.full_text, n.creation_date, n.modification_date FROM news n WHERE n.news_id = ?";
	private static final String SQL_UPDATE_NEWS_BY_ID_QUERY = "UPDATE news n SET n.title = ?, n.short_text = ?, n.full_text = ?, n.modification_date = SYSDATE WHERE n.news_id = ? ";
	private static final String SQL_DELETE_NEWS_BY_ID_QUERY = "DELETE FROM news n WHERE n.news_id = ?";
	private static final String SQL_READ_ALL_NEWS_QUERY = "SELECT DISTINCT  res.news_id, res.title, res.short_text, res.full_text, res.creation_date, res.modification_date "
			+ "FROM ( SELECT row_number() OVER (ORDER BY nc.comments_count DESC, nc.modification_date) rn, nc.* "
			+ "FROM (SELECT n.news_id , n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date, COUNT(c.news_id) AS comments_count "
			+ "FROM news n "
			+ "LEFT JOIN comments c ON n.news_id=c.news_id GROUP BY n.news_id, n.title , n.short_text ,n.full_text , n.creation_date , n.modification_date) nc "
			+ ") res  " + "WHERE res.rn  BETWEEN ? AND ?  ";
	private static final String SQL_ADD_SEARCH_CRITERIA_QUERY = "LEFT JOIN (SELECT na.author_id, na.news_id, nt.tag_id FROM news_tags nt "
			+ "LEFT JOIN news_authors na ON na.news_id = nt.news_id) links ON links.news_id = nc.news_id ";
	private static final String SQL_WHERE_AUTHOR_ID_QUERY = "WHERE links.author_id=? ";
	private static final String SQL_WHERE_TAGS_ID_QUERY = "WHERE links.tag_id IN ";
	private static final String SQL_AND_TAGS_ID_QUERY = "AND links.tag_id IN ";

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return return tag id if it is created
	 * @see com.epam.dao.NewsManagementDAO#create(com.epam.entity.News)
	 */
	@Override
	public Long create(News entity) throws DAOException {
		Long id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_CREATE_NEW_NEWS_QUERY, new String[] { "NEWS_ID" });
			String title = entity.getTitle();
			String shortText = entity.getShortText();
			String fullText = entity.getFullText();
			statement.setString(1, title);
			statement.setString(2, shortText);
			statement.setString(3, fullText);
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
	 *
	 * 
	 * @see com.epam.dao.NewsManagementDAO#read(Long)
	 */
	@Override
	public News read(Long id) throws DAOException {
		News news = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_READ_NEWS_BY_ID_QUERY);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				news = new News();
				Long idNews = resultSet.getLong(1);
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
			throw new DAOException(System.lineSeparator() + " Problem during reading comment ", e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return news;
	}

	/**
	 * 
	 * @see com.epam.dao.NewsManagementDAO#update(com.epam.entity.News )
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
			statement.setLong(4, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	/**
	 * 
	 * @see com.epam.dao.NewsManagementDAO#delete(com.epam.entity.News )
	 */
	@Override
	public void delete(News entity) throws DAOException {
		this.delete(entity.getId());
	}

	/**
	 * 
	 * @see com.epam.dao.NewsManagementDAO#delete(java.lang.Long )
	 */
	@Override
	public void delete(Long id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(SQL_DELETE_NEWS_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during preparing statement ", e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	private String createQuery(SearchCriteria searchCriteria) {
		StringBuilder sbAllNews = new StringBuilder(SQL_READ_ALL_NEWS_QUERY);

		if (searchCriteria != null) {
			int lastIndex = sbAllNews.lastIndexOf(")");
			sbAllNews.insert(lastIndex, createQueryWithSearchCriteria(searchCriteria));
		}
		return sbAllNews.toString();
	}

	private String createQueryWithSearchCriteria(SearchCriteria searchCriteria) {
		StringBuilder sbSearchCriteria = new StringBuilder(SQL_ADD_SEARCH_CRITERIA_QUERY);
		if (searchCriteria.getIdAuthor() != null) {
			sbSearchCriteria.append(SQL_WHERE_AUTHOR_ID_QUERY);
		}
		if (!searchCriteria.getIdTagList().isEmpty()) {
			if (searchCriteria.getIdAuthor() == null) {
				sbSearchCriteria.append(SQL_WHERE_TAGS_ID_QUERY);
			} else {
				sbSearchCriteria.append(SQL_AND_TAGS_ID_QUERY);
			}
			sbSearchCriteria.append(makeParametres(searchCriteria));
		}
		return sbSearchCriteria.toString();
	}

	private String makeParametres(SearchCriteria searchCriteria) {
		StringBuilder sb = new StringBuilder("(");
		for (Long a : searchCriteria.getIdTagList()) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

	private PreparedStatement insertParametres(SearchCriteria sc, PreparedStatement ps, int startIndex, int lastIndex)
			throws DAOException {
		try {
			int i = 1;
			if (sc.getIdAuthor() != null) {
				ps.setLong(i, sc.getIdAuthor());
				++i;
			}
			for (Long id : sc.getIdTagList()) {
				ps.setLong(i, id);
				++i;
			}
			ps.setInt(i++, startIndex);
			ps.setInt(i, lastIndex);
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during inserting parametrs ", e);
		}
		return ps;
	}

	@Override
	public List<News> getNews(SearchCriteria searchCriteria, int startIndex, int lastIndex) throws DAOException {
		List<News> newsList = null;
		News news = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		newsList = new ArrayList<>();
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(createQuery(searchCriteria));
			resultSet = insertParametres(searchCriteria, statement, startIndex, lastIndex).executeQuery();
			while (resultSet.next()) {
				news = new News();
				Long idNews = resultSet.getLong(1);
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
				newsList.add(news);
			}
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during reading comment ", e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return newsList;
	}

}
