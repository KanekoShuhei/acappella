<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<sec:authorize access="!hasRole('USER')">
	<button type="button" class="btn btn-default btn-xs"
		data-toggle="modal" data-target="#notLoginLike{{$index}}">★</button>
	<div class="modal fade" id="notLoginLike{{$index}}" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>×</span>
					</button>
				</div>
				<div class="modal-body text-center">いいねを押すにはログインしてください</div>
				<div class="modal-footer">
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/loginform">ログイン</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">キャンセル</button>
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('USER')">
	<span ng-bind-html="result.likeVideoButton"></span>
</sec:authorize>
&nbsp;&nbsp;{{result.video.likes}}
