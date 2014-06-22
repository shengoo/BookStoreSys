<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cuc.model.*,java.util.*"%>
<%@ include file="header.jsp"%>

<%
	ArrayList<Order> orderList =(ArrayList<Order>) request.getAttribute("orderList");;
%>
		
		<table width="88%" border="0" align="center" cellspacing="0">
			<tr align="center" valign="middle" bgcolor="#006699">
				<td>
					<font color="#FFFFFF">定单号</font>
				</td>
				<td>
					<font color="#FFFFFF">网络用户号</font>
				</td>
				<td>
					<font color="#FFFFFF">姓名</font>
				</td>
				<td>
					<font color="#FFFFFF">Email</font>
				</td>
				<td>
					<font color="#FFFFFF">电话号码</font>
				</td>
				<td>
					<font color="#FFFFFF">通信地址</font>
				</td>
				<td>
					<font color="#FFFFFF">定单状态</font>
				</td>
				<td>
					<font color="#FFFFFF">定购时间</font>
				</td>
				<td>
					<font color="#FFFFFF">定单明细</font>
				</td>
				<td>
					<font color="#FFFFFF">操作选择</font>
				</td>
			</tr>

			<%
				for(int i=0;i<orderList.size();i++) {
					Order order=orderList.get(i);
			%>
			<tr align="center">
				<td height="26"><%=order.getOrderid()%></td>
				<td height="26"><%=order.getWebuserid()%></td>
				<td height="26"><%=order.getName()%></td>
				<td height="26"><%=order.getEmail()%></td>
				<td height="26"><%=order.getPhone()%></td>
				<td height="26"><%=order.getAddress()%></td>
				<td height="26"><%=order.getStatus()%></td>
				<td height="26"><%=order.getSaletime()%></td>
				<td height="26">
					<a href="<%=basePath%>servlet/AdminOrderServlet?method=showinfo&orderid=<%=order.getOrderid()%>">查看明细单</a>
				</td>
				<td height="26">
					删除/发货
				</td>
			</tr>
			<%
				}
			%>
		</table>
<%=request.getAttribute("pageTool") %>
<%@ include file="footer.jsp"%>