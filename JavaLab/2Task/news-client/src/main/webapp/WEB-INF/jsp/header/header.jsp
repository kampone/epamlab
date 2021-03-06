<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<table align="center" width="1200px">
	<tr>
		<td>

			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							 <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand news_logo" href="/news-client/news/reset"><spring:message
								code="label.head.name" /></a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="navbar-form navbar-right">
							<a href="?lang=ru"><button type="button"
									class="btn btn-default news_button" >
									<spring:message code="label.button.ru" />
								</button></a>
							<a href="?lang=en"><button type="button"
									class="btn btn-default news_button">
									<spring:message code="label.button.en" />
								</button></a>
						</ul>
						<%--Need to be added to admin application 
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#"><spring:message code="label.head.logout" /></a></li>
						</ul> 
 --%>					</div>
				</div>
			</nav>

		</td>
	</tr>
</table>