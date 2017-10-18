<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<body>
	<script src="${pageContext.request.contextPath}/js/all_bands.js"></script>
	<div class="container">
		<div class="row" ng-controller="allBandsCtrl">
			<div class="col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10">
				<form:form modelAttribute="videoForm"
					action="${pageContext.request.contextPath}/video/save">
					<legend>REGIST VIDEO</legend>
					<div class="form-group">
						<label style="color: red" for="inputError"><form:errors path="bandId" id="inputError"/></label>
						<label for="inputDescription">BAND</label> <select name="bandId"
							ng-model="band" class="form-control">
							<option ng-repeat="option in bands" value="{{option.id}}">{{option.name}}</option>
						</select>
					</div>
					<div class="form-group">
						<label for="inputName">YouTubeID</label>
						<label style="color: red" for="inputError"><form:errors path="youtubeId" id="inputError"/></label>
						<div class="input-group mb-2 mb-sm-0">
							<div class="input-group-addon">https://www.youtube.com/watch?v=</div>
							<input name="youtubeId" id="inputYoutubeId" class="form-control"
								placeholder="YouTubeID" required/>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-info center-block">ADD
							VIDEO</button>
					</div>
				</form:form>

				<form:form modelAttribute="bandForm"
					action="${pageContext.request.contextPath}/band/save">
					<legend>REGIST BAND</legend>
					<div class="form-group">
						<label for="inputName">BAND NAME</label>
						<form:input path="name" id="inputName" class="form-control"
							placeholder="バンド名" required="required"/>
					</div>
					<div class="form-group">
						<label for="inputTwitterId">TwitterID</label>
						<label style="color: red" for="inputError"><form:errors path="twitter" id="inputError"/></label>
						<div class="input-group mb-2 mb-sm-0">
							<div class="input-group-addon">@</div>
							<form:input path="twitter" id="inputTwitter" class="form-control"
								placeholder="Twitterアカウント" required="required"/>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-info center-block">ADD
							BAND</button>
					</div>
				</form:form>
			</div>
		</div>
		<!-- end container -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	</div>
</body>
</html>