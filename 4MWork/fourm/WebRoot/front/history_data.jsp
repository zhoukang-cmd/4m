<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" media="all" />
	<%@ include file="/js/history_data.js"%>
</head>

<body>
    <div class="content">
      <%@ include file="/front/common/header.jsp"%>
      <div class="main">
        <div class="mian_nav">
          <div class="svn_nav">
            <dd class="bgpic"><a href="${ctx}/history/historyData_goHistoryData.html">历史参数</a></dd>
            <dd><a href="${ctx}/camera/camera_getPlayBackList.html">历史视频</a></dd>
            <dd><a href="${ctx}/history/report_goReportSelect.html">历史报表</a></dd>
          </div>
          <%@ include file="/front/common/nowdate.jsp"%>
        </div>
        <%@ include file="/front/common/left.jsp"%>
        <div class="mainright">
          <div class="subNav">当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;<a href="${ctx}/history/historyData_goHistoryData.html">历史数据</a>&gt;历史参数
            <div class="btn_determine2" ><img src="${ctx}/images/btn_determine.jpg" onclick="checkAndSubmit(1)" width="80" height="32" /></div>
          </div>
          <form action="" id="form" method="post">
          <input type="hidden" id="timeFlag" name="timeFlag" value="timeFlag"/>
          <div class="reference">
            <dd class="marginr25">选择参数：
              <select id="selectFieldNo" name="selectFieldNo" > 
                <s:iterator value="fields" id="field">      
                  <option value='<s:property value="#field.fieldNo"/>' <s:if test="#field.fieldNo==selectFieldNo">selected</s:if>><s:property value="#field.fieldName"/></option>                                                  
                </s:iterator>
              </select>
            </dd>
            <dd class="prelar">开始时间：
              <input name="beginTime" type="text" id="beginTime" value="${backTime}"  />
              <a href="javascript:WdatePicker({el:'beginTime', dateFmt:'yyyy-MM-dd'})"><img class="btn_time" src="${ctx}/images/btn_wind.jpg" /></a>
            </dd>
          </div>
          </form>
          <div class="separate">
            <ul>
              <li <s:if test='timeFlag==1'>class="bgs"</s:if>><a href="javascript:checkAndSubmit(1)">日</a></li>
              <li <s:if test='timeFlag==2'>class="bgs"</s:if>><a href="javascript:checkAndSubmit(2)">周</a></li>
              <li <s:if test='timeFlag==3'>class="bgs"</s:if>><a href="javascript:checkAndSubmit(3)">月</a></li>
            </ul>
          </div>
          <div class="highcharts">
            <%@ include file="/front/history_chart.jsp"%>
          </div>
        </div>
      </div>
      <%@ include file="/front/common/footer.jsp"%>
    </div>
</body>
</html>
