package com.cuc.webuser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.IWebUserDAO;
import com.cuc.dao.imp.WebUserDAO;
import com.cuc.model.WebUser;

public class WebUserServlet extends HttpServlet {
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
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("sameuser")) {
			sameuser(request, response);
		} else if (method.equals("insert")) {
			insert(request, response);
		} else if (method.equals("logout")) {
			logout(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String webuser=request.getParameter("webuser");
		String password=request.getParameter("password");

		IWebUserDAO webUserDAO = new WebUserDAO();
		WebUser webUser = webUserDAO.login(webuser, password);
		if (webUser==null) {
			response.getWriter().println("���ݿ�����ʧ��!");
		}else if(webUser.getWebuserid()>0){
			HttpSession session = request.getSession();
			session.setAttribute("webUser", webUser);
			response.getWriter().println("��ӭ" + webUser.getWebuser() + "����!");
		} else {
			response.getWriter().println("�ʺŻ��������!");
		}
	}

	public void sameuser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String webuser=request.getParameter("webuser");
		
		IWebUserDAO webUserDAO = new WebUserDAO();
		if (webUserDAO.sameuser(webuser)) {
			response.getWriter().println("<font color=red>�ʺ��Ѵ���</font>");
		}
	}

	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebUser webUser = new WebUser();
		webUser.setWebuser(request.getParameter("webuser"));
		webUser.setPassword(request.getParameter("password"));

		IWebUserDAO webUserDAO = new WebUserDAO();
		if (webUserDAO.insert(webUser)) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('��ӳɹ�');javascript: history.back(-1);</SCRIPT>");
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('����ʧ��');javascript: history.back(-1);</SCRIPT>");
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		String path = request.getContextPath();// ȡ��Ŀ��
		response.sendRedirect(path + "/index.jsp");
	}
}
