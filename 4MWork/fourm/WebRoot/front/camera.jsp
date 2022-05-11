<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" media="all"/>
	<%@ include file="/js/camera.js"%>
</head>

<body onload="init();">
<div class="content">
  <%@ include file="/front/common/header.jsp"%>
  <div class="main">
    <div class="mian_nav">
      <%@ include file="/front/common/nowdate.jsp"%>
    </div>
    <%@ include file="/front/common/left.jsp"%>
    <div class="mainright">
      <div class="subNav">
        <div class="tips">友情提示：<a href="${ctx}/front/download/download.jsp">视频插件下载</a></div>
        	当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;视频数据
        <div class="btn_determine3" ><img src="${ctx}/images/btn_determine.jpg" onclick="javascript:getAndSetCameraID()" width="80" height="32" /></div>
        <div class="btn_determine2" ><img src="${ctx}/images/btn_stop.jpg" onclick="javascript:stop()" width="80" height="32" /></div>
      </div>
      <div class="reference">
        <div class="marginr25">通道信息：
          <select id="select_camera" name="select_camera">
            <s:iterator value="lstCameraInfo" id="lst" status="st">
              <option value='<s:property value="#lst.cameraId"/>' <s:if test="#lst.cameraId==cameraInfo.cameraId">selected</s:if>><s:property value="#lst.cameraName"/></option>
            </s:iterator>
          </select>
        </div>
      </div>
      <div class="highcharts marginb15">
        <object	classid="CLSID:D5E14042-7BF6-4E24-8B01-2F453E8154D7" id="RealTimePlayOcx" width="100%" height="542" name="RealTimePlayOcx"></object>
      </div>
    </div>
  </div>
  <%@ include file="/front/common/footer.jsp"%>
</div>
</body>
</html>
