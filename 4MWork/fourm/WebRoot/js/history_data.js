<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
    	function checkAndSubmit(flag){
    		if(document.getElementById("selectFieldNo").value==""){
    			alert("请选择参数");
                return;
    		}
    		
            if(document.getElementById("beginTime").value=="" ){
                alert("请输入时间");
                return;
            }
            document.getElementById("timeFlag").value=flag;
            $("#form").attr("action","${ctx}/history/historyData_goHistoryData.html"+"?tempId="+Math.round(Math.random()*100));
            document.getElementById("form").submit();
        }
        
    	  //更改设备
        function deal(){	
       	 	$("#form_default").attr("action","${ctx}/history/historyData_goHistoryData.html"+"?tempId="+Math.round(Math.random()*100));
            $("#form_default").submit();
    	}
    </script>
