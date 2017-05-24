<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>参与调查</title>
<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("a[href*=delete]").click(function(){
			return confirm("删除该项?");
		});
	});
	
	$(function(){
		$("a[href*=send]").click(function(){
			return confirm("确认发送邮件?");
		});
	});
</script>
</head>
<body>
<s:include value="../manager/manager_header.jsp" />
<div class="container">
	<div class="surveyhead">
		<div class="row">
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4"></div>
				   	<div class="col-md-5">
						<s:form action="ManagerAction_findAllSurveysByTitle" namespace="/" method="post" validate="true" >
							<div class="input-group">
								<input type="text" name="survey_name" <s:if test="%{#survey_name!=null}">value="<s:property value="#survey_name"/>"</s:if> class="form-control" placeholder="Search for...">
		      					 <span class="input-group-btn">
		      					 <s:submit class="btn" value="search"/>
		      					 </span>
		   					</div>
		   				</s:form>
	  				</div>
	  			</div>
			</div>
		</div>
	</div>
	<hr>	
	<s:if test="surveys.isEmpty()">目前没有公开的调查!</s:if>
	<s:else>
		<table class="table table-hover table-bordered ">
			<thead>
				<tr bgcolor="#f8f8f8">
				<th width="200">问卷标题</th>
				<th width="70">创建人/email</th>
				<th width="120">创建时间</th>
				<th width="70">状态</th>
				<th width="70">查看</th>
				<th width="120">邮件提醒</th>		
				<th width="70">删除</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="surveys">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="title" />
						</td>
						<td>
							<s:iterator value="user" var="obj">
								<s:property value="#obj.email" />
							</s:iterator>
						</td>
						<td><s:date name="createTime" format="MM/dd/yy HH:mm" />
						</td>
						<td>
							<s:if test="closed">关闭</s:if><s:else>开放</s:else>
						</td>
						<td>
							<s:a action="ManagerAction_allSurvey?sid=%{#sId}" namespace="/" cssClass="aList">查看</s:a>
						</td>
						<td>
							<s:a action="ManagerAction_sendHtmlMessage?sid=%{#sId}" namespace="/" cssClass="aList">邮件警告</s:a>
						</td>
						<td>
							<s:a action="SurveyAction_deleteSurvey?sid=%{#sId}" namespace="/" cssClass="aList">删除</s:a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:else>
	
	<s:set var="totalPage" value="totalPage" />
	<s:if test="%{#totalPage>1}">
		<nav>
		  <ul class="pagination">
		  <s:if test="%{pageNow > 1}">
			    <li>
			      <s:a action="ManagerAction_findAllSurveys" namespace="/" aria-label="Previous">
						<s:set var="pageNow" value="pageNow" />
					 	<s:param name="pageNow"> <s:property value="#pageNow-1"/></s:param> 
			        <span aria-hidden="true">&laquo;</span>
			      </s:a>
			    </li>
		  </s:if>
		    <s:iterator value="pageList" var="pageNum">
				<li><s:a action="ManagerAction_findAllSurveys" namespace="/">
				 		<s:param name="pageNow"> <s:property value="#pageNum"/></s:param>
						<s:property value="#pageNum" />
				</s:a></li>
			</s:iterator>
			
			<s:if test="%{totalPage > pageNow}">	
			    <li>
			      <s:a action="ManagerAction_findAllSurveys" namespace="/" aria-label="Next">
			      			<s:set var="pageNow" value="pageNow" />
					 		<s:param name="pageNow"> <s:property value="#pageNow+1"/></s:param> 
			        <span aria-hidden="true">&raquo;</span>
			      </s:a>
			    </li>
		    </s:if>
		  </ul>
		</nav>
	</s:if>

</div>
</body>
</html>