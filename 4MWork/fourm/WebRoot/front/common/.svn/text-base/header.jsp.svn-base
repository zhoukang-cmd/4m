<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
 <div class="header">
    <div class="banner">
      <img class="logo_pic" src="${ctx}/images/logo.jpg" />
      <div class="logo_text">
        <div class="logo_group_name" id ="logo"></div>
        <div class="logo_4m">大型矿山机电设备远程故障诊断系统</div>
      </div>
    </div>
    <div class="boxa">
      <div class="nav">
        <ul>
          <li <s:if test='nav=="analyse"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/analyse/analyse_goAnalyse.html">智能分析</a></li>
          <li <s:if test='nav=="display"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/display/display_goDisplay.html">实时数据</a></li>
          <li <s:if test='nav=="video"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/camera/camera_getList.html">视频数据</a></li>
          <li <s:if test='nav=="history"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/history/historyData_goHistoryData.html">历史数据</a></li>
          <li <s:if test='nav=="compare"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/compare/compareone_goCompareone.html">工况对比</a></li>
          <li <s:if test='nav=="equip"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/equip/equip_goEquip.html">设备信息</a></li>
     	  <li <s:if test='nav=="suggest"'>class="onlist"</s:if><s:else>class=""</s:else>><a href="${ctx}/suggest/suggest_goSuggest.html">分析结果</a></li>
        </ul>
      </div>
      <div class="login">欢迎：${accountId} &nbsp;&nbsp;<a href="${ctx}/user/user_logout.html" >退出</a></div>
    </div>
  </div>