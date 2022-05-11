<%@ page contentType="text/html; charset=GBK" import="java.io.*" import="com.jspsmart.upload.*"%>
<%@ include file="/front/common/taglib.jsp"%>

<%
String name= request.getParameter("name");
String path = request.getContextPath();
System.out.println(path);
if("".equals(name) || name==null){
	return;
}
super.init(config);
String appPath = config.getServletContext().getRealPath(""); // tomcat的物理路径
System.out.println("appPath:"+appPath);
System.out.println(appPath.substring(0,appPath.length()-13));
String realPath = appPath.substring(0,appPath.length()-13);
// 新建一个SmartUpload对象
SmartUpload su = new SmartUpload();
// 初始化
su.initialize(pageContext);
// 设定contentDisposition为null以禁止浏览器自动打开文件，
//浏览器将用acrobat打开。
su.setContentDisposition(null);
realPath=realPath.replace('\\','/');
// 下载文件   说明书
String downloadPath = realPath+"webapps/manage/upload/"+name;
System.out.println("downloadPath:"+downloadPath);
su.downloadFile(downloadPath);
out.clear();
out = pageContext.pushBody();
%>