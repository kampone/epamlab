<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table  width="1200px" align="center">
	<tr>
		<td align="left"><a href="/news-client/current/news/${index-1}"><button class="btn btn-primary btn-sm" type="button" name="previuos"><spring:message code="label.button.previous"/></button></a></td>
		<td align="right"><a href="/news-client/current/news/${index+1}"><button class="btn btn-primary btn-sm" type="button" name="next"><spring:message code="label.button.next"/></button></a></td>
	</tr>
</table>