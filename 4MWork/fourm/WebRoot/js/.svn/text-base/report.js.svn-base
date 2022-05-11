<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>

<script type="text/javascript" src="${ctx}/js/common/jquery-1.8.3.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	//timeSpan 0:按天  1：按月    2：按小时
	function changeHour(){
		$("#span").val("2");
		$("#radioH").attr("class","bgs");
		$("#radioD").attr("class","");
		$("#radioM").attr("class","");
	}
	function changeDay(){
		$("#span").val("0");
		$("#radioH").attr("class","");
		$("#radioD").attr("class","bgs");
		$("#radioM").attr("class","");
	}
	function changeMonth(){
		$("#span").val("1");
		$("#radioH").attr("class","");
		$("#radioD").attr("class","");
		$("#radioM").attr("class","bgs");
	}
	//提交查询
	function checkAndSubmit(){	
		if($("#beginTime").val()==""){
			alert("请输入起始时间");
			return;
		}
		document.getElementById("searchTime").value = document.getElementById("beginTime").value;
		$("#form_all").attr("action","${ctx}/history/report_goReport.html"+"?tempId="+Math.round(Math.random()*100));
		document.getElementById("form_all").submit();
	}
	
	function goback(){
		window.location.href = "${ctx}/history/report_goReportSelect.html";
 	}

	/*function Exportexcel(){
		document.write(excel.outerHTML);
	    try{
			document.execCommand("SaveAs",true,"123.xls");
		}catch(exception){ 
			alert("您的浏览器版本太低，请升级您的浏览器！");
		}
	}*/
</script>