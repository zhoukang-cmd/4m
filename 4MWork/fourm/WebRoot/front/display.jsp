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
                	<div class="subNav">当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;实时数据
                    	<div class="btn_determine5" id="message"></div>
                    	<div class="btn_determine4" ><a href="javascript:floatOpen();"><img src="${ctx}/images/btn_show.png" width="108" height="32" /></a></div>
                    	<div class="btn_determine1" ><a href="${ctx}/display/display_goFullScreen.html"><img src="${ctx}/images/btn_full.jpg" width="80" height="32" /></a></div>
                    </div>
                   
                    <div class="real_data"  id="contentarea"  style="width:100%; height:550px;">
                    </div>
                   
                </div>
          	</div>
            <%@ include file="/front/common/footer.jsp"%>
        </div>
        <div class="floatbox" id="floatWindow" >
			<div class="box">
				<div class="titlebar">
					<span class="title01">全部参数</span>
					<span class="info" id='titleInfo'></span>
					<span class="close" onclick="floatClose();">关闭</span>
				</div>
                <div class="group" id="group"></div>
			</div>
        </div>
		<script type="text/javascript" src="${ctx}/js/common/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/jQuery.timers.js"></script>
		<script type="text/javascript">
		
	 	function deal(){
	 		$("#message").html(""); 
	 		var url = "${ctx}/display/display_getExtList.html"+"?tempId="+Math.round(Math.random()*100);
	 		$.ajax({
					url:url,
					dataType: "json",		
					data:{},          
					error: function(XMLHttpRequest, textStatus, errorThrown){
						$("#message").html("deal()无法取得设备字段！");
					},
		    		success: function(json){
						$("#contentarea").empty(); 
		    		    if(json.extList.length > 0){
		    		    	var htmlstr = "";
		    		    	$.each(json.extList,function(i, ext){
		    		    		htmlstr += "<div id=\"ext"+ext.fieldNo+ext.fieldType+"\"><label>"+ext.fieldName+"：</label><input id=\"textext"+ext.fieldNo+ext.fieldType+"\" type=\"text\" size=\"4\" value=\"\"/></div>";
							});
		    		    	
		    		    	$("#contentarea").css("background-image", "url(" + json.imagePath + "?tempId=" + Math.round(Math.random()*100) + ")");
		    		    	$("#contentarea").html(htmlstr);
		    		    	var ua = navigator.userAgent;
		    		    	if ((ua.indexOf('MSIE 6.0') > -1) || (ua.indexOf('MSIE 7.0') > -1)) { //IE 6,7浏览器
		    		    		$("#message").html($("#message").html()+"deal()://IE 6,7浏览器");
			    		    	var basicOffsetTop = null;
								$.each(json.extList,function(i, ext){
									var displayX = parseInt(ext.fieldDisplayX * 740);
									var displayY = parseInt(ext.fieldDisplayY * 550);
									if (basicOffsetTop == null) {
			    		    			basicOffsetTop = 0 - document.getElementById("ext"+ext.fieldNo+ext.fieldType).offsetTop;
									} else if (basicOffsetTop <= 0) {
										basicOffsetTop = document.getElementById("ext"+ext.fieldNo+ext.fieldType).offsetTop + basicOffsetTop;
									}
			    		    		
									$("#ext"+ext.fieldNo+ext.fieldType).css("position","relative"); 
									$("#ext"+ext.fieldNo+ext.fieldType).css("left", displayX);
									if (basicOffsetTop > 0) {
										$("#ext"+ext.fieldNo+ext.fieldType).css("top", displayY - basicOffsetTop * i);
									} else {
										$("#ext"+ext.fieldNo+ext.fieldType).css("top", displayY);
									}
								});
							} else { //Firefox, Chrome, IE 8以上或其它符合规范的浏览器
								var sign=document.getElementById('contentarea');
								var baseX = parseInt(sign.offsetLeft);
								var baseY = parseInt(sign.offsetTop);
								$.each(json.extList,function(i, ext){	
									$("#ext"+ext.fieldNo+ext.fieldType).css("position","absolute"); 
									$("#ext"+ext.fieldNo+ext.fieldType).css("left", baseX + parseInt(ext.fieldDisplayX * 740));
									$("#ext"+ext.fieldNo+ext.fieldType).css("top", baseY + parseInt(ext.fieldDisplayY * 550));
								});
								$("#message").html($("#message").html()+"deal() ×××××××××××××["+baseX+","+baseY+"]");
							}
							$('body').everyTime('5s','A',showData,0,true);
		    		    }
					}
				});
	 	}
	
	
	 	function showData(){	
	 		var date = new Date();
			var now = date.getFullYear() + (date.getMonth()) +date.getDate() + date.getHours() + date.getMinutes() + date.getSeconds(); 
	 		var url = "${ctx}/display/display_showData.html?time="+now+"&tempId="+Math.round(Math.random()*100);
			$.ajax({
					url:url,
					dataType: "json",		
					data:{},          
					error: function(XMLHttpRequest, textStatus, errorThrown){
						$("#message").html($("#message").html()+"showData()无法获得设备的运行数据！");
					},
		    		success: function(json){	 
		    		    if(json.extList.length > 0){
		    		    	$("#message").html(json.info);
		    		    	$.each(json.extList,function(i, ext){
				    		    $("#textext"+ext.orderNo+ext.fieldType).val(ext.extString);
		    		    		//alert(ext.orderNo+ext.fieldType+ext.extString+ext.alarmFlag);
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
		//显示全部参数浮动窗口
	 	function floatOpen() {
	 		var url = "${ctx}/display/groupDisplay_getGroupList.html"+"?tempId="+Math.round(Math.random()*100);
	 		floatWindow.style.visibility='visible';
	 		$("#floatWindow").fadeIn();
			$("#titleInfo").html("");
			$.ajax({
					url:url,
					dataType: "json",		
					data:{},          
					error: function(XMLHttpRequest, textStatus, errorThrown){
			   			alert("floatOpen()无法取得设备参数分组信息input！");
					},
		    		success: function(json){
						$("#contentarea").empty(); 
						
		    		    if(json.flagTableName ==1 && json.groupList.length > 0){
		    		    	var grids = 0;
		    		    	$.each(json.groupList,function(i, ext){
		    		    		if(! document.getElementById("group"+ext.groupId)){
		    		    			$("#group").append("<dl id=\"group" + ext.groupId + "\"><dd class=\"title\">" + ext.groupName + "</dd></dl>");
		    		    			grids += 1;
		    		    		}
		    		    		$("#group"+ext.groupId).append("<dd id=\"group_field" + ext.fieldNo + "\">" + ext.fieldName + "：<span id=\"group_value" + ext.fieldNo + "\"></span></dd>");
		    		    		grids += 1;
							});
		    		    	var lines = (grids > 75) ? ((grids - grids % 5) / 5 + 1) : 15;
	    		    		$(".floatbox .box").animate({height:(((lines > 15) ? (500 + 26 * (lines - 15)) : 500))});
		    		    	$("#group").html($(".group dd").detach());
		    		    	for (var i=0; i<5; i++) {
		    		    		$("#group").append("<dl id=\"fieldList" + i + "\"></dl>");
		    		    		for (var j=1; j<=lines; j++) {
		    		    			if (i * lines + j <= grids) {
		    		    				$("#fieldList"+i).append($("#group dd:first").detach());
		    		    			}
		    		    		}
		    		    	}
							$('body').everyTime('5s','A',groupData);
		    		    }else {
		    		       $("#group").html("floatOpen()该设备未设置参数分组信息");
		    		    }
					}
				});
	 	}
		//分组显示全部参数
	 	function groupData() {
	 		var date = new Date();
			var now = date.getFullYear() + (date.getMonth()) +date.getDate() + date.getHours() + date.getMinutes() + date.getSeconds(); 
	 		var url = "${ctx}/display/groupDisplay_groupData.html?time="+now+"&tempId="+Math.round(Math.random()*100); 
			$("#titleInfo").html("");
			$.ajax({
				url:url,
				dataType: "json",		
				data:{},          
				error: function(XMLHttpRequest, textStatus, errorThrown){
					$("#titleInfo").html("groupData()无法获得设备的运行数据！");
				},
	    		success: function(json){	
	    		    if(json.extList.length > 0){
	    		    	$.each(json.extList,function(i, ext){
			    		    $("#group_value"+ext.orderNo).html(ext.extString);
			    		    if(ext.alarmFlag){
			    		    	$("#group_value"+ext.orderNo).css("color","red");
				    		}
						});
	    		    }
				}
			});
	 	}
		//关闭全部参数浮动窗口
	 	function floatClose() {
	 		$("#floatWindow").fadeOut();
			$("#group").empty(); 
	 		deal();
		}
	</script>
	</body>	
</html>
