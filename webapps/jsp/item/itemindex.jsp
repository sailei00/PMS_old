<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物料编码管理</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
</head>
<body> 
	<h1 align="center">物料编码管理</h1>
	<br>
	<form name="fm" action="/item/query" method="GET">
		<center>
			<table>
				<tr>
					<td>物料编码：<input type="text" name="code" />
					</td>
					<td>物料名称：<input type="text" name="name" />
					</td>
					<td>物料型号：<input type="text" name="model"  />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="查询"/>
							<a href="/item/add">新增</a>
						<c:if test="${loginuser.usertype eq 0}">		<!-- 只有管理员可以操作 物料编码    |   暂时放开新增权限 -->
						</c:if>
					</td>
				</tr>
			</table>
			<br/>
			
			<c:if test="${not empty itemList }">
				<table width=" 90%"  border="1">
					<caption>查询结果</caption>
					<tr>
						<td colspan="8" align="center">
						<pg:pager url="/item/query" items="${pageInfo.total}"  maxPageItems="${pageInfo.pageSize}"  export="currPageNo=pageNumber">
						<pg:param name="code"/>
						<pg:param name="name"/>
						<pg:param name="model"/>
						共查询到${pageInfo.total} 条数据，
							<pg:first>
								<a href="${pageUrl }"  >首页</a>         
							</pg:first>
							<pg:prev>
								<a href="${pageUrl }"  >上一页</a>
							</pg:prev>
							<pg:pages>
								<c:choose>
						         <c:when test="${pageNumber eq currPageNo}">
						                  [${pageNumber}]
						         </c:when>
						         <c:otherwise>
						                  <a href="${pageUrl }"  >${pageNumber}</a>
						         </c:otherwise>
								</c:choose>
							</pg:pages>
							<pg:next>
								<a href="${pageUrl }"  >下一页</a>
							</pg:next>
							<pg:last>
								<a href="${pageUrl }"  >尾页</a>
							</pg:last>
						</pg:pager>
						</td>
					</tr>
					<tr>
						<td align="center">序号</td>
						<td align="center">编码</td>
						<td align="center">名称</td>
						<td align="center">型号</td>
						<td align="center">价格</td>
						<td align="center">计量单位</td>
						<td align="center">库存</td>
						<td align="center">操作</td>
					</tr>
						<c:forEach items="${itemList}" var="it" varStatus="status">
							<tr>
								<td align="center">${status.index + 1 + (pageInfo.pageNum-1) * pageInfo.pageSize }</td>
								<td align="center">${it.code} </td>
								<td align="center">${it.name} </td>
								<td align="center">${it.model}</td>
								<td align="center">${it.price} </td>
								<td align="center">${it.unit} </td>
								<td align="center">${it.amount} </td>
								<td align="center">
								
								<a href="${it.code}/update">修改</a>
									<c:if test="${loginuser.usertype eq 0}">		<!-- 只有管理员可以操作 物料编码  |  暂时放开修改权限-->
										 <a href="${it.code}/delete">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
				</table>
			</c:if>
		</center>
	</form>
</body>
</html>