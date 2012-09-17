<%@ page language="java" import="java.util.*"   contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath",basePath);
%>
<html>
<body>
<% Date myDate = new Date(); %>
<s:text name="successMsg">
	<s:param> <%= session.getAttribute("userName")%></s:param>
	<s:param><%= myDate %></s:param>
</s:text>
<p>${content }</p>
</body>
</html>