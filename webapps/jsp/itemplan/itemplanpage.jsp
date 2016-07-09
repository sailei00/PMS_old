<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>材料计划录入</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
<script language="javascript" type="text/javascript" src="/datepicker/WdatePicker.js"></script>
<script type="text/javascript">
//获取Node的offset值，返回一个map
function getOffset(Node, offset) {
	if (!offset) {
		offset = {};
		offset.top = 0;
		offset.left = 0;
	}
	if (Node == document.body) {//当该节点为body节点时，结束递归
		return offset;
	}
	offset.top += Node.offsetTop;
	offset.left += Node.offsetLeft;
	return getOffset(Node.parentNode, offset);//向上累加offset里的值
}

function showSearchPage(obj) {
	var searchDiv = document.getElementById("searchDiv");
	var searchFrame = document.getElementById("searchFrame");
	var inputPlanTable = document.getElementById("inputPlanTable");
	offset = getOffset(inputPlanTable);
	searchDiv.style.display = "block";
	
	//searchDiv.style.left = offset.left +  inputPlanTable.offsetWidth + "px";
	//searchDiv.style.top = offset.top + "px";
	
	if(obj.name == 'itemCode') {
		searchFrame.src = "/item/queryforadd?source=itemplan&code=" + obj.value;
	} else if (obj.name == 'itemName') {
		searchFrame.src = "/item/queryforadd?source=itemplan&name=" + obj.value;
	} else if (obj.name =='itemModel') {
		searchFrame.src = "/item/queryforadd?source=itemplan&model=" + obj.value;
	}
	
	
//	var srctext = document.getElementById("srctext");
//	srctext.value = searchFrame.src;
}
</script>
</head>
<body>
<form:form method="post"  modelAttribute="itemPlan">
 <center>
	<c:if test="${not empty id}">修改</c:if><c:if test="${empty id }">添加</c:if>月度计划
	<c:if test="${not empty id}"><form:label path="id" cssStyle="background-color:#F0F0F0;color:#9933CC;border-style: solid;  border-width: 1px;  border-color: gray;">${id}</form:label></c:if>
	<table  id="inputPlanTable" border="1">
		<tr>
			<td>材料编码：</td>
			<td>
				<form:input path="itemCode"  ondblclick="showSearchPage(this)" cssStyle="border:#999 1px solid;height:20px;background:#fff url(/images/search.png)  no-repeat right;"/><form:errors path="itemCode" cssStyle="color:red"></form:errors>
			</td>
			<td>材料名称：</td>
			<td>
				<div style="display:block;position:relative">
					<form:input path="itemName"  ondblclick="showSearchPage(this)" cssStyle="border:#999 1px solid;height:20px;background:#fff url(/images/search.png)  no-repeat right;"/><form:errors path="itemName" cssStyle="color:red"></form:errors>
				</div>
			</td>
		</tr>
		<tr>
			<td>型号：</td>
			<td>
				<form:input path="itemModel"  ondblclick="showSearchPage(this)" cssStyle="border:#999 1px solid;height:20px;background:#fff url(/images/search.png)  no-repeat right;"/>
			</td>
			<td>归属部门：</td>
			<td>
				<form:input path="department"  readonly="true" /><form:errors  path="department" cssStyle="color:red"/>
			</td>
		</tr>
		<tr>
			<td>单位：</td>
			<td>
				<form:input path="itemUnit"  />
			</td>
			<td>计划数量：</td>
			<td>
				<form:input path="planNumber"/><form:errors path="planNumber" cssStyle="color:red"/>
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
			<td>计划月份：</td>
			<td>
				<form:input path="planMonth"  cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"/>
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
			<td>用途</td>
			<td colspan="3">
				<form:textarea path="purpose"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" value="保存" />	<a href="/itemplan/index">返回</a></td>
		</tr>
	</table>
</center>
	</form:form>
<center>
	<div id="searchDiv" align="center"  style="display:none;">
		<iframe id="searchFrame"  ></iframe>
	</div>
</center>
</body>
</html>