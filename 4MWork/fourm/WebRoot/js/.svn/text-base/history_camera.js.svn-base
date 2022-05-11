<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
function getAndSetCameraID(){
		if (navigator.userAgent.indexOf('MSIE') < 0) {
			alert("对不起，视频插件仅支持IE内核浏览器！");
			return;
		}
		var cameraID = document.getElementById("select_playback").options[document.getElementById("select_playback").selectedIndex].value;
		document.getElementById("cameraID").value = cameraID;
		if (cameraID == -1)
			return;
			
		var strStartTime = document.getElementById("stime").value;
		document.getElementById("strStartTime").value = strStartTime;
		
		var strEndTime = document.getElementById("etime").value;		
		document.getElementById("strEndTime").value = strEndTime;

		document.getElementById("form_all").submit();
	}

	var localParamIsSet = 0;//本地参数是否设置
	var playbackOcx = document.getElementById("PlayBackOCX");

	//向cag注册用户
	var isRegister = false;
	function Register_CagByUserInfo(){		
		var resultRegister = playbackOcx.Register_CagByUserInfo("58.83.226.77","6610","admin","12345");
		if(0 != resultRegister){
			alert("CAG注册失败!");
		}
else {
	alert("CAG注册成功!");
			isRegister = true;
		}
	}

	//设置窗口的回放参数
	var isSetPBP = false;
	function SetPlayBackParam(){
		var cameraId = document.getElementById("CameraID").value;
		var cameraName = document.getElementById("CameraName").value;
		var szVrmUrl = document.getElementById("VRMUrl").value;
		var recordStyle = document.getElementById("RecLocation").value;
		var deviceId = document.getElementById("deviceId").value;
		var devType = document.getElementById("devType").value;
		if (deviceId == null || deviceId == ""){
			return;
		}
		if (cameraId == null || cameraId == ""){
			return;
		}		

		
		//创建xml格式的配置参数文件
		 var OCXobj = document.getElementById("PlayBackOCX");
	     var WndIndex = OCXobj.GetFocusWndIndex();//获取当前选中窗口
	     
	     var ZoneID ="1";//网域ID
	     var RecLocation ="1";
	     var DevType ="0";
	     var CameraID =cameraId;
	     var CameraName =cameraName;
	     var VRMUrl ="58.83.226.77";
	     strXML = "<?xml version='1.0'?><Parament><PlayWndIndex>"+WndIndex+"</PlayWndIndex><ZoneID>"+ZoneID+"</ZoneID><RecLocation>"+RecLocation+"</RecLocation><DevType>"+DevType+"</DevType><CameraID>"+CameraID+"</CameraID><CameraName>"+CameraName+"</CameraName><VRMUrl>vrm://"+VRMUrl+":6302</VRMUrl></Parament>";

		var Result = playbackOcx.SetPlayBackParam(strXML);
		if (-1 != Result)
		{
			isSetPBP = true;
		} else {
			isSetPBP = false;
			alert("设置设备和监控通道失败!");
		}

	}

	


var isSetParam = false;

function Start_PlayBack(){
SetPlayBackParam();
if (!isSetPBP)
{
	return;
}


isSetParam = true;
}

	//开始搜索
	function searchPlayBack(){
		if (!isSetParam)
		{
			return;
		} 
		var startTime = document.getElementById("strStartTime").value;
		var endTime = document.getElementById("strEndTime").value;
		if (startTime == null || startTime == "")
		{
			alert("开始时间不能为空!");
			return;
		}
		if (endTime == null || endTime == "")
		{
			alert("结束时间不能为空!");
			return;
		}
		
		//开始时间和结束时间的验证
		var result = compareDateAndTime(endTime, startTime);
		if (result < 0) {
			alert("结束时间不能早于开始时间!");
			return;
		}

		//验证查询的时间间隔
		var st = getTime(startTime);
		var et = getTime(endTime);
		var dif = et - st;
		if (dif > 3 * 24 * 3600 * 1000) 
		{
			alert("查询时间不要超过三天！");
			return;
		}

		var XmlDocQuery = new ActiveXObject("MSXML2.DOMDocument");
		var Instruction = XmlDocQuery.createProcessingInstruction("xml", "version='1.0' encoding='utf-8'");
		XmlDocQuery.appendChild(Instruction);
		var Root = XmlDocQuery.createNode(1, "Parament", "");
		Element = XmlDocQuery.createElement("BeginTime");	//开始时间
		Element.text = startTime;
		Root.appendChild(Element);
		Element = XmlDocQuery.createElement("EndTime");//结束时间
		Element.text = endTime;
		Root.appendChild(Element);
		Element = XmlDocQuery.createElement("RecordType");//录像类型
		var rectype = 0;
		var isPlanRec = true;		
		var isManualRec = true;
		var isMoveRec = true;
		var isAlarmRec = true;		
		if (isManualRec) 
		{
			rectype = rectype + 16;
		}
		if (isAlarmRec) 
		{
			rectype = rectype + 4;
		}
		if (isMoveRec) 
		{
			rectype = rectype + 2;
		}
		if (isPlanRec) 
		{
			rectype = rectype + 1;
		}
		Element.text = rectype;
		Root.appendChild(Element);
		XmlDocQuery.appendChild(Root);
		try 
		{
			playbackOcx.StartQueryRecord(XmlDocQuery.xml);
		} catch (error) 
		{
			alert("搜索录像失败!");
		}
	}

	function  compareDateAndTime(endDate, startDate) {
		var endTime = getTime(endDate);
		var startTime = getTime(startDate);
		if (endTime == startTime)
			return "0";
		if (endTime > startTime)
			return "1";
		if (endTime < startTime)
			return "-1";
	}

	function getTime(date) {

		if (date == null || date == "") {
			date = new Date();
			return date.getTime();
		}

		var dt = date.split(" ");
		var vdate = dt[0].split("-");
		var vtime = dt[1].split(":");
		var ndate = new Date(vdate[0], vdate[1] - 1, vdate[2], vtime[0], vtime[1], vtime[2]);
		return ndate.getTime();
	}

	//停止当前窗口的录像回放,lWindowsID：窗口编号;返回值：0表示成功，-1表示失败
/*	function StopPlayBack(){
		var lWindowsID = GetFocusWndIndex();
		var stopPB = playbackOcx.StopPlayBack(lWindowsID);
		//alert(stopPB);
	}*/

	//获取当前焦点窗口
	function GetFocusWndIndex() {
		try {
			return playbackOcx.GetFocusWndIndex();
		}
catch (error) {
			alert("录像获取失败!");
		}
}

	//停止所有回放,返回值：0表示成功，-1表示失败
	function StopAllPlayBack (){
		try {
	return playbackOcx.StopAllPlayBack();
}
catch (error) {
	alert("停止所有录像回放失败!");
}
}

	//获取当前焦点窗口
	function  GetFocusWndIndex(){
		try{
			return playbackOcx.GetFocusWndIndex();
		}
catch (error){
			alert("获取当前焦点窗口失败!");
		}
	}


		

	
	function init(){
		if (document.getElementById("deviceId").value != -1&&document.getElementById("deviceId").value !=""){
  		//Register_CagByUserInfo();	
  		Start_PlayBack();
  		searchPlayBack();
		}	
	}	
	
	//更改设备
	 function deal(){	
  	 $("#form_default").attr("action","${ctx}/camera/camera_getPlayBackList.html"+"?tempId="+Math.round(Math.random()*100));
       $("#form_default").submit();
	 }
    </script>
