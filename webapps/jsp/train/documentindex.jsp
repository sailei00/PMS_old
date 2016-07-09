<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>培训管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"     >
<!-- LXUI -->
<link rel="stylesheet" href="/lxui/css/lxui.css">
<link rel="stylesheet" type="text/css" href="/lxui/css/lxui-ie6.min.css">
<!-- jQuery -->
<script type="text/javascript" src="/js/jquery.min.js"></script>
<!-- LXUI -->
<link rel="stylesheet" href="/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/lxui/js/lxui.min.js"></script>
<script type="text/javascript" src="/lxui/js/lxui-ie.min.js"></script>
<script type="text/javascript" src="/ztree/js/jquery.ztree.core.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/zTreeMenu.js"></script>
<style type="text/css">
body {
	margin:10px;
	background: #efeeea;
}
</style>
<script type="text/javascript">
//zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
	callback : {
		onClick : zTreeOnClick
	}
};
function zTreeOnClick(event, treeId, treeNode) {
	var result = treeNode.name;
	while (treeNode.level != 0){
		treeNode = treeNode.getParentNode();
		result = treeNode.name + "|" + result;
	}
	$("#category").val(result);
	$('#myModal').modal('hide');
};
$(document).ready(function() {
	zTreeObj = $.fn.zTree.init($("#categoryTree"), setting, zNodes);
	$("#category").css("cursor","Pointer");
});

function showTree() {
	$('#myModal').modal('show');
}

function chongzhi(){
	$("#category").val("");
	$("#title").val("");
}
</script>
</head>
<body>
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#">文章</a></li>
		<li><a href="/train/add">新增文档</a></li>
	</ul>
	
	<form:form role="form" action="query" method="get" modelAttribute="document">
		<div class="input-group" style="width: 30%; float: left">
			<span class="input-group-addon">栏目</span>
			<form:input path="category" cssClass="form-control "  readonly="true"   placeholder="栏目"   onclick="showTree()"/>
		</div>
		<div class="input-group" style="width: 30%; float: left">
			<span class="input-group-addon">标题</span>
			<form:input path="title" cssClass="form-control" placeholder="标题" />
		</div>
		<div class="input-group" style="width: 20%; float: left">
			<span class="input-group-addon">类型</span> 
			<form:select path="filetype" cssClass="form-control" cssStyle="width:100px;">
				<form:option value="">全部</form:option>
				<form:option value="ppt">幻灯片</form:option>
				<form:option value="doc">文档</form:option>
				<form:option value="video">视频</form:option>
				<form:option value="other">其他</form:option>
			</form:select>
		</div>
		<button type="submit" class="btn btn-default">查询</button>
		<input type="button" class="btn btn-default"  onclick="chongzhi();"  value="重置">
	</form:form>
<c:if test="${not empty documentList }">
				<table class="table table-bordered table-striped table-hover table-default-theme1" >
				<thead>
					<tr>
						<th align="center">序号</th>
						<th align="center">标题</th>
						<th align="center">文档类型</th>
						<th align="center">栏目</th>
						<th align="center">发布者</th>
						<th align="center">上传时间</th>
						<th align="center">操作</th>
					</tr>
						<c:forEach items="${documentList}" var="it" varStatus="status">
							<tr>
								<td align="center">${status.index + 1 + (pageInfo.pageNum-1) * pageInfo.pageSize }</td>
								<td align="center">${it.title} </td>
								<td align="center">${it.filetype} </td>
								<td align="center">${it.category}</td>
								<td align="center">${it.uploader} </td>
								<td align="center"><fmt:formatDate value="${it.uploadtime}" pattern="yyyy-MM-dd"/></td>
								<td align="center">
								<a href="${it.id}/view">查看</a>
								<a href="${it.id}/update">修改</a>
									<c:if test="${loginuser.usertype eq 0}">		<!-- 只有管理员可以删除-->
										 <a href="${it.id}/delete">删除</a>
									</c:if>
								</td>
						</c:forEach>
						
				</table>
			</c:if>
			
			
<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<ul id="categoryTree" class="ztree"
						style="border: 0px; margin: 0px; padding: 0px;">
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>