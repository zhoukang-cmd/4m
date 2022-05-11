<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<form id="page" name = "page" action="${ctx}/suggest/suggest_goSuggest.html" method="post">
        	<input type="hidden" name="pageCtrl.currPage" id = "iCurrPage" value=""/>
        	<input type="hidden" name="startTime" id="startTime" value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        	<input type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value="${endTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </form>
        
        <form action="" id="form_submit" method="post">
        	<input type="hidden" name="startTime" id="dayStartTime" />
        </form>
        
        <form action="${ctx}/suggest/suggest_goSuggestDetail.html" id="form_detail" method="post">
            <input type="hidden" id="detailId" name="detailId"/>
        </form>
        
        <form action="" id="form_page" method="post">
        	<input type="hidden" name="leftParam.startTime" id="startTime" value="<fmt:formatDate value="${leftParam.startTime}" type="date" pattern="yyyy-MM-dd"/>"/>
        	<input type="hidden" name="leftParam.endTime" id="endTime" value="<fmt:formatDate value="${leftParam.endTime}" type="date" pattern="yyyy-MM-dd"/>"/>
            <input type="hidden" id="index" name="index" />
        </form>
		<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
   		<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
    
		 <script>
		  /*用户翻页操作*/
          function gotoPage(iCurrPage){
          	document.getElementById("iCurrPage").value = iCurrPage;
          	document.getElementById("page").submit();
          }
	     //提交查询
         function checkAndSubmit(){
  			$("#dayStartTime").val($("#time").val());
  			//alert($("#dayStartTime").val());
              $("#form_submit").attr("action","${ctx}/suggest/suggest_goSuggest.html");
              $("#form_submit").submit();
          }
         //查看详情
         function suggestDetail(id){
             document.getElementById("detailId").value=id;
             document.getElementById("form_detail").submit();
         }
		//更改设备
         function deal(){	
        	 //alert("deal");
        	 $("#form_default").attr("action","${ctx}/suggest/suggest_goSuggest.html");
             $("#form_default").submit();
		 }
	   </script>