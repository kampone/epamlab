<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	
<ul class="nav nav-pills nav-stacked">
  <li role="presentation" onclick="toggleClass(this)"><a href="/news-admin/news/watch"><spring:message code="label.menu.newslist"/></a></li>
 	<li role="presentation" onclick="toggleClass(this)"><a href="/news-admin/edit_news/view"><spring:message code="label.menu.addnews"/></a></li>
 	<li role="presentation" onclick="toggleClass(this)"> <a href="/news-admin/authors/view"><spring:message code="label.menu.editauthors"/></a></li>
 	<li role="presentation" onclick="toggleClass(this)"><a href="/news-admin/tags/view"><spring:message code="label.menu.edittags"/></a></li>
</ul>

