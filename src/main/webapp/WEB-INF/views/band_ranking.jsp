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
							<th class="col-xs-1 text-center">Rank</th>
							<th class="col-xs-8 text-center">Band</th>
							<th class="col-xs-1 text-center">Point</th>
							<th class="col-xs-2 text-center">Twitter</th>
						</tr>
					</thead>
					<tbody>
					<tr ng-repeat="band in bands | orderBy: '-popularity'" class="text-center">
						<td>{{$index + 1}}</td>
						<td class="lead"><a href="${pageContext.request.contextPath}/band/detail?bandId={{band.id}}" style="text-decoration: none;">
						{{band.name}}</a></td>
						<td>{{band.popularity}}</td>
						<td><a href="https://twitter.com/{{band.twitter}}"> 
            			<img src="${pageContext.request.contextPath}/img/if_43-twitter_104461.png" height="20%"></a></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	</div>
</body>
</html>