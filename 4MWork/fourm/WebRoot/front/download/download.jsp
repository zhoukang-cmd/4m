<%@ page contentType="text/html; charset=GBK" import="java.io.*" import="com.jspsmart.upload.*"%><%
// 新建一个SmartUpload对象
SmartUpload su = new SmartUpload();
// 初始化
su.initialize(pageContext);
// 设定contentDisposition为null以禁止浏览器自动打开文件，
//浏览器将用acrobat打开。
su.setContentDisposition(null);
// 下载文件
String downloadPath = "/downloads/videopackage-build20120228.exe";
su.downloadFile(downloadPath);
 out.clear();
 out = pageContext.pushBody();
%>