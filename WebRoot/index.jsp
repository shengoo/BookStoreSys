<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
  <frameset rows='30,*' framespacing='0' border='0' bordercolor='#000000' frameborder='0'>
    <frame src='<%=basePath%>menubar.jsp' name='menu' scrolling=no >
    <frame src='<%=basePath%>welcome.jsp' name='content'>
  </frameset>
</html>