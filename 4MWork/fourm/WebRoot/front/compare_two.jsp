<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" media="all" />
</head>

<body>
    <div class="content">
      <%@ include file="/front/common/header.jsp"%>
      <div class="main">
        <div class="mian_nav">
          <div class="svn_nav">
            <dd><a href="${ctx}/compare/compareone_goCompareone.html">同一设备</a></dd>
            <dd class="bgpic"><a href="${ctx}/compare/comparetwo_goComparetwo.html">不同设备</a></dd>
          </div>
          <%@ include file="/front/common/nowdate.jsp"%>
        </div>
        <%@ include file="/front/common/left.jsp"%>
        <div class="mainright">
          <div class="subNav">当前位置：<a href="${ctx}/display/display_goDisplay.html">首页</a>&gt;<a href="${ctx}/compare/compareone_goCompareone.html">工况对比</a>&gt;不同设备
            <div class="btn_determine2" ><img src="${ctx}/images/btn_determine.jpg" onclick="checkAndSubmit()" width="80" height="32" /></div>
          </div>
          <form action="${ctx}/compare/comparetwo_goComparetwo.html" id="compareForm" method="post">
          <div class="reference2 margint3">
            <dd class="marginr25">对比机房：
              <select name="selectRoomId" id="compareRoomList" onchange="getCompareEquipList()">
                <s:iterator value="roomList" id="room">                                                        
                  <option value='<s:property value="#room.keyString"/>' <s:if test="#room.keyString==selectRoomId">selected</s:if>><s:property value="#room.valueString"/></option>
                </s:iterator>        
              </select>
            </dd>
            <dd class="marginr25">对比设备：
              <select name="selectEquipId" id="compareEquipList" onchange="getCompareFieldList()" >
                <s:iterator value="equipList" id="equip">   
                  <option value='<s:property value="#equip.keyString"/>' <s:if test="#equip.keyString==selectEquipId">selected</s:if>><s:property value="#equip.valueString"/></option>
                </s:iterator>        
              </select>
            </dd>
          </div>
          <div class="reference2">
            <dd class="marginr25">选择参数：
              <select id="compareFieldList" name="fieldNoStr" > 
                <s:iterator value="fieldList" id="field">                                                        
                  <option value='<s:property value="#field.keyString"/>' <s:if test="#field.keyString==fieldNoStr">selected</s:if>><s:property value="#field.valueString"/></option>
                </s:iterator>
              </select>
            </dd>
            <dd class="prelar">对比时间：
              <input name="compareTime" type="text" id="compareTime" value="${backTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
              <input name="" style="position: absolute; top:6px; height:19px; right:8px" type="image" src="${ctx}/images/btn_wind.jpg" />
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
		var selectEquipId = "${selectEquipId}";
		var fieldNoStr = "${fieldNoStr}";
	 	function getCompareEquipList(){
	 		var selectRoomId  = $("#compareRoomList").val();
			var url = "${ctx}/compare/comparetwo_getCompareEquipList.html"+"?tempId="+Math.round(Math.random()*100);
			$.ajax({
				url:url,
				dataType: "json",
				data:{selectRoomId:selectRoomId},		
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getCompareEquipList(");
				},
	    		success: function(json){	    
					$("#compareEquipList").empty();	   
	    			if(json.equipList.length > 0){
	    		        var equiphtml = "";
	    		    	$.each(json.equipList,function(i, equip){
	    		    		if (selectEquipId == equip.keyString ){
		    		    		equiphtml += "<option value='"+equip.keyString+"' selected>"+equip.valueString+"</option>";
							}
		    		    	else
		    		    		equiphtml += "<option value='"+equip.keyString+"'>"+equip.valueString+"</option>";
						});
						$("#compareEquipList").html(equiphtml);
						getCompareFieldList();
	    		    }
				}
			});
		}
	 	function getCompareFieldList(){
	 		var selectEquipId  = $("#compareEquipList").val();
			var url = "${ctx}/compare/comparetwo_getCompareFieldList.html"+"?tempId="+Math.round(Math.random()*100);
			$.ajax({
				url:url,
				dataType: "json",
				data:{selectEquipId:selectEquipId},		
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getCompareFieldList(");
				},
	    		success: function(json){	    
					$("#compareFieldList").empty();	   
	    			if(json.fieldList.length > 0){
	    		        var fieldhtml = "";
	    		    	$.each(json.fieldList,function(i, field){
	    		    		if (fieldNoStr == field.keyString ){
		    		    		fieldhtml += "<option value='"+field.keyString+"' selected>"+field.valueString+"</option>";
							}
		    		    	else
		    		    		fieldhtml += "<option value='"+field.keyString+"'>"+field.valueString+"</option>";
						});
						$("#compareFieldList").html(fieldhtml);
	    		    }
				}
			});
		}
			
		function checkAndSubmit(){
			//alert($("#compareFieldList").val() );
			if($("#compareFieldList").val() ==null ){
				alert("请选择有共同参数的设备进行对比");
				return;
			}
			if($("#compareTime").val() =="" ){
				alert("请输入对比时间");
				return;
			}
			$("#compareForm").submit();
		}
        //更改设备
	    function deal(){	
			$("#form_default").attr("action","${ctx}/compare/comparetwo_goComparetwo.html"+"?tempId="+Math.round(Math.random()*100));
			$("#form_default").submit();
		}
    </script>
</body>
</html>