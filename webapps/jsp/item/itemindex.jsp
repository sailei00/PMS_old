<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物料编码管理</title>
</head>
<body> 
	<center>物料编码管理</center>
	<br>
	<form name="fm" action="/item/query" method="GET">
		<center>
			<table border="1">
				<tr>
					<td>物料编码：<input type="text" name="code" />
					</td>
					<td>物料名称：<input type="text" name="name" />
					</td>
					<td>物料型号：<input type="text" name="model"  />
					</td>
					<td>
						计量单位：<input type="text" name="unit" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="查询"/>
						<a href="/item/add">新增</a>
						<a href="/">返回</a>
					</td>
				</tr>
			</table>
			<br/>
			<c:if test="${not empty itemList }">
				<table border="1">
					<caption>查询结果</caption>
					<tr>
						<td align="center">编码</td>
						<td align="center">名称</td>
						<td align="center">型号</td>
						<td align="center">价格</td>
						<td align="center">计量单位</td>
						<td align="center">库存</td>
						<td align="center">修改</td>
						<td align="center">删除</td>
						<c:forEach items="${itemList}" var="it" begin="0">
							<tr>
								<td><input type="text" name="code" disabled value="${it.code}" /></td>
								<td><input type="text" name="name" disabled value="${it.name}" /></td>
								<td><input type="text" name="model" disabled value="${it.model}" /></td>
								<td><input type="text" name="price" disabled value="${it.price}" /></td>
								<td><input type="text" name="unit" disabled value="${it.unit}" /></td>
								<td><input type="text" name="amount" disabled value="${it.amount}" /></td>
								<td><a href="${it.code}/update">修改</a></td>
								<td><a href="${it.code}/delete">删除</a></td>
						</c:forEach>
				</table>
			</c:if>
		</center>
	</form>
</body>
</html>