<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>

<form id="form_all"  name = "form_all"  action="${ctx}/camera/camera_getVideo.html"  method="post"">
	<input type="hidden"  id="cameraID" name="cameraID" value=""/>
</form>

	<input type="hidden" id="cameraId" name="realTimePlayOcx.cameraId" value="${cameraInfo.cameraId}" />
	<input type="hidden" id="cameraName" name="realTimePlayOcx.cameraName"value="${cameraInfo.cameraName}" />
	<input type="hidden" id="deviceIp" name="realTimePlayOcx.cdeviceIp"value="${cameraInfo.deviceIp}" />
	<input type="hidden" id="devicePort" name="realTimePlayOcx.devicePort"value="${cameraInfo.devicePort}" />
	<input type="hidden" id="deviceType" name="realTimePlayOcx.deviceType"value="${cameraInfo.deviceType}" />
	<input type="hidden" id="deviceCode" name="realTimePlayOcx.deviceName"value="${cameraInfo.deviceCode}" />
	<input type="hidden" id="deviceName" name="realTimePlayOcx.deviceName"value="${cameraInfo.deviceName}" />
	<input type="hidden" id="devicePassword" name="realTimePlayOcx.devicePassword"value="${cameraInfo.devicePassword}" />
	<input type="hidden" id="channelNum" name="realTimePlayOcx.channelNum"value="${cameraInfo.channelNum}" />
	<input type="hidden" id="regionId" name="realTimePlayOcx.regionId"value="${cameraInfo.regionId}" />
	<input type="hidden" id="controlUnitId" name="realTimePlayOcx.controlUnitId"value="${cameraInfo.controlUnitId}" />
	
<script type="text/javascript" language="javascript" >
	//获得camera ID的信息
	function getAndSetCameraID()
	{
		if (navigator.userAgent.indexOf('MSIE') < 0) {
			alert("对不起，视频插件仅支持IE内核浏览器！");
			return;
		}
		var cameraID = document.getElementById("select_camera").options[document.getElementById("select_camera").selectedIndex].value;
		if (cameraID == -1)
			return;
		document.getElementById("cameraID").value = cameraID;
		document.getElementById("form_all").submit();	
	}
	
	//创建xml格式的配置参数文件
	
	var isRegister = false;
	//注册cag
	function register_cag(){
		var playOcx = document.getElementById("RealTimePlayOcx");	//得到控件引用
		var result = playOcx.Register_CagByUserInfo("58.83.226.77","7000","admin","12345");//向cag注册用户
		if(0 !== result){
			alert("cag注册失败！");
		}	
		isRegister = true;
	}
	
	
	
	//开始预览
	function play(){
	//	if(!isRegister){
	//		alert("请先向cag服务器注册！");
	//		return ;
	//	}
		
		
		
		var OCXobj = document.getElementById("RealTimePlayOcx");
      //  strIP = document.getElementById("TextIP").value;
     //   strPort = document.getElementById("TextPort").value;
     //   strName1 = document.getElementById("TextName1").value;
      //  strPwd1 = document.getElementById("Textpwd1").value;
     //   ChanNum1 = document.getElementById("SelectChan1").value;
	//cameraID1 = document.getElementById("TextCameraID1").value;
        strName2 = document.getElementById("deviceName").value;
        strPwd2 = document.getElementById("devicePassword").value;
        ChanNum2 = document.getElementById("channelNum").value;
	cameraID2 = document.getElementById("cameraId").value;
	var streamSvrIp ="58.83.226.77";
	var streamSvrPort ="554";
    var deviceCode = document.getElementById("deviceCode").value;
	var pagIP ="58.83.226.77";
	var pagPort ="7302";
	
        if(deviceCode && pagIP && pagPort){
		if(streamSvrIp && streamSvrPort){
			strXML = "<?xml version='1.0'?><Parament><CameraID>" + cameraID2 + "</CameraID><DeviceIP>" + pagIP + "</DeviceIP><DevicePort>" + pagPort + "</DevicePort><DeviceType>10001</DeviceType><RegistType>4</RegistType><DeviceID>" + deviceCode+ "</DeviceID><User>"+ strName2 +"</User><Password>" + strPwd2 + "</Password><ChannelNum>"+ ChanNum2 +"</ChannelNum><ProtocolType>0</ProtocolType><StreamType>0</StreamType><Transmits><Transmit><SrvIp>" + streamSvrIp + "</SrvIp><Port>" + streamSvrPort + "</Port></Transmit></Transmits></Parament>";
		}else{
			alert("请填写流媒体服务器信息，E家设备预览必须经过流媒体服务器！");
     			return;
		}
	}else{
	if(streamSvrIp && streamSvrPort){
	strXML = "<?xml version='1.0'?><Parament><CameraID>" + cameraID1 + "</CameraID><DeviceIP>" + strIP + "</DeviceIP><DevicePort>" + strPort + "</DevicePort><User>"+ strName1 +"</User><Password>" + strPwd1 + "</Password><ChannelNum>"+ ChanNum1 +"</ChannelNum><ProtocolType>0</ProtocolType><StreamType>0</StreamType><Transmits><Transmit><SrvIp>"+streamSvrIp+"</SrvIp><Port>"+streamSvrPort+"</Port></Transmit></Transmits></Parament>";
	}else{
	strXML = "<?xml version='1.0'?><Parament><CameraID>" + cameraID1 + "</CameraID><DeviceIP>" + strIP + "</DeviceIP><DevicePort>" + strPort + "</DevicePort><User>"+ strName1 +"</User><Password>" + strPwd1 + "</Password><ChannelNum>"+ ChanNum1 +"</ChannelNum><ProtocolType>0</ProtocolType><StreamType>0</StreamType><Transmits></Transmits></Parament>";
	}
}
      	
		
       var retval=OCXobj.StartTask_Preview_InWnd(strXML,0);
		if(0 != retval)
			alert("预览失败！"+retval);
	}
	
	//停止预览
	function stop(){
		var playOcx = document.getElementById("RealTimePlayOcx");//得到控件引用
		var win_num = playOcx.GetSelWnd();//当前选中窗口
		var retval = playOcx.stopRealPlay(win_num);
		clear_video_para();//清除视频参数
		if(retval != 0){
			alert("关闭预览失败！");	
		}
	}

	//加载	
	function init(){
		
		var OCXobj = document.getElementById("RealTimePlayOcx");
		OCXobj.SetOcxMode(0);
		
		//register_cag();
		play();
	}	
	
	//更改设备
	function deal(){	
	 	$("#form_default").attr("action","${ctx}/camera/camera_getList.html"+"?tempId="+Math.round(Math.random()*100));
	 	$("#form_default").submit();
	 }
</script>