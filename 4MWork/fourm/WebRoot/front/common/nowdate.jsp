<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<div class="time" id="nowDate"></div>

<script type="text/javascript">
function time(){
	var date = new Date();
	var now = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
	var sec = date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds(); 
	now += " " + hour + ":" + min + ":" + sec;
	document.getElementById("nowDate").innerHTML = now;
	setTimeout("time()",1000);
}
time();
</script>