<%@ page contentType="text/html; charset=UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbarEexample">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/video/list">ACAPPELLA</a>
		</div>

		<div class="collapse navbar-collapse" id="navbarEexample">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/band/ranking">BAND&nbsp;RANKING</a></li>
				<li><a href="${pageContext.request.contextPath}/band/list">BAND&nbsp;LIST</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!hasRole('USER')">
					<li><a href="${pageContext.request.contextPath}/loginform">LOGIN</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('USER')">
					<li><a href="${pageContext.request.contextPath}/band/regist">REGIST</a></li>
					<li><a href="${pageContext.request.contextPath}/my-page">MY&nbsp;PAGE</a></li>
					<li><a href="${pageContext.request.contextPath}/logout">LOGOUT</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>