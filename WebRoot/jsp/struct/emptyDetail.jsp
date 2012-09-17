<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
	function deleteAccount(eid) {
		var detailForm = document.getElementById("AccountDetail");
		if (confirm("<s:text name='qDelAcc' />")) {
			detailForm.action = "mailOperationAction_deleteMailAccount.action?eid="+eid;
			detailForm.target = "_parent";
			detailForm.submit();
		}
	}
</script>
</head>
<body>
	<form id="AccountDetail" name="AccountDetail" action="">
		<table border="1" style="width:100%">
			<tr>
				<td><c:forEach var="acc" items="${ma.accounts}"
						varStatus="status" begin="0" step="1">
						<table border="1" style="width:100%">
							<tr>
								<th width="100%" colspan="2"><a
									href="mailOperationAction_showSingleAccountInformation.action?folderName=${item.fullName}&eid=${acc.eid}"
									target="mainFrame"> ${acc.accountName} </a></th>
							</tr>
							<c:forEach var="item" items="${ acc.folders}" varStatus="status"
								begin="0" step="1">
								<tr>
									<td width="50%"><a
										href="mailOperationAction_getMailList.action?folderName=${item.fullName}&eid=${acc.eid}"
										target="topFrame">${item.fullName}</a></td>
									<td width="50%"><label>${acc.size[status.count-1 ]
											} (${acc.unreadSize[status.count-1 ] } )</label>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="2">
									<button onclick="javascript:deleteAccount(${acc.eid});"><s:text name='deleteEmailAccount' /></button>
								</td>
							</tr>

						</table>
					</c:forEach>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td> -->
<!-- 					<a href="mailOperationAction_forAddMailAccount.action" target="_self"> -->
<!-- 						<button value="<s:text name='addEmailAccount' />"><s:text name='addEmailAccount' /></button> -->
<!-- 					</a> -->
<!-- 				</td> -->
<!-- 			</tr> -->
		</table>
	</form>
</body>
</html>