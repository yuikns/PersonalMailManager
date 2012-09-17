<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>注册</title>
		<script>
			function doSubmit(){
				var MainForm = document.getElementById("mainForm");
				if(document.getElementById("password1").value != document.getElementById("password2").value){
					alert("两次口令不一致");
					return;
				}
				MainForm.action="enrollAction_enroll.action";
				MainForm.submit();
			}
		</script>
	</head>

	<body>

		<form id="mainForm" action="" method="post">
			userName:
			<input type="text" id="username" name="username" />
			<br />
			password:
			<input type="password" id="password1" name="password" />
			<br />
			again:
			<input type="password" id="password2" />
			<br />
			<input type="button" onclick="doSubmit();" value="注册" />
		</form>
	</body>
</html>
