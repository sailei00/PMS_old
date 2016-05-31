<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
<style type="text/css">

div#navdiv {
	width: 100%;
	height: 40px;
	/* 设置IE浏览器中菜单栏的底色渐变  */
	filter: progid:DXImageTransform.Microsoft.Gradient(startColorStr='#086ed5',	endColorStr='#0f5dbf', gradientType='0');
	background-image: linear-gradient(rgb(8, 110, 213), rgb(5, 93, 181));
	background-position-x: initial;
	background-position-y: initial;
	background-size: initial;
	background-repeat-x: initial;
	background-repeat-y: initial;
	background-attachment: initial;
	background-origin: initial;
	background-clip: initial;
	background-color: initial;
}

ul#nav {
	padding: 0px 10px;
	float: left;
	width: 60%;
	height: 40px;
	margin: 0;
}

div#logininfo {
	color: white;
	float: right;
	padding: 10px 10px;
}

a {
	text-decoration: none;
	color: white;
}

div#logininfo  a:hover {
	background: #0095BB
}

ul#nav li {
	display: inline;
	height: 40px;
}

ul#nav li a {
	display: inline-block;
	padding: 0px 10px;
	height: 40px;
	line-height: 40px;
	color: #FFF;
	font-family: "\5FAE\8F6F\96C5\9ED1";
	font-size: 16px
}

/*设置鼠标滑过或悬停时变化的背景颜色*/
ul#nav li a:hover {
	background: #0095BB
}
</style>
</head>
<body>
	<div id="navdiv">
		<ul id="nav">
			<!-- <li><a href="/user/index" target="_content">用户管理页面</a></li>   -->
			<li><a href="/item/index" target="_content">物料编码管理</a></li>
			<li><a href="/itemplan/index" target="_content">每月计划</a></li>
			<li><a href="/account/index" target="_content">出入库信息</a></li>
			<li><a href="/stock/index" target="_content">查询库存</a></li>
			<li><a href="/report/index" target="_content">使用情况月报</a></li>
		</ul>
		<div id="logininfo">
			<span style="font-size: 15px;">${loginuser.username} | ${loginuser.department} |</span>
			<span style="font-size: 15px;"><a href="/user/changepassword"
				target="_content">修改密码</a>·<a href="/logout" target="_content">退出登录</a></span>

		</div>
	</div>
</body>
</html>