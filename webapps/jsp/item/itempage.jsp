<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物料编码</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
</head>
<body>
<center>
	<form:form method="post" modelAttribute="item">
		<table border="1">
			<caption>物料编码</caption>
			<tr>
				<td>编码：</td>
				<td>
					<%-- <form:input path="code"   readonly="${not empty item.code}" /> --%>
					<input type="text" name="code" value="${item.code}" /><form:errors path="code"></form:errors> 
				</td>
			</tr>
			<tr>
				<td>名称：</td>
				<td><input type="text" name="name" value="${item.name}" />
				<form:errors path="name"></form:errors></td>
			</tr>
			<tr>
				<td>型号：</td>
				<td><input type="text" name="model" value="${item.model}" /></td>
			</tr>
			<tr>
				<td>单价：</td>
				<td><input type="text" name="price" value="${item.price}" /></td>
			</tr>
			<tr>
				<td>计量单位：</td>
				<td><input type="text" name="unit" value="${item.unit}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="保存" /><a href="/item/index">返回</a>
				</td>
			</tr>


		</table>
	</form:form>
</center>
</body>
</html>