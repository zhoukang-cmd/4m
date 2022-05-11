<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
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
						<a href="${ctx}/fault/fault_goFault.html">设备信息</a>&gt;历史故障
						<div class="btn_determine2">
							<a href="javascript:selquery()"><img src="${ctx}/images/btn_determine.jpg"
									width="80" height="32" />
							</a>
						</div>
					</div>
					<div class="reference">
						<dd class="prelar">
							开始时间：
							<input name="time" type="text" id="stime" style="width:150px"
								value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
							<a
								href="javascript:WdatePicker({el:'stime', dateFmt:'yyyy-MM-dd HH:mm:ss'})"><img
									class="btn_time" src="${ctx}/images/btn_wind.jpg" />
							</a>
							
							</dd>
							
							<dd class="prelar">
							 &nbsp;结束时间：
							<input name="time" type="text" id="etime" style="width:150px"
								value="<fmt:formatDate value="${endTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
							<a
								href="javascript:WdatePicker({el:'etime', dateFmt:'yyyy-MM-dd HH:mm:ss'})"><img
									class="btn_time" src="${ctx}/images/btn_wind.jpg" />
							</a>
						</dd>
					</div>
					<div class="download">
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
					<div class="result_tab margint7">
						<table width="0" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="52" align="center" bgcolor="#fafafa">
									选择
								</td>
								<td width="138" align="center" bgcolor="#fafafa">
									故障时间
								</td>
								<td width="132" align="center" bgcolor="#fafafa">
									故障部位
								</td>
								<td width="132" align="center" bgcolor="#fafafa">
									故障参数
								</td>
								<td width="108" align="center" bgcolor="#fafafa">
									故障等级
								</td>
								<td width="289" align="center" bgcolor="#fafafa">
									发展趋势
								</td>
							</tr>
							<s:iterator value="faultList" id="fau" status="stat">
								<tr>
									<td align="center">
										<input type="checkbox" name="checkbox" id="checkbox"
											value="${fau.faultId}" />
									</td>
									<td align="center">
										<a href="javascript:showPainter('${fau.faultId}')">${faultTime}</a>
									</td>
									<td align="center">
										${fau.faultPosition}
									</td>
									<td align="center">
										${fau.faultField}
									</td>
									<td align="center">
										${fau.faultLevel}
									</td>
									<td class="paddingl15">
										${fau.faultTrend}
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="page">
						<div class="page_btn">
							<dd>
								<img src="${ctx}/images/btn_all.jpg" width="80" height="32"
									onclick="checkAll()" />
							</dd>
							<dd>
								<img src="${ctx}/images/btn_off.jpg" width="80" height="32"
									onclick="deleteMail()" />
							</dd>
							<dd>
								<img src="${ctx}/images/btn_amend.jpg" width="80" height="32"
									onclick="modify()" />
							</dd>
							<dd>
								<a href="${ctx}/fault/fault_goAddFault.html"><img
										src="${ctx}/images/btn_add.jpg" width="80" height="32" />
								</a>
							</dd>
						</div>
						<div class="quotes">
							<span class="disabled">
								每页${pageCtrl.pageSize}条，第${pageCtrl.currPage}页/共${pageCtrl.pageCount}页
							</span>
							<!-- TODO 当前页应改为使用class="current"风格并取消超链接 -->
							<a href="javascript:gotoPage(1)">首页</a><a
								href="javascript:gotoPage(${pageCtrl.prePage})">上页</a><a
								href="javascript:gotoPage(${pageCtrl.nextPage})">下页</a><a
								href="javascript:gotoPage(${pageCtrl.pageCount})">末页</a>
						</div>
					</div>
				</div>
			</div>
			
			
			
			<%@ include file="/front/common/footer.jsp"%>
		</div>
		
		<script language="javascript" type="text/javascript">
	 //更改设备
   	function deal(){	
    	$("#form_default").attr("action","${ctx}/fault/fault_goFault.html"+"?tempId="+Math.round(Math.random()*100));
    	$("#form_default").submit();
   	}
   function  showPainter(id){
   window.open("${ctx}/fault/fault_goPainter.html?detailId="+id);
   }
	</script>
	</body>
</html>
