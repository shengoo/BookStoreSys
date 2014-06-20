package com.cuc.webuser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IBookDAO;
import com.cuc.dao.imp.BookDAO;
import com.cuc.model.Book;
import com.cuc.util.PageUtil;

public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = new Book();
		book.setBookkind(request.getParameter("bookkind"));
		book.setBookname(request.getParameter("bookname"));
		book.setIsbn(request.getParameter("isbn"));
		IBookDAO bookDAO = new BookDAO();
		
		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(3); // 设置分页大小
		int pageSize = pageUtil.getPageSize();
		int rsCount = bookDAO.getRsCount(book);// 获得记录总数

		pageUtil.setRsCount(rsCount);

		int pageCount = pageUtil.getPageCount();// 计算页数
		int currentPage = pageUtil.getCurrentPage();// 获得当前页
		String pageTool = pageUtil.createPageTool(pageUtil.BbsText);// 创建分页工具条

		request.setAttribute("pageTool", pageTool);

		ArrayList<Book> list=bookDAO.search(book,pageSize,currentPage);
		request.setAttribute("booklist", list);
		request.getRequestDispatcher("/displaybook.jsp").forward(request,
				response);
	}
}
