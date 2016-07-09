<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表</title>
<script language="javascript" type="text/javascript" src="/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/jquery.spider.js"></script>
<link rel="stylesheet" type="text/css" href="/css/basecss.css">
<link rel="stylesheet" type="text/css" href="/css/jquery.spider.css">
<style type="text/css">
.money{
	width:100px;
	border:0px;
}
.selectDiv{
	width:100px;
	border:1px solid gray;
	position:reactive;
}
</style>
<script type="text/javascript">
<c:if test="${not empty reportList}">
		$(function() {
			var department = '${department}';
			if(department == '综采队'){
				/* $("td:contains('费用系数')").html("费用系数(元/吨)"); */
				$("#ratio1").val(0.66);
				$("#ratio2").val(2.99);
				$("#ratio3").val(0.86);
			}
			if(department == '机电机运队'){
				$("#ratio1").val(0.65);
				$("#ratio2").val(2.41);
				$("#ratio3").val(0.32);
			}
			if(department == '综掘一队') {
				$("#drivingtype").show(0).change();
				$("#ratio2").val(0.9);
				//$("#ratio4").val(0.32);
				$("#ratio4type").change();
			}
			if(department == '综掘二队') {
				$("#drivingtype").show(0).change();
				$("#ratio2").val(0.5);
			}
			if(department == '机修厂') {
				$("#ratio1").val(0);
				$("#ratio2").val(0.5);
				$("#ratio3").val(0.05);
			}
			if(department == '通风科') {
				$("#ratio1").val(0);
				$("#ratio2").val(1.25);
			}
			if(department == '机电科') {
				$("#ratio1").val(0);
				$("#ratio2").val(0.05);
			}
			if(department == '安全科') {
				$("#ratio1").val(0);
				$("#ratio2").val(0.35);
			}
			if(department == '生产技术科') {
				$("#ratio1").val(0);
				$("#ratio2").val(0.25);
				$("#ratio3").val(0.01);
			}
			if(department == '安全指挥中心') {
				$("#ratio1").val(0);
				$("#ratio2").val(0.97);
			}
		});
		
		
		function calc(){
			var department = '${department}';
			var length = $("#length").val();		//掘进进尺
			var mine = $("#mine").val();		//产量
			if(!$.isNumeric(length) || !$.isNumeric(mine) ){
				alert('进尺或产量输入有误');
				return;
			}
			
			/* 计算承包费 */
			var money1 =$("#money1").val();	//使用金额
			if(money1 != '' && money1 != null) {
				var ratio1  = $("#ratio1").val();		//费用系数
				var result1 ;			//计划量
				var percent1 ;		//使用比例
				var mineOrLength;		//产量进尺计算因子
				var balance1;
				if(department == '综掘一队' || department == '综掘二队') {
					mineOrLength = length;
				} else {
					mineOrLength = mine;
				}
				if($.isNumeric(ratio1)){
					result1 = Number(mineOrLength * ratio1).toFixed(2);
					$("#result1").val(result1);
					percent1= Number(money1 / result1 * 100).toFixed(2);
					$("#percent1").val(percent1 + "%");
				} else {
					alert('承包费用系数输入有误');
					return;
				}
				balance1 = Number(money1 - result1).toFixed(2);
				if(balance1>0){
					$("#balance1").val("超支"+balance1);
				}
				if(balance1<0){
					$("#balance1").val("节余"+Math.abs(balance1));
				}
			}
			
			
			/* 计算安全费 */
			var money2 =$("#money2").val();	//使用金额
			if(money2 != '' && money2 != null) {
				var ratio2 = $("#ratio2").val();		//费用系数
				var result2 ;			//计划量
				var percent2 ;		//使用比例
				var balance2;		//超支/节余
				if($.isNumeric(ratio2)){
					result2 = Number(mine * ratio2).toFixed(2);
					$("#result2").val(result2);
					percent2= Number(money2 / result2 * 100).toFixed(2);
					$("#percent2").val(percent2 + "%");
				} else {
					alert('安全费用系数输入有误');
					return;
				}
				balance2 = Number(money2 - result2).toFixed(2);
				if(balance2>0){
					$("#balance2").val("超支"+balance2);
				}
				if(balance2<0){
					$("#balance2").val("节余"+Math.abs(balance2));
				}
			}
			
			/* 计算生产费 */
			var money3 =$("#money3").val();	//使用金额
			var ratio3 = $("#ratio3").val();		//费用系数
			if(money3 != '' && money3 != null && ratio3!='') {
				var result3;			//计划量
				var percent3 ;		//使用比例
				var balance3;		//超支/节余
				if($.isNumeric(ratio3)){
					result3 = Number(mine * ratio3).toFixed(2);
					$("#result3").val(result3);
					percent3= Number(money3 / result3 * 100).toFixed(2);
					$("#percent3").val(percent3 + "%");
				} else {
					alert('生产费用系数输入有误');
					return;
				}
				
				balance3 = Number(money3 - result3).toFixed(2);
				if(balance3>0){
					$("#balance3").val("超支"+balance3);
				}
				if(balance3<0){
					$("#balance3").val("节余"+Math.abs(balance3));
				}
			}
			
			/* 计算掘进费 */
			var money4 =$("#money4").val();	//使用金额
			if(money4 != '' && money4 != null) {
				var ratio4 = $("#ratio4").val();		//费用系数
				var result4;			//计划量
				var percent4 ;		//使用比例
				var balance4;		//超支/节余
				if($.isNumeric(ratio4)){
					result4 = Number(mine * ratio4).toFixed(2);
					$("#result4").val(result4);
					percent4= Number(money4 / result4 * 100).toFixed(2);
					$("#percent4").val(percent4 + "%");
				} else {
					alert('掘进费用系数输入有误');
					return;
				}
				balance4 = Number(money4 - result4).toFixed(2);
				if(balance4>0){
					$("#balance4").val("超支"+balance4);
				}
				if(balance4<0){
					$("#balance4").val("节余"+Math.abs(balance4));
				}
			}
			
			//value为使用率，money为月使用金额， total为月计划量
			var data1={disks:[
							{id:'10000',name:'承包费：',money:money1,value:percent1,total:result1},
							{id:'10001',name:'安全费：',money:money2,value:percent2,total:result2},
							{id:'10002',name:'生产费：',money:money3,value:percent3,total:result3},
							{id:'10003',name:'掘进费：',money:money4,value:percent4,total:result4}]}; 
			refresh(data1);
			/* 
			$("#mineresult").val($("#mine").val() * $("#mineratio").val());
			var percent1 = Number($("#money1").val() / $("#mineresult").val() * 100).toFixed(2);
			var money1 = $("#money1").val();
			var total1 = $("#mineresult").val();
			$("#percent1").val(percent1 + "%");
			
			var percent2 = Number($("#money2").val() / $("#mineresult").val() * 100).toFixed(2);
			var money2 = $("#money2").val();
			var total2 = $("#mineresult").val();
			$("#percent2").val(percent2 + "%");
			
			var percent3 = Number($("#money3").val() / $("#mineresult").val() * 100).toFixed(2);
			var money3 = $("#money3").val();
			var total3 = $("#mineresult").val();
			$("#percent3").val(percent3 + "%");
			
			var percent4 = Number($("#money4").val() / $("#mineresult").val() * 100).toFixed(2);
			var money4 = $("#money4").val();
			var total4 = $("#mineresult").val();
			$("#percent4").val(percent4 + "%"); */
			
		}
		
		
		function refresh(data) {
			$("#disk_a").disk("poll1", {
				width : '100%',
				data : data
			});
		}
		
		
		function selectDrivingOrMining(obj) {
			var ratio4 = $("#ratio4");
			if(obj.value == 'isDriving') {
				ratio4.val('509');
			}
			if(obj.value == 'isMining') {
				ratio4.val('1.69');
			}
		}
</c:if>
</script>
</head>
<body>
	<h1 align="center">材料使用情况月报</h1>
	<form:form action="getreport" method="get"  modelAttribute="querybean">
		<div align="center">
			年月<input type="text" name="month"  class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"/>
			部门： <c:if test="${loginuser.usertype eq 1}" >	<!-- 队组 -->
							<input type="text"  id="department" name="department"  value="${loginuser.department}"   readonly/>
						</c:if>
						<c:if test="${loginuser.usertype ne 1}">	<!-- 科室 和 领导-->
							<form:select path="department">
								<form:option value="机修厂">机修厂</form:option>
								<form:option value="机电机运队">机电机运队</form:option>
								<form:option value="综掘一队">综掘一队</form:option>
								<form:option value="综掘二队">综掘二队</form:option>
								<form:option value="综采队">综采队</form:option>
								<form:option value="通风科">通风科</form:option>
								<form:option value="生产技术科">生产技术科</form:option>
								<form:option value="机电科">机电科</form:option>
							</form:select>
						</c:if>
			 <br />
			<input type="submit" value="查询" /> 
		</div>
	</form:form>
	<br/><br/>
	<center>
		<c:if test="${not empty reportList}">
			<table width="60%">
				<tr style="border:0px;"> 
					<td align="left" colspan="2"  style="border:0px;">统计月份：${month}</td>
					<td align="right" colspan="2"  style="border:0px;">队别：${department}</td>
				</tr>
			</table>
			<table align="center"  width="60%" border=1>
				<tr><td align="center">物料编码</td><td align="center">物料名称</td><td align="center">型号</td><td align="center">数量</td><td align="center">单价</td><td align="center">金额</td><td align="center">统计金额是否正确</td></tr>
				<c:forEach items="${reportList}" var="li">
					<tr>
						<td align="center">${li.itemCode}</td>
						<td align="center">${li.itemName}</td>
						<td align="center">${li.model}</td>
						<td align="center">${li.number}</td>
						<td align="center">${li.price}</td>
						<td align="center">${li.amount}</td>
						<td align="center">${li.amount == li.price*li.number?"√":"×"}</td>
					</tr>
				</c:forEach>
			</table>
			<br/>
			<table  align="center"  width="60%" border=1>
				<caption>
						进尺<input id="length" type="text" class="money" />米 ,
						产量<input id="mine" type="text" class="money" />吨
					<input type="button" class="money"  onclick="calc()" value="计算"/>
				</caption>
				<tr>
					<td></td>
					<td align="center">承包费</td>
					<td align="center">安全费</td>
					<td align="center">生产费</td>
					<td align="center">掘进费</td>
				</tr>
				<tr>
					<td>金额(元)</td>
					<td align="center"><input id="money1"   type="text" class="money" value="${costList[0].price}" /></td>
					<td align="center"><input id="money2"   type="text" class="money" value="${costList[1].price}" /></td>
					<td align="center"><input id="money3"   type="text" class="money" value="${costList[2].price}" /></td>
					<td align="center"><input id="money4"   type="text" class="money" value="${costList[3].price}" /></td>
				</tr>
				<tr>
					<td>费用系数</td>
					<td align="center">
						<select id="drivingtype" style="display:none;" onchange="javascript:ratio1.value=this.value;">
							<option value="1644.76">回风顺槽(4.5m×4m)</option>
							<option value="1489.15">运输顺槽(5m×4m)</option>
							<option value="1640.11">切眼首次掘进(4.3m×4m)</option>
							<option value="1321.94">切眼二次掘进(3.9m×4m)</option>
						</select>
						<input id="ratio1"   type="text" class="money"  />
					</td>
					<td align="center"><input id="ratio2"   type="text" class="money"  /></td>
					<td align="center"><input id="ratio3"   type="text" class="money"  /></td>
					<td align="center">
						<select id="ratio4type" style="display:;" onchange="selectDrivingOrMining(this);">
							<option value="isMining">按产量算</option>
							<option value="isDriving">按进尺算</option>
						</select>
						<input id="ratio4"   type="text" class="money"  />
					</td>
				</tr>
				<tr>
					<td>控制费用</td>
					<td align="center"><input id="result1"   type="text" class="money"  /></td>
					<td align="center"><input id="result2"   type="text" class="money"  /></td>
					<td align="center"><input id="result3"   type="text" class="money"  /></td>
					<td align="center"><input id="result4"   type="text" class="money"  /></td>
				</tr>
				<tr>
					<td>完成比例</td>
					<td align="center"><input id="percent1" type="text" class="money"/></td>
					<td align="center"><input id="percent2" type="text" class="money"/></td>
					<td align="center"><input id="percent3" type="text" class="money"/></td>
					<td align="center"><input id="percent4" type="text" class="money"/></td>
				</tr>
				<tr>
					<td>超支/结余</td>
					<td align="center"><input id="balance1" type="text" class="money"/></td>
					<td align="center"><input id="balance2" type="text" class="money"/></td>
					<td align="center"><input id="balance3" type="text" class="money"/></td>
					<td align="center"><input id="balance4" type="text" class="money"/></td>
				</tr>
			</table>
		</c:if>
	</center>
	<div class="formbody"><div id="disk_a"></div></div>
	<br/>
</body>
</html>