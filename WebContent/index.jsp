<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>index</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme-dcp.css" />
<link rel="stylesheet" type="text/css" href="css/index/site-dcp.css" />
<link rel="stylesheet" type="text/css" href="css/index/site-globe.css" />    
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
	/* 轮播广告 */	
	.carousel {
		height: 432px;
	}
	.carousel .item {
		height: 432px;
		background-color: #000;
	}
	.carousel .item img {
		width: 100%;
	}
	.carousel-caption {
		z-index: 10;
	}
	.carousel-caption p {
		margin-bottom: 20px;
		font-size: 20px;
		line-height: 1.8;
	}
	#summary-container .col-md-4 {
		text-align: center;
	}
</style>
</head>
<body>
	<header class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/surveypark/index.jsp"><img src="img/logo.gif"></a>
        </div>
            <nav class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right nav-login">
                    <li><s:a action="LoginAction_toLoginPage" namespace="/">登录</s:a></li>
                    <li><s:a class="btn btn-green" action="RegAction_toRegPage" namespace="/">注册</s:a></li>
                </ul>
            </nav>
        </div>
    </header>
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<img src="img/banner_01.jpg" alt="1 slide">
			</div>
			<div class="item">
				<img src="img/banner_02.jpg" alt="2 slide">
			</div>
			<div class="item">
				<img src="img/banner_03.jpg" alt="3 slide">
					<div class="container">
						<div class="carousel-caption">
							<h1>不同的数据收集渠道，独立统计</h1>
							<p><h3>
                                	独立的访问地址<br/>
                     				独立的控制填写规则<br/>
   									单独查看渠道数据和汇总数据
                			</h3></p>
						</div>
					</div>
				<h1></h1>
		
			</div>
		</div>
		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
    <footer>
        <div class="copyright">随时联系我们 
			<p>
				<a href="mailto:chenkaic4233@163.com">chenkaic4233@163.com</a>    电话：025 88888888  QQ：747272975
            </p>
         </div>
    </footer>
</body>

</html>