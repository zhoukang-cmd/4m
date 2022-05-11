<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中矿四迈 4M系统</title>
		<style type="text/css">
		*{
		margin:0px;
		padding:0px;
list-style:none;
		}
		body{
		 background-color:#EEF7FF;
		}
.mapPainter {
	background-color: #2996f1;
	height: 36px;
	color: #FFF;
}

.mapPainter p {
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	line-height: 36px;
}
.fudong {
	background-image: url(../images/painter.jpg);
	height: 180px;
	width: 476px;
	position:absolute;
	left:60%;
	top: 85%;
	font-family:'Lucida Grande','Lucida Sans Unicode',Verdana,Arial,Helvetica,sans-serif;
	font-size:14px;
	color: #3E576F;
}
.fudong-csbt p{
	height:30px;
	line-height:30px;
	text-align:center;
	
	}
.fudong-csnr ul li {
	float:left;
	height: 28px;
	
	line-height:36px;
	text-align:center;
	color:#036;
	}	
.fudong-csnr-kd {
    width:142px;
}
.fudong-csnr-kd01{
    width:330px;
}	
</style>
		<script type="text/javascript"
			src="${ctx}/js/common/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/highcharts.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/jQuery.timers.js"></script>
		<script language="javascript">
	var x = new Array();
	var y = new Array();
	var x1 = new Array();
	var y1 = new Array();
	var x2;
	var id =<%=request.getParameter("detailId")%>

    function requestData() {
		var url = "${ctx}/fault/fault_goFaultPainter.html" + "?tempId="
				+ Math.round(Math.random() * 100);
		$.ajax({
			url : url,
			dataType : "json",
			data : {
				detailId : id
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("无法取得实时数据！");
			},
			success : function(json) {

				$.each(json.extLs, function(i, ext) {

					x.push(ext.fieldName);
					y.push(parseFloat(ext.extString));
				});

			}
		});
	   
	    }

function showDispalyData(){
    var url = "${ctx}/fault/fault_showDispalyData.html" + "?tempId="
				+ Math.round(Math.random() * 100);
		$.ajax({
			url : url,
			dataType : "json",
			data : {
				detailId : id
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert("无法取得实时数据！");
			},
			success : function(json) {
              
            x2=parseFloat(json.extString);
				

			}
		});
}


function queryData(){
 var url = "${ctx}/fault/fault_queryData.html" + "?tempId="
				+ Math.round(Math.random() * 100);
		$.ajax({
			url : url,
			dataType : "json",
			data : {
				detailId : id
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("无法取得八小时内的实时数据！");
			},
			success : function(json) {
              
            
				$.each(json.extLs, function(i, ext) {

//					x1.push( Highcharts.dateFormat('%H:%M:%S',ext.fieldName));
            var str =ext.fieldName;
            str = str.replace(/-/g,"/");
            var date = new Date(str );
       var d=Highcharts.dateFormat('%H:%M:%S', date);
					x1.push(date.getTime());
					
					y1.push(parseFloat(ext.extString));
				});
				

			}
		});

}

$(document).ready(function() {
Highcharts.setOptions({global:{useUTC : false}});  
        requestData();
        queryData();
	   setTimeout("paint()", 2000);
	   setTimeout("paintDsipaly()",2000);
	}
	);
	
function paint(){
var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'painterContent',
				type : 'spline',
				shadow:true,
				borderRadius:50,
                borderWidth:5
			},
			title : {
				text : '<b>故障信息前后6小时数据</b>' ,
				x : -20
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'top',
				borderColor : '#C98657',
				x : -20,
				y : 100,
				borderWidth : 1
				   
			},
			credits:{//右下角的文本  
          enabled: true,  
           position: {//位置设置  
               align: 'right',  
                x: -50,  
              y: -20  
           },  
          href: "${ctx}/display/display_goDisplay.html",//点击文本时的链接  
           style: {  
                color:'blue'  
           },  
           text: "中矿四迈"//显示的内容  
        },  
			
			xAxis : {
				title : {
					text : '时间'
				},
				categories : x
			},
			yAxis : {
				title : {
					text : '单位'
				}
			},
			tooltip : {
				enabled : true,
				formatter : function() {
					return '<b>' + this.series.name + '</b><br/>' + '<b>'
							+  this.x + '时: </b>' + this.y;
				}
			},
			series : [ {
			     name:'故障数据',
				data :y
			} ]
		});
		}
function paintDsipaly(){
var chart;  
    chart = new Highcharts.Chart({  
        chart: {  
            renderTo: 'displayPaint',  
            type: 'column',  
             shadow:true,
             borderRadius:50,
             borderWidth:5,
             events: {  
               load: function() {  
                    // set up the updating of the chart each second  
                   var series = this.series[0];  
                   
               /**   $('body').everyTime('3s','A',function() {  
                      showDispalyData();                
                                               var Digital=new Date();
                                              
                  var k=Digital.getTime();
                       var  y =x2;  
                        series.addPoint([k, y],true, true);  
                    },0,true);
                    */
                   setInterval(function() {  
                      showDispalyData();                
                                               var Digital=new Date();
                                              
                  var k=Digital.getTime();
                       var  y =x2;  
                        series.addPoint([k, y],true, true);  
                    },3000); 
        
                }  
}  
           
},  
       title: {  
           text: '<b>故障信息实时参数</b>'  
        },  
        xAxis: {  
         type: 'datetime',
        tickPixelInterval: 50  

        },  
        yAxis: {  
            title: {  
                text: '单位'  
            }
          
        },  
        tooltip: {  
            formatter: function() {  
return  '<b>' + this.series.name + '</b><br/>' + '<b>'
							+ Highcharts.dateFormat('%H:%M:%S',this.x) + '时: </b>' + this.y; 
            }  
        },  

        series: [{  
          name: '监测数据',  
           data:  (function() {  
              // generate an array of random data  
             var data = [];
               
              for (var i = 0; i < x1.length; i++) {  
                   data.push({  
                     x:x1[i],  
                       y:y1[i]  
                   });  
               }  
              return data;  
            })()  
       }]  
   });  
		}
</script>


	</head>

	<body>
		<div class="mapPainter">
			<p>
				故障分析图
			</p>
		</div>
		
		<div style="position:absolute;left:45%;top:30%;width:128px;height: 128px;"><img src="../images/load.gif"/></div>
		
		<div id="painterContent"
			style="width: 90%; height: 100%; margin: 0 auto;">
		</div>
		<div id="displayPaint" style="width: 50%; height: 100%;float: left;"></div>
	
		<div class="fudong">
             <div class="fudong-csbt"><p>故障参数</p>
               </div>
              <div class="fudong-csnr"> 
             <ul>
             <li class="fudong-csnr-kd">故障时间:</li>
             <li class="fudong-csnr-kd01"><s:property  value="fault.faultTime"/> </li>
             <li class="fudong-csnr-kd">故障位置:</li>
             <li class="fudong-csnr-kd01"><s:property  value="fault.faultPosition"/> </li>
             <li class="fudong-csnr-kd">故障参数:</li>
             <li class="fudong-csnr-kd01"><s:property  value="fault.faultField"/> </li>
             <li class="fudong-csnr-kd">故障等级:</li>
             <li class="fudong-csnr-kd01"><s:property  value="fault.faultLevel"/> </li>
             <li class="fudong-csnr-kd">发展趋势: </li>
             <li class="fudong-csnr-kd01"><s:property  value="fault.faultTrend"/> </li>
          
           </ul>
</div>
</div>
		
		
		
	</body>


</html>
