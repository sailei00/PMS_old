<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询物料</title>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
<style type="text/css">
html { overflow-x: hidden; overflow-y: hidden; }/*隐藏横向滚动，垂直滚动根据内容自适应（去除IE默认垂直滚动条）*/ 
.itemsearch {
	width:50px;
}
</style>
<script type="text/javascript">
var iframeLoaded = function (iframe) {
    if (iframe.src.length > 0) {
        if (!iframe.readyState || iframe.readyState == "complete") {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(bHeight, dHeight);
            iframe.height = height;
            iframe.width = "800px";
        }
    }
}
//分页时重新设置 iframe 高度 ； 修改后：iframe.name = iframe.id
var reSetIframeHeight = function()
{
    try {
        var oIframe = parent.document.getElementById('searchFrame');
        oIframe.height = 100;
        iframeLoaded(oIframe);
    }
    catch (err)
    {
    	alert("重新设置iframe大小报错了err:" + err);
        try {
         parent.document.getElementById(window.name).height = 1000;
        } catch (err2) { 
        	alert("重新设置iframe大小报错了err2:" + err);
        }
    }
}

function selectItem(t) {
	var pcode,pname,pmodel,punit;
	var source = document.getElementById("source");
	if("account,".indexOf(source.value) >= 0) {
		pcode = parent.document.getElementById("item.code");
		pname = parent.document.getElementById("item.name");
		pmodel = parent.document.getElementById("item.model");
		punit = parent.document.getElementById("item.unit");
	}else if("itemplan,".indexOf(source.value) >= 0) {
		pcode = parent.document.getElementById("itemCode");
		pname = parent.document.getElementById("itemName");
		pmodel = parent.document.getElementById("itemModel");
		punit = parent.document.getElementById("itemUnit");
	} else {
		alert("没有页面参数source");
	}
	var code = t.cells[0].innerText;
	var name = t.cells[1].innerText;
	var model = t.cells[2].innerText;
	var unit = t.cells[3].innerText;
	pcode.value = code;
	pname.value = name;
	pmodel.value = model;
	punit.value = unit;
	parent.document.getElementById('searchDiv').style.display = "none";
}

</script>
</head>
<body onload="reSetIframeHeight()">
	<form name="fm" action="/item/queryforadd" method="GET">
	<input type="hidden" value="${source}" name="source" id="source">
		<center>
			<table border="1">
				<tr>
					<td>物料编码：<input type="text"  class="itemsearch" name="code" value="${itemQueryBean.code}" />
					</td>
					<td>物料名称：<input type="text" class="itemsearch" name="name" value="${itemQueryBean.name}" />
					</td>
					<td>物料型号：<input type="text" class="itemsearch" name="model" value="${itemQueryBean.model}" />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="查询"/>
					</td>
				</tr>
			</table>
			<br/>
			<c:if test="${not empty itemList }">
				<table border="1">
					<caption>查询结果</caption>
					<tr>
						<td colspan="8" align="center">
						<pg:pager url="/item/queryforadd" items="${pageInfo.total}"  maxPageItems="${pageInfo.pageSize}"  export="currPageNo=pageNumber">
						<pg:param name="code"/>
						<pg:param name="name"/>
						<pg:param name="model"/>
						<pg:param name="source"/>
						共查询到${pageInfo.total} 条数据，
							<pg:first>
								<a href="${pageUrl }"  >首页</a>         
							</pg:first>
							<pg:prev>
								<a href="${pageUrl }"  >上一页</a>
							</pg:prev>
							<pg:pages>
								<c:choose>
						         <c:when test="${pageNumber eq currPageNo}">
						                  [${pageNumber}]
						         </c:when>
						         <c:otherwise>
						                  <a href="${pageUrl }"  >${pageNumber}</a>
						         </c:otherwise>
								</c:choose>
							</pg:pages>
							<pg:next>
								<a href="${pageUrl }"  >下一页</a>
							</pg:next>
							<pg:last>
								<a href="${pageUrl }"  >尾页</a>
							</pg:last>
						</pg:pager>
						</td>
					</tr>
					<tr>
						<td align="center">编码</td>
						<td align="center">名称</td>
						<td align="center">型号</td>
						<td align="center">计量单位</td>
						<c:forEach items="${itemList}" var="it" begin="0">
							<tr onmouseover="this.style.color='red'" onmouseout="this.style.color=''" onclick="selectItem(this)">
								<td align="center">${it.code}</td>
								<td align="center">${it.name}</td>
								<td align="center">${it.model}</td>
								<td align="center">${it.unit}</td>
						</c:forEach>
				</table>
			</c:if>
		</center>
	</form>
</body>
</html>