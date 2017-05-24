<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>角色管理</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
		<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('tbody > tr:even').css('background-color','rgb(240,240,240)');
			});
		</script>
	</head>
	<body>
	<s:include value="../manager/manager_header.jsp" />
		<div class="container">
			<table>
				<tr>
					<td colspan="10" style="height: 5px"></td>
				</tr>
				<tr>
					<td colspan="10" class="tdPHeaderR"><s:a action="RoleAction_toAddRolePage" namespace="/">添加角色</s:a></td>
				</tr>
				<tr>
					<td colspan="10" style="height: 5px"></td>
				</tr>
			</table>
			<s:if test="allRoles.isEmpty() == true">没有任何角色!</s:if >
			<s:else>
				<table>
					<thead>
						<tr>
							<td colspan="10" style="height: 5px"></td>
						</tr>
						<tr>
							<td colspan="10" class="tdHeader">角色管理:</td>
						</tr>
						<tr>
							<td colspan="10" style="height: 5px"></td>
						</tr>
						<tr>
							<td class="tdListHeader">序号</td>
							<td class="tdListHeader">ID</td>
							<td class="tdListHeader">角色名称</td>
							<td class="tdListHeader">修改</td>
							<td class="tdListHeader">删除</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="allRoles" status="st">
							<s:set var="roleId" value="id" />
							<tr>
								<td><center><s:property value="#st.count" /></center></td>
								<td><center><s:property value="id" /></center></td>
								<td style="text-align: left;"><center><s:property value="roleName" /></center></td>
								<td><center><s:a action="RoleAction_editRole?roleId=%{#roleId}" cssClass="aList">修改</s:a></center></td>
								<td><center><s:a action="RoleAction_deleteRole?roleId=%{#roleId}" cssClass="aList">删除</s:a></center></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:else>
		</div>
	</body>
</html>