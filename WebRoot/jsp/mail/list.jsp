<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
function doSubmit(id){
	var ListForm = document.getElementById("listForm");
	ListForm.action = "mailOperationAction_getMailDetailInformaiton.action?id="+id;
	ListForm.submit();
}
</script>
</head>
<body>
	<form id="listForm" method="post" target="mainFrame">
	<table border="1" width="100%">
		<c:forEach var="smsi" items="${mailSimpleList}" varStatus="status" begin="0" step="1">
		<tr>
			<td>
				<a onclick="doSubmit('${status.count-1 }')">${smsi.subject }</a>
				
			</td>
			<td></td>
		</tr> 
		</c:forEach>
	</table>
	</form>
</body>
</html>