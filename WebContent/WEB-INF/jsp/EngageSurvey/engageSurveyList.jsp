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
</head>
<body>
<s:include value="../common/header.jsp" />
<div class="container">
	<div class="surveyhead">
		<div class="row">
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4"></div>
				   	<div class="col-md-5">
						<s:form action="EngageSurveyAction_findAllAvailableSurveysByTitle" namespace="/" method="post" validate="true" >
							<div class="input-group">
								<input type="text" name="survey_name" class="form-control" placeholder="Search for...">
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
		<s:set var="cells" value="5" />
		<table>
			<tr>
				<td class="tdListHeader" colspan='<s:property value="#cells"/>'>请选择要参与的调查</td>
			</tr>
			<s:iterator var="i" begin="0" end="%{surveys.size -1}" step="#cells">
				<s:set var="sId" value="id" />
				<tr>
					<s:iterator var="j" begin="0" end="%{#cells-1}" step="1">
						<s:set var="idx" value="#i + #j" />
						<td width='<s:property value="100/#cells" />%'>
							<s:if test="#idx < surveys.size">
								<s:a action="EngageSurveyAction_entry?sid=%{surveys[#idx].id}" cssClass="aList" namespace="/">
								<center><img src='<s:property value="getImageUrl(surveys[#idx].logoPhotoPath)" />' 
										 alt="<s:property value='surveys[#idx].title' />"
										 width="180px"
										 height="100px">
									<br>
									<s:set var="pageNow" value="pageNow" />
									<s:property value="#idx + 1+ (#pageNow-1)*5" />.<s:property value="surveys[#idx].title" />
									</center>	
								</s:a>
							</s:if>
						</td>
					</s:iterator>
				</tr>
			</s:iterator>
		</table>
	</s:else>
	
	<s:set var="totalPage" value="totalPage" />
	<s:if test="%{#totalPage>1}">
		<nav>
		  <ul class="pagination">
		  <s:if test="%{pageNow > 1}">
			    <li>
			      <s:a action="EngageSurveyAction_findAllAvailableSurveys" namespace="/" aria-label="Previous">
						<s:set var="pageNow" value="pageNow" />
					 	<s:param name="pageNow"> <s:property value="#pageNow-1"/></s:param> 
			        <span aria-hidden="true">&laquo;</span>
			      </s:a>
			    </li>
		  </s:if>
		    <s:iterator value="pageList" var="pageNum">
				<li><s:a action="EngageSurveyAction_findAllAvailableSurveys" namespace="/">
				 		<s:param name="pageNow"> <s:property value="#pageNum"/></s:param>
						<s:property value="#pageNum" />
				</s:a></li>
			</s:iterator>
			
			<s:if test="%{totalPage > pageNow}">	
			    <li>
			      <s:a action="EngageSurveyAction_findAllAvailableSurveys" namespace="/" aria-label="Next">
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