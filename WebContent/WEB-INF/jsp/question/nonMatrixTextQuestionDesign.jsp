<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>非矩阵型问题设计</title>
				<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
		<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<s:include value="../common/header.jsp" />
		<div class="container">
		<s:form action="QuestionAction_saveOrUpdateQuestion.action" method="post">
		<s:hidden name="id" />
		<s:hidden name="questionType" />
		<s:hidden name="pid" />
		<s:hidden name="sid" />
		<table>
			<tr>
				<td colspan="2" class="tdQHeaderL">非矩阵型问题设计:</td>
			</tr>
			<tr>
				<td width="35%" style="text-align: right;">问题标题:</td>
				<td width="*" style="text-align: left;"><s:textfield name="title" cssClass="text" /></td>
			</tr>
			<tr>
				<td style="text-align: right;"></td>
				<td width="*" style="text-align: left;"><input type="submit" name="ok" value="确定" class="btn btn-info"></td>
			</tr>
		</table>
		</s:form>
		</div>
	</body>
</html>