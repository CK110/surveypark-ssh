<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>调查派 - 简单，好用的在线调查系统</title>
<script type="text/javascript" src="js/boot.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme-dcp.css" />
<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/fontawesome/4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript" src="js/user/user.js"></script>
</head>
<body>
<div class="container">
	<div class="header">
		<h1 class="text-center"><a href="/surveypark/index.jsp"><img src="img/login_logo.gif" /></a></h1>
		<h4 class="text-center text-muted">简单，好用的在线调查系统</h4>
	</div>
	<div class="login-layout">
		<div class="stage">
			<h3 class="text-center">注册</h3>			
			<form name="form1" action="RegAction_doReg" method="post">				
				<!-- 输入注册email -->
				<div class="form-group">
					<div class="input-group">
						<input type="email" name="email" maxlength="50" id="email" value="<s:property value='email'/>" class="form-control" placeholder="请输入注册Email" require="require"/>
						<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
					</div>					
					<s:if test="%{fieldError==2}">
						<p class="text-danger">&nbsp;邮箱已经被注册</p>
					</s:if>
					<s:else>
					 	<p class="text-danger" id="errEmail">&nbsp;</p>
					</s:else>	
				</div>				
				<!-- 输入密码 -->
				<div class="form-group">
					<div class="input-group">
						<input type="password" name="password" id="password" value="<s:property value='password'/>" class="form-control" placeholder="请输入密码" require="require"/>					
						<span class="input-group-addon"><i class="fa fa-lock fa-lg"></i></span>
					</div>
			   		<p class="text-danger" id="errPassword">&nbsp;</p>
				</div>				
				<!-- 确认密码 -->
				<div class="form-group">
					<div class="input-group">
						<input type="password" name="confirmPassword" value="<s:property value='confirmPassword1'/>" id="confirmPassword" class="form-control" placeholder="再输入一次密码" require="require"/>				
						<span class="input-group-addon"><i class="fa fa-lock fa-lg"></i></span>
					</div>
					<p class="text-danger" id="errConfirmPassword">&nbsp;</p>				
				</div>
				<!-- 验证码-->
				<input id="session" type ="hidden" value="<s:property value="#random"/>"/>
				<div class="form-inline row">
					<div class="form-group">
						<div class="col-md-7">
							<div class="input-group">
								<s:textfield class="form-control" type="text" name="captcha" id="captcha" maxlength="10" placeholder="请输入验证码" />								
								<span class="input-group-addon"><i class="fa fa-shield fa-lg"></i></span>
							</div>
							<!-- 验证码错误显示 -->
 							<s:if test="%{fieldError==1}">
								<p class="text-danger">&nbsp;验证码错误</p>
							</s:if>
							<s:else>
					 			<p class="text-danger" id="errCaptcha">&nbsp;</p>
							</s:else>
						</div>						
						<div class="col-md-5 captcha text-center">
							<img src="rand.action" onclick="changeValidateCode(this)"/>
						</div>
					</div>
				</div> 				
				<input type="submit" value="注册" class="btn btn-success btn-block btn-lg"  onclick="return checkForm()"/>				
				<p class="text-right">
					<s:a action="LoginAction_toLoginPage" namespace="/">马上登陆</s:a>
				</p>
			</form>
		</div>
	</div>
</div>
</body>
</html>



 
