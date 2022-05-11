<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="zsbtj" id="zsbtj">
									<div class="zsbtj-top">
										<p>
											添加子设备
										</p>
										<span onclick="closeAddCE()"> 关闭</span>
									</div>
						<form action="${ctx}/equip/equip_addCEquip.html" id="form_add"
						method="post">
									<div class="zsbtj-nr">
										<ul>
											<li>
												<span>设备类型：</span>
												<select name="ceq.cequipCode" id="cequipCode">
												<option>暂无设备类型</option>
												</select>
											</li>
											<li>
												<span>设备名称：</span>
												<input type="text" name="ceq.cequipName" id="cequipName"/>
											</li>
											<li>
												<span>设备编号：</span>
												<input type="text" name="ceq.cequipNum" id="cequipNum"/>
											</li>
											<li>
												<span>设备型号：</span>
												<input type="text" name="ceq.cequipModel" id="cequipModel"/>
											</li>
											<li>
												<span>厂家信息：</span>
												<input type="text" name="ceq.manuInfo" id="manuInfo"/>
											</li>
											<li>
								                <span class="prelar-sj"><b >启用时间：</b>
													<input name="ceq.startTime" type="text" id="stime" style="width:115px"
														value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd "/>" />
													<a
														href="javascript:WdatePicker({el:'stime', dateFmt:'yyyy-MM-dd'})"><img
															class="btn_time-zsbtj" src="${ctx}/images/btn_wind.jpg" />
													</a>
													
													</span>
											</li>
											<li>
												<span>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</span>
												<input type="text" name="ceq.cequipStore" id="cequipStore"/>
											</li>

										</ul>
									</div>
									</form>
									<div class="zsbtj-dibu">
										<ul>
											<li>
												<a href="#"><img src="${ctx}/images/sbtz-bc.jpg" width="80"
														height="32" onmousedown="cequipSubmit()" />
												</a>
											</li>
											<li>
												<a href="#"><img src="${ctx}/images/sbtz-cz.jpg" width="80"
														height="32" onclick="cequipReset()"/>
												</a>
											</li>
										</ul>
									</div>
								</div>
 <script type="text/javascript">
	function cequipSubmit() {
		if(document.getElementById("cequipName").value ==""){
		    alert("请输入设备名称");
		    return;
		}
		if (document.getElementById("cequipNum").value == "") {
			alert("请输入设备编号");
			return;
		}
		if (document.getElementById("cequipModel").value == "") {
			alert("请输入设备型号");
			return;
		}
		if (document.getElementById("manuInfo").value == "") {
			alert("请输入厂家信息");
			return;
		}
		
		if(document.getElementById("stime").value == ""){
	        alert("请输入启用时间");
		    return;
		}
		
		var num = document.getElementById("cequipStore").value;
		var re = /^[1-9]+[0-9]*]*$/;
		if (num == "") {
			alert("请输入设备数量");
			return;
		}
		if (!re.test(num))
		{
		    alert("输入的设备数量错误，请重新输入");
		    return;
		}
		
		$("#form_add").submit();
	}
	
	function cequipReset(){
	    document.getElementById("form_add").reset();
	}
</script>
