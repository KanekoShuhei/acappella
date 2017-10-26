<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<body>
	<div class="container" ng-controller="bandDetailCtrl">
		<h3 class="row">
			<div class="col-xs-9">
				<span class="lead" style="vertical-align: middle;">{{band.name}}（<a href="https://twitter.com/{{band.twitter}}">@{{band.twitter}}</a>）</span>
			</div>
			<div class="col-xs-3">
				<sec:authorize access="!hasRole('USER')">
					<button type="button"
						class="btn btn-sm btn-danger btn-group pull-right"
						data-toggle="modal" data-target="#notLoginLove{{$index}}">LOVE
						THIS BAND</button>
					<!-- モーダル・ダイアログ -->
					<div class="modal fade" id="notLoginLove{{$index}}" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span>×</span>
									</button>
								</div>
								<div class="modal-body text-center h5">バンドをお気に入りするにはログインしてください</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">キャンセル</button>
									<a class="btn btn-danger"
										href="${pageContext.request.contextPath}/loginform"> ログイン</a>
								</div>
							</div>
						</div>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('USER')">
					<span ng-bind-html="loveBandButton"></span>
				</sec:authorize>
			</div>
		</h3>
		<div
			ng-repeat="result in results | orderBy:'-video.likes' | filter:bandSearch"
			class="panel panel-default">
			<div class="panel-heading clearfix panel-inverse">
				<a
					href="${pageContext.request.contextPath}/video/detail?youtubeId={{result.data.id}}"
					class="h1 panel-title pull-left"> {{result.data.snippet.title}}
				</a>
				<div class="text-right">
					<%@ include file="common/like.jsp"%>
				</div>
			</div>
			<div class="panel-body text-center">
				<a
					href="${pageContext.request.contextPath}/video/detail?youtubeId={{result.data.id}}">
					<img ng-src="{{result.data.snippet.thumbnails.medium.url}}" class="img-responsive center-block">
				</a>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/band_detail.js"></script>
</body>
</html>