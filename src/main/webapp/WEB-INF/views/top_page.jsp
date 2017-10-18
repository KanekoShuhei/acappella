<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<body>
	<script src="${pageContext.request.contextPath}/js/video_list.js"></script>
	<div class="container" ng-controller="mainCtrl">
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10">
				<div class="form-group text-right">
					<input class="form-control" type="text" value="band-name"
						ng-model="bandSearch" placeholder="Search">
				</div>
				<%@ include file="common/video_list.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>