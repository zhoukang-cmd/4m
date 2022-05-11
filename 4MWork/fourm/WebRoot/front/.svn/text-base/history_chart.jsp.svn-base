<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>${selectFieldName}参数走势图</title>

		<script type="text/javascript" src="${ctx}/js/common/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			$(function () {
				var x = new Array();
				var y = new Array();
			
				<s:iterator value="valueList" id="vl">
					x.push("${vl.keyString}");		
					<s:if test="#vl.valueString==null">
						y.push(null);
					</s:if>
					<s:else>
						y.push(parseFloat("${vl.valueString}"));
					</s:else>
				</s:iterator>
				
				var chart;
			    $(document).ready(function() {
			        chart = new Highcharts.Chart({
			            chart: {
			                renderTo: 'container',
			                type: 'spline'
			            },
			            title: {
			                text: '',
			                x: -20
			            },
			            legend: {
                			layout: 'vertical',
		                	align: 'right',
		                	verticalAlign: 'top',
							borderColor: '#C98657',
		                	x: -20,
		                	y: 100,
		                	borderWidth: 1
            			},
			            xAxis: {
			            	title: {
			                    text: '时间'
			                },
			                categories: x
			            },
			            yAxis: {
			                title: {
			                    text: '单位'
			                }
			            },
			            tooltip: {
			            	enabled: true,
			                formatter: function() {
			                        return '<b>'+ this.series.name +'</b><br/>'+
			                        this.x +': '+ this.y;
			                }
			            },
			            plotOptions: {
			                line: {
			                    dataLabels: {
			                        enabled: false
			                    },
			                    enableMouseTracking: true
			                }
			            },
			            series: [{
			                name: "${selectFieldName}",
			                data: y
			            }]
			        });
			    });
			});
		</script>
		<script src="${ctx}/js/common/highcharts.js"></script>
		<script src="${ctx}/js/modules/exporting.js"></script>
	</head>
	<body>
		<div id="container" style="width:100%; height:400px;"></div>
	</body>
</html>