<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
</head>
<body>
<!-- 
login form -->
<div class="row">
	<div class="col-md-offset-2 col-md-8 col-xs-offset-1 col-xs-10">
		<div class="well">
			<form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/user_register/register">
				<fieldset>
					<legend>ユーザ登録</legend>
					<div class="form-group">
						<label for="inputName">名前</label>
						<label style="color: red" for="inputError"><form:errors path="name" id="inputError"/></label>
						<form:input path="name" id="inputName" class="form-control" placeholder="Name"/>
					</div>
					<div class="form-group">
						<label for="inputEmail">メールアドレス</label>
						<label style="color: red" for="inputError"><form:errors path="email" id="inputError"/></label>
						<span id="check-mail-result" style="color:red"></span>
						<form:input path="email" id="inputEmail" class="form-control" placeholder="Email"/>
					</div>
					<div class="form-group">
						<label for="inputPassword">パスワード</label>
						<label style="color: red" for="inputError"><form:errors path="password" id="inputError"/></label>
						<form:password path="password" id="inputPassword" class="form-control" placeholder="Password"/>
					</div>
					<div class="form-group">
						<label for="inputConfirmationPassword">確認用パスワード</label>
						<label style="color: red" for="inputError"><form:errors path="confirmationPassword" id="inputError"/></label>
						<form:password path="confirmationPassword" id="inputConfirmationPassword" class="form-control" placeholder="Confirmation Password"/>
					</div>
					<div class="form-group pull-right">
						<button type="submit" class="btn btn-success">登録</button>
						<button type="reset" class="btn">クリア</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
<!-- end container -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>