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
import com.cuc.util.PageUtil;

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
		}else if(method.equals("show")){
			show(request, response);
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
		order.setStatus("����");
		order.setItemList(itemList);
		
		IOrderDAO orderDAO=new OrderDAO();
		int orderId=orderDAO.insert(order);
		String message="";
		if(orderId==0){
			message="��������ʧ�ܣ��������Ա��ϵ�����Ժ����ԡ�";
		}else{
			session.removeAttribute("cart");
			message="�㵱ǰ������Ϊ��"+orderId+"������������빫˾�ۺ��������ϵ��";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/result.jsp").forward(request,
				response);
	}
	
	public void show(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		HttpSession session=request.getSession();
		WebUser webUser=(WebUser)session.getAttribute("webUser");
		
		IOrderDAO orderDAO=new OrderDAO();

		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(3); // ���÷�ҳ��С
		int pageSize = pageUtil.getPageSize();
		int rsCount = orderDAO.getOrderCount(webUser.getWebuserid());// ��ü�¼����

		pageUtil.setRsCount(rsCount);
		

		int pageCount = pageUtil.getPageCount();// ����ҳ��
		int currentPage = pageUtil.getCurrentPage();// ��õ�ǰҳ
		String pageTool = pageUtil.createPageTool(pageUtil.BbsText);// ������ҳ������

		
		int skip = pageSize * (currentPage-1);
		ArrayList<Order> orderList = orderDAO.GetUserOrders(webUser.getWebuserid(), skip, pageSize, new java.util.Date(), new java.util.Date());
		request.setAttribute("orderList", orderList);
		
		request.setAttribute("pageTool", pageTool);
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/orders.jsp");
		rd.forward(request, response);
	}
}
