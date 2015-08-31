<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table  width="900px">
	<tr>
		<td align="left"><a href="/news-admin/current/news/${index-1}"><button class="btn btn-primary btn-sm" type="button" name="previuos"><spring:message code="label.button.previous"/></button></a></td>
		<td align="right"><a href="/news-admin/current/news/${index+1}"><button class="btn btn-primary btn-sm" type="button" name="next"><spring:message code="label.button.next"/></button></a></td>
	</tr>
</table>