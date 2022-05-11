<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="zsbtj" id="modifyCE">
  <div class="zsbtj-top">
   <p>修改子设备</p>
  <span onclick="closeModifyCE()"> 关闭</span>
  </div>
  
  <div class="zsbtj-nr">
   <ul>
  <li>
  <span>设备编码：</span>
  <input type="text" disabled="disabled" id="ceCode"/></li>
  <form action="${ctx}/equip/equip_modifyCEquip.html" id="mcform" method="post">
	   <input type="hidden" name="ceq.cequipId" id="ceId"/>
	  <input type="hidden" value=""  name="ceq.cequipCode" id="hceCode"/>
	  <li>
	  <span>设备名称：</span>
	  <input type="text" name="ceq.cequipName"  id="ceName">
	  </li>
	  <li>
	  <span>设备编号：</span>
	  <input type="text" name="ceq.cequipNum"  id="ceNum"></li>
	  <li>
	  <span>设备型号：</span>
	  <input type="text" name="ceq.cequipModel" id="ceModel">
	  </li>
	  <li>
	  <span>厂家信息：</span>
	  <input type="text" name="ceq.manuInfo"  id="cemanuInfo"></li>
	  <li>
	     <span class="prelar-sj"><b >启用时间：</b>
			<input name="ceq.startTime" type="text" id="cesT" style="width:115px" />
			<a
				href="javascript:WdatePicker({el:'cesT', dateFmt:'yyyy-MM-dd'})"><img
					class="btn_time-zsbtj" src="${ctx}/images/btn_wind.jpg" />
			</a>
			
			</span>
	  </li>
	  <li>
	  <span>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</span>
	  <input type="text" name="ceq.cequipStore" id="ceStore"></li>
	  </ul>
	  </div>
  </form>
  <div class="zsbtj-dibu">
  <ul>
 <li><a href="javascript:updmceqSubmit()"><img src="${ctx}/images/sbtz-bc.jpg" width="80" height="32" /></a></li>
  <li><a href="javascript:modifyCequipReset()"><img src="${ctx}/images/sbtz-cz.jpg" width="80" height="32" /></a></li>
  </ul>
  </div>
  
</div>
	<script type="text/javascript">
	//提交查询
	function updmceqSubmit(){
	if(document.getElementById("ceName").value ==""){
		    alert("请输入设备名称");
		    return;
		}
		if (document.getElementById("ceNum").value == "") {
			alert("请输入设备编号");
			return;
		}
		if (document.getElementById("ceModel").value == "") {
			alert("请输入设备型号");
			return;
		}
		if (document.getElementById("cemanuInfo").value == "") {
			alert("请输入厂家信息");
			return;
		}
		
		if(document.getElementById("cesT").value == ""){
	        alert("请输入启用时间");
		    return;
		}
		
		var num = document.getElementById("ceStore").value;
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
		$("#mcform").submit();
	}
	function modifyCequipReset(){
		$("#mcform")[0].reset();
		var s=$("#ceCode").val();
		$("#hceCode").val(s);
	}
</script>