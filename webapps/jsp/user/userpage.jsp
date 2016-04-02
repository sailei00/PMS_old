<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:if test="${not empty usercode}">修改用户信息</c:if><c:if test="${empty usercode }">新增用户</c:if></title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
/*	border: 0px solid red;*/
}
</style>
</head>
<body>
<form:form method="post"  modelAttribute="user">
<center>
	<c:if test="${not empty usercode}">修改用户信息</c:if><c:if test="${empty usercode }">新增用户</c:if>
	<c:if test="${empty usercode }"><form:hidden path="password"   value="111111"/></c:if>
	<c:if test="${not empty usercode }"><form:hidden path="password"   /></c:if>
	<table  border="1">
		<tr>
			<td>用户编码：</td>
			<td>
				<form:input path="usercode"/>
				<form:errors path="usercode" cssStyle="color:red"></form:errors>
			</td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td>
				<form:input path="username" /> 
				<form:errors path="username" cssStyle="color:red"></form:errors>
			</td>
		</tr>
		<%-- <tr>
			<td>密码：</td>
			<td>
				<form:password path="password"/>
				<form:errors path="password" cssStyle = "color:red"></form:errors>
			</td>
		</tr> --%>
		<tr>
			<td>归属部门：</td>
			<td>
				<form:input path="department"/>
				<form:errors path="department" cssStyle = "color:red"></form:errors>
			</td>
		</tr>
		<tr>
			<td>用户类型：</td>
			<td>
				<form:select path="usertype" >
					<form:option value="1" label="普通用户"  /> 
					<form:option value="0" label="管理员" />
				</form:select>
				<form:errors path="usertype" cssStyle="color:red"></form:errors>
			</td>
		</tr>
		<tr>
			<td>用户状态：</td>
			<td>
				<form:select path="status" >
					<form:option value="0" label="停用" />
					<form:option value="1" label="启用"  /> 
				</form:select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="保存" />	<a href="/user/index">返回</a></td>
		</tr>
	</table>
</center>
</form:form>
</body>
</html>