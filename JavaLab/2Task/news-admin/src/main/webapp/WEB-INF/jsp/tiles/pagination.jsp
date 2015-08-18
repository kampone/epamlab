<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="pagination pagination-lg">
	<c:forEach var="i" begin="1" end="${pages}">
			<li><a href="/news-admin/news/page/${i}">${i}</a></li>
	</c:forEach>
</ul>