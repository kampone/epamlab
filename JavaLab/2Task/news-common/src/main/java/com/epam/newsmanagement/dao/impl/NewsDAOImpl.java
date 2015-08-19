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
	private static final String SQL_UPDATE_NEWS_BY_ID_QUERY = "UPDATE news n SET n.title = ?, n.short_text = ?, n.full_text = ?, n.modification_date = ? WHERE n.news_id = ? ";
	private static final String SQL_DELETE_NEWS_BY_ID_QUERY = "DELETE FROM news n WHERE n.news_id = ?";
	private static final String SQL_READ_ALL_NEWS_QUERY = "SELECT news_id,title,short_text,full_text,creation_date,modification_date,rn FROM( "
			+ "SELECT tmp.news_id,tmp.title,tmp.short_text,tmp.full_text,tmp.creation_date,tmp.modification_date,tmp.cnt,ROWNUM rn "
			+ "FROM ( SELECT n.news_id,title,short_text,full_text,n.creation_date,n.modification_date,COUNT(DISTINCT c.comment_id) cnt "
			+ "FROM news n " + "LEFT JOIN comments c ON n.news_id = c.news_id " + " %s "
			+ "GROUP BY n.news_id,title,short_text,full_text,n.creation_date,modification_date "
			+ "ORDER BY cnt DESC NULLS LAST,n.modification_date DESC )" + "tmp ) " + "WHERE rn BETWEEN ? AND ?";
	private static final String SQL_READ_NUMBER_OF_NEWS_QUERY = "  SELECT DISTINCT total_amount " + "FROM ("
			+ "SELECT COUNT(n.news_id) over() total_amount " + "FROM news n "
			+ "LEFT JOIN comments c ON n.news_id = c.news_id " + "%s "
			+ "GROUP BY n.news_id,title,short_text,full_text,n.creation_date,modification_date ) ";
	private static final String SQL_ADD_SEARCH_CRITERIA_QUERY = "LEFT JOIN NEWS_TAGS nt ON nt.NEWS_ID = n.NEWS_ID "
			+ "LEFT JOIN NEWS_AUTHORS na ON na.NEWS_ID = n.NEWS_ID ";
	private static final String SQL_WHERE_AUTHOR_ID_QUERY = " WHERE na.author_id=? ";
	private static final String SQL_WHERE_TAGS_ID_QUERY = " WHERE nt.tag_id IN ";
	private static final String SQL_AND_TAGS_ID_QUERY = " AND nt.tag_id IN ";

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#create(java.lang.Object)
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
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#read(java.lang.Long)
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
				Long newsId = resultSet.getLong(1);
				String title = resultSet.getString(2);
				String shortText = resultSet.getString(3);
				String fullText = resultSet.getString(4);
				Timestamp creationDate = resultSet.getTimestamp(5);
				Date modificationDate = resultSet.getDate(6);
				news.setId(newsId);
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
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#update(java.lang.Object)
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
			statement.setLong(5, entity.getId());
			statement.setDate(4, new Date(entity.getModificationDate().getTime()));
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsManagementDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(News entity) throws DAOException {
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
			statement = connection.prepareStatement(SQL_DELETE_NEWS_BY_ID_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during preparing statement ", e);
		} finally {
			closeConnection(dataSource, connection, statement);
		}
	}

	private String createQuery(SearchCriteria searchCriteria, String query) {

		if (searchCriteria != null) {
			return String.format(query, createQueryWithSearchCriteria(searchCriteria));
		} else {
			return String.format(query, " ");
		}

	}

	private String createQueryWithSearchCriteria(SearchCriteria searchCriteria) {
		StringBuilder sbSearchCriteria = new StringBuilder(SQL_ADD_SEARCH_CRITERIA_QUERY);
		if (searchCriteria.getAuthorId() != null) {
			sbSearchCriteria.append(SQL_WHERE_AUTHOR_ID_QUERY);
		}
		if (!searchCriteria.getTagIdList().isEmpty()) {
			if (searchCriteria.getAuthorId() == null) {
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
		for (int j = 0; j < searchCriteria.getTagIdList().size(); j++) {
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
			if (sc != null) {
				if (sc.getAuthorId() != null) {
					ps.setLong(i, sc.getAuthorId());
					++i;
				}
				for (Long id : sc.getTagIdList()) {
					ps.setLong(i, id);
					++i;
				}
			}
			ps.setInt(i++, startIndex);
			ps.setInt(i, lastIndex);
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during inserting parametrs ", e);
		}
		return ps;
	}

	private PreparedStatement insertParametres(SearchCriteria sc, PreparedStatement ps) throws DAOException {
		try {
			int i = 1;
			if (sc != null) {
				if (sc.getAuthorId() != null) {
					ps.setLong(i, sc.getAuthorId());
					++i;
				}
				for (Long id : sc.getTagIdList()) {
					ps.setLong(i, id);
					++i;
				}
			}
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during inserting parametrs ", e);
		}
		return ps;
	}

	/**
	 * @see com.epam.newsmanagement.dao.NewsDAO#getNews(com.epam.newsmanagement.entity.SearchCriteria,
	 *      int, int)
	 */
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
			statement = connection.prepareStatement(createQuery(searchCriteria, SQL_READ_ALL_NEWS_QUERY));
			resultSet = insertParametres(searchCriteria, statement, startIndex, lastIndex).executeQuery();
			while (resultSet.next()) {
				news = new News();
				Long newsId = resultSet.getLong(1);
				String title = resultSet.getString(2);
				String shortText = resultSet.getString(3);
				String fullText = resultSet.getString(4);
				Timestamp creationDate = resultSet.getTimestamp(5);
				Date modificationDate = resultSet.getDate(6);
				news.setId(newsId);
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

	@Override
	public int getNewsNumber(SearchCriteria searchCriteria) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int number = 0;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(createQuery(searchCriteria, SQL_READ_NUMBER_OF_NEWS_QUERY));
			resultSet = insertParametres(searchCriteria, statement).executeQuery();
			while (resultSet.next()) {
				number = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during reading number of news ", e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return number;

	}

	@Override
	public int findIndex(SearchCriteria searchCriteria, Long newsId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceUtils.doGetConnection(dataSource);
			statement = connection.prepareStatement(createQuery(searchCriteria, SQL_READ_ALL_NEWS_QUERY));
			resultSet = insertParametres(searchCriteria, statement, Integer.MIN_VALUE, Integer.MAX_VALUE).executeQuery();
			while (resultSet.next()) {
				Long actualNewsId = resultSet.getLong(1);
				if (actualNewsId == newsId) {
					return resultSet.getInt(7);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(System.lineSeparator() + " Problem during reading index of news ", e);
		} finally {
			closeConnection(dataSource, connection, statement, resultSet);
		}
		return 0;

}
}
