<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PersonalMailManager</title>
</head>

<frameset rows="*" cols="300,*" frameborder="yes" border="1"
	framespacing="0">
	<!-- 滚动条 scrolling="yes"  -->
	<frame src="mailOperationAction_forNavigation.action" name="leftFrame" noresize="noresize" title="leftFrame">
	<frameset rows="100,*" frameborder="yes" border="1" framespacing="0">
		<frame src="mailOperationAction_forTop.action" name="topFrame" noresize="noresize" title="topFrame">
		<frame src="mailOperationAction_forMain.action" name="mainFrame" title="mainFrame">
	</frameset>

</frameset>
<noframes>
	<body>
	    <p>${content }</p>
	    <p>抱歉,此浏览器不支持框架网页</p>
	</body>
</noframes>
</html>
