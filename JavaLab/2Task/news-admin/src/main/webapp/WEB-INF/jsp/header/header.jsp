<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<table align="center" width="90%">
	<tr>
		<td>
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">

						<a class="navbar-brand news_logo" href="/news-admin/news/reset"><spring:message
								code="label.head.name" /></a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="navbar-form navbar-right">
							<a href="?lang=ru"><button type="button"
									class="btn btn-default news_button">
									<spring:message code="label.button.ru" />
								</button></a>
							<a href="?lang=en"><button type="button"
									class="btn btn-default news_button">
									<spring:message code="label.button.en" />
								</button></a>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li><a href="#"><spring:message code="label.head.logout" /></a></li>
						</ul>
					</div>
				</div>
			</nav>

		</td>
	</tr>
</table>