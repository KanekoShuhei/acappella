<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<body>
<script src="${pageContext.request.contextPath}/js/video_detail.js"></script>
	<div class="container col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10">
		<div class="row" ng-controller="videoDetailCtrl">
			<div ng-model="result">
				<div class="embed-responsive embed-responsive-16by9">
					<iframe
						title="YouTube video player"
						class="youtube-player embed-responsive-item" type="text/html"
						width="100%" height="100%" ng-src="{{trustSrc(result.data.id)}}"
						frameborder="0"> </iframe>
				</div> 
					<h3>{{result.data.snippet.title}}</h3>
				<div class="text-right">
					<h4>BAND：<a href="${pageContext.request.contextPath}/band/detail?bandId={{result.video.bandId}}">{{result.video.bandName}}</a></h4>
					<p>YouTube再生回数：{{result.data.statistics.viewCount | number}}</p>
					<%@ include file="common/like.jsp"%>
				</div>
			</div>	
		</div>
		<!-- end container -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	</div>
</body>
</html>