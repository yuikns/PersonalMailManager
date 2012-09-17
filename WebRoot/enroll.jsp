<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link href="${basePath }css/jquery.css"  rel="stylesheet" type="text/css" />
<script>
function doSubmit()
{
    var automaticOnSubmit = username.form.onsubmit;
    var valid = automaticOnSubmit();
	if(valid) mainForm.submit();
	else return false;
}
</script>
</head>

<body>
<!-- 国际化 -->
		<s:url id="url" action="loginAction_forEnroll.action">
			<s:param name="request_locale">zh_CN</s:param>
		</s:url>
		<s:a href="%{url}">
			<s:text name="Chinese" />
		</s:a>
		<s:url id="url" action="loginAction_forEnroll.action">
			<s:param name="request_locale">en_US</s:param>
		</s:url>
		<s:a href="%{url}">
			<s:text name="English" />
		</s:a>
<!-- end of 国际化 -->
<a href="loginAction_forIndex.action">
	<s:text name="toIndex" />
</a>

	<form id="MainForm" action="enrollAction_enroll.action" method="post">
		<table align="center" width="60%" border="0">
			<tr>
				<td colspan="2">
					<font color="red"><s:property value="exception.message"/></font>
				</td>
			</tr>
			<tr >
				<td width="20%">
					<s:text name="userName" />
				</td>
				<td width="80%">
					<input type="text" id="username" width="100%" name="username" />
					<script type="text/javascript">
						var username = new LiveValidation('username', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						username.add(Validate.Presence, {
							failureMessage : "<s:text name='needUserName' />"
						});
						username.add( Validate.Length, { minimum: 3, maximum: 15 } );
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
						password.add( Validate.Length, { minimum: 4, maximum: 20 } );
					</script>
				</td>
			</tr>
			<tr>
				<td>
					<s:text name="userPassword2" />
				</td>
				<td>
					<input type="password" id="password2" name="password2" />
					<script type="text/javascript">
						var password2 = new LiveValidation('password2', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						password2.add( Validate.Confirmation, { match: 'password' } );
						password2.add( Validate.Length, { minimum: 4, maximum: 20 } );
					</script>					
				</td>
			</tr>
			<tr>
			    <td><input type="reset" value="<s:text name='resetForm' />"></td>
				<td colspan="1"><input type="button" onclick="doSubmit();" value="<s:text name='reg' />" /></td>
			</tr>
		</table>
	</form>
	<script>var mainForm = document.getElementById('MainForm');</script>
</body>
</html>
