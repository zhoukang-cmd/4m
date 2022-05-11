<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统 </title>
        <link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" media="all"/>
	</head>	
	<body style="background:none" onload="deal();" >
	    <div style="display:none;position:absolute;left:45%;top:0px;color:red;font-size:15px" id="error">注意:    数据接收发生错误</div>
		<div style="width:100%;height:100%" id="contentarea"></div>
		
	    <script type="text/javascript" src="${ctx}/js/common/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/jQuery.timers.js"></script>
		<script type="text/javascript">
	 	function deal(){	 			 	 	
	 		var url = "${ctx}/display/display_getExtList.html"+"?tempId="+Math.round(Math.random()*100);
			$.ajax({
					url:url,
					dataType: "json",		
					data:{},          
					error: function(XMLHttpRequest, textStatus, errorThrown){
			   			alert("出错了:(");
					},
		    		success: function(json){
							
						var w = 0;
						var h = 0;
						if(window.innerHeight){ //for Chrome, Firefox, IE 9 & later
							w = window.innerWidth;
							h = window.innerHeight;
						} else{ //for IE 8 & earlier
							w = document.documentElement.clientWidth;
							h = document.documentElement.clientHeight-4;
						}
		    		    if(json.extList.length > 0){
		    		   
		    		       // var htmlstr = "<img src=\"${ctx}/"+json.imagePath+"\" width=\""+w+"\" height=\""+h+"\" />";
		    		       var htmlstr = "<img src=\""+json.imagePath+"\" width=\""+w+"\" height=\""+h+"\" />";
		    		    	$.each(json.extList,function(i, ext){
		    		    		htmlstr += "<div id=\"ext"+ext.fieldNo+ext.fieldType+"\" class=\"real_data\"><label>"+ext.fieldName+"：</label><input id=\"textext"+ext.fieldNo+ext.fieldType+"\" type=\"text\" size=\"4\" value=\"\"/></div>";
							});
							htmlstr += "<div id=\"goback\"><img src=\"${ctx}/images/btn_back.jpg\" width=\"80\" height=\"32\" onclick=\"goback()\"/></div> ";
		    		    	
		    		    
		    		    	$("#contentarea").html(htmlstr+error);
		    				 var sign=document.getElementById('contentarea');
		    				 var x=sign.offsetLeft;
		    				 var y=sign.offsetTop;
							$.each(json.extList,function(i, ext){
								var displayX = ext.fieldDisplayX;
								var displayY = ext.fieldDisplayY;
								$("#ext"+ext.fieldNo+ext.fieldType).css("position","absolute"); 
								$("#ext"+ext.fieldNo+ext.fieldType).css("left",parseInt(x)+parseInt(displayX*w));
								$("#ext"+ext.fieldNo+ext.fieldType).css("top",parseInt(y)+parseInt(displayY*h));								
							});
							$("#goback").css("position","absolute"); 
							$("#goback").css("left",w-120);
							$("#goback").css("top",30);
							$("body").everyTime("6s","A",showData);
		    		    }
					}
				});
	 	}
	
	
	 	function showData(){	 		
	 		var date = new Date();
			var now = date.getFullYear() + (date.getMonth()) +date.getDate() + date.getHours() + date.getMinutes() + date.getSeconds(); 
	 		var url = "${ctx}/display/display_showData.html?time="+now;
			$.ajax({
					url:url,
					dataType: "json",		
					data:{},          
					error: function(XMLHttpRequest, textStatus, errorThrown){
			   			//alert("出错了:(");
			   			$("#error").show();
					},
		    		success: function(json){	
		    		$("#error").hide(); 
		    		    if(json.extList.length > 0){
		    		    	$.each(json.extList,function(i, ext){
				    		    $("#textext"+ext.orderNo+ext.fieldType).val(ext.extString);
				    		    if(ext.alarmFlag){
				    		    	$("#textext"+ext.orderNo+ext.fieldType).css("color","red");
					    		}else{
					    			$("#textext"+ext.orderNo+ext.fieldType).css("color","#000000");
						    	}
							});
		    		    }
					}
				});
	 	}	

	 	function goback(){
			window.location.href = "${ctx}/display/display_goDisplay.html";
	 	}
	</script>
	</body>	
</html>