<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>网络书店</title>
		<link href="<%=basePath%>admin/style.css" type="text/css"
			rel="stylesheet">
		<link href="<%=basePath%>css/jquery-ui-1.10.4.css" type="text/css"
			rel="stylesheet">
		<script src="<%=basePath%>scripts/jquery-2.1.1.js"></script>
		<script src="<%=basePath%>scripts/jquery-ui-1.10.4.js"></script>
	</head>
	<body>
		<div id="container">
			<div id="banner">
<img border="0" src="<%=basePath%>images/logo.jpg">
			</div>
			<div id="menu">
				<a href="mailto:"><img
						src="<%=basePath%>admin/images/c3.gif" border=0 alt="救助中心"></a>
				 <a href="<%=basePath%>admin/about.jsp"><img src="<%=basePath%>admin/images/c2.gif" border=0 alt="系统简介"></a>
				<img src="<%=basePath%>admin/images/c1.gif" border=0 alt="操作手册">
			</div>
			<div id="main">