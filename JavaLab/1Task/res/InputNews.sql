SELECT n.NEWS_ID, n.TITLE, n.SHORT_TEXT, n.FULL_TEXT,
n.CREATION_DATE, n.MODIFICATION_DATE,
COUNT(comments.COMMENT_ID) as comment_count
FROM news n LEFT JOIN comments  ON n.NEWS_ID = comments.NEWS_ID
LEFT JOIN NEWS_AUTHORS ON  
group by n.NEWS_ID, n.TITLE, n.SHORT_TEXT, n.FULL_TEXT, n.CREATION_DATE, 
n.MODIFICATION_DATE

ORDER BY comment_count DESC NULLS LAST, n.MODIFICATION_DATE DESC ;