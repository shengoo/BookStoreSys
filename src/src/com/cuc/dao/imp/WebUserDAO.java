package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IWebUserDAO;
import com.cuc.model.WebUser;
import com.cuc.util.DBUtil;

public class WebUserDAO implements IWebUserDAO {

	public boolean delete(WebUser webUser) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();

		try {
			PreparedStatement pstmt = con
					.prepareStatement("delete from webuser where webuserid=?");
			pstmt.setInt(1, webUser.getWebuserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

	public boolean insert(WebUser webUser) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();

		try {
			PreparedStatement pstmt = con
					.prepareStatement("insert into webuser(webuser,password) values(?,?)");
			pstmt.setString(1, webUser.getWebuser());
			pstmt.setString(2, webUser.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

	public ArrayList<WebUser> search(String webuser) {
		return null;
	}

	public boolean update(WebUser webUser) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();

		try {
			PreparedStatement pstmt = con
					.prepareStatement("update webuser set webuser=?,password=? where webuserid=?");
			pstmt.setString(1, webUser.getWebuser());
			pstmt.setString(2, webUser.getPassword());
			pstmt.setInt(3, webUser.getWebuserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

	public boolean sameuser(String webuser) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();

		try {
			PreparedStatement pstmt = con
					.prepareStatement("select * from webuser where webuser=?");
			pstmt.setString(1, webuser);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			db.close(con);
		}
		return false;
	}

	public WebUser login(String webuser, String password) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();
		
		WebUser webUser=new WebUser();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("select * from webuser where webuser=? and password=?");
			pstmt.setString(1, webuser);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				webUser.setWebuserid(rs.getInt("webuserid"));
				webUser.setWebuser(rs.getString("webuser"));
				webUser.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			return null;
		} finally {
			db.close(con);
		}
		return webUser;
	}
}
