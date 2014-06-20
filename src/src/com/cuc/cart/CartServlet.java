package com.cuc.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.model.WebUser;

public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null) {
			method = "";
		}
		HttpSession session = request.getSession();
		WebUser webUser = (WebUser) session.getAttribute("webUser");
		if (webUser == null) {
			response.getWriter().println("<SCRIPT LANGUAGE='JavaScript'>alert('请先注册，并登录！');javascript:history.back(-1);</SCRIPT>");
		}else{
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
			if(method.equals("add")){
				add(request, response);
			}else if(method.equals("show")){
				show(request, response);
			}else if(method.equals("remove")){
				remove(request, response);
			}else if(method.equals("change")){
				change(request, response);
			}
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String goodId = request.getParameter("goodId");
        //以下这行代码是URL超链接传值的乱码处理，此处过滤器不起作用
		String goodName = new String(request.getParameter("goodName").getBytes("ISO-8859-1"),"UTF-8");
		float price = Float.parseFloat(request.getParameter("price"));
		int number = 1;
		try {
			number = Integer.parseInt(request.getParameter("number"));
		} catch (NumberFormatException e) {
			number = 1;
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.addCartItem(new CartItem(goodId, goodName, price, number));
		response.getWriter().println("<SCRIPT LANGUAGE='JavaScript'>alert('已添加到购物车！');javascript:history.back(-1);</SCRIPT>");
	}
	public void show(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session=request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		request.setAttribute("itemList",cart.getCart());
		request.setAttribute("total",cart.getTotal());
		RequestDispatcher rd =request.getRequestDispatcher("/ShowCartItem.jsp");
		rd.forward(request, response);
	}
	public void remove(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session=request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.removeCartItem(request.getParameter("goodId"));

		show(request, response);
	}
	public void change(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String goodId = request.getParameter("goodId");
		int number = 1;
		try {
			number = Integer.parseInt(request.getParameter("number"));
		} catch (NumberFormatException e) {
			number = 1;
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.changeNumber(goodId, number);

		show(request, response);
	}
}
