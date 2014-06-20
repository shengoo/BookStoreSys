package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IBookDAO;
import com.cuc.model.Book;
import com.cuc.util.DBUtil;
import com.cuc.util.StringUtil;

public class BookDAO implements IBookDAO {
	
	public int getRsCount(Book book) {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		int rsCount=0;
		try {
			String sql = "select count(*) as rscount from bookinfo";
			if (!("all".equals(book.getBookkind())))
				sql += " where bookkind='" + book.getBookkind() + "'";
			else
				sql += " where bookkind like '%%'";
			if (!(StringUtil.validateNull(book.getIsbn())))
				sql += " and isbn like '%" + book.getIsbn() + "%'";
			if (!(StringUtil.validateNull(book.getBookname())))
				sql += " and bookname like '%" + book.getBookname() + "%'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				rsCount=rs.getInt("rscount");
			}
			pstmt.close();
		} catch (SQLException e) {
			return 0;
		}finally {
			db.close(con);
		}
		return rsCount;
	}

	public ArrayList<Book> search(Book book, int pageSize, int currentPage) {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		
		ArrayList<Book> list = new ArrayList<Book>();

		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		
		try {
			//此处代码为普通数据库的分页操作
			/*
			String sql = "select * from(select b.*,rownum r from bookinfo b";
			if (!("all".equals(book.getBookkind())))
				sql += " where bookkind='" + book.getBookkind() + "'";
			else
				sql += " where bookkind like '%%'";
			if (!(StringUtil.validateNull(book.getIsbn())))
				sql += " and isbn like '%" + book.getIsbn() + "%'";
			if (!(StringUtil.validateNull(book.getBookname())))
				sql += " and bookname like '%" + book.getBookname() + "%'";
			
			sql+=" and rownum <="+end+" order by r)t where t.r>="+start;
			*/
			//此处代码为MySql数据库的分页操作
			String sql = "select * from bookinfo";
			if (!("all".equals(book.getBookkind())))
				sql += " where bookkind='" + book.getBookkind() + "'";
			else
				sql += " where bookkind like '%%'";
			if (!(StringUtil.validateNull(book.getIsbn())))
				sql += " and isbn like '%" + book.getIsbn() + "%'";
			if (!(StringUtil.validateNull(book.getBookname())))
				sql += " and bookname like '%" + book.getBookname() + "%'";
			
			sql+=" limit "+(start-1)+","+pageSize;
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book1 = new Book();
				book1.setBookkind(rs.getString("bookkind"));
				book1.setBookname(rs.getString("bookname"));
				book1.setIsbn(rs.getString("isbn"));
				book1.setNumber(rs.getInt("number"));
				book1.setPrice(rs.getFloat("price"));
				book1.setPublisher(rs.getString("publisher"));
				list.add(book1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			db.close(con);
		}
		return list;
	}

	public boolean updateByID(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deltet(int ID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insert(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Book book) {
		// TODO Auto-generated method stub
		return false;
	}
}
