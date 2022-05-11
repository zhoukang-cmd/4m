<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<%@ include file="/js/equip.js"%>
	</head>

	<body>
		<div class="content">
			<%@ include file="/front/common/header.jsp"%>
			<div class="main">
				<div class="mian_nav">
					<div class="svn_nav">
						<dd class="bgpic">
							<a href="${ctx}/equip/equip_goEquip.html">设备台账</a>
						</dd>
						<dd>
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
						<a href="${ctx}/fault/fault_goFault.html">设备台账</a>

					</div>


					<div class="result_tab margint7">
						<div class="sysb">
							<div class="sysb01">
								<a href="javascript:floatOpen()"> <img
										src="${ctx}/images/sbtz-sysb.jpg" width="108" height="32"
										alt="所有设备" /> </a>
							</div>
						</div>
						<div class="sbtz">
							<div class="zhushebei">
								<div class="zhushebei-xinxi">
									<span>主设备信息</span>
								</div>

								<div class="sbbh">
									<ul>
										<li>
											设备编码
										</li>
										<li>
											设备状态
										</li>
										<li>
											设备名称
										</li>
										<li>
											设备型号
										</li>

										<li style="width: 200px;">
											使用单位
										</li>
										<li style="width: 200px;">
											使用地点
										</li>
										<li>
											负责人
										</li>
										<li style="width: 200px;">
											厂家信息
										</li>
										<li>
											启用时间
										</li>

									</ul>
								</div>
								<div class="sbbh-xinxi">
									<ul>
										<li>
											<s:property value="equip.equipCode" />
											_
											<s:property value="equip.equipNum" />
										</li>

										<li>
											<s:property value="equip.state" />
										</li>
										<li>
											<s:property value="equip.equipName" />
										</li>
										<li>
											<s:property value="equip.model" />
										</li>
										<li style="width: 200px;">
											<s:property value="equip.usersection" />
										</li>
										<li style="width: 200px;">
											<s:property value="equip.useaddress" />
										</li>
										<li>
											<s:property value="equip.officer" />
										</li>
										<li style="width: 200px;">
											<s:property value="equip.manuInfo" />
										</li>
										<li>
											<s:property value="equip.startTime" />
										</li>
									</ul>
								</div>
							</div>
							<div class="zsbxx">
								<div class="zsbxin-wenzi">
									<span>子设备信息</span>
								</div>
								<div class="cxtj">
									<form method="post" id="selQueryCEquip">
										<div class="zsbxx-chaxun">
											<ul>
												<li>
													<span>设备名称</span>

													<input type="text" name="cEquipName" />
												</li>
												<li>
													<span>启用时间</span>

													<input type="text" name="csTime" id="csTime"
														value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd" />"
														onclick="WdatePicker({el:'csTime', dateFmt:'yyyy-MM-dd '})" />
													至
													<input type="text" name="ceTime" id="ceTime"
														value="<fmt:formatDate value="${endTime}" type="date" pattern="yyyy-MM-dd" />"
														onclick="WdatePicker({el:'ceTime', dateFmt:'yyyy-MM-dd '})" />
												</li>

											</ul>
										</div>
									</form>
									<div class="zsbxx-tj">
										<ul>
											<li>
												<a href="javascript:selQueryCEquip()"> <img
														src="${ctx}/images/sbtz-cx.jpg" width="47" height="22" />
												</a>
											</li>
											<li>
												<a href="javascript:openAddCE('cm')"><img
														src="${ctx}/images/sbtz-tj.jpg" width="47" height="22">
												</a>
											</li>
											<li>
												<a href="javascript:openModifyCE()"><img
														src="${ctx}/images/sbtz-xg.jpg" width="47" height="22">
												</a>
											</li>
											<li>
												<a href="javascript:delSelect('cm')"><img
														src="${ctx}/images/sbtz-sc.jpg" width="47" height="22">
												</a>
											</li>
										</ul>
									</div>
								</div>
								<div class="zsbxx-xz">
									<ul>
										<li style="width: 72px;">
											<a href="javascript:selectAll('cm')">选择</a>
										</li>
										<li style="width: 72px;">
											序号
										</li>
										<li>
											设备编码
										</li>
										<li>
											设备名称
										</li>
										<li>
											设备编号
										</li>
										<li>
											设备型号
										</li>
										<li style="width: 284px;">
											厂家信息
										</li>
										<li>
											启用时间
										</li>
										<li style="width: 72px;">
											数量
										</li>
									</ul>

								</div>
								<s:iterator value="cequip" id="ce" status="s">
									<div class="zsbxx-xh">
										<ul>
											<li style="width: 72px;">
												<input type="checkbox" name="cm"
													value="<s:property value = "#ce.cequipId"/>" />
											</li>
											<li style="width: 72px;">
												<s:property value="#s.count" />
											</li>
											<li >
											<input type="hidden" id="<s:property value = "#ce.cequipId"/>Code" value="<s:property value="#ce.cequipCode" />" />
												<s:property value="#ce.cequipCode" />
											</li>
											<li style="cursor: pointer;" id="<s:property value = "#ce.cequipId"/>Name" onmouseover="showData('<s:property value = "#ce.cequipId"/>')" onmouseout="hideData('<s:property value = "#ce.cequipId"/>')">
												<s:property value="#ce.cequipName"/>
											</li>


											<div class="fudong" id="<s:property value = "#ce.cequipId"/>">
												<div class="fudong-csbt">
													<p>
														<s:property  value="#ce.cequipName" />参数
													</p>
												</div>
												<div class="fudong-csnr">
													<ul>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>1"></span>
															<span id="<s:property value = "#ce.cequipId"/>1-1"></span>
														</li>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>2"></span>
															<span id="<s:property value = "#ce.cequipId"/>2-2"></span>
														</li>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>3"></span>
															<span id="<s:property value = "#ce.cequipId"/>3-3"></span>
														</li>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>4"></span>
															<span id="<s:property value = "#ce.cequipId"/>4-4"></span>
														</li>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>5"></span>
															<span id="<s:property value = "#ce.cequipId"/>5-5"></span>
														</li>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>6"></span>
															<span id="<s:property value = "#ce.cequipId"/>6-6"></span>
														</li>
														<li>
															<span id="<s:property value = "#ce.cequipId"/>7"></span>
															<span id="<s:property value = "#ce.cequipId"/>7-7"></span>
														</li>
														<li>
														<span id="<s:property value = "#ce.cequipId"/>8"></span>
															<span id="<s:property value = "#ce.cequipId"/>8-8"></span>
														</li>
													</ul>
												</div>
											</div>


											<li>
												<s:property value="#ce.cequipNum" />
											</li>
											<li>
												<s:property value="#ce.cequipModel" />
											</li>
											<li style="width: 284px;">
												<s:property value="#ce.manuInfo" />
											</li>
											<li>
												<s:property value="#ce.startTime" />
											</li>
											<li style="width: 72px;">
												<s:property value="#ce.cequipStore" />
											</li>
									</div>
								</s:iterator>


								<%@ include file="/front/equip_addCEquip.jsp"%>

								<%@ include file="/front/equip_modifyCEquip.jsp"%>

							</div>
							<div class="lssbxx">
								<div class="lssbxx-wenzi">
									<span>历史变动信息</span>
								</div>
								<div class="lssbxx-cxtj">
									<form method="post" id="selQueryChange">
										<div class="lssbxx-chaxun">
											<ul>
												<li>

													<span>设备状态</span>



													<select id="sel" name="state"
														style="width: 100px; height: 24px; text-align: center;">
														<option value="所有">
															所有状态
														</option>
														<option value="正常">
															正常
														</option>
														<option value="故障">
															故障
														</option>
														<option value="维护">
															维护
														</option>
														<option value="空闲">
															空闲
														</option>
													</select>
													<script language="javascript" type="text/javascript">
	var sel = '${request.state}';
	$("#sel").val(sel);
</script>
												</li>
												<li>
													<span>变动日期</span>

													<input type="text" name="sTime" id="sTime"
														value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd" />"
														onclick="WdatePicker({el:'sTime', dateFmt:'yyyy-MM-dd '})" />

													至
													<input type="text" name="eTime" id="etime"
														value="<fmt:formatDate value="${endTime}" type="date" pattern="yyyy-MM-dd" />"
														onclick="WdatePicker({el:'etime', dateFmt:'yyyy-MM-dd '})" />

												</li>

											</ul>
										</div>
									</form>
									<div class="lssbxx-tj">
										<ul>
											<li>
												<a href="javascript:selQueryChange()"> <img
														src="${ctx}/images/sbtz-cx.jpg" width="47" height="22" />
												</a>
											</li>
											<li>
												<a href="javascript:openAddChange()"><img
														src="${ctx}/images/sbtz-tj.jpg" width="47" height="22" />
												</a>
											</li>
											<li>
												<a href="javascript:openModifyChange()"><img
														src="${ctx}/images/sbtz-xg.jpg" width="47" height="22" />
												</a>
											</li>
											<li>
												<a href="javascript:delSelect('bm')"><img
														src="${ctx}/images/sbtz-sc.jpg" width="47" height="22">
												</a>
											</li>
										</ul>
									</div>
								</div>
								<div class="lssbxx-xz">
									<ul>
										<li style="width: 62px;">
											<a href="javascript:selectAll('bm')">选择</a>
										</li>
										<li style="width: 62px;">
											序号
										</li>
										<li>
											管理部门变动
										</li>
										<li>
											使用部门变动
										</li>

										<li>
											责任人变动
										</li>
										<li style="width: 238px;">
											使用地点变动
										</li>

										<li>
											状态变动
										</li>
										<li>
											变动日期
										</li>
										<li style="width: 238px;">
											变动原因
										</li>

									</ul>


								</div>
								<s:iterator value="equipChange" id="change" status="s">
									<div class="lssbxx-xh">
										<ul>
											<li style="width: 62px;">
												<input type="checkbox" name="bm"
													value="<s:property  value="#change.changeId"/>" />
											</li>
											<li style="width: 62px;">
												<s:property value="#s.count" />
											</li>
											<li>
												<s:property value="#change.managesection" />
											</li>
											<li>
												<s:property value="#change.usersection" />
											</li>
											<li>
												<s:property value="#change.useaddress" />
											</li>
											<li style="width: 238px;">
												<s:property value="#change.officer" />
											</li>
											<li>
												<s:property value="#change.state" />
											</li>
											<li>
												<s:property value="#change.date" />
											</li>
											<li style="width: 238px;">
												<s:property value="#change.reason" />
											</li>
									</div>
								</s:iterator>
							</div>
						</div>


						<%@ include file="/front/equip_addChange.jsp"%>
						<%@ include file="/front/equip_modifyChange.jsp"%>


					</div>

				</div>
			</div>
			<%@ include file="/front/common/footer.jsp"%>
		</div>

		<%@ include file="/front/equip_AllEquip.jsp"%>



		<script language="javascript" type="text/javascript">
	 //更改设备
   	function deal(){	
    	$("#form_default").attr("action","${ctx}/equip/equip_goEquip.html"+"?tempId="+Math.round(Math.random()*100));
    	$("#form_default").submit();
   	}
	</script>
	</body>
</html>
