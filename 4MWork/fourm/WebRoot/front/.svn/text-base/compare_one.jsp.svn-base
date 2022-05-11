<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" media="all"/>
</head>

<body>
    <div class="content">
      <%@ include file="/front/common/header.jsp"%>
      <div class="main">
        <div class="mian_nav">
          <div class="svn_nav">
            <dd class="bgpic"><a href="${ctx}/compare/compareone_goCompareone.html">同一设备</a></dd>
            <dd><a href="${ctx}/compare/comparetwo_goComparetwo.html">不同设备</a></dd>
          </div>
          <%@ include file="/front/common/nowdate.jsp"%>
        </div>
        <%@ include file="/front/common/left.jsp"%>
        <div class="mainright">
          <div class="subNav">当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;<a href="${ctx}/compare/compareone_goCompareone.html">工况对比</a>&gt;同一设备
            <div class="btn_determine2" ><img src="${ctx}/images/btn_determine.jpg" onclick="checkAndSubmit();" width="80" height="32" /></div>
          </div>
          <form action="${ctx}/compare/compareone_goCompareone.html" id="compareForm" method="post">
          <div class="reference">
            <dd class="marginr25">选择参数：
              <select id="selectFieldNo" name="selectFieldNo" style="width: 120px"> 
                <s:iterator value="fields" id="field">      
                  <option value='<s:property value="#field.fieldNo"/>' <s:if test="#field.fieldNo==selectFieldNo">selected</s:if>><s:property value="#field.fieldName"/></option>                                                  
                </s:iterator>
              </select>
            </dd>
            <dd class="prelar">参考日期：
              <input name="valueSeries" type="text" id="time1" value="${backValueSeries}" style="width: 100px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
              <input name="" class="btn_time" type="image" src="${ctx}/images/btn_wind.jpg" />
            </dd>
            <dd class="prelar paddingl8" >对比日期：
              <input name="compareSeries" type="text" id="time2"  value="${backCompareSeries}" style="width: 100px"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
              <input name="" class="btn_time" type="image" src="${ctx}/images/btn_wind.jpg" />
            </dd>
          </div>
          </form>
          <div class="highcharts">
            <%@ include file="/front/compare_chart.jsp"%>
          </div>
        </div>
      </div>
      <%@ include file="/front/common/footer.jsp"%>
    </div>
	<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" type="text/javascript">
	
	    function checkAndSubmit(){
	    	//alert("check");
			if($("#selectFieldNo").val()==""){
				alert("请选择参数");
				return;
			}
			if($("#time1").val()==""){
				alert("请输入参考日期");
				return;
			}
			if($("#time2").val()==""){
				alert("请输入对比日期");
				return;
			}
			$("#compareForm").submit();
		}
		//更改设备
	    function deal(){	
		 	$("#form_default").attr("action","${ctx}/compare/compareone_goCompareone.html"+"?tempId="+Math.round(Math.random()*100));
		 	$("#form_default").submit();
		 }
	</script>
</body>
</html>