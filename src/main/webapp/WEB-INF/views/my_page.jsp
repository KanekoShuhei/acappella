<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<link href="${pageContext.request.contextPath}/css/my_page.css"
	rel="stylesheet">
<body>
	<script src="${pageContext.request.contextPath}/js/my_page.js"></script>
	<div class="container">
		<div class="row" ng-controller="myPageCtrl">
			<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
				<h3 class="text-center">MY PAGE</h3>
				<h3>LOVE BANDS</h3>
					<div ng-repeat="band in bands" class="row text-center" style="padding-bottom:10px;">
						<div class="col-xs-8 lead">
							<a href="${pageContext.request.contextPath}/band/detail?bandId={{band.id}}">{{band.name}}</a>
						</div>
						<div class="col-xs-4">	
							<a class="btn btn-danger btn-xs"
								style="vertical-align: inherit;" data-toggle="modal"
								data-target="#deleteBandAlert{{$index}}">削除</a>
						</div>
						<!-- モーダル・ダイアログ -->
						<div class="modal fade" id="deleteBandAlert{{$index}}" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span>×</span>
										</button>
										<!-- <h4 class="modal-title">タイトル</h4> -->
									</div>
									<div class="modal-body">「{{band.name}}」をお気に入りバンドから削除しますか</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">キャンセル</button>
										<a class="btn btn-danger"
											href="${pageContext.request.contextPath}/my-page/delete-love-bands?bandId={{band.id}}"> 　削除　 </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				<h3>LIKE VIDEOS</h3>
				<%@ include file="common/video_list.jsp"%>
			</div>
		</div>
		<!-- end container -->
	</div>
</body>
</html>