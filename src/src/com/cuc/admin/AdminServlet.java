package com.cuc.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.IAdminDAO;
import com.cuc.dao.imp.AdminDAO;
import com.cuc.model.Admin;
import com.cuc.util.FileUpload;

public class AdminServlet extends HttpServlet {
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
		if (!method.equals("login")) {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			if (admin == null) {
				String path = request.getContextPath();// 取项目名
				response.sendRedirect(path + "/admin/login.jsp");
				return;
			}
		}
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("insert")) {
			insert(request, response);
		} else if (method.equals("search")) {
			search(request, response);
		} else if (method.equals("change")) {
			change(request, response);
		} else if (method.equals("preupdate")) {
			preupdate(request, response);
		} else if (method.equals("update")) {
			update(request, response);
		} else if (method.equals("delete")) {
			delete(request, response);
		} else if (method.equals("logout")) {
			logout(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rand = (String) request.getSession().getAttribute("rand");
		String code = request.getParameter("code");
		if (!code.equals(rand)) {
			request.setAttribute("message", "验证码不正确！");
			request.getRequestDispatcher("/admin/login.jsp").forward(request,
					response);
		} else {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			IAdminDAO adminDAO = new AdminDAO();
			Admin admin = adminDAO.login(username, password);
			if (admin == null) {
				request.setAttribute("message", "数据库连接异常！");
				request.getRequestDispatcher("/admin/login.jsp").forward(
						request, response);
			} else if (admin.getId() > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				String path = request.getContextPath();// 取项目名
				response.sendRedirect(path + "/admin/welcome.jsp");
			} else {
				request.setAttribute("message", "帐号或密码错误！");
				request.getRequestDispatcher("/admin/login.jsp").forward(
						request, response);
			}
		}
	}

	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文件上传

		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/uploadFiles");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:
			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据
			Admin admin = new Admin();
			admin.setUsername(fu.getParameter("username"));
			admin.setPassword(fu.getParameter("password"));
			admin.setName(fu.getParameter("name"));
			admin.setPhone(fu.getParameter("phone"));
			admin.setIdcard(fu.getParameter("idcard"));
			String str[] = fu.getParameters("tt");
			admin.setPhoto(fileArr[0]);
			IAdminDAO adminDAO = new AdminDAO();
			if (adminDAO.insert(admin)) {
				request.setAttribute("message", "帐户添加成功！");
			} else {
				//删除照片
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				request.setAttribute("message", "操作失败！");
			}
			break;
		case 1:
			request.setAttribute("message", "request对象不存在！");
			break;
		case 2:
			request.setAttribute("message", "没有设置保存路径！");
			break;
		case 3:
			request
					.setAttribute("message",
							"表单没设置enctype=multipart/form-data！");
			break;
		case 4:
			request.setAttribute("message", "上传操作失败！");
			break;
		}
		request.getRequestDispatcher("/admin/result.jsp").forward(request,
				response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/uploadFiles");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:
			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据
			Admin admin = new Admin();
			admin.setId(Integer.parseInt(fu.getParameter("id")));
			admin.setUsername(fu.getParameter("username"));
			admin.setPassword(fu.getParameter("password"));
			admin.setName(fu.getParameter("name"));
			admin.setPhone(fu.getParameter("phone"));
			admin.setIdcard(fu.getParameter("idcard"));
			String str[] = fu.getParameters("tt");
			String oldphoto=fu.getParameter("oldphoto");
			try {
				admin.setPhoto(fileArr[0]);
				//删除原照片
				File f = new File(realPath + "\\" + oldphoto);
				f.delete();
			} catch (ArrayIndexOutOfBoundsException e) {
				admin.setPhoto(oldphoto);
			}
			IAdminDAO adminDAO = new AdminDAO();
			if (adminDAO.update(admin)) {
				request.setAttribute("message", "帐户修改成功！");
			} else {
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				request.setAttribute("message", "操作失败！");
			}
			break;
		case 1:
			request.setAttribute("message", "request对象不存在！");
			break;
		case 2:
			request.setAttribute("message", "没有设置保存路径！");
			break;
		case 3:
			request
					.setAttribute("message",
							"表单没设置enctype=multipart/form-data！");
			break;
		case 4:
			request.setAttribute("message", "上传操作失败！");
			break;
		}
		request.getRequestDispatcher("/admin/result.jsp").forward(request,
				response);
	}

	public void change(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String newpassword = request.getParameter("newpassword");

		IAdminDAO adminDAO = new AdminDAO();
		if (adminDAO.change(id, newpassword)) {
			HttpSession session = request.getSession();
			Admin admin=(Admin)session.getAttribute("admin");
			admin.setPassword(newpassword);
			request.setAttribute("message", "密码修改成功！");
		} else {
			request.setAttribute("message", "操作失败！");
		}
		request.getRequestDispatcher("/admin/result.jsp").forward(request,
				response);
	}

	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IAdminDAO adminDAO = new AdminDAO();
		String username = request.getParameter("username");
		request.setAttribute("adminList", adminDAO.search(username));
		request.setAttribute("condition", username);// 存放查询条件
		request.getRequestDispatcher("/admin/displayaccount.jsp").forward(
				request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");// 取得查询条件
		IAdminDAO adminDAO = new AdminDAO();
		if(adminDAO.deleteById(id)){
			// 删除照片
			String realPath = this.getServletContext().getRealPath("/uploadFiles");
			String photo = adminDAO.getById(id).getPhoto();
			File file = new File(realPath + "\\" + photo);
			file.delete();
		}
		request.setAttribute("username", username);// 存放查询条件
		search(request, response);
	}

	public void preupdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		IAdminDAO adminDAO = new AdminDAO();
		request.setAttribute("admin", adminDAO.getById(id));
		request.getRequestDispatcher("/admin/editaccount.jsp").forward(request,
				response);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		String path = request.getContextPath();// 取项目名
		response.sendRedirect(path + "/admin/login.jsp");
	}
}
