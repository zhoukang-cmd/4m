<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" media="all" />
</head>

<body onload="init();">
    <div class="content">
      <%@ include file="/front/common/header.jsp"%>
      <div class="main">
        <div class="mian_nav">
          <div class="svn_nav">
            <dd><a href="${ctx}/history/historyData_goHistoryData.html">历史参数</a></dd>
            <dd class="bgpic"><a href="${ctx}/camera/camera_getPlayBackList.html">历史视频</a></dd>
            <dd><a href="${ctx}/history/report_goReportSelect.html">历史报表</a></dd>
          </div>
          <%@ include file="/front/common/nowdate.jsp"%>
		</div>
        <%@ include file="/front/common/left.jsp"%>
        <div class="mainright">
          <div class="subNav">当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;<a href="${ctx}/history/historyData_goHistoryData.html">历史数据</a>&gt;历史视频
            <div class="btn_determine2" ><img src="${ctx}/images/btn_determine.jpg" onclick="getAndSetCameraID();" width="80" height="32" /></div>
          </div>
	      <div class="reference">
	        <dd class="marginr25">通道信息：
	          <select id="select_playback" name="cameraID" >                    	
	            <s:iterator value="lstPlayBackInfo" id="lst">			              
	              <option value='<s:property value="#lst.cameraId"/>' <s:if test="#lst.cameraId==playbackInfo.cameraId">selected</s:if>><s:property value="#lst.cameraName"/></option>
	            </s:iterator>                            
	          </select>
	        </dd>
	        <dd class="prelar">开始时间：
	          <input type="text" id="stime" name="strStartTime" value="${strStartTime}" style="width: 130px;"/>
              <a href="javascript:WdatePicker({el:'stime', dateFmt:'yy-MM-dd HH:mm:ss'})"><img class="btn_time" src="${ctx}/images/btn_wind.jpg" /></a>
	        </dd>
	        <dd class="prelar paddingl8" >结束时间：
	          <input type="text" id="etime" name="strEndTime" value="${strEndTime}" style="width: 130px;" />
              <a href="javascript:WdatePicker({el:'etime', dateFmt:'yy-MM-dd HH:mm:ss'})"><img class="btn_time" src="${ctx}/images/btn_wind.jpg" /></a>
	        </dd>
	      </div>
	      <div class="main_pic">
	        <object classid="clsid:61978326-6772-4595-9EC3-D23C5CD5E61F" id="PlayBackOCX" width="100%" height="640" name="PlayBackOCX" ></object>
	      </div>
        </div>
      </div>
      <%@ include file="/front/common/footer.jsp"%>
    </div>
    
    <input type="hidden" id="deviceId" name="deviceId" value="${playbackInfo.deviceId}" />
	<input type="hidden" id="devType" name="devType" value="0" />
	<input type="hidden" id="RecLocation" name="RecLocation" value="1" />
	<input type="hidden" id="VRMUrl" name="VRMUrl" value="vrm://58.83.226.77:6610" />
	<input type="hidden" id="CameraID" name="CameraID"value="${playbackInfo.cameraId}" />
	<input type="hidden" id="CameraName" name="CameraName"value="${playbackInfo.cameraName}" />
	<form id="form_all"  name = "form_all"  action="${ctx}/camera/camera_getPlayBackVideo.html"  method="post"">
		<input type="hidden"  id="cameraID" name="cameraID" value=""/>
		<input type="hidden"  id="strStartTime" name="strStartTime" value="${strStartTime}"/>
		<input type="hidden"  id="strEndTime" name="strEndTime" value="${strEndTime}"/>
	</form>
	<%@ include file="/js/history_camera.js"%>
</body>
</html>
