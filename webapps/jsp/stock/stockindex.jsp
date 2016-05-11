<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存查询</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
</head>
<body>
	<h1 align="center">库存查询</h1>
	<form:form action="checkstock" method="get">
		<div align="center">
			物料编码：<input type="text" name="itemCode" /> 
			物料名称：<input type="text" name="itemName" /> 
			物料型号：<input type="text" name="model" />
			部门：<input type="text" name="department"  <c:if test="${loginuser.usertype eq 1}" > value="${loginuser.department}"   readonly</c:if> />
			 <br />
			<input type="submit" value="查询" /> <a href="/">返回</a>
		</div>
	</form:form>
	
	<c:if test="${not empty stockList}">
		<table align="center" border=1>
			<tr><td align="center">部门</td><td align="center">物料编码</td><td align="center">物料名称</td><td align="center">型号</td><td align="center">单位</td><td align="center">库存</td></tr>
			<c:forEach items="${stockList}" var="li">
				<tr style="background-color:${li.reason}">
					<td align="center">${li.department}</td>
					<td align="center">${li.item.code}</td>
					<td align="center">${li.item.name}</td>
					<td align="center">${li.item.model}</td>
					<td align="center">${li.item.unit}</td>
					<td align="center">${li.number}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>