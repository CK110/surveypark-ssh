<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日志管理</title>
<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
<script src='<s:url value="js/jquery-1.11.1.min.js" />'></script>
<script src='<s:url value="js/bootstrap.min.js" />'></script>
</head>
<body>
<s:include value="../manager/manager_header.jsp" />
<div class="container">
<div class="row">
	<div class="col-md-6"></div>
	<div class="col-md-6">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-4"></div>			    
			<div class="col-md-5">
				<s:form namespace="/" action="LogAction_findNearestLogs">
					<div class="input-group">
						<s:textfield type="text" name="monthNum" class="form-control"/>
		    			<span class="input-group-btn">
		    				<s:submit class="btn" value="search"/>
		    			</span>
		 			</div>
		 		</s:form>
			</div>
		</div>
	</div>		
</div>
<hr>	
<s:if test="logs.isEmpty() == true">没有日志!</s:if >
<s:else>
<table class="table-hover table-bordered ">
	<thead>
		<tr>
			<td class="tdListHeader">操作人</td>
			<td class="tdListHeader">操作名称</td>
			<td class="tdListHeader">参数</td>
			<td class="tdListHeader">操作结果</td>
			<td class="tdListHeader">消息</td>
			<td class="tdListHeader">时间</td>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="logs" status="st">
		<tr>
		<td><s:property value="operator" /></td>
		<td><s:property value="operName" /></td>
		<td>
			<span title='<s:property value="operParams"/>'><s:property value="operParams"/></span>
		</td>
		<td>
			<span title='<s:property value="operResult"/>'><s:property value="operResult"/></span>
		</td>
		<td>
			<span title='<s:property value="resultMsg"/>'><s:property value="operResult"/></span>
		</td>
		<td><s:date name="operTime" format="yy/MM/dd hh:mm:ss"/></td>
		</tr>
		</s:iterator>
	</tbody>
</table>
</s:else>
</div>	
</body>
</html>