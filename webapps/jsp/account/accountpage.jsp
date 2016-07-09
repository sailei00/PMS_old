<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出入库单</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
<script language="javascript" type="text/javascript" src="/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="/jsp/account/accountpage.js"></script>
</head>
<body>
<form:form method="post"  modelAttribute="account" onsubmit="return checkForm();">
	<form:hidden path="updateTime"  readonly="true" />
 <center>
	<c:if test="${not empty account.id}">修改</c:if><c:if test="${empty account.id }">添加</c:if>
	<c:if test="${account.type==0}">出库单</c:if><c:if test="${account.type==1}">入库单</c:if>
	
	<c:if test="${not empty account.id}"><div style="background-color:#F0F0F0;color:#9933CC;border-style: solid;  border-width: 1px;  border-color: gray;">${account.id}</div></c:if>
	<table  id="inputAccountTable" border="1">
		<tr>
			<td>单据类型：</td>
			<td>
				<c:if test="${not empty account.id}"> <%--  id不空则是修改 ，修改单据不允许修改单据类型，用隐藏域保存--%>
					<c:if test="${account.type==0}"><form:hidden path="type" />出库单</c:if>
					<c:if test="${account.type==1}"><form:hidden path="type" />入库单</c:if>
				</c:if>
				<c:if test="${empty account.id}">   <%--   id为空则为是新增 --%>
					<form:select path="type" >
						<form:option value="0" label="出库" />
						<form:option value="1" label="入库"  /> 
					</form:select>
				</c:if>
				<form:errors path="type" cssStyle="color:red"></form:errors>
			</td>
			<td>编码：</td>
			<td>
				<form:input path="item.code"  ondblclick="showSearchPage(this)" cssStyle="border:#999 1px solid;height:20px;background:#fff url(/images/search.png)  no-repeat right;"/><form:errors path="item.code" cssStyle="color:red"></form:errors>
			</td>
		</tr>
		<tr>
			<td>名称：</td>
			<td>
				<div style="display:block;position:relative">
					<form:input path="item.name"  ondblclick="showSearchPage(this)" cssStyle="border:#999 1px solid;height:20px;background:#fff url(/images/search.png)  no-repeat right;"/><form:errors path="item.name"  cssStyle="color:red"></form:errors>
				</div>
			</td>
			<td>型号：</td>
			<td>
				<form:input path="item.model"  ondblclick="showSearchPage(this)" cssStyle="border:#999 1px solid;height:20px;background:#fff url(/images/search.png)  no-repeat right;"/>
			</td>
		</tr>
		<tr>
			<td>单位：</td>
			<td>
				<form:input path="item.unit"  />
			</td>
			<td>数量：</td>
			<td>
				<!-- 录入时间24小时后不允许修改数量      (每天是86400000毫秒)  -->
				<form:input path="number"  readonly="${account.createTime.time + 86400000 < now.time}" /><form:errors path="number" cssStyle="color:red"/>
			</td>
		</tr>
		<tr>
			<td>单价：</td>
			<td>
				<form:input path="price"  /><form:errors path="price" cssStyle="color:red"/>
			</td>
			<td>金额：</td>
			<td>
				<form:input path="amount"  /><form:errors path="amount" cssStyle="color:red"/>
			</td>
		
		</tr>
		<tr>
			<td>归属部门：</td>
			<td>
				<form:input path="department" readonly="true"/><form:errors  path="department" cssStyle="color:red"/>
			</td>
			<td>操作员：</td>
			<td>
				<form:input path="operator" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td>经办人：</td>
			<td>
				<form:input path="handler"/>
			</td>
			<td>办理时间：</td>
			<td>
				<form:input path="optTime" cssClass="Wdate" onfocus="WdatePicker({firstDayOfWeek:1})"/><form:errors  path="optTime" cssStyle="color:red"/>
			</td>
		</tr>
		<tr>
			<td>录入时间：</td>
			<td>
				<form:input path="createTime"  readonly="true"/>
			</td>
			<td>费用类别：</td>
			<td>
				<form:select path="costType">
					<form:option value="">请选择</form:option>
					<form:option value="承包费">承包费</form:option>
					<form:option value="掘进费">掘进费</form:option>
					<form:option value="生产费">生产费</form:option>
					<form:option value="安全费">安全费</form:option>
				</form:select>
				<form:errors  path="costType" cssStyle="color:red"/>
			</td>
		</tr>
		<tr>
			<td>出入库原因</td>
			<td colspan="3">
				<form:textarea path="reason"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" value="保存" />	<a href="/account/index">返回</a></td>
		</tr>
	</table>
</center>
	</form:form>

<center>
	<div id="searchDiv" style="display:none;">
		<iframe id="searchFrame"  ></iframe>
	</div>
</center>
</body>
</html>