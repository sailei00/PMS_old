<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
</head>
<body>
<form:form method="post"  modelAttribute="user">
<center>
	修改密码
	<table  border="1">
		<tr>
			<td>用户名：</td>
			<td>
				<form:input path="usercode" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td>
				<form:input path="username" readonly="true"/>
			</td>
		</tr>
		 <tr>
			<td>原密码：</td>
			<td>
				<form:password path="oldpassword"  />
			</td>
		</tr>
		 <tr>
			<td>新密码：</td>
			<td>
				<form:password path="password"  />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="保存" />	<form:errors path="*" cssStyle="color:red;"></form:errors><font>${result}</font>
			</td>
		</tr>
	</table>
</center>
</form:form>
</body>
</html>