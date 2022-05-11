<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>

        <form id="page" name = "page" action="${ctx}/fault/fault_goFault.html" method="post">
        	<input type="hidden" name="pageCtrl.currPage" id = "iCurrPage" value=""/>
        	<input type="hidden" name="startTime" id="startTime" value="<fmt:formatDate value="${startTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        	<input type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value="${endTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </form>
        
         <form action="" id="form_submit" method="post">
        	<input type="hidden" name="startTime" id="dayStartTime" />
        </form>
         <form action="${ctx}/fault/fault_goFaultDetail.html" id="form_detail" method="post">
        	<input type="hidden" id="detailId" name="detailId"/>
        </form>
        
        <form action="${ctx}/equip/equip_delSelectChange.html" id="form_del" method="post">
            <input type="hidden" id="delfaultStr" name="delfaultStr"/>
	    </form>
	   
	    <form action="${ctx}/equip/equip_delCEquipById.html" id="form_delce" method="post">
        <input type="hidden" id="delceqStr" name="delceqStr"/>
       </form>
	    <form action="${ctx}/equip/equip_goModifyCEquip.html" id="form_modify" method="post">
            <input type="hidden" id="modifyId" name="detailId"/>
        </form>
<script language="javascript" type="text/javascript">
		 //更改设备
    	function deal(){	
       	 	$("#form_default").attr("action","${ctx}/equip/equip_goEquip.html"+"?tempId="+Math.round(Math.random()*100));
            $("#form_default").submit();
    	}
	 /*用户翻页操作*/
    function gotoPage(iCurrPage)
    {
    	document.getElementById("iCurrPage").value = iCurrPage;
    	document.getElementById("page").submit();
    }
    
    /**
 	 * 设备条件查询
 	 * 
 	 */
 	function selQueryEquip(){
 		var url = "${ctx}/equip/equip_selQueryEquip.html"+"?tempId="+Math.round(Math.random()*100);
		var equipName=$("#equipName").val();
		var equipNum=$("#equipNum").val();
 		$.ajax({
 			type: "post", 
				url:url,
				dataType: "json",		
				data:{equipName:equipName,equipNum:equipNum},          
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("无法取得设备参数分组信息！");
				},
	    		success: function(json){
	    			var htm="";
	    			
	    			$.each(json.allEquip,function(i, ext){
	    				htm=htm+"<div class='cksysb-nr'><ul>  <li style='width:100px;'>"+(i+1)+"</li><li>"+ext.equipCode+"_"+ext.equipNum+"</li><li>"+ext.equipName+"</li><li>"+ext.model+"</li><li style='width: 200px;'> "+ext.usersection+"</li><li style='width: 200px;'> "+ext.useaddress+"</li><li> "+ext.officer+"</li><li style='width: 200px;'> "+ext.manuInfo+"</li> <li> "+ext.startTime+"</li><li>"+ext.state+"</li></ul>  </div>";
	    				
	    			});
	    			$("#content").html(htm);
				}
			});
 		
 	}
    
    
  //提交子设备查询
	 function selQueryCEquip(){
	   var csTime = document.getElementById("csTime").value;
	   var ceTime = document.getElementById("ceTime").value;
	   if(ceTime!=""){
	   if(csTime>ceTime){
		   alert("开始时间不能大于结束时间");
		   return;
	   }
	   }
	 
       $("#selQueryCEquip").attr("action","${ctx}/equip/equip_selQueryCEquip.html"+"?tempId="+Math.round(Math.random()*100));
       $("#selQueryCEquip").submit();
   }

//提交变动信息查询
	 function  selQueryChange(){
	   var sTime = document.getElementById("sTime").value;
	   var eTime = document.getElementById("etime").value;
	   if(eTime!=""){
	   if(sTime>eTime){
		   alert("开始时间不能大于结束时间");
		   return;
	   }
	   }
        $("#selQueryChange").attr("action","${ctx}/equip/equip_selQueryChange.html"+"?tempId="+Math.round(Math.random()*100));
        $("#selQueryChange").submit();
	 }

	//提交查询
	 function checkAndSubmit(){
		$("#dayStartTime").val($("#time").val());
		//alert($("#dayStartTime").val());
        $("#form_submit").attr("action","${ctx}/fault/fault_goFault.html"+"?tempId="+Math.round(Math.random()*100));
        $("#form_submit").submit();
    }
    //查看详情
    function questionDetail(id){
        document.getElementById("detailId").value=id;
        document.getElementById("form_detail").submit();
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
                //alert(trNode);                
                arr[pos++] = trNode;
            }
        }
        var ids = "";
        for(var x=0; x<arr.length; x++){  
            ids = ids + arr[x] + "%";               
        }
        if(arr.length>0){
           if(confirm("确定要删除选择的记录吗？")){
	           document.getElementById("delfaultStr").value="ids";
	           document.getElementById("delfaultStr").value=ids;
	           document.getElementById("form_del").submit();
          }
        }
        else{
        	alert("请至少选择一条需要删除的记录!");
        }
	}
    function selectAll(comb){
    	var sl=$("input[name='"+comb+"']");
    	var flag;
    	sl.each(function (){
    		if($(this).attr("checked")){
        		flag=false;
        	}
    		else{
    			flag=true;
    		}
    	});
    	if(flag){
    		$("input[name='"+comb+"']").attr("checked",true);
    	}
    	else{
    		$("input[name='"+comb+"']").attr("checked",false);
    	}
    	      
    }
    
    function delSelect(comb){
    	var idAll="";
    	var sl=$("input[name='"+comb+"']:checked");
    	if(sl.length>0){
	    	if(comb=='bm'){
	    		sl.each(function (){
	    				var id=$(this).val();
	            		idAll=idAll+id+"%";
	    			});
	    		if(confirm("确定要删除选择的记录吗？")){
	    			document.getElementById("delfaultStr").value=idAll;
		            document.getElementById("form_del").submit();
	    		}
	        	}
	    	else if(comb=='cm'){
	    		sl.each(function (){
					var id=$(this).val();
	        		idAll=idAll+id+"%";
				});
	    		if(confirm("确定要删除选择的记录吗？")){
		    		document.getElementById("delceqStr").value=idAll;
		            document.getElementById("form_delce").submit();
	    		}
	    	}
    	}
    	else {alert("请至少选择一条需要删除的记录！");}
    	
    	}
    	
  //显示全部设备窗口
 	function floatOpen() {
 		var url = "${ctx}/equip/equip_goEquipAll.html"+"?tempId="+Math.round(Math.random()*100);
 		$("#cksysb").css("visibility",'visible');
 		$("#cksysb").fadeIn();
		$.ajax({
				url:url,
				dataType: "json",		
				data:{},          
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("无法取得设备参数分组信息！");
				},
	    		success: function(json){
	    			var htm="";
	    			
	    			$.each(json.allEquip,function(i, ext){
	    				htm=htm+"<div class='cksysb-nr'><ul>  <li style='width:100px;'>"+(i+1)+"</li><li>"+ext.equipCode+"_"+ext.equipNum+"</li><li>"+ext.equipName+"</li><li>"+ext.model+"</li><li style='width: 200px;'> "+ext.usersection+"</li><li style='width: 200px;'> "+ext.useaddress+"</li><li> "+ext.officer+"</li><li style='width: 200px;'> "+ext.manuInfo+"</li> <li> "+ext.startTime+"</li><li>"+ext.state+"</li></ul>  </div>";
	    				
	    			});
	    			$("#content").html(htm);
				}
			});
 	}
	//关闭全部设备浮动窗口
 	function floatClose() {
 		$("#cksysb").fadeOut();
	}
 	 //显示添加子设备窗口
 	function openAddCE() {
 		
 		$("#zsbtj").css("visibility",'visible');
 		$("#zsbtj").fadeIn();
 		
 		var url="${ctx}/equip/equip_getCequipType.html"+"?tempId="+Math.round(Math.random()*100);
 		$.ajax({
			url:url,
			dataType: "json",		
			data:{},          
			error: function(XMLHttpRequest, textStatus, errorThrown){
	   			alert("无法取得设备参数分组信息！");
			},
    		success: function(json){
    			var htm="";
    			
    			$.each(json.typeLs,function(i, ext){
    				htm=htm+"<option value='"+ext.cequipCode+"'>"+ext.typeName+"</option>";
    				
    				
    			});
    			$("#cequipCode").html(htm);
			}
		});
		
 	}
	//关闭添加子设备浮动窗口
 	function closeAddCE() {
 		$("#zsbtj").fadeOut();
	}
    
 	 //显示修改子设备窗口
 	function openModifyCE() {
 		var s=$("input[name='cm']:checked");
 		if(s.length!=1){
 			alert("只能选择一条进行修改");
 			return;
 		}
 		$("#modifyCE").css("visibility",'visible');
 		$("#modifyCE").fadeIn();
 		
 		var id=s.val();
 	
 		var url = "${ctx}/equip/equip_goModifyCEquip.html"+"?tempId="+Math.round(Math.random()*100);
 		$.ajax({
			url:url,
			dataType: "json",		
			data:{"detailId":id},          
			error: function(XMLHttpRequest, textStatus, errorThrown){
	   			alert("无法取得设备参数分组信息！");
			},
    		success: function(json){
    		  $("#ceId").val(json.cequipId);
    		  $("#hceCode").val(json.cequipCode);
    		  $("#ceCode").val(json.cequipCode);
    		  $("#ceName").val(json.cequipName);
    		  $("#ceNum").val(json.cequipNum);
    		  $("#ceModel").val(json.cequipModel);
    		  $("#cemanuInfo").val(json.manuInfo);
    		  $("#cesT").val(json.startTime);
    		  $("#ceStore").val(json.cequipStore);
			}
		});
 		
 		
 		
 		
		
 	}
	//关闭修改子设备窗口
 	function closeModifyCE() {
 		$("#modifyCE").fadeOut();
	}
 	
 	
	 //显示添加变动窗口
 	function openAddChange() {
 		
 		$("#addChange").css("visibility",'visible');
 		$("#addChange").fadeIn();
		
 	}
	//关闭添加变动窗口
 	function closeAddChange() {
 		$("#addChange").fadeOut();
	}
 	
 	//显示修改变动窗口
 	function openModifyChange() {
 		var s=$("input[name='bm']:checked");
 		if(s.length!=1){
 			alert("只能选择一条进行修改");
 			return;
 		}
 		
 		$("#modifyChange").css("visibility",'visible');
 		$("#modifyChange").fadeIn();
 		
 		
 		
 		var id=s.val();
 		var url = "${ctx}/equip/equip_goModifyChange.html"+"?tempId="+Math.round(Math.random()*100);
 		$.ajax({
			url:url,
			dataType: "json",		
			data:{"detailId":id},          
			error: function(XMLHttpRequest, textStatus, errorThrown){
	   			alert("无法取得设备参数分组信息！");
			},
    		success: function(json){
    		
    		  $("#cdetailId").val(json.changeId);
    		  $("#cmanagesection").val(json.managesection);
    		  $("#cusersection").val(json.usersection);
    		  $("#cofficer").val(json.officer);
    		  $("#cuseaddress").val(json.useaddress);
    		  $("#cstate").val(json.state);
    		  $("#cdate").val(json.date);
    		  $("#creason").val(json.reason);
			}
		});
 		
		
 	}
	//关闭修改变动窗口
 	function closeModifyChange() {
 		$("#modifyChange").fadeOut();
	}
 	
 	function showData(id){
 		document.getElementById(id+"Name").style.color='red';
 		document.getElementById(id).style.display='block';
 		var cequipCode=document.getElementById(id+"Code").value;
 		var url = "${ctx}/equip/equip_queryData.html"+"?tempId="+Math.round(Math.random()*100);
 		$.ajax({
			url:url,
			dataType: "json",		
			data:{"cequipCode":cequipCode},          
			error: function(XMLHttpRequest, textStatus, errorThrown){
	   			alert("无法取得设备参数分组信息！");
			},
    		success: function(json){
    			$.each(json.dataList,function(i, ext){
    				var t=i+1;
    				var e1=id+t;
    				var e2=id+t+"-"+t;
  
    				document.getElementById(e1).innerHTML=ext.fieldName;
    		    		document.getElementById(e2).innerHTML=ext.extString;
    		    		
    		    		if(ext.alarmFlag){
    		    			document.getElementById(e1).style.color='red';
    		    			document.getElementById(e2).style.color='red';
    		    		}
    			});
    		
    		 
			}
		});
 		
 		
 		
 	}
 	function hideData(id){
 		document.getElementById(id+"Name").style.color='';
 		document.getElementById(id).style.display='none';
 	}
 	
 	
 		
 	
	window.onscroll=scorll;
 	function scorll(){
 		
 		var sc=document.body.scrollTop||document.documentElement.scrollTop;
 		var height=window.screen.height;
 		var s=sc+height*0.1;
 		if(s<height*0.4){
 		$(".zsbtj").css("top",s+"px");
 		$(".cksysb").css("top",s+"px");
 		}
 	}
 	

	
    
</script>
<script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
