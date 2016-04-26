<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/datepicker/WdatePicker.js"></script>
<style type="text/css">
body{
	margin:0px;
	padding:0px;
}
</style>
</head>
<body>
	<h1 align="center">材料计划查询</h1>
	<form:form action="query" method="get">
	<div align="center"  >
		物料编码：<input type="text" name="itemCode" />
		物料名称：<input type="text" name="itemName" />
		物料型号：<input type="text" name="itemModel" />
		部门：<input type="text" name="department" />
		计划月份：<input type="text" name="planMonth"  class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"/>
		<br/><input type="submit" value="查询"/> 
		<br/><a href="add">添加本月计划</a>	<a href="/">返回</a>
		</div>
	</form:form>
	
	<c:if test="${not empty itemplanlist}">
				<table  width=" 100%" border="1">
					<caption>查询结果</caption>
					<tr>
						<td align="center">计划编号</td>
						<td align="center">物料编码</td>
						<td align="center">物料名称</td>
						<td align="center">型号</td>
						<td align="center">计量单位</td>
						<td align="center">数量</td>
						<td align="center">部门</td>
						<td align="center">计划月份</td>
						<td align="center">修改</td>
						<td align="center">删除</td>
						<c:forEach items="${itemplanlist}" var="it" begin="0">
							<tr>
								<td align="center">${it.id}</td>
								<td align="center">${it.itemCode} </td>
								<td align="center">${it.itemName} </td>
								<td align="center">${it.itemModel} </td>
								<td align="center">${it.itemUnit}</td>
								<td align="center">${it.planNumber}</td>
								<td align="center">${it.department}</td>
								<td align="center">${it.planMonth}</td>
								<td align="center"><a href="${it.id}/update">修改</a></td>
								<td align="center"><a href="${it.id}/delete">删除</a></td>
						</c:forEach>
				</table>
			</c:if>
</body>
</html>