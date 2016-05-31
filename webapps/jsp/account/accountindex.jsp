<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
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
		费用类别：
				<select name="costType">
					<option value="">请选择</option>
					<option value="承包费">承包费</option>
					<option value="掘进费">掘进费</option>
					<option value="生产费">生产费</option>
					<option value="安全费">安全费</option>
				</select>
		<br/><input type="submit" value="查询"/> 
		<br/>
		<c:if test="${loginuser.usertype ne 2}">		<!-- 领导只能看，不能增改入出库 -->
			<a href="add">增加入出库信息</a>
		</c:if>
		</div>
	</form:form>
	<c:if test="${not empty accList }">
		<center>
				<table  width=" 90%" border="1">
					<caption>查询结果</caption>
					<tr>
						<td align="center">序号</td>
						<td align="center">入出库</td>
						<td align="center">物料编码</td>
						<td align="center">物料名称</td>
						<td align="center">型号</td>
						<td align="center">计量单位</td>
						<td align="center">数量</td>
						<td align="center">费用类型</td>
						<td align="center">日期</td>
						<td align="center">部门</td>
						<td align="center">操作</td>
						<c:forEach items="${accList}" var="it" varStatus="status">
							<tr>
								<td align="center">${status.index + 1}</td>
								<td align="center">
									<c:if test="${it.type==1}">入库</c:if>
									<c:if test="${it.type==0}">出库</c:if>
								</td>
								<td align="center">${it.item.code} </td>
								<td align="center">${it.item.name} </td>
								<td align="center">${it.item.model} </td>
								<td align="center">${it.item.unit}</td>
								<td align="center">${it.number}</td>
								<td align="center">${it.costType}</td>
								<td align="center"><fmt:formatDate value="${it.optTime}" pattern="yyyy-MM-dd"/> </td>
								<td align="center">${it.department}</td>
								<td align="center">
									<c:if test="${loginuser.usertype ne 2}">		<!-- 领导只能看，不能增改入出库 -->
										<a href="${it.id}/update">修改</a> <a href="${it.id}/delete" onClick="return confirm('确定要删除序号为(${status.index + 1})的记录吗？');">删除</a>
									</c:if>
								</td>
						</c:forEach>
				</table>
		</center>
	</c:if>
</body>
</html>