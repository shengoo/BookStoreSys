<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
<HEAD>
<TITLE></TITLE>
<LINK REL=stylesheet HREF="script/toc.css" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script/AdminTree.js"></script>
<STYLE TYPE='text/css'>
.level1 {margin-left:30;}
.level2 {display:none;margin-left:38;}
</STYLE>
</HEAD>
<BODY onload="init()" topmargin="0" leftmargin="0" rightmargin="0">
<br><br>
<h4>系统菜单</h4><hr>
<DIV CLASS="level1" ID='head2Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head2");'><IMG border=0 name=imEx  src="images/arrowUp.gif" id=ttt>帐户管理</a>
</DIV>

<DIV CLASS="level2" ID='head2Child'>
	<A href="addaccount.jsp" target="right" id=ttt onclick="doClick()"><LI>添加帐户</LI></a>
	<A href="changepassword.jsp" target="right" id=ttt ><LI>更新密码</LI></a>
	<A href="searchaccount.jsp" target="right" id=ttt ><LI>查询帐户</LI></a>
</DIV>

<DIV CLASS="level1" ID='head3Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head3");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17>客户管理</a>
</DIV>

<DIV CLASS="level2" ID='head3Child'>
	<LI>添加客户</LI>
	<LI>更新客户</LI>
	<LI>查询客户</LI>
</DIV>

<DIV CLASS="level1" ID='head4Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head4");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17>商品管理</a>
</DIV>

<DIV CLASS="level2" ID='head4Child'>
	<LI>添加商品</LI>
	<LI>更新商品</LI>
	<LI>查询商品</LI>
</DIV>

<DIV CLASS="level1" ID='head6Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head6");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17>订单管理</a>
</DIV>

<DIV CLASS="level2" ID='head6Child'>
	<A href="<%=basePath%>servlet/AdminOrderServlet?method=show" target=right id=ttt><LI>查看订单</LI></a>
</DIV>

<DIV CLASS="level1" ID='head7Parent'>
               <A class=OUTDENT href=<%=basePath%>servlet/AdminServlet?method=logout target=_top><IMG border=0 height=13 name=imEx  src="images/arrowDn.gif" width=17>退出系统</a>
</DIV>
</BODY>
</html>
