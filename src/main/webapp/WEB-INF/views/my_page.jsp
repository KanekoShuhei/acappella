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
				<div class="lovebands"
					style="border: solid 1px #a9a9a9; background-color: #F1F1F1;">
					<span ng-repeat="band in bands" class="row text-center">
						<a class="col-xs-9"
							href="${pageContext.request.contextPath}/band/detail?bandId={{band.id}}" style="vertical-align:middle;">{{band.name}}</a>
						<a class="btn btn-danger btn-xs" data-toggle="modal"
							data-target="#deleteBandAlert{{$index}}">削除</a>
						<!-- モーダル・ダイアログ -->
						<div class="modal fade" id="deleteBandAlert{{$index}}"
							tabindex="-1">
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
											href="${pageContext.request.contextPath}/my-page/delete-love-bands?bandId={{band.id}}">
											削除 </a>
									</div>
								</div>
							</div>
						</div>
					</span>
				</div>
				<h3>LIKE VIDEOS</h3>
				<%@ include file="common/video_list.jsp"%>
			</div>
		</div>
		<!-- end container -->
	</div>
</body>
</html>