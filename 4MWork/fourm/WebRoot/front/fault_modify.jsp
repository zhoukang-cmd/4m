<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"
			media="all" />
	</head>

	<body>
		<div class="content">
			<%@ include file="/front/common/header.jsp"%>
			<div class="main">
				<div class="mian_nav">
					<div class="svn_nav">
						<dd>
							<a href="${ctx}/equip/equip_goEquip.html">设备台账</a>
						</dd>
						<dd class="bgpic">
							<a href="${ctx}/fault/fault_goFault.html">历史故障</a>
						</dd>
						<dd>
							<a href="${ctx}/repair/repair_goRepair.html">运行维护</a>
						</dd>
					</div>
					<%@ include file="/front/common/nowdate.jsp"%>
				</div>
				<%@ include file="/front/common/left.jsp"%>
				<div class="mainright">
					<div class="subNav">
						当前位置：
						<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;
						<a href="${ctx}/fault/fault_goFault.html">设备信息</a>&gt;
						<a href="${ctx}/fault/fault_goFault.html">历史故障</a>&gt;修改历史故障
					</div>
					<div class="download margint7">
						<ul>
							<li>
								设备名称：${sessionScope.currentSession.equipName}
							</li>
							<li>
								设备型号：${sessionScope.currentSession.model}
							</li>
							<li>
								产品说明书：
								<a
									href="${ctx}/front/download/equipDownload.jsp?name=${sessionScope.currentSession.manual}">下载</a>
							</li>
							<li>
								设计参数：
								<a
									href="${ctx}/front/download/equipDownload.jsp?name=${sessionScope.currentSession.designParam}">下载</a>
							</li>
							<li>
								设计简图：
								<a
									href="${ctx}/front/download/equipDownload.jsp?name=${sessionScope.currentSession.designSketch}">下载</a>
							</li>
							<li>
								厂家信息：${sessionScope.currentSession.manuInfo}
							</li>
							<li>
								起用时间：
								${sessionScope.currentSession.startTime}
									
							</li>
						</ul>
					</div>
					<form action="" id="form_modify" method="post">
						<div class="reference2 margint25">
							<dd class="prelar">
								故障时间：
								<input name="fault.faultTime" type="text" id="faultTime" style="width: 150px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									value="${fault.faultTime}" />
								<a
									href="javascript:WdatePicker({el:'faultTime', dateFmt:'yyyy-MM-dd HH:mm:ss'})"><img
										class="btn_time" src="${ctx}/images/btn_wind.jpg" />
								</a>
							</dd>
						</div>
						<div class="reference2 margint7">
							<dd class="prelar">
								故障部位：
									<select id="faultPosition" name="fault.faultPosition" >
					              <option>请选择故障部位</option>
					             </select>
							</dd>
							<dd class="prelar">
								&nbsp;&nbsp;&nbsp;故障参数：
											
					             <select id="faultField" name="fault.faultField" >
					              <option>请选择故障参数</option>
					             </select>
							</dd>
						</div>
						<div class="reference2 margint7">
							<dd class="prelar">
								故障等级：
								<select name="fault.faultLevel" id="faultLevel">
									<option <s:if test="fault.faultLevel=='轻度'">selected</s:if>>
										轻度
									</option>
									<option <s:if test="fault.faultLevel=='中度'">selected</s:if>>
										中度
									</option>
									<option <s:if test="fault.faultLevel=='重度'">selected</s:if>>
										重度
									</option>
								</select>
							</dd>
						</div>
						<div class="reference3 margint7">
							<dd class="prelar">
								发展趋势：
								<textarea name="fault.faultTrend" cols="65" rows="4"
									id="questrend">${fault.faultTrend}</textarea>
							</dd>
						</div>
						<input type="hidden" id="faultId" name="fault.faultId"
							value="${fault.faultId}" />
					</form>
					<div class="page_btn marginl65">
						<dd>
							<a href="javascript:checkAndSubmit()"><img src="${ctx}/images/btn_amend.jpg" width="80" height="32"
								/></a>
						</dd>
						<dd>
							<img src="${ctx}/images/btn_back.jpg" width="80" height="32"
								onclick="javascript:history.go(-1);" />
						</dd>
					</div>
				</div>
			</div>
			<%@ include file="/front/common/footer.jsp"%>
		</div>
		<script language="javascript" type="text/javascript"
			src="${ctx}/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		
		
		
		$("document").ready(function (){
    	var url="${ctx}/equip/equip_getCequipType.html"+"?tempId="+Math.round(Math.random()*100);
 		$.ajax({
			url:url,
			dataType: "json",		
			data:{},          
			error: function(XMLHttpRequest, textStatus, errorThrown){
	   			alert("无法取得设备参数分组信息！");
			},
    		success: function(json){
    			var htm="";
    			
    			$.each(json.typeLs,function(i, ext){
    				htm=htm+"<option value='"+ext.typeName+"'>"+ext.typeName+"</option>";
    				
    				
    			});
    			$("#faultPosition").html(htm);
    			var p='${fault.faultPosition}';
    			$("#faultPosition").val(p);
			}
		});
 		
 		
 		var url="${ctx}/fault/fault_getExtList.html"+"?tempId="+Math.round(Math.random()*100);
 		$.ajax({
			url:url,
			dataType: "json",		
			data:{},          
			error: function(XMLHttpRequest, textStatus, errorThrown){
	   			alert("无法取得设备参数分组信息！");
			},
    		success: function(json){
    			var htm="";
    			
    			$.each(json.extList,function(i, ext){
    				htm=htm+"<option value='"+ext.fieldDesc+"'>"+ext.fieldDesc+"</option>";
    				
    				
    			});
    			$("#faultField").html(htm);
    			var f='${fault.faultField}';
    			$("#faultField").val(f);
			}
		});
 		
    });
		
		
		
		
		
		
	//锁定选择设备功能
	document.getElementById("compList").disabled = "disabled";
	document.getElementById("mineList").disabled = "disabled";
	document.getElementById("roomList").disabled = "disabled";
	document.getElementById("equipList").disabled = "disabled";
	//提交查询
	function checkAndSubmit() {
		if (document.getElementById("faultTime").value == "") {
			alert("请输入故障时间");
			return;
		}
		if (document.getElementById("faultPosition").value == "") {
			alert("请输入故障部位");
			return;
		}
		if (document.getElementById("faultField").value == "") {
			alert("请输入故障参数");
			return;
		}
		if (document.getElementById("questrend").value == "") {
			alert("请输入发展趋势");
			return;
		}
		$("#form_modify").attr(
				"action",
				"${ctx}/fault/fault_modifyFault.html" + "?tempId="
						+ Math.round(Math.random() * 100));
		$("#form_modify").submit();
	}
</script>
	</body>
</html>
