<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
	<form action="mailOperationAction_sendEmail.action">
		<table style="align:center" border="1" width="100%">
			<tr>
				<td width="30%">From</td>
				<td width="70%"><input name="mailDetail.from" style="width:100%" value="${mailDetail.from }" readOnly />
				</td>
			</tr>
			<tr>
				<td>To</td>
				<td><input name="mailDetail.to" style="width:100%" value="${mailDetail.to }" />
				</td>
			</tr>
			<!-- 	<tr> -->
			<!-- 		<td>CC</td> -->
			<!-- 		<td><input name="mailDetail.cc" value="${mailDetail.cc }"/></td> -->
			<!-- 	</tr> -->
			<tr>
				<td>主题</td>
				<td colspan="2"><input name="mailDetail.subject"  style="width:100%"  value="${mailDetail.subject }" /></td>
			</tr>
			<tr>
				<td>正文</td>
				<td><textarea cols="100" rows="20" id="contactus"
						name="mailDetail.textContent">${mailDetail.textContent}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="发送" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
