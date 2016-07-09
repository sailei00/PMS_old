<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" " http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文档</title>
<!-- LXUI -->
<link rel="stylesheet" href="/lxui/css/lxui.css">
<link rel="stylesheet" type="text/css" href="/lxui/css/lxui-ie6.min.css">
<!-- jQuery -->
<script src="/js/jquery.min.js"></script>
<!-- LXUI -->
<script type="text/javascript" src="/lxui/js/lxui.min.js"></script>
<script type="text/javascript" src="/lxui/js/lxui-ie.min.js"></script>
<!-- zTree -->
<link rel="stylesheet" href="/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="/ztree/js/jquery.ztree.core.min.js"></script>
<script src="/js/zTreeMenu.js"></script>
</head>
<body>

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
	<form:form method="post" modelAttribute="document" enctype="multipart/form-data">
		<center>
			<c:if test="${status == 'view'}">查看</c:if>
			<c:if test="${status == 'update'}">修改</c:if>
			<c:if test="${status == 'add'}">上传</c:if>
			文档
			<c:if test="${status != 'add'}">
				<span style="background-color:#F0F0F0;color:#9933CC;border-style: solid;  border-width: 1px;  border-color: gray;">${id}</span>
			</c:if>
			<table>
				<tr>
					<td>标题：</td>
					<td><form:input path="title" /></td>
				</tr>
				<tr id="filetypetr">
					<td>文档类型：</td>
					<td><form:select path="filetype">
							<form:option value="ppt">幻灯片</form:option>
							<form:option value="doc" >文档</form:option>
							<form:option value="video">视频</form:option>
							<form:option value="other">其他</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>栏目：</td>
					<td><form:input path="category"   readonly="true"  onclick="showTree()" />
					</td>
				</tr>
				<tr>
					<td>上传时间：</td>
					<td><form:input path="uploadtime" /></td>
				</tr>
				<tr>
					<td>发布者：</td>
					<td><form:input path="uploader" /></td>
				</tr>
				<tr>
					<td>附件：</td>
					<td>
						<c:if test="${not empty id}">
							<img src="${document.icon}" width="26px" height="26px">${document.filename}<a href="/uploadfiles/${document.filename }">下载</a><a href="/train/${id}/preview">预览</a>
						</c:if>
						<c:if test="${empty id}">
								<input type="file" name = "attch">
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<c:if test="${status != 'view'}"><input type="submit" value="保存" /></c:if>
						<a href="/train/documentindex">返回</a></td>
				</tr>
			</table>
		</center>
	</form:form>
</body>

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
});

function showTree() {
	$('#myModal').modal('show');
}

$(function(){
	<c:if test="${status == 'add'}">
		$("#filetypetr").css("display","none");
		$('#filetypetr').attr("disabled",true);
	</c:if>
	<c:if test="${status == 'view'}">
		$("input").css("background-color","#ddd");
		$('input').attr("readonly",true);
		
		$('input').removeAttr("onclick");
		
		var selected = $("#filetype").val();
		$("#filetype option[value!='"+selected+"']").remove();
	</c:if>
})
</script>
</html>