/**
 * 
 */
$(function(){
	$("input[readonly]").css("background-color","#ddd");
})

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
	var inputAccountTable = document.getElementById("inputAccountTable");
	offset = getOffset(inputAccountTable);
	searchDiv.style.display = "block";
	searchDiv.style.left = offset.left +  inputAccountTable.offsetWidth + "px";
	searchDiv.style.top = offset.top + "px";
	
	if(obj.name == 'item.code') {
		searchFrame.src = "/item/queryforadd?source=account&code=" + obj.value;
	} else if (obj.name == 'item.name') {
		searchFrame.src = "/item/queryforadd?source=account&name=" + obj.value;
	} else if (obj.name =='item.model') {
		searchFrame.src = "/item/queryforadd?source=account&model=" + obj.value;
	}
}

function checkForm() {
	var amount = $("#amount").val();
	var number = $("#number").val();
	var price = $("#price").val();
	if (Number(amount) != Number(number*price).toFixed(2)) {
		alert("金额不等于数量乘单价！");
		$("#amount").focus();
		return false;
	}
}
