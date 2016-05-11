<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
</head>
<body>
	<h1 align="center">入出库查询</h1>
	<form:form action="query" method="get">
	<div align="center"  >
		物料编码：<input type="text" name="item.code" />
		物料名称：<input type="text" name="item.name" />
		物料型号：<input type="text" name="item.model" />
		部门：<input type="text" name="department" <c:if test="${loginuser.usertype eq 1}" > value="${loginuser.department}"   readonly</c:if> />
		<br/><input type="submit" value="查询"/> 
		<br/>
		<c:if test="${loginuser.usertype eq 1}">		<!-- 管理员只能看，不能增改入出库 -->
			<a href="add">增加入出库信息</a>
		</c:if>
		<a href="/">返回</a>
		</div>
	</form:form>
	<c:if test="${not empty accList }">
				<table  width=" 100%" border="1">
					<caption>查询结果</caption>
					<tr>
						<td align="center">序号</td>
						<td align="center">单号</td>
						<td align="center">入出库</td>
						<td align="center">物料编码</td>
						<td align="center">物料名称</td>
						<td align="center">型号</td>
						<td align="center">计量单位</td>
						<td align="center">数量</td>
						<td align="center">部门</td>
						<td align="center">操作</td>
						<c:forEach items="${accList}" var="it" varStatus="status">
							<tr>
								<td align="center">${status.index + 1}</td>
								<td align="center">${it.id}</td>
								<td align="center">
									<c:if test="${it.type==1}">入库</c:if>
									<c:if test="${it.type==0}">出库</c:if>
								</td>
								<td align="center">${it.item.code} </td>
								<td align="center">${it.item.name} </td>
								<td align="center">${it.item.model} </td>
								<td align="center">${it.item.unit}</td>
								<td align="center">${it.number}</td>
								<td align="center">${it.department}</td>
								<td align="center">
									<c:if test="${loginuser.usertype eq 1}">		<!-- 管理员只能看，不能增改入出库 -->
										<a href="${it.id}/update">修改</a> <a href="${it.id}/delete">删除</a>
									</c:if>
								</td>
						</c:forEach>
				</table>
			</c:if>
</body>
</html>