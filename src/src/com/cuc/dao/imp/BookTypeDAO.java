package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cuc.dao.IBookTypeDAO;
import com.cuc.model.BookType;
import com.cuc.util.DBUtil;

public class BookTypeDAO implements IBookTypeDAO{
	
	public ArrayList<BookType> searchAll() {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		
		ArrayList<BookType> list=new ArrayList<BookType>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from booktype");
			while (rs.next()) {
				BookType booktype = new BookType();
				booktype.setBookkind(rs.getString("bookkind"));
				booktype.setKindname(rs.getString("kindname"));
				list.add(booktype);
			}
			stmt.close();
		} catch (SQLException e) {
			return null;
		} finally {
			db.close(con);
		}
		return list;
	}

	public boolean deltet(int ID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insert(BookType bookType) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<BookType> search(BookType bookType) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(BookType bookType) {
		// TODO Auto-generated method stub
		return false;
	}
}
