<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
	<table border="1" style="width:100%" >
		<tr>
		<td>
		<c:forEach var="acc" items="${ma.accounts}" varStatus="status" begin="0" step="1">
			<table border="1" style="width:100%" >			
			<tr>
				<th width="100%" colspan="1">
					<a href="mailOperationAction_showSingleAccountInformation.action?folderName=${item.fullName}&eid=${acc.eid}" target="mainFrame">
						${acc.accountName}
					</a>
				</th>
			</tr>
			<c:forEach var="item" items="${ acc.folders}" varStatus="status" begin="0" step="1">
				<tr>
					<td>
						<a href="mailOperationAction_getMailList.action?folderName=${item.fullName}&eid=${acc.eid}" target="topFrame">${item.fullName}</a>
						(${acc.unreadSize[status.count-1 ] } )
					</td>
				</tr>
			</c:forEach>

			</table>
		</c:forEach>
		</td>
		</tr>
		<tr>
			<td>
				<a href="mailOperationAction_forAddMailAccount.action" target="mainFrame">
					<button value="<s:text name='addEmailAccount' />"><s:text name='addEmailAccount' /></button>
				</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="loginAction_reload.action" target="_parent">
					<button value="<s:text name='refresh' />"><s:text name='refresh' /></button>
				</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="loginAction_logoff.action" target="_parent">
					<button value="<s:text name='logoff' />"><s:text name='logoff' /></button>
				</a>
			</td>
		</tr>		
		
	</table>
</body>
</html>