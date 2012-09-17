<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath",basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${basePath }js/jquery/ga.js" type="text/javascript"></script>
<script src="${basePath }js/jquery/livevalidation_prototype.js" type="text/javascript"></script>
<script src="${basePath }js/jquery/livevalidation_standalone.js" type="text/javascript"></script>
<link href="${basePath }css/jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function doSubmit(){
	document.getElementById("loginForm").submit();

}
</script>

</head>

<body>
<!-- 国际化 -->
		<s:url id="url" action="loginAction_forIndex.action">
			<s:param name="request_locale">zh_CN</s:param>
		</s:url>
		<s:a href="%{url}">
			<s:text name="Chinese" />
		</s:a>
		<s:url id="url" action="loginAction_forIndex.action">
			<s:param name="request_locale">en_US</s:param>
		</s:url>
		<s:a href="%{url}">
			<s:text name="English" />
		</s:a>
<!-- end of 国际化 -->

	<form id="loginForm" action="loginAction_login.action" method="post">

	<!-- s:token -->
		<s:token /> 
		<table align="center" width="50%">
			<tr>
				<td colspan="2">
					<font color="red"><s:property value="exception.message"/></font>
				</td>
			</tr>
			<tr>
				<td width="20%">
					<s:text name="userName" />
				</td>
				<td width="80%">
					<input type="text" id="username" name="username" />
					<script type="text/javascript">
						var username = new LiveValidation('username', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						username.add(Validate.Presence, {
							failureMessage : "<s:text name='needUserName' />"
						});
						username.add(Validate.Length, {
							minimum : 3,
							maximum : 15
						});
					</script>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userPassword" />
				</td>
				<td>
					<input type="password" id="password" name="password" />
					<script type="text/javascript">
						var password = new LiveValidation('password', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						password.add(Validate.Presence, {
							failureMessage : "<s:text name='needPassword' />"
						});
					</script>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name='verificationCode' />
				</td>
				<td>
					<input type="text" id="check" name="check" maxlength="4" /><br>
					<img border=0 src="${basePath }jsp/commons/verificationCode/image.jsp" />  
				<script type="text/javascript">
						var checkVal = new LiveValidation('check', {
							validMessage : 'OK！',
							wait : 500
						});
						checkVal.add(Validate.Presence, {
							failureMessage : "<s:text name='verificationCode' />"
						});
					</script>
				</td>				
			</tr>
			<tr>
				<td colspan="1">
					<a href="loginAction_forEnroll.action">
						<input type="button" value="<s:text name='reg' />" />
					</a>
				</td>
				<td colspan="1"><input type="submit" value="<s:text name='loginPage' />" />
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
