<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>������ - �򵥣����õ����ߵ���ϵͳ</title>
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
		<h4 class="text-center text-muted">�򵥣����õ����ߵ���ϵͳ</h4>
	</div>
	<div class="login-layout">
		<div class="stage">
			<h3 class="text-center">ע��</h3>			
			<form name="form1" action="RegAction_doReg" method="post">				
				<!-- ����ע��email -->
				<div class="form-group">
					<div class="input-group">
						<input type="email" name="email" maxlength="50" id="email" value="<s:property value='email'/>" class="form-control" placeholder="������ע��Email" require="require"/>
						<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
					</div>					
					<s:if test="%{fieldError==2}">
						<p class="text-danger">&nbsp;�����Ѿ���ע��</p>
					</s:if>
					<s:else>
					 	<p class="text-danger" id="errEmail">&nbsp;</p>
					</s:else>	
				</div>				
				<!-- �������� -->
				<div class="form-group">
					<div class="input-group">
						<input type="password" name="password" id="password" value="<s:property value='password'/>" class="form-control" placeholder="����������" require="require"/>					
						<span class="input-group-addon"><i class="fa fa-lock fa-lg"></i></span>
					</div>
			   		<p class="text-danger" id="errPassword">&nbsp;</p>
				</div>				
				<!-- ȷ������ -->
				<div class="form-group">
					<div class="input-group">
						<input type="password" name="confirmPassword" value="<s:property value='confirmPassword1'/>" id="confirmPassword" class="form-control" placeholder="������һ������" require="require"/>				
						<span class="input-group-addon"><i class="fa fa-lock fa-lg"></i></span>
					</div>
					<p class="text-danger" id="errConfirmPassword">&nbsp;</p>				
				</div>
				<!-- ��֤��-->
				<input id="session" type ="hidden" value="<s:property value="#random"/>"/>
				<div class="form-inline row">
					<div class="form-group">
						<div class="col-md-7">
							<div class="input-group">
								<s:textfield class="form-control" type="text" name="captcha" id="captcha" maxlength="10" placeholder="��������֤��" />								
								<span class="input-group-addon"><i class="fa fa-shield fa-lg"></i></span>
							</div>
							<!-- ��֤�������ʾ -->
 							<s:if test="%{fieldError==1}">
								<p class="text-danger">&nbsp;��֤�����</p>
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
				<input type="submit" value="ע��" class="btn btn-success btn-block btn-lg"  onclick="return checkForm()"/>				
				<p class="text-right">
					<s:a action="LoginAction_toLoginPage" namespace="/">���ϵ�½</s:a>
				</p>
			</form>
		</div>
	</div>
</div>
</body>
</html>



 
