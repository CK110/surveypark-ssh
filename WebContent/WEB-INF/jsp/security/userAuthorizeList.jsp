<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户权限管理</title>
<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<s:include value="../manager/manager_header.jsp" />
<div class= "container">
	<div class="surveyhead">
		<div class="row">
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4"></div>			    
					<div class="col-md-5"></div>
		 		</div>			
			</div>
		</div>
	</div>			

	<hr>
	<s:if test="allUsers.isEmpty() == true">目前没有任何用户!</s:if >
	<s:else>
		<table class="table table-hover table-bordered table-condensed ">
			<thead>
				<tr>
					<td class="tdListHeader">序号</td>
					<td class="tdListHeader">ID</td>
					<td class="tdListHeader">email</td>
					<td class="tdListHeader">修改授权</td>
					<td class="tdListHeader">清除授权</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="allUsers" status="st">
					<s:set var="userId" value="id" />
					<tr>
						<td><s:property value="#st.count" /></td>
						<td><s:property value="id" /></td>
						<td style="text-align: left;"><center><s:property value="email" /></center></td>
						<td><center><s:a action="UserAuthorizeAction_editAuthorize?userId=%{#userId}" cssClass="aList">修改授权</s:a></center></td>
						<td><center><s:a action="UserAuthorizeAction_clearAuthorize?userId=%{#userId}" cssClass="aList">清除授权</s:a></center></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:else>
</div>	
</body>
</html>