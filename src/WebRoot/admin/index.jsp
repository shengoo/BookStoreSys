<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//获得项目名
	String path = request.getContextPath();
	//获得绝对路径
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% response.sendRedirect(basePath+"admin/login.jsp"); %>