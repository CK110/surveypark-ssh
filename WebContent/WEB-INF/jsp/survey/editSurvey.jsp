<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑调查</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
		<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<s:include value="../common/header.jsp" />
		<div class="container">
		<table>
			<tr>
				<td class="tdHeader">编辑调查标题:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
								<s:form action="SurveyAction_updateSurvey" namespace="/" method="post">
								<s:hidden name="id" />
								<table>
									<tr>
										<td class="tdFormLabel">调查标题:</td>
										<td class="tdFormControl"><s:textfield name="title" class="form-control" /></td>
										<td class="tdFormLabel"></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"下一步"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="nextText" class="form-control" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"上一步"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="preText" class="form-control" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"完成"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="doneText" class="form-control" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"退出"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="exitText" class="form-control" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl"><s:submit value="确定" class="btn btn-info" /></td>
									</tr>
								</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
	</body>
</html>