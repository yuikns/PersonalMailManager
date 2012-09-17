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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="failMsg" /></title>
	</head>
	<body>
		<p><s:text name="failMsg" /></p>
		<p>${content }</p>
		<p><a href="loginAction_forIndex.action"> <s:text name="toIndex" /></a></p>
		<p><a href="loginAction_forEnroll.action"> <s:text name="toIndex" /></a></p>
	</body>
</html>
