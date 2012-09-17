<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
	session.setAttribute("basePath",basePath);
%>
<html>
<head>
<script src="${basePath }js/jquery/ga.js" type="text/javascript"></script>
<script src="${basePath }js/jquery/livevalidation_prototype.js" type="text/javascript"></script>
<script src="${basePath }js/jquery/livevalidation_standalone.js" type="text/javascript"></script>
<link href="${basePath }css/jquery.css"  rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="mailOperationAction_addMailAccount.action" target="_parent">
		<table width="100%" border="1">
			<tr><td colspan="2"><b><s:text name="accountInfo" /></b></td></tr>
			<tr>
				<td><s:text name="userName" /></td>
				<td>
					<input type="text" style="width:70%" id="emailinfo.euser"  name="emailinfo.euser" value="${emailinfo.euser }"/>
					<script type="text/javascript">
						var euser = new LiveValidation('emailinfo.euser', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						euser.add(Validate.Presence, {
							failureMessage : "<s:text name='needUserName'/>"
						});
						euser.add( Validate.Length, { minimum: 0, maximum: 100 } );
					</script>
				</td>
			</tr>
			<tr>
				<td width="30%">imap url</td>
				<td width="70%">
					<input type="text" style="width:70%" id="emailinfo.pop3" name="emailinfo.pop3" value="${emailinfo.pop3 }"/>
					<script type="text/javascript">
						var pop3 = new LiveValidation('emailinfo.pop3', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						pop3.add(Validate.Presence, {
							failureMessage : "<s:text name='needSth'><s:param>IMAP</s:param></s:text>"
						});
						pop3.add( Validate.Length, { minimum: 0, maximum: 100 } );
						pop3.add(Validate.Format, { pattern: /imap/i });
					</script>
				</td>
			</tr>
			<tr>
				<td>smtp url</td>
				<td>
					<input type="text"  style="width:70%" id="emailinfo.smtp" name="emailinfo.smtp" value="${emailinfo.smtp }"/>
					<script type="text/javascript">
						var smtp = new LiveValidation('emailinfo.smtp', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						smtp.add(Validate.Presence, {
							failureMessage : "<s:text name='needSth'><s:param>SMTP</s:param></s:text>"
						});
						smtp.add( Validate.Length, { minimum: 0, maximum: 100 } );
						smtp.add(Validate.Format, { pattern: /smtp/i });
					</script>
				</td>
			</tr>
			<tr>
				<td><s:text name="userPassword" /></td>
				<td>
					<input type="password" style="width:70%" id="emailinfo.epwd" name="emailinfo.epwd" value="${emailinfo.epwd }"/>
					<script type="text/javascript">
						var epwd = new LiveValidation('emailinfo.epwd', {
							validMessage : 'OK！',
							//onlyOnSubmit: true,
							wait : 500
						});
						epwd.add(Validate.Presence, {
							failureMessage : "<s:text name='needPassword'/>"
						});
						epwd.add( Validate.Length, { minimum: 0, maximum: 100 } );
					</script>
				</td>
			</tr>
			<!-- 
			<tr>
				<td colspan="2"><label >status: ${content } </label></td>
			</tr>
			-->
			<tr>
				<td colspan="2">
					<input type="reset" value="<s:text name='resetForm' />"/>
					<input type="submit" value="<s:text name='submitForm' />"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>