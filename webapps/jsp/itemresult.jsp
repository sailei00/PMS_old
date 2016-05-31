<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${resultMessage }</title>
</head>
<body bgcolor="yellow">
	<table>
		<caption>物料编码</caption>
		<tr>
			<td>编码：</td>
			<td><input type="text" disabled name="code" value="${item.code}" /></td>
		</tr>
		<tr>
			<td>名称：</td>
			<td><input type="text" disabled name="name" value="${item.name}" /></td>
		</tr>
		<tr>
			<td>型号：</td>
			<td><input type="text" disabled name="model" value="${item.model}" /></td>
		</tr>
		<tr>
			<td>单价：</td>
			<td><input type="text" disabled name="price" value="${item.price}" /></td>
		</tr>
		<tr>
			<td>计量单位：</td>
			<td><input type="text" disabled name="unit" value="${item.unit}" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" ><font color=red>${resultMessage }</font></td>
		</tr>
		<tr>
			<td colspan="2" align="center" ><a href="/item/index">返回</a></td>
		</tr>
	</table>
</body>
</html>