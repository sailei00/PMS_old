<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>视频播放</title>
<script type="text/javascript" src="/js/jwplayer.js"></script>
</head>
<body>
${path }
|||
${filename }
<div id='myElement'>Loading the player...</div>
<a href="javascript:jwplayer('myElement').play();">Toggle playback</a>
<a href="javascript:alert('The volume of the player is: ' + jwplayer('myElement').getVolume());">Report volume</a>
<script type='text/javascript'>
	var filename = "${filename}";
	jwplayer("myElement").setup({ 
	  "file": "/uploadfiles/" + filename
	});
  
	jwplayer("myElement").on('complete', function(){
	 alert("Complete fired - Your content has completed!");
	});
</script>
</body>
</html>