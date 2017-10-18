<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<script src="${pageContext.request.contextPath}/js/all_bands.js"></script>
<body>
	<div class="container">
		<div class="row" ng-controller="allBandsCtrl">
			<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
				<h3 class="text-center">BAND RANKING</h3>
				<table class="table table-responsive table-bordered" style="background-color:#FFFFFF;">
					<thead class="thead-inverse">
						<tr>
							<th class="text-center">Rank</th>
							<th class="text-center">Band</th>
							<th class="text-center">Point</th>
							<th class="text-center">Twitter</th>
						</tr>
					</thead>
					<tbody>
					<tr ng-repeat="band in bands | orderBy: '-popularity'">
						<td class="text-center">{{$index + 1}}</td>
						<td class="text-center lead"><a href="${pageContext.request.contextPath}/band/detail?bandId={{band.id}}" style="text-decoration: none;">
						{{band.name}}</a></td>
						<td class="text-center">{{band.popularity}}</td>
						<td class="text-center"><a href="https://twitter.com/{{band.twitter}}" style="text-decoration: none;">@{{band.twitter}}</a></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	</div>
</body>
</html>