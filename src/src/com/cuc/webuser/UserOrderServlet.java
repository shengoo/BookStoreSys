package com.cuc.webuser;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.cart.Cart;
import com.cuc.cart.CartItem;
import com.cuc.dao.IOrderDAO;
import com.cuc.dao.imp.OrderDAO;
import com.cuc.model.Order;
import com.cuc.model.WebUser;

public class UserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null) {
			method="";
		}
		if(method.equals("make")){
			make(request, response);
		}else if(method.equals("send")){
			send(request, response);
		}
	}
	public void make(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher rd =request.getRequestDispatcher("/MakeOrder.jsp");
		rd.forward(request, response);
	}
	public void send(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session=request.getSession();
		WebUser webUser=(WebUser)session.getAttribute("webUser");
		Cart cart=(Cart)session.getAttribute("cart");
		ArrayList<CartItem> itemList = cart.getCart();
		
		Order order=new Order();
		order.setWebuserid(webUser.getWebuserid());
		order.setName(request.getParameter("name"));
		order.setPhone(request.getParameter("phone"));
		order.setAddress(request.getParameter("address"));
		order.setEmail(request.getParameter("email"));
		order.setSaletime(new Date(System.currentTimeMillis()));
		order.setStatus("订购");
		order.setItemList(itemList);
		
		IOrderDAO orderDAO=new OrderDAO();
		int orderId=orderDAO.insert(order);
		String message="";
		if(orderId==0){
			message="订单发送失败，请与管理员联系，或稍后再试。";
		}else{
			session.removeAttribute("cart");
			message="你当前订单号为："+orderId+"，如果问题请与公司售后服务部门联系。";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/result.jsp").forward(request,
				response);
	}
}
