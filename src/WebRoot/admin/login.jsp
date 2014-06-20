<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
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
	</head>

	<body>
<SCRIPT LANGUAGE="JavaScript">
	function changeCode() {  
	       var img=document.getElementById("checkCode"); 
	       img.src="<%=basePath%>admin/imgcode.jsp?date="+new Date();//此处很重要，不加后面的就不会变
	}    
</SCRIPT>
		<div id="container">
			<div id="banner">
				<h1>
					福建师范大学协和学院网络书店
				</h1>
			</div>
			<div id="menu">
				<a href="mailto:"><img
						src="<%=basePath%>admin/images/c3.gif" border=0 alt="救助中心"></a>
				 <a href="<%=basePath%>admin/about.jsp"><img src="<%=basePath%>admin/images/c2.gif" border=0 alt="系统简介"></a>
				<img src="<%=basePath%>admin/images/c1.gif" border=0 alt="操作手册">
			</div>
			<br />
			<div id="main">
				<table border="0" align="center">
					<tr>
						<td>
							<%
								String message = (String) request.getAttribute("message");
								if (message != null) {
									out.print("<font color='red'>" + message + "</font>");
								}
							%>
							<form action="<%=basePath%>servlet/AdminServlet?method=login"
								method="post">
								用户名：
								<input type="text" name="username">
								<br>
								密&nbsp;&nbsp;&nbsp;码：
								<input type="password" name="password">
								<br>
								验证码：
								<input type="text" name="code">
								<img id="checkCode" src="<%=basePath%>admin/imgcode.jsp" onclick="changeCode();" title="点击图片刷新验证码"/>
								<br>
								<input type="submit" value="登录">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="取消">
								<br>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>