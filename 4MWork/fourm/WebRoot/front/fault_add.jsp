<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"
			media="all" />
		<%@ include file="/js/fault.js"%>
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
						<a href="${ctx}/fault/fault_goFault.html">历史故障</a>&gt;添加历史故障
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
					<form action="${ctx}/fault/fault_addFault.html" id="form_add"
						method="post">
						<div class="reference2 margint25">
							<dd class="prelar">
								故障时间：
								<input name="fault.faultTime" type="text" id="faultTime" style="width: 150px;" />
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
					              <option>请选择故障部位</option>
					             </select>
							</dd>
						</div>
						<div class="reference2 margint7">
							<dd class="prelar">
								故障等级：
								<select name="fault.faultLevel" size="sss" id="faultLevel">
									<option>
										轻度
									</option>
									<option>
										中度
									</option>
									<option>
										重度
									</option>
								</select>
							</dd>
						</div>
						<div class="reference3 margint7">
							<dd class="prelar">
								发展趋势：
								<textarea name="fault.faultTrend" cols="" rows=""
									id="faultTrend"></textarea>
							</dd>
						</div>
					</form>
					<div class="page">
						<div class="page_btn marginl65">
							<dd>
								<a href="javascript:checkAndSubmit()"><img src="${ctx}/images/btn_add.jpg" width="80"
										height="32"/>
								</a>
							</dd>
							<dd>
								<a href="${ctx}/fault/fault_goFault.html"><img
										src="${ctx}/images/btn_back.jpg" width="80" height="32" />
								</a>
							</dd>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="/front/common/footer.jsp"%>
		</div>
		<script language="javascript" type="text/javascript"
			src="${ctx}/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		
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
		if (document.getElementById("faultTrend").value == "") {
			alert("请输入发展趋势");
			return;
		}
		$("#form_add").submit();
	}
</script>
	</body>
</html>