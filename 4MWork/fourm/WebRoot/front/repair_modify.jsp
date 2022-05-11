<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"
			media="all" />
		<%@ include file="/js/repair.js"%>
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
						<dd>
							<a href="${ctx}/fault/fault_goFault.html">历史故障</a>
						</dd>
						<dd class="bgpic">
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
						<a href="${ctx}/repair/repair_goRepair.html">运行维护</a>&gt;修改运行维护
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
								<fmt:formatDate value="${sessionScope.currentSession.startTime}"
									type="date" pattern="yyyy-MM-dd HH:mm:ss" />
							</li>
						</ul>
					</div>
					<form action="" id="modify" method="post">
						<div class="reference2 margint25">
							<dd class="prelar">
								维护时间：
								<input name="repair.repairTime" type="text" id="repairTime"
									value="${repair.repairTime}" />
								<a
									href="javascript:WdatePicker({el:'repairTime', dateFmt:'yyyy-MM-dd HH:mm:ss'})"><img
										class="btn_time" src="${ctx}/images/btn_wind.jpg" />
								</a>
						</div>
						<div class="reference2 margint7">
							<dd class="prelar">
								维护对象：
								<input type="text" name="repair.repairObject" id="repairObject"
									value="${repair.repairObject}" />
							</dd>
						</div>
						<div class="reference3 margint7">
							<dd>
								维修内容：
								<textarea name="repair.repairContent" cols="" rows=""
									id="repairContent">${repair.repairContent}</textarea>
							</dd>
							<dd class="margint7">
								解决问题：
								<textarea name="repair.repairQues" cols="" rows=""
									id="repairQues">${repair.repairQues}</textarea>
							</dd>
							<dd class="margint7">
								遗留问题：
								<textarea name="repair.remainQues" cols="" rows=""
									id="remainQues">${repair.remainQues}</textarea>
							</dd>
						</div>
						<input type="hidden" id="repairId" name="repair.repairId"
							value="${repair.repairId}" />
					</form>
					<div class="page">
						<div class="page_btn marginl65">
							<dd>
								<img src="${ctx}/images/btn_amend.jpg" width="80" height="32"
									onclick="checkAndSubmit()" />
							</dd>
							<dd>
								<img src="${ctx}/images/btn_back.jpg" width="80" height="32"
									onclick="javascript:history.go(-1);" />
							</dd>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="/front/common/footer.jsp"%>
		</div>
		<script type="text/javascript"
			src="${ctx}/js/common/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
	//锁定选择设备功能
	document.getElementById("compList").disabled = "disabled";
	document.getElementById("mineList").disabled = "disabled";
	document.getElementById("roomList").disabled = "disabled";
	document.getElementById("equipList").disabled = "disabled";
	//提交查询
	function checkAndSubmit() {
		if (document.getElementById("repairTime").value == "") {
			alert("请输入维护时间");
			return;
		}
		if (document.getElementById("repairObject").value == "") {
			alert("请输入维护对象");
			return;
		}
		if (document.getElementById("repairContent").value == "") {
			alert("请输入维修内容");
			return;
		}
		if (document.getElementById("repairQues").value == "") {
			alert("请输入已解决问题");
			return;
		}
		if (document.getElementById("remainQues").value == "") {
			alert("请输入遗留问题");
			return;
		}
		$("#modify").attr(
				"action",
				"${ctx}/repair/repair_modifyRepair.html" + "?tempId="
						+ Math.round(Math.random() * 100));
		document.getElementById("modify").submit();
	}
</script>
		<script language="javascript" type="text/javascript"
			src="${ctx}/My97DatePicker/WdatePicker.js"></script>
	</body>
</html>