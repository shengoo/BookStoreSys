<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cuc.dao.imp.*,com.cuc.model.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	WebUser user = (WebUser)request.getSession().getAttribute("webUser");
%>
<html>
	<head>
		<link href="<%=basePath%>admin/style.css" type="text/css"
			rel="stylesheet">
	</head>
<body>
	<SCRIPT LANGUAGE="JavaScript">
	function login() {
		var webuser = document.loginForm.webuser.value;
		var password = document.loginForm.password.value;
		var xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		var url = "<%=basePath%>servlet/WebUserServlet?method=login&webuser=" + webuser
				+ "&password=" + password;
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				loginDiv.innerHTML = xmlHttp.responseText;
			} else {
				loginDiv.innerHTML = "正在登录，请稍候......";
			}
		}
		xmlHttp.send();
	}
</SCRIPT>
		<center>
			<table boder=0>
				<tr>
					<td>
					<%if(user!=null) {%>
					欢迎  <%= user.getWebuser() %> 光临!
					<% }else{ %>
						<div id="loginDiv">
							<form name="loginForm">
								帐号：
								<input type="text" maxlength="6" size="6" name="webuser">
								密码：
								<input type="password" maxlength="6" size="6" name="password">
								<input type="button" value="登录" onclick="login()">
							</form>
						</div>
					<%} %>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=basePath%>register.jsp" target="content">新用户注册</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=basePath%>servlet/UserOrderServlet?method=show" target="content">订单查询</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=basePath%>servlet/CartServlet?method=show" target="content">查看我的购物车</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=basePath%>servlet/WebUserServlet?method=logout" target="_top">安全退出</a>
					</td>
					<td>
						<form method="post" action="<%=basePath%>servlet/SearchServlet"
							name="booksearch" target="content">
							书名：
							<input type="text" maxlength="20" size="20" name="bookname">
							ISBN：
							<input type="text" maxlength="15" size="15" name="isbn">
							图书类别：
							<select name="bookkind">
								<option value="all">
									所有类别
								</option>
								<%
									ArrayList<BookType> list=new BookTypeDAO().searchAll();
									BookType booktype=new BookType();
										for(int i=0;i<list.size();i++) {
											booktype=list.get(i);
								%>
								<option value=<%=booktype.getBookkind()%>>
									<%=booktype.getKindname()	%>
								</option>
								<%
										}
								%>
							</select>
							<input type="submit" value="提交">
						</form>
					</td>
				</tr>
			</table>
		</center>
	</body>
</html>
