<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>材料计划查询</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
<script language="javascript" type="text/javascript" src="/datepicker/WdatePicker.js"></script>
</head>
<body>
	<h1 align="center">材料计划查询</h1>
	<form:form action="query" method="get">
	<div align="center"  >
		物料编码：<input type="text" name="itemCode" />
		物料名称：<input type="text" name="itemName" />
		<!-- 物料型号：<input type="text" name="itemModel" />  -->
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
		<br/>	<br/>
		计划月份：<input type="text" name="planMonth"  class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"/>
		至<input type="text" name="planMonthEnd"  class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"/>
		<span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    </span> 费用类别：
				<select name="costType">
					<option value="">请选择</option>
					<option value="承包费">承包费</option>
					<option value="掘进费">掘进费</option>
					<option value="生产费">生产费</option>
					<option value="安全费">安全费</option>
				</select>
		<br/><input type="submit" value="查询"/> <input type="reset">
		<br/><a href="add">添加本月计划</a>	
		</div>
	</form:form>
	
	
	
	<c:if test="${not empty itemplanlist}">
		<center>
				<table  width=" 90%" border="1">
					<caption>查询结果</caption>
					<tr>
						<td align="center">序号</td>
 <!-- 						<td align="center">计划编号</td> -->
						<td align="center">计划月份</td>
 						<td align="center">物料编码</td>
						<td align="center">物料名称</td>
						<td align="center">型号</td>
						<td align="center" style="width:40px;">计量单位</td>
						<td align="center">数量</td>
						<td align="center" style="width:80px;">部门</td>
						<td align="center">用途</td>
						<td align="center" style="width:65px;">费用类别</td>
						<td align="center" style="width:69px;">操作</td>
						<c:forEach items="${itemplanlist}" var="it"  varStatus="status">
							<tr>
								<td align="center">${status.index + 1}</td>
<%-- 								<td align="center">${it.id}</td> --%>
								<td align="center">${it.planMonth}</td>
								<td align="center">${it.itemCode} </td>
								<td align="center">${it.itemName} </td>
								<td align="center">${it.itemModel} </td>
								<td align="center">${it.itemUnit}</td>
								<td align="center">${it.planNumber}</td>
								<td align="center">${it.department}</td>
								<td align="center">${it.purpose}</td>
								<td align="center">${it.costType}</td>
								<td align="center"><a href="${it.id}/update">修改</a> <a href="${it.id}/delete"  onClick="return confirm('确定要删除序号为(${status.index + 1})的记录吗？');">删除</a></td>
						</c:forEach>
				</table>
		</center>
	</c:if>
</body>
</html>