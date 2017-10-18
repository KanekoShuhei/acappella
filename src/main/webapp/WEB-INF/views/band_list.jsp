<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<script src="${pageContext.request.contextPath}/js/all_bands.js"></script>
<body>
	<div class="container" ng-controller="allBandsCtrl">
		<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
			<h3>BAND LIST</h3>
			<div class="form-group text-right">
				<input class="form-control" type="text" value="band-name" ng-model="bandSearch" placeholder="Search">
			</div>
			<%@ include file="common/band_list.jsp"%>
		</div>
	</div>
</body>
</html>