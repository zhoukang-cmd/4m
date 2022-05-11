<%@ page language="java" import="java.util.*,com.fourm.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/jquery-1.3.2.min.js"></script>

<script language="javascript" type="text/javascript">	 	
	 	//省份列表
	 	function getProvList(){
	 		var currentProvId ="${sessionScope.currentSession.provId}";
	 		var currentCompId ="${sessionScope.currentSession.compId}";
	 		var currentMineId ="${sessionScope.currentSession.mineId}";
			var currentRoomId ="${sessionScope.currentSession.roomId}";
			var currentEquipId ="${sessionScope.currentSession.equipId}";
		  	//alert("getProvList(),currentProvId:"+currentProvId+"--");
			var url = "${ctx}/common/common_getProvList.html"+"?tempId="+Math.round(Math.random()*100);		
			$.ajax({
				url:url,
				dataType: "json",
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getProvList()");
				},
	    		success: function(json){	    
					$("#provList").empty();  
	    			if(json.provList.length > 0){
	    		        var provhtml = "";
	    		    	$.each(json.provList,function(i, prov){
		    		    	if(currentProvId == prov.keyString){
		    		    		//alert("相同："+currentProvId +prov.keyString+prov.valueString);
	    		    			provhtml += "<option value='"+prov.keyString+"' selected>"+prov.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentProvId +prov.keyString+prov.valueString);
		    		    		provhtml += "<option value='"+prov.keyString+"'>"+prov.valueString+"</option>";
			    		    }
						});
						$("#provList").html(provhtml);
	    		    }
	    		    
	    		    $("#compList").empty();
	    		    if(json.compList.length > 0){
	    		        var comphtml = "";
	    		    	$.each(json.compList,function(i, comp){
		    		    	if(currentCompId == comp.keyString){
		    		    		//alert("相同："+currentCompId +comp.keyString+comp.valueString);
	    		    			comphtml += "<option value='"+comp.keyString+"' selected>"+comp.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentCompId +comp.keyString+comp.valueString);
		    		    		comphtml += "<option value='"+comp.keyString+"'>"+comp.valueString+"</option>";
			    		    }
						});
						$("#compList").html(comphtml);
					}
					$("#mineList").empty();
					 if(json.mineList.length > 0){
	    		        var minehtml = "";
					$.each(json.mineList,function(i, mine){
		    		    	if(currentMineId == mine.keyString){
		    		    		//alert("相同："+currentMineId +mine.keyString+mine.valueString);
	    		    			minehtml += "<option value='"+mine.keyString+"' selected>"+mine.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		///alert("不同："+currentMineId +mine.keyString+mine.valueString);
		    		    		minehtml += "<option value='"+mine.keyString+"'>"+mine.valueString+"</option>";
			    		    }
						});
						$("#mineList").html(minehtml);
					}
					$("#roomList").empty();
					if(json.roomList.length > 0){
						var roomhtml = "";
	    		    	$.each(json.roomList,function(i, room){
		    		    	if(currentRoomId == room.keyString){
		    		    		//alert("相同："+currentRoomId +room.keyString+room.valueString);
	    		    			roomhtml += "<option value='"+room.keyString+"' selected>"+room.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentRoomId +room.keyString+room.valueString);
		    		    		roomhtml += "<option value='"+room.keyString+"'>"+room.valueString+"</option>";
			    		    }
						});
						$("#roomList").html(roomhtml);
					}
					$("#equipList").empty();
					if(json.equipList.length > 0){
						var equiphtml = "";
	    		    	$.each(json.equipList,function(i, equip){
		    		    	if(currentEquipId == equip.keyString){
		    		    		//alert("相同："+currentEquipId +equip.keyString+equip.valueString);
	    		    			equiphtml += "<option value='"+equip.keyString+"' selected>"+equip.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentEquipId +equip.keyString+equip.valueString);
		    		    		equiphtml += "<option value='"+equip.keyString+"'>"+equip.valueString+"</option>";
			    		    }
						});
						$("#equipList").html(equiphtml);
						}
				}	
			});
		}
</script>  
<script language="javascript" type="text/javascript">
	 	//集团列表
	 	function getCompList(){	
			var powerLevel = "${sessionScope.powerLevel}";
	 		var currentCompId ="${sessionScope.currentSession.compId}";
	 		var selectProvId="";
	 		if(powerLevel==1){
	 			selectProvId ="${sessionScope.privId}"; 			
	 		}
		  	//alert("getCompList(),currentCompId:"+currentCompId+"--selectProvId:"+selectProvId);
			var url = "${ctx}/common/common_getCompList.html"+"?tempId="+Math.round(Math.random()*100);
			
			$.ajax({
				url:url,
				dataType: "json",
				data:{selectProvId:selectProvId}, 
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getCompList()");
				},
	    		success: function(json){	    
					$("#compList").empty();  
	    			if(json.compList.length > 0){
	    		        var comphtml = "";
	    		    	$.each(json.compList,function(i, comp){
		    		    	if(currentCompId == comp.keyString){
		    		    		//alert("相同："+currentCompId +comp.keyString+comp.valueString);
	    		    			comphtml += "<option value='"+comp.keyString+"' selected>"+comp.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentCompId +comp.keyString+comp.valueString);
		    		    		comphtml += "<option value='"+comp.keyString+"'>"+comp.valueString+"</option>";
			    		    }
						});
						$("#compList").html(comphtml);
	    		    }
				}
			});
		}
</script>  
<script language="javascript" type="text/javascript">
		//煤矿列表
		function getMineList(){
	 		var currentMineId ="${sessionScope.currentSession.mineId}";
	 		var selectCompId="";
	 		if(powerLevel==2 ){
	 			selectCompId ="${sessionScope.privId}";
	 		}else {
	 			selectCompId =$("#compList").val();
	 		}
		  	//alert("getMineList(),currentMineId:"+currentMineId+"--selectCompId:"+selectCompId);
			var url = "${ctx}/common/common_getMineList.html"+"?tempId="+Math.round(Math.random()*100);
			
			$.ajax({
				url:url,
				dataType: "json",
				data:{selectCompId:selectCompId}, 
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getCompList()");
				},
	    		success: function(json){	    
					$("#mineList").empty();  
	    			if(json.mineList.length > 0){
	    		        var minehtml = "";
	    		    	$.each(json.mineList,function(i, mine){
		    		    	if(currentMineId == mine.keyString){
		    		    		//alert("相同："+currentMineId +mine.keyString+mine.valueString);
	    		    			minehtml += "<option value='"+mine.keyString+"' selected>"+mine.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentMineId +mine.keyString+mine.valueString);
		    		    		minehtml += "<option value='"+mine.keyString+"'>"+mine.valueString+"</option>";
			    		    }
						});
						$("#mineList").html(minehtml);
						getRoomList();
	    		    }
				}
			});
		}
</script>  
<script language="javascript" type="text/javascript">
		//机房列表
		function getRoomList(){
	 		var currentRoomId ="${sessionScope.currentSession.roomId}";
	 		var selectMineId="";
	 		if(powerLevel==3){
	 			selectMineId ="${sessionScope.privId}";
	 		}else {
	 			selectMineId =$("#mineList").val();
	 		}
		  	//alert("getRoomList(),currentRoomId:"+currentRoomId+"--selectMineId:"+selectMineId);
			var url = "${ctx}/common/common_getRoomList.html"+"?tempId="+Math.round(Math.random()*100);
			
			$.ajax({
				url:url,
				dataType: "json",
				data:{selectMineId:selectMineId}, 
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getRoomList()");
				},
	    		success: function(json){	    
					$("#roomList").empty();  
	    			if(json.roomList.length > 0){
	    		        var roomhtml = "";
	    		    	$.each(json.roomList,function(i, room){
		    		    	if(currentRoomId == room.keyString){
		    		    		//alert("相同："+currentRoomId +room.keyString+room.valueString);
	    		    			roomhtml += "<option value='"+room.keyString+"' selected>"+room.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentRoomId +room.keyString+room.valueString);
		    		    		roomhtml += "<option value='"+room.keyString+"'>"+room.valueString+"</option>";
			    		    }
						});
						$("#roomList").html(roomhtml);
						getEquipList();
	    		    }
				}
			});
		}
</script>  
<script language="javascript" type="text/javascript">
		//设备列表
		function getEquipList(){
	 		var currentEquipId ="${sessionScope.currentSession.equipId}"+"?tempId="+Math.round(Math.random()*100);;
	 		var selectRoomId="";
	 		if(powerLevel==4){
	 			selectRoomId ="${sessionScope.privId}";
	 		}else {
	 			selectRoomId =$("#roomList").val();
	 		}
		  	//alert("getEquipList(),currentEquipId:"+currentEquipId+"--selectRoomId:"+selectRoomId);
			var url = "${ctx}/common/common_getEquipList.html"+"?tempId="+Math.round(Math.random()*100);
			
			$.ajax({
				url:url,
				dataType: "json",
				data:{selectRoomId:selectRoomId}, 
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:getEquipList()");
				},
	    		success: function(json){	    
					$("#equipList").empty();  
	    			if(json.equipList.length > 0){
	    		        var equiphtml = "";
	    		    	$.each(json.equipList,function(i, equip){
		    		    	if(currentEquipId == equip.keyString){
		    		    		//alert("相同："+currentEquipId +equip.keyString+equip.valueString);
	    		    			equiphtml += "<option value='"+equip.keyString+"' selected>"+equip.valueString+"</option>";	    		    			
		    		    	}else{
		    		    		//alert("不同："+currentEquipId +equip.keyString+equip.valueString);
		    		    		equiphtml += "<option value='"+equip.keyString+"'>"+equip.valueString+"</option>";
			    		    }
						});
						$("#equipList").html(equiphtml);
						setCurrentSession();
	    		    }
				}
			});
		}
</script>  
<script language="javascript" type="text/javascript">
		//设置当前session
		function setCurrentSession(){
	 		var selectEquipId=$("#equipList").val();
	 		
		  	//alert("setCurrentSession(),selectEquipId:"+selectEquipId+"--");
			var url = "${ctx}/common/common_setCurrentSession.html"+"?tempId="+Math.round(Math.random()*100);
			
			$.ajax({
				url:url,
				dataType: "",
				data:{selectEquipId:selectEquipId}, 
				error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("出错了:setCurrentSession()");
				},
	    		success: function(){	    
				//alert("setCurrentSession()");
				deal();
				}
			});
		}
</script>
