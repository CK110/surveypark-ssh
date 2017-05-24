<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的调查</title>
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
</script>
</head>
<body>
<s:include value="../common/header.jsp" />
<div class="container">
	<div class="surveyhead">
		<div class="row">
 				<div class="col-md-6">
 					<s:a action="SurveyAction_newSurvey" namespace="/" class="btn btn_ck btn-info" role="button">+新建调查</s:a>
 				</div>
 				<div class="col-md-6"> 	
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<s:form action="SurveyAction_searchSurveyByStatus" method="post">
							<select class="form-control" name="survey_status" onchange="this.form.submit();" >
		  						<s:set var="survey_status" value="survey_status" />

		  						<option value="3" <s:if test="%{#survey_status==null}">selected="selected"</s:if>>全部问卷</option>
		  							
		  						<option value="0" <s:if test="%{#survey_status==0}">selected="selected"</s:if>>未发布问卷</option>
		  						
		  						<option value="1" <s:if test="%{#survey_status==1}">selected="selected"</s:if>>已发布问卷</option>
							</select>
						</s:form>	
					</div>			    
					<div class="col-md-5">
						<s:form action="SurveyAction_searchSurveyByTitle" namespace="/" method="post" validate="true" >
							<div class="input-group">
								<s:set var="survey_name" value="survey_name" />
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
	<s:if test="mySurveys.isEmpty() == true"><center>没有此项调查问卷！</center></s:if >
	<s:else>
		<table class="table table-hover table-bordered ">
			<thead>
				<tr bgcolor="#f8f8f8">
				<th width="200">问卷标题</th>
				<th width="120">创建时间</th>
				<th width="70">状态</th>
				<th width="70">设计</th>
				<th width="70">收集</th>
				<th width="70">分析</th>
				<th width="120">打开/关闭</th>
				<th width="70">清除</th>
				<th width="70">删除</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="mySurveys">
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="title" />
						</td>
						<td><s:date name="createTime" format="MM/dd/yy HH:mm" />
						</td>
						<td>
							<s:if test="closed">关闭</s:if>
							<s:else>开放</s:else>
						</td>
						<td>
							<s:a action="SurveyAction_designSurvey?sid=%{#sId}" namespace="/" cssClass="aList">设计</s:a>
						</td>
						<td>
							<s:a action="CollectionSurveyAction?sid=%{#sId}" namespace="/" cssClass="aList">收集信息</s:a>
						</td>
						<td>
							<s:a action="SurveyAction_analyzeSurvey?sid=%{#sId}" namespace="/" cssClass="aList">分析</s:a>
						</td>
						<td>
							<s:a action="SurveyAction_toggleStatus?sid=%{#sId}" namespace="/" cssClass="aList">打开/关闭</s:a>
						</td>
						<td>
							<s:a action="SurveyAction_clearAnswers?sid=%{#sId}" namespace="/" cssClass="aList">清除调查</s:a>
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
		<s:set var="survey_name" value="survey_name" />
	  	<ul class="pagination">
			<s:if test="%{pageNow > 1}">
		    <li>
		    	<s:a action="SurveyAction_searchSurveyByTitle" namespace="/" aria-label="Previous">
					<s:set var="pageNow" value="pageNow" />
				 	<s:param name="pageNow"> <s:property value="#pageNow-1"/></s:param>
				 	<s:param name="survey_name"> <s:property value="#survey_name"/></s:param>   
		        	<span aria-hidden="true">&laquo;</span>
		    	</s:a>
		    </li>
	  		</s:if>
	    	<s:iterator value="pageList" var="pageNum">
				<li>
					<s:a action="SurveyAction_searchSurveyByTitle" namespace="/">
				 		<s:param name="pageNow"> <s:property value="#pageNum"/></s:param>
				 		<s:param name="survey_name"> <s:property value="#survey_name"/></s:param>  
						<s:property value="#pageNum" />
					</s:a>
				</li>
			</s:iterator>	
			<s:if test="%{totalPage > pageNow}">	
		    <li>
				<s:a action="SurveyAction_searchSurveyByTitle" namespace="/" aria-label="Next">
					<s:set var="pageNow" value="pageNow" />
					<s:param name="pageNow"><s:property value="#pageNow+1"/></s:param> 
					<s:param name="survey_name"><s:property value="#survey_name"/></s:param> 
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