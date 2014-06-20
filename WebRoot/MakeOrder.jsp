<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cuc.cart.*"%>
<%@ include file="header.jsp"%>
<table width="500" border="1">
<tr>
<td width="200">商品名称</td>
<td width="100">商品价格</td>
<td width="100">购买数量</td>
<td width="100">小计</td>
</tr>
<%		Cart cart = (Cart) session.getAttribute("cart");
		ArrayList<CartItem> items = cart.getCart();
		CartItem item = null;
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
%>

<tr>
<td><%=item.getGoodName()%></td>
<td><%=item.getPrice()%>元</td>
<td><%=item.getNumber() %></td>
<td><%=item.getSum() %>元</td>
</tr>
<%
}
%>
<tr>
<td colspan="4">总计：<%=cart.getTotal()%>元</td>
</tr>
</table>
<br><a href="<%=basePath%>" target=_top>继续购物</a><br>
<table><tr><td><form method='post' action='<%=basePath%>servlet/UserOrderServlet?method=send' id='addorder'>
<br>请真实准确填写以下信息，以便我们向您发送您所定购的图书<br>
真实姓名：<input type='text' maxlength='8' size='8' name='name'>（必填）<br>
电子邮件：<input type='text' maxlength='20' size='20' name='email'><br>
联系电话：<input type='text' maxlength='13' size='13' name='phone'>（必填）<br>
联系地址：（必填）<br><textarea cols='40' rows='5' name='address'></textarea><br>
付款方式：<input type='radio' name="pay" value="1" checked>网银支付<input type='radio' name="pay" value="2">货到付款<input type='radio' name="pay" value="3">汇款<br>
送货方式：<select name="send"><option value="1">顺丰</option><option value="2">申通</option><option value="3">EMS</option><option value="4">其它</option></select><br>
</td></tr></table>
<input type='submit' value='发送定单'></form>
</table>
<%@ include file="footer.jsp"%>