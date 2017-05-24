<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>	
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<img src="img/logo.gif">
		</div>
		<nav class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><s:a action="LoginAction_toMain" namespace="/">首页</s:a>
				</li>
				<li><s:a action="SurveyAction_mySurveys" namespace="/">我的问卷</s:a>
				</li>
				<li><s:a namespace="/" action="EngageSurveyAction_findAllAvailableSurveys">参与调查</s:a>
				</li>
			</ul>
	
			<ul class="nav navbar-nav navbar-right nav-login">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						<s:if test="#session['user'] != null">
							<s:property value="#session['user'].email" />
						</s:if><span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">个人信息</a>
						</li>
						<li><s:a namespace="/" action="LoginAction_logOut">注销</s:a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
