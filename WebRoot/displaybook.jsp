<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.model.*"%>
<%@ include file="header.jsp"%>
		<%
			ArrayList<Book> booklist = (ArrayList<Book>) request
					.getAttribute("booklist");
		%>
		<br>
		<table width="88%" border="0" align="center" cellspacing="0">
			<tr align="center" valign="middle" bgcolor="#006699">
				<td>
					<font color="#FFFFFF">ISBN</font>
				</td>
				<td>
					<font color="#FFFFFF">图书名称</font>
				</td>
				<td >
					<font color="#FFFFFF">价格</font>
				</td>
				<td>
					<font color="#FFFFFF">图书类别</font>
				</td>
				<td >
					<font color="#FFFFFF">出版社</font>
				</td>
				<td>
					<font color="#FFFFFF">数量</font>
				</td>
				<td>
					<font color="#FFFFFF">操作</font>
				</td>
			</tr>
			<%
				Book book = new Book();
				for (int i = 0; i < booklist.size(); i++) {
					book = booklist.get(i);
			%>
			<tr align="center">
				<td height="26"><%=book.getIsbn()%></td>
				<td height="26"><%=book.getBookname()%></td>
				<td height="26"><%=book.getPrice()%></td>
				<td height="26"><%=book.getBookkind()%></td>
				<td height="26"><%=book.getPublisher()%></td>
				<td height="26"><%=book.getNumber()%></td>
				<td height="26">
				<a href="<%=basePath%>servlet/CartServlet?method=add&goodId=<%=book.getIsbn()%>&goodName=<%=book.getBookname()%>&price=<%=book.getPrice()%>&number=1"><img src="<%=basePath%>images/buybutton.gif" border='0'></a>
				</td>
			</tr>
			<%
				}
			%>
		</table>
<%=request.getAttribute("pageTool") %>
<%@ include file="footer.jsp"%>
