<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="zsbtj" id="addChange">
  <div class="zsbtj-top">
   <p>添加历史变动信息</p>
  <span onclick="closeAddChange()"> 关闭</span>
  </div>
  <form action="${ctx}/equip/equip_addEquipChange.html" id="form_addChange"
						method="post">
		  <div class="zsbtj-nr">
		   <ul>
		  <li>
		  <span>管理部门：</span>
		  <input type="text" name="echange.managesection" id="managesection"></li>
		  <li>
		  <span>使用部门：</span>
		  <input type="text" name="echange.usersection" id="usersection">
		  </li>
		  <li>
		  <span>&nbsp;&nbsp;&nbsp;责任人：</span>
		  <input type="text" name="echange.officer" id="officer"></li>
		  <li>
		  <span>使用地点：</span>
		  <input type="text" name="echange.useaddress" id="useaddress">
		  </li>
		  <li>
		  <span>状态变动：</span>
			  <select id="state" name="echange.state" 
				style="width: 117px; height: 25px; text-align: center;">
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
		</li>
		  <li>
		  <span class="prelar-sj"><b >变动日期：</b>
			<input name="echange.date" type="text" id="date" style="width:115px" />
			<a
				href="javascript:WdatePicker({el:'date', dateFmt:'yyyy-MM-dd'})"><img
					class="btn_time-zsbtj" src="${ctx}/images/btn_wind.jpg" />
			</a>
			</span>
		  </li>
		  <li>
		  <span>变动原因：</span>
		  <input type="text" name="echange.reason" id="reason"></li>
		  </ul>
		  </div>
  </form>
  <div class="zsbtj-dibu">
  <ul>
  <li><a href="#"><img src="${ctx}/images/sbtz-bc.jpg" width="80" height="32" onclick="changeSubmit()"/></a></li>
  <li><a href="#"><img src="${ctx}/images/sbtz-cz.jpg" width="80" height="32" onclick="changeReset()"/></a></li>
  </ul>
  </div>
</div>

<script type="text/javascript">
	function changeSubmit() {
	    if (document.getElementById("managesection").value == "") {
			alert("请输入管理部门");
			return;
		}
		if (document.getElementById("usersection").value == "") {
			alert("请输入使用部门");
			return;
		}
		if (document.getElementById("officer").value == "") {
			alert("请输入责任人");
			return;
		}
		if (document.getElementById("useaddress").value == "") {
			alert("请输入使用地点");
			return;
		}
		if (document.getElementById("date").value == "") {
			alert("请输入变动日期");
			return;
		}
		if (document.getElementById("reason").value == "") {
			alert("请输入变动原因");
			return;
		}
		$("#form_addChange").submit();
	}
	
	function changeReset(){
	    document.getElementById("form_addChange").reset();
	}
</script>