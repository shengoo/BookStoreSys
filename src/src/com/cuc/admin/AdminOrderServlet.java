package com.cuc.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IOrderDAO;
import com.cuc.dao.imp.OrderDAO;
import com.cuc.model.Order;

public class AdminOrderServlet extends HttpServlet {
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
		if (method.equals("show")) {
			show(request, response);
		} else if (method.equals("showinfo")) {
			showinfo(request, response);
		}
	}

	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IOrderDAO orderDAO=new OrderDAO();
		ArrayList<Order> orderList = orderDAO.searchAll();
		request.setAttribute("orderList", orderList);
		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/ShowOrder.jsp");
		rd.forward(request, response);
	}

	public void showinfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int orderid=Integer.parseInt(request.getParameter("orderid"));
		IOrderDAO orderDAO=new OrderDAO();
		ArrayList orderinfo=orderDAO.searchOrderInfo(orderid);
		request.setAttribute("orderinfo", orderinfo);
		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/ShowOrderInfo.jsp");
		rd.forward(request, response);
	}

}
