<%@ page contentType="text/html; charset=UTF-8"%>
<div ng-repeat="result in results | orderBy:'-video.likes' | filter:bandSearch" class="panel panel-default">
	<div class="panel-heading clearfix">
		<a href="${pageContext.request.contextPath}/video/detail?youtubeId={{result.data.id}}" class="panel-title pull-left">
		{{result.data.snippet.title}}
		</a>
		<div class="likeVideo text-right">
		<%@ include file="like.jsp"%>
		</div>
	</div>
	<div class="panel-body text-center">
		<a href="${pageContext.request.contextPath}/video/detail?youtubeId={{result.data.id}}">
			<img ng-src="{{result.data.snippet.thumbnails.medium.url}}" class="img-rounded img-responsive center-block">
		</a>
	</div>
	<div class="panel-footer text-center">
		<span class="text-muted">BAND</span> &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/band/detail?bandId={{result.video.bandId}}">
			{{result.video.bandName}}
		</a>
	</div>
</div>