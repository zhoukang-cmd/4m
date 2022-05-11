<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
		
		<form id="page" name = "page" action="${ctx}/repair/repair_goRepair.html" method="post">
        	<input type="hidden" name="pageCtrl.currPage" id = "iCurrPage" value=""/>
        	<input type="hidden" name="startTime" id="startTime" value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        	<input type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value="${endTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </form>
         <form action="" id="form_submit" method="post">
        	<input type="hidden" name="startTime" id="dayStartTime" />
        </form>
         <form action="${ctx}/repair/repair_goRepairDetail.html" id="form_detail" method="post">
            <input type="hidden" id="detailId" name="detailId"/>
        </form>
        <form action="${ctx}/repair/repair_delRepair.html" id="form_del" method="post">
            <input type="hidden" id="delrepairStr" name="delrepairStr"/>
        </form>
        <form action="${ctx}/repair/repair_goModifyRepair.html" id="form_modify" method="post">
            <input type="hidden" id="modifyId" name="detailId"/>
        </form>
        
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
                $("#form_submit").attr("action","${ctx}/repair/repair_goRepair.html");
                $("#form_submit").submit();
            }
            //查看详情
            function repairDetail(id){
                document.getElementById("detailId").value=id;
                document.getElementById("form_detail").submit();
            }
            //更改设备
        	function deal(){	
	       	 	$("#form_default").attr("action","${ctx}/repair/repair_goRepair.html"+"?tempId="+Math.round(Math.random()*100));
	            $("#form_default").submit();
        	}
            //添加条目
            function addRepair(){
                $("#paramsubmit").attr("action","${ctx}/equip/equip_goAddRepair.html");
                $("#paramsubmit").submit();
            }
			//全选
			function checkAll(){
    			var mailNodes = document.getElementsByName("checkbox");
    			for(var x=0; x<mailNodes.length; x++){
                    mailNodes[x].checked = "checked";
                }
            }
            //修改所选
            function modify(){
            	var id = 0;
            	var mailNodes = document.getElementsByName("checkbox");
            	for (var x=0; x<mailNodes.length; x++){
            		if (mailNodes[x].checked && id == 0){
            			id = mailNodes[x].value;
            		}
            		else if (mailNodes[x].checked && id != 0){
            			alert("只能选择一条进行修改");
            			return;
            		}
            		if (x == mailNodes.length - 1 && id == 0){
            			alert("请选择要修改的信息");
            			return;
            		}
            	}
            	if(id==0){
            		alert("无记录");
            		return;
            	}
            	document.getElementById("modifyId").value=id;
                document.getElementById("form_modify").submit();
            }
            
            //删除所选
            function deleteMail(){
                var mailNodes = document.getElementsByName("checkbox");
                var arr = [];
                var pos = 0;
                for(var x=0; x<mailNodes.length; x++){
                    if(mailNodes[x].checked){
                        var trNode = mailNodes[x].value;
                        arr[pos++] = trNode;
                    }
                }
                var ids = "";
                for(var x=0; x<arr.length; x++){  
                    ids = ids + arr[x] + "%";               
                }
                if(arr.length>0){
                	if(confirm("确定要删除选择的记录吗？")){
                        document.getElementById("delrepairStr").value="ids";
                        document.getElementById("delrepairStr").value=ids;
                        document.getElementById("form_del").submit();
                	}
                }
                else{
                	alert("请至少选择一条需要删除的记录!");
                }
                	
			}
	    </script>
        <script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>