<%@ page pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/report.css" />
    <%@ include file="/js/report.js"%>
</head>

<body>

<div class="sbox">
	<div class="report_bottons">
		<hr />
		<label id="title"><b>${tableTitle}</b>&nbsp;${roomname}&nbsp;${backTime}</label>
		<button onclick="myPrint()">打 印</button>
		<button onclick="goback()">返 回</button>
		<hr />
	</div>
	<div id="table" class="tab_auto" >
        <table width="2100" border="0" cellspacing="0" cellpadding="0">
        <tbody>
          <tr bgcolor="#fafafa">
            <s:iterator value="strListTitle" id="lst">
              <td width="70">${lst}</td>
            </s:iterator>
          </tr>
          <s:iterator value="strListAll" id="lstAll">
            <tr>
          	  <s:iterator value="lstAll"  id="lst" >
          		<td><s:property value="lst"/></td>
          	  </s:iterator>
            </tr>
          </s:iterator>
        </tbody>
        </table>
    </div>
</div>
<script>
	function myPrint(){
		var newWindow=window.open("打印窗口");
		newWindow.document.write(document.getElementById('title').innerHTML);
		newWindow.document.write(document.getElementById('table').innerHTML);
		newWindow.document.close();
		newWindow.print();
		newWindow.close();
	}
</script>
</body>
</html>
