/**
 * 
 */
package com.epam.dao.comment;

import java.util.List;

import com.epam.dao.NewsManagementDAO;
import com.epam.entity.Comment;

/**
 * @author Uladzislau_Kaminski
 *
 */
public interface CommentDAO extends NewsManagementDAO<Comment> {
	public List<Comment> takeCommentsByNewsId(long idNews);
}
