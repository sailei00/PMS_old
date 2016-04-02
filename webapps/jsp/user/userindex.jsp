<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
<center>用户管理</center>
<form:form action="query" method="get">
	<div align="center"  >
		用户编码：<input type="text" name="usercode" />
		用户名称：<input type="text" name="username" />
		归属部门：<input type="text" name="department" />
		<br/><input type="submit" value="查询"/> 
		<br/><a href="add">新增用户</a>	<a href="/">返回</a>
	</div>
</form:form>

	<br>
		<center>
			<br/>
			<c:if test="${not empty userList }">
				<table border="1">
					<caption>查询结果</caption>
					<tr>
						<td align="center">用户编码</td>
						<td align="center">用户名称</td>
						<td align="center">归属部门</td>
						<td align="center">用户类型</td>
						<td align="center">用户状态</td>
						<td align="center">操作</td>
						<c:forEach items="${userList}" var="it" begin="0">
							<tr>
								<td  align="center">${it.usercode}</td>
								<td  align="center">${it.username}</td>
								<td  align="center">${it.department}</td>
								<td  align="center">
									<c:if test="${it.usertype==0}">管理员</c:if>
									<c:if test="${it.usertype==1}">普通用户</c:if>
								</td>
								<td  align="center">
									<c:if test="${it.status==0}">停用</c:if>
									<c:if test="${it.status==1}">启用</c:if>
								</td>
								<td  align="center"><a href="${it.usercode}/update">修改</a> <a href="${it.usercode}/delete">删除</a></td>
						</c:forEach>
				</table>
			</c:if>
		</center>
</body>
</html>