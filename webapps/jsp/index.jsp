<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
<script language="javascript">
  	function quit() 
 	{ 
        event.returnValue="是否确定要退出页面?";
  	} 
  	
  	if (self!=top){
       top.location=self.location;
    }
</script>
</head>
<frameset rows="40,*"  frameborder="yes"  border="1">
	<frame src="/jsp/header.jsp" scrolling="no" noresize="noresize">
	<frame id="_content"  name="_content"  />
</frameset>
</html>