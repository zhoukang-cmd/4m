<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/front/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name ="keywords" content="中矿四迈,4M系统,远程监控,视频监控,故障诊断,煤矿,设备" />
	<meta name="description" content="大型矿山机电设备远程故障诊断系统" />
	<title>中矿四迈 4M系统</title>
	<link rel="stylesheet" href="${ctx}/css/index.css" type="text/css" media="all" />
</head>
<body>
	<div class="login">
		<div class="login_box">
			<div class="logintitle"></div>
			<form id="form1" name="form1" method="post" action="${ctx}/user/user_login.html">
			<div class="loginmiddle">
				<ul>
					<li>用户名：<input type="text" name="user.userName" id="adminName"  /></li>
					<li>密　码：<input type="password" name="user.userPassword" id="adminPasswd"/></li>
					<li id="loginButton" style=" padding-top:15px;">
						<span style=" margin-right:35px;"><img src="${ctx}/images/btn_login.jpg" width="113" height="36" onclick="checkandsubmit()"/></span>
						<span><img src="${ctx}/images/btn_reset.jpg" width="113" height="36" onclick="reset()"/></span>
					</li>
					<li id="loginInfo" style="visibility:hidden;margin:-40px 0 0 40px">正在自动登录，请稍候...</li>
				</ul>
			</div>
			</form>
			<div class="loginbottom"></div>
		</div>
		<%@ include file="/front/common/footer.jsp"%>
	</div>
	<script type="text/javascript">
		if("${autoLogin}" != "" && "${autoLogin}" != null) { //自动登录
			document.getElementById("loginButton").style.visibility="hidden";
			document.getElementById("loginInfo").style.visibility="visible";
			document.getElementById("adminName").value="${autoLogin}".split('\t')[0];
			document.getElementById("adminPasswd").value="${autoLogin}".split('\t')[1];
			document.getElementById("form1").submit();
		}
		function checkandsubmit(){
			document.getElementById("form1").submit();
		}
		function reset()
		{
			document.getElementById("adminName").value="";
			document.getElementById("adminPasswd").value="";
		}
		
		
		document.onkeydown=function(event){
		
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){
		 checkandsubmit();
		 }
		 }; 
		
		
	</script>
</body>
</html>
