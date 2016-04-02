<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
/*	border: 0px solid red;*/
}
</style>
</head>
<body>
<form action="/login" method="post"  >
	用户名：<input type="text" name="usercode"/>
	密码：  <input type="password" name="password" />
	<input type="submit" value="登录" />
</form>
</body>
</html>