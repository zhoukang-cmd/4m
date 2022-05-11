<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" media="all" />
    <%@ include file="/js/report.js"%>
</head>

<body onload="changeDay();">
    <div class="content">
      <%@ include file="/front/common/header.jsp"%>
      <div class="main">
        <div class="mian_nav">
          <div class="svn_nav">
            <dd><a href="${ctx}/history/historyData_goHistoryData.html">历史参数</a></dd>
            <dd><a href="${ctx}/camera/camera_getPlayBackList.html">历史视频</a></dd>
            <dd class="bgpic"><a href="${ctx}/history/report_goReportSelect.html">历史报表</a></dd>
          </div>
          <%@ include file="/front/common/nowdate.jsp"%>
        </div>
        <%@ include file="/front/common/left.jsp"%>
        <div class="mainright">
          <div class="subNav">
          	当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;<a href="${ctx}/history/historyData_goHistoryData.html">历史数据</a>&gt;历史报表
          	<div class="btn_determine2" ><img src="${ctx}/images/btn_determine.jpg" onclick="checkAndSubmit();" width="80" height="32" /></div>
          </div>
          <div class="reference">
	        <div  class="separate2">
	          <ul>
	            <li id="radioH"><a href="javascript:changeHour()">时</a></li>
                <li id="radioD"><a href="javascript:changeDay()">天</a></li>
                <li id="radioM"><a href="javascript:changeMonth()">月</a></li>
	          </ul>
	        </div>
	        <dd style="position:relative; ">开始时间：
	          <input name="beginTime" id="beginTime" type="text" />
              <a href="javascript:WdatePicker({el:'beginTime', dateFmt:'yyyy-MM-dd HH:mm:ss'})"><img class="btn_time" src="${ctx}/images/btn_wind.jpg" /></a>
	        </dd>
          </div>
          <div class="separate"></div>
        </div>
      </div>
      <%@ include file="/front/common/footer.jsp"%>
    </div>
    <form id="form_all" name="form_all" action="" method="post">
		<input type="hidden" id="searchTime" name="searchTime" value=""/>
		<input type="hidden" id="span" name="timeSpan" value=""/>
	</form>
</body>
</html>
