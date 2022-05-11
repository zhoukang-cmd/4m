<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
        <link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" media="all"/>
	</head>	
	<body onload="deal();">
		<div class="content">
        	<%@ include file="/front/common/header.jsp"%>
          	<div class="main">
          		<div class="mian_nav">
			      <%@ include file="/front/common/nowdate.jsp"%>
			    </div>
            	<%@ include file="/front/common/left.jsp"%>
                <div class="mainright">
                	<div class="subNav">当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;智能分析
                    	<div class="btn_determine5" id="message"></div>
                    </div>
                    <div class="rollBox">
						<img onclick="ISL_GoUp()" style="float:left;" src="${ctx}/images/shqm_left_pic.gif" width="27" height="42" />
						<div class="Cont" id="ISL_Cont">
							<div class="ScrCont">
								<div id="reportList"></div>
							</div>
						</div>
						<img  onclick="ISL_GoDown()" style="float:left;" src="${ctx}/images/shqm_right_pic.gif" width="27" height="42" />
					</div>
                    <iframe id="reader" src="" width="100%" height="750" style="border: none;"></iframe>
                </div>
          	</div>
            <%@ include file="/front/common/footer.jsp"%>
        </div>
		<script type="text/javascript" src="${ctx}/js/common/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/jQuery.timers.js"></script>
		<script type="text/javascript">	
	 	function deal() {
	 		if ($("#reportList").html() != "") {
	 			window.location.reload(); 
	 		}
			if ("${reportList}" == "[]") {
				$("#reportList").html("当前设备无分析报表");
				return;
	 		}
			var list = "${reportList}".replace("[", "").replace("]", "").split(',');
	 		for (var i=0; i<list.length; i++) {
	 			var date = list[i].split('.')[0].split('_')[6];
	 			$("#reportList").append("<div class=\"pic\"><a href=\"javascript:setContent(" + i + ")\">" + date + "</a></div>");
	 		}
	 		setContent(0);
	 	}
	 	function setContent(num) {
	 		var url = "${ctx}/analyse/analyse_getAnalyse.html";
	 		$.ajax({
				url:url,
				dataType: "json",
				data:{order:num},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("获取报表失败");
				},
	    		success: function(json){
	    			$("#reader").attr("src", "https://docs.google.com/viewer?url=http://fourm.zk4m.com/downloads/" + json.fileName + "&embedded=true&a=v");
	    			//TODO $("#reader").attr("src", "https://docs.google.com/viewer?url=${ctx}/downloads/" + json.fileName + "&embedded=true&a=v");
	    		}
	 		});
	 	}
	 	//以下代码控制滚动选择框
	 	var Space = 10; //每次移动的px
	 	var MoveLock = false;
	 	var MoveTimeObj;
	 	function GetObj(objName){
	 		if(document.getElementById){
	 			return eval('document.getElementById("'+objName+'")')
	 		}else{
	 			return eval('document.all.'+objName)
	 		}
	 	}
	 	function ISL_GoUp(){ //上翻
	 		clearInterval(MoveTimeObj);
	 		if(MoveLock) return;
	 		MoveLock = true;
	 		MoveTimeObj = setInterval('ISL_ScrUp();', 10);
	 		setTimeout("clearInterval(MoveTimeObj);", 500);
	 		MoveLock = false;
	 	}
	 	function ISL_ScrUp(){ //上翻动作
	 		GetObj('ISL_Cont').scrollLeft -= Space ;
	 		if(GetObj('ISL_Cont').scrollLeft <= 0){
	 			GetObj('ISL_Cont').scrollLeft = 0;
	 		}
	 	}
	 	function ISL_GoDown(){ //下翻
	 		clearInterval(MoveTimeObj);
	 		if(MoveLock) return;
	 		MoveLock = true;
	 		ISL_ScrDown();
	 		MoveTimeObj = setInterval('ISL_ScrDown()', 10);
	 		setTimeout("clearInterval(MoveTimeObj);", 500);
	 		MoveLock = false;
	 	}
	 	function ISL_ScrDown(){ //下翻动作
	 		//alert("scrollLeft:" + GetObj('ISL_Cont').scrollLeft + ", scrollWidth:" + GetObj('List').scrollWidth);
	 		GetObj('ISL_Cont').scrollLeft += Space;
	 	}
	</script>
	</body>	
</html>