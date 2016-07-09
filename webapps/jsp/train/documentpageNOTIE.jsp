<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>培训管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- LXUI -->
<link rel="stylesheet" href="/lxui/css/lxui.css">
<link rel="stylesheet" type="text/css" href="/lxui/css/lxui-ie6.min.css">
<!-- jQuery -->
<script src="/js/jquery.min.js"></script>
<!-- LXUI -->
<script type="text/javascript" src="/lxui/js/lxui.min.js"></script>
<script type="text/javascript" src="/lxui/js/lxui-ie.min.js"></script>
<style type="text/css">
body {
	margin: 10px;
	background: #efeeea;
}
</style>
</head>
<body>

	<form role="form" class='form-horizontal'>
		<div class="form-group" >
			<label for="exampleInputEmail1" class="span2 control-label" >标题</label>
			<div class="span10">
				<input type="email" class="form-control" id="exampleInputEmail1" placeholder="标题" >
			</div>
		</div>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入邮箱">
		    </div>
		  </div>
		<div class="input-group">
			<span class="input-group-addon">类型</span>
			<select class="form-control" >
				<option value="ppt">幻灯片</option>
				<option value="doc">Word文档</option>
				<option value="video">视频</option>
			</select>
		</div>
		<div class="input-group">
			<label for="exampleInputPassword1">栏目</label> 
			<input type="password" class="form-control" id="exampleInputPassword1" placeholder="输入密码">
		</div>
				
		<div class="col-sm-offset-2 col-sm-0">
			<button type="submit" class="btn btn-default">上传</button>
		</div>
	</form>

</body>
</html>