<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>增加/编辑页内容</title>
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
				<td class="tdHeader">增加/编辑页内容:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
								<s:form action="PageAction_saveOrUpdatePage" namespace="/" method="post">
								<s:hidden name="id" />
								<s:hidden name="sid" />
								<s:hidden name="orderno" />
								<table>
									<tr>
										<td class="tdFormLabel">页面标题:</td>
										<td class="tdFormControl"><s:textfield name="title" class="form-control"/></td>
										<td class="tdFormLabel"></td>
									</tr>
									<tr>
										<td class="tdFormLabel">页面描述:</td>
										<td class="tdFormControl"><s:textarea name="description" class="form-control" cols="20" rows="8"/></td>
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
