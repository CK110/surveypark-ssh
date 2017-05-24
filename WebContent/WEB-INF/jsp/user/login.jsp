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
		<h1 class="text-center">
			<a href="/surveypark/index.jsp"><img src="img/login_logo.gif" /></a>
		</h1>
		<h4 class="text-center text-muted">�򵥣����õ����ߵ���ϵͳ</h4>
	</div>
	<div class="login-layout">
		<div class="stage">
			<h3 class="text-center">��¼</h3>
			<form name="form1" action="LoginAction_doLogin" method="post" >
				<div class="form-group">
					<div class="input-group">
					<input type="email" name="email" maxlength="50" id="email"  value="<s:property value='email'/>" class="form-control" placeholder="������ע��Email" require="require"/>
					<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
					</div>				
					<s:if test="%{fieldError==1}">
						<p class="text-danger">&nbsp;��Ч��email</p>
					</s:if>
					<s:else>
					 	<p class="text-danger" id="errEmail">&nbsp;</p>
					</s:else>				
				</div>
				<div class="form-group">
					<div class="input-group">
						<input type="password" name="password" id="password" value="<s:property value='password'/>" class="form-control" placeholder="����������" require="require"/>					
						<span class="input-group-addon"><i class="fa fa-lock fa-lg"></i></span>
					</div>				
					<p class="text-danger" id="errPassword">&nbsp;</p>
				</div>				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<div class="checkbox">
								<label class="checkbox-inline"> <input type="checkbox" name="remember" />�´��Զ���¼</label>
							</div>
						</div>
					</div>
					<div class="col-md-6 text-right">
						<a class="btn btn-link" href="#">��������</a>
					</div>
				</div>				
				<input type="submit" class="btn btn-success btn-block btn-lg" value="��½" onclick="return checkForm2()"/>			
				<s:a action="RegAction_toRegPage" namespace="/" class="pull-right">����ע��</s:a>
			</form>
		</div>
	</div>
</div>
</body>
</html>