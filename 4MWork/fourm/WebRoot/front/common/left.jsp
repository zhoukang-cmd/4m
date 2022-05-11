<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<%@ include file="/js/common/left.js"%>
	<div class="mainleft">
	    <div class="left_title">请选择设备：</div>
			<div class="left_cont2">
		        <ul>
		          <li>
		            <dd class="cont_name">省份名称：</dd>
		            <dd class="cont_select">
		            	<select name="provList" id="provList" onChange="getCompList();">
							<option value="">请选择</option>
						</select>
		            </dd>
		          </li>
		          <li>
		            <dd class="cont_name">集团名称：</dd>
		            <dd class="cont_select">
		            	<select name="compList" id="compList" onChange="getMineList();">
							<option value="">请选择</option>
						</select>
		            </dd>
		          </li>
		          <li>
		          	<dd class="cont_name">煤矿名称：</dd>
		            <dd class="cont_select">
			        	<select name="mineList" id="mineList" onChange="getRoomList();">
						 <option value="">请选择</option>  
						</select>
		          	</dd>
		          </li>
		          <li>
		            <dd class="cont_name">机房名称：</dd>
		            <dd class="cont_select">
		            	<select name="roomList" id="roomList" onChange="getEquipList();">
							<option value="">请选择</option>
						</select>
		            </dd>
		          </li><li>
		            <dd class="cont_name">设备名称：</dd>
		            <dd class="cont_select">
		            	<select name="equipList" id="equipList" onChange="setCurrentSession();">
			            	<option value="">请选择</option>
			        	</select>
		            </dd>
		          </li>
		        </ul>
			</div>
	    </div>
	 
<form action="" id="form_default" method="post"></form>
<script language="javascript" type="text/javascript">
	 	var powerLevel = "${sessionScope.powerLevel}";
	 	getProvList();
	 	if(powerLevel ==1){//省份用户
		 	document.getElementById("provList").setAttribute("disabled", true);
		 	$("#logo").html("${sessionScope.currentSession.provName}");
	 	}else if(powerLevel==2){//集团用户
		 	document.getElementById("provList").setAttribute("disabled", true);
		 	document.getElementById("compList").setAttribute("disabled", true);
		 	$("#logo").html("${sessionScope.currentSession.provName}"+"${sessionScope.currentSession.compName}");
	 	}else if(powerLevel==3){//煤矿用户
	 		document.getElementById("provList").setAttribute("disabled", true);
		 	document.getElementById("compList").setAttribute("disabled", true);
		 	document.getElementById("mineList").setAttribute("disabled", true);
		 	$("#logo").html("${sessionScope.currentSession.provName}"+"${sessionScope.currentSession.compName}"+"${sessionScope.currentSession.mineName}");
	 	}else if(powerLevel==4){//机房用户
	 		document.getElementById("provList").setAttribute("disabled", true);
		 	document.getElementById("compList").setAttribute("disabled", true);
		 	document.getElementById("mineList").setAttribute("disabled", true);
		 	document.getElementById("roomList").setAttribute("disabled", true);
		 	$("#logo").html("${sessionScope.currentSession.provName}"+"${sessionScope.currentSession.compName}"+"${sessionScope.currentSession.mineName}"+"${sessionScope.currentSession.roomName}");
	 	}
</script>
