<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cuc.cart.*"%>
<%@ include file="header.jsp"%>

<table width="700" border="1">
	<tr>
		<td width="200">
			商品名称
		</td>
		<td width="100">
			商品价格
		</td>
		<td width="100">
			购买数量
		</td>
		<td width="100">
			更新数量
		</td>
		<td width="100">
			小计
		</td>
		<td width="100">
			操作
		</td>
	</tr>
	<%
		ArrayList<CartItem> itemList = (ArrayList<CartItem>) request
				.getAttribute("itemList");
		CartItem item = null;
		for (int i = 0; i < itemList.size(); i++) {
			item = itemList.get(i);
	%>

	<tr>
		<td><%=item.getGoodName()%></td>
		<td><%=item.getPrice()%>元
		</td>
		<td><%=item.getNumber()%></td>
		<td>
			<form action="<%=basePath%>servlet/CartServlet?method=change"
				method=post>
				<input type=text size=3 name=number value="1">
				<input type=hidden name=goodId value=<%=item.getGoodId()%>>
				<input type=submit value="修改">
			</form>
		</td>
		<td><%=item.getSum()%>元
		</td>
		<td>
			<a
				href="<%=basePath%>servlet/CartServlet?method=remove&goodId=<%=item.getGoodId()%>">删除</a>
		</td>
	</tr>
	<%
		}
	%>
	<tr>
		<td colspan="4">
			总计：<%=request.getAttribute("total")%>元
		</td>
		<%
			if (itemList.size() != 0) {
		%>
		<td colspan="2">
			<center>
				<a href="<%=basePath%>servlet/UserOrderServlet?method=make">结算</a>
			</center>
		</td>
		<%
			} else {
		%>
		<td colspan="2">
			没有商品！
		</td>
		<%
			}
		%>
	</tr>
</table>
<br>
<a href="<%=basePath%>" target=_top>继续购物</a>
<%@ include file="footer.jsp"%>