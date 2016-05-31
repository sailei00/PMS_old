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
			部门： <c:if test="${loginuser.usertype eq 1}" ><input type="text" name="department"  value="${loginuser.department}"   readonly/></c:if>
					<c:if test="${loginuser.usertype ne 1}">
						<select name="department">
							<option value="">所有部门</option>
							<option value="机修厂">机修厂</option>
							<option value="机电机运队">机电机运队</option>
							<option value="综掘一队">综掘一队</option>
							<option value="综掘二队">综掘二队</option>
							<option value="综采队">综采队</option>
							<option value="通风科">通风科</option>
							<option value="生产技术科">生产技术科</option>
							<option value="机电科">机电科</option>
						</select>   
					</c:if>
			 <br />
			<input type="submit" value="查询" /> 
		</div>
	</form:form>
	<center>
		<c:if test="${not empty stockList}">
			<table align="center" border=1>
				<tr><td align="center">序号</td><td align="center">部门</td><td align="center">物料编码</td><td align="center">物料名称</td><td align="center">型号</td><td align="center">单位</td><td align="center">库存</td></tr>
				<c:forEach items="${stockList}" var="li" varStatus="status">
					<tr style="background-color:${li.color};color:${li.color eq 'red'?'yellow':'black'}">
						<td align="center">${status.index + 1}</td>
						<td align="center">${li.department}</td>
						<td align="center">${li.itemcode}</td>
						<td align="center">${li.itemname}</td>
						<td align="center">${li.itemmodel}</td>
						<td align="center">${li.itemunit}</td>
						<td align="center">${li.number}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</center>
</body>
</html>