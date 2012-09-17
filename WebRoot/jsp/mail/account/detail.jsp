<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
	function updateAccount() {
		detailForm.action = "mailOperationAction_forUpdateMailAccount.action";
		detailForm.target = "_self";
		detailForm.submit();
	}
	function deleteAccount() {
		if (confirm("<s:text name='qDelAcc' />")) {
			detailForm.action = "mailOperationAction_deleteMailAccount.action";
			detailForm.target = "_parent";
			detailForm.submit();
		}
	}
</script>
</head>
<body>
	<form id="AccountDetail" name="AccountDetail" action="">
		<table style="width:100%;" border="1">
			<tr>
				<td colspan="2"><b><s:text name="accountInfo" /></b>
				</td>
			</tr>
			<tr>
				<td><s:text name="userName" /></td>
				<td>${emailinfo.euser }</td>
			</tr>
			<tr>
				<td>imap url</td>
				<td>${emailinfo.pop3 }
			</tr>
			<tr>
				<td>smtp url</td>
				<td>${emailinfo.smtp }
			</tr>
			<tr>
				<td colspan="2"><b><s:text name="mailInfo" /></b>
				</td>
			</tr>
			<c:forEach var="item" items="${ sa.folders}" varStatus="status"
				begin="0" step="1">
				<tr>
					<td width="50%"><a
						href="mailOperationAction_getMailList.action?folderName=${item.fullName}&eid=${sa.eid}"
						target="topFrame"> ${item.fullName} </a></td>
					<td width="50%"><label>${sa.size[status.count-1 ]
							}(${sa.unreadSize[status.count-1 ] } unread)</label></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
					<button onclick="javascript:updateAccount();"><s:text name="updateEmailAccount" /></button>
					<button onclick="javascript:deleteAccount();"><s:text name="deleteEmailAccount" /></button></td>
			</tr>
		</table>
	</form>
	<script>
		var detailForm = document.getElementById("AccountDetail");
	</script>
</body>
</html>