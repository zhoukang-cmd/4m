<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="zsbtj" id="modifyChange">
<form action="${ctx}/equip/equip_modifyChange.html" id="mcForm" method="post">
  <input type="hidden" name="echange.changeId" id="cdetailId">
  <div class="zsbtj-top">
   <p>修改历史变动信息</p>
  <span onclick="closeModifyChange()"> 关闭</span>
  </div>
  <div class="zsbtj-nr">
   <ul>
  <li>
  <span>管理部门：</span>
  <input type="text" name="echange.managesection" id="cmanagesection"></li>
  <li>
  <span>使用部门：</span>
  <input type="text" name="echange.usersection" id="cusersection">
  </li>
  <li>
  <span>&nbsp;&nbsp;&nbsp;责任人：</span>
  <input type="text" name="echange.officer" id="cofficer"></li>
  <li>
  <span>使用地点：</span>
  <input type="text" name="echange.useaddress" id="cuseaddress">
  </li>
  <li>
  <span>状态变动：</span>
   <select id="cstate" name="echange.state" 
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
			<input name="echange.date" type="text" id="cdate" style="width:115px" />
			<a
				href="javascript:WdatePicker({el:'cdate', dateFmt:'yyyy-MM-dd'})"><img
					class="btn_time-zsbtj" src="${ctx}/images/btn_wind.jpg" />
			</a>
	</span>
  </li>
  <li>
  <span>变动原因：</span>
  <input type="text" name="echange.reason" id="creason"></li>
  
  </ul>
  </div>
 </form>
  <div class="zsbtj-dibu">
  <ul>
  <li><a href="javascript:modifyChange()"><img src="${ctx}/images/sbtz-bc.jpg" width="80" height="32" /></a></li>
  <li><a href="javascript:modifyReset()"><img src="${ctx}/images/sbtz-cz.jpg" width="80" height="32" /></a></li>
  </ul>
  </div>
  <script type="text/javascript">
  function modifyChange(){
if (document.getElementById("cmanagesection").value == "") {
			alert("请输入管理部门");
			return;
		}
		if (document.getElementById("cusersection").value == "") {
			alert("请输入使用部门");
			return;
		}
		if (document.getElementById("cofficer").value == "") {
			alert("请输入责任人");
			return;
		}
		if (document.getElementById("cuseaddress").value == "") {
			alert("请输入使用地点");
			return;
		}
		if (document.getElementById("cdate").value == "") {
			alert("请输入变动日期");
			return;
		}
		if (document.getElementById("creason").value == "") {
			alert("请输入变动原因");
			return;
		}
    $("#mcForm").submit();
  }
  function modifyReset(){
  $("#mcForm")[0].reset();
  }
  </script>
</div>