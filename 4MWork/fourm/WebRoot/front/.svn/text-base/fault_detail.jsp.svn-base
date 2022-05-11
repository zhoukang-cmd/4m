<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>中矿四迈 4M系统</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css"
			media="all" />
	</head>
	<body>
		<div class="content">
			<%@ include file="/front/common/header.jsp"%>
			<div class="main" style="height: 400px;">
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
						<a href="${ctx}/fault/fault_goFault.html">历史故障</a>&gt;历史故障详情
					</div>
					<div class="reference2 margint25 paddingl15">
						<dd class="prelar">
							故障时间：${fault.faultTime}
						</dd>
					</div>
					<div class="reference2 margint7 paddingl15">
						<dd class="prelar">
							故障部位：${fault.faultPosition}
						</dd>
					</div>
					<div class="reference2 margint7 paddingl15">
						<dd class="prelar">
							故障等级：${fault.faultLevel}
						</dd>
					</div>
					<div class="reference3 margint7 paddingl15">
						<dd class="prelar">
							发展趋势：${fault.faultTrend}
						</dd>
					</div>
					<div class="page">
						<div class="page_btn marginl65">
							<dd>
								<img src="${ctx}/images/btn_back.jpg" width="80" height="32"
									onclick="javascript:history.go(-1);" />
							</dd>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/front/common/footer.jsp"%>
		<script type="text/javascript">
	//锁定选择设备功能
	document.getElementById("compList").disabled = "disabled";
	document.getElementById("mineList").disabled = "disabled";
	document.getElementById("roomList").disabled = "disabled";
	document.getElementById("equipList").disabled = "disabled";
</script>
	</body>
</html>
