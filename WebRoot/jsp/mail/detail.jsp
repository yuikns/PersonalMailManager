<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
function getNextMail(id){
	id++;
	var DetailForm = document.getElementById("detailForm");
	DetailForm.target="mainFrame";
	DetailForm.action = "mailOperationAction_getMailDetailInformaiton.action?id="+id;
	DetailForm.submit();
}
function getPastMail(id){
	id--;
	var DetailForm = document.getElementById("detailForm");
	DetailForm.target="mainFrame";
	DetailForm.action = "mailOperationAction_getMailDetailInformaiton.action?id="+id;
	DetailForm.submit();
}
function deleteMail(id){
	var DetailForm = document.getElementById("detailForm");
	if(confirm("<s:text name='qDelMail' />?")){
		DetailForm.target="topFrame";
		DetailForm.action = "mailOperationAction_deleteMail.action?id="+id;
		DetailForm.submit();
	}
}
function replyMail(id){
	var DetailForm = document.getElementById("detailForm");
	DetailForm.target="mainFrame";
	DetailForm.action = "mailOperationAction_reply.action?id="+id;
	DetailForm.submit();
}
function newMail(id){
	var DetailForm = document.getElementById("detailForm");
	DetailForm.target="mainFrame";
	DetailForm.action = "mailOperationAction_newMailToSend.action?id="+id;
	DetailForm.submit();
}


function getAttach(id){
	var DetailForm = document.getElementById("detailForm");
	DetailForm.target="mainFrame";
	DetailForm.action = "mailOperationAction_getAttach.action?attachid="+id;
	DetailForm.submit();
}
</script>
</head>
<body>
	<form id="detailForm" method="post" target="mainFrame">
		<table border="1" width="100%">
			<tr>
				<td>
					<a onclick="javascript:newMail(${id});" ><button><s:text name='new' /></button></a>
				</td>
				<td>
					<a onclick="javascript:replyMail(${id});" ><button><s:text name='reply' /></button></a>
				</td>
				<td>
					<a onclick="javascript:deleteMail(${id})" ><button><s:text name='delete' /></button></a>
				</td>
				<td>
					<a onclick="javascript:getNextMail(${id})"><button><s:text name='next' /></button></a>
				</td>
				<td><a onclick="javascript:getPastMail(${id})"><button><s:text name='last' /></button>
				</a>
				</td>
			</tr>
		</table>
		<table border="1" width="100%">
			<tr>
				<td width="20%"><label />From:</td>
				<td width="80%"><label />${mailDetail.from }</td>
			</tr>
			<tr>
				<td width="20%"><label />ReplyTo:</td>
				<td width="80%"><label />${mailDetail.replyTo }</td>
			</tr>
			<tr>
				<td width="20%"><label />To:</td>
				<td width="80%"><label />${mailDetail.to }</td>
			</tr>
			<tr>
				<td width="20%"><label />Subject:</td>
				<td width="80%"><label />${mailDetail.subject }</td>
			</tr>
			<tr>
				<td width="20%"><label />SentDate:</td>
				<td width="80%"><label />${mailDetail.stringSentDate }</td>
			</tr>
			<tr>
				<td colspan="2">${mailDetail.htmlContent }</td>
			</tr>
<!-- 			<tr>
				<td colspan="2"><p>${mailDetail.textContent }</p></td>
			</tr>
-->
			<% int attachid = 0; %>
			<c:forEach var="dh" items="${mailDetail.dataHandlers}" varStatus="status" begin="0" step="1">
				<tr>
					<td colspan="2"><a onclick="javascript:getAttach('<%=attachid %>')">${dh.name }</a><%attachid++; %></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
