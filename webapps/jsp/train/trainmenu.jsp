<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>培训管理</title>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<link rel="stylesheet" href="/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/ztree/js/jquery.ztree.core.min.js"></script>
<script src="/js/zTreeMenu.js"></script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

div#menu {
	width: 100%;
	height: 20px;
	font-size: medium;
	text-align: center;
	background-image: linear-gradient(rgb(247, 247, 247), rgb(239, 238, 234));
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
};
$(document).ready(function() {
	zTreeObj = $.fn.zTree.init($("#categoryTree"), setting, zNodes);
});
</script>
</head>
<body style="background: #efeeea;">
	<div id="menu">栏目列表</div>
	<ul id="categoryTree" class="ztree"
		style="border: 0px; margin: 0px; padding: 0px;">
	</ul>
</body>

</html>