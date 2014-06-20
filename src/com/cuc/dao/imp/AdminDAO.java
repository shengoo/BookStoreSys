package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IAdminDAO;
import com.cuc.model.Admin;
import com.cuc.util.DBUtil;

public class AdminDAO implements IAdminDAO {

	public boolean deleteById(int id) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("delete from login where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

	public boolean insert(Admin admin) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("insert into login(username,password,name,phone,idcard,photo) values(?,?,?,?,?,?)");
			pstmt.setString(1, admin.getUsername());
			pstmt.setString(2, admin.getPassword());
			pstmt.setString(3, admin.getName());
			pstmt.setString(4, admin.getPhone());
			pstmt.setString(5, admin.getIdcard());
			pstmt.setString(6, admin.getPhoto());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

	public ArrayList<Admin> search(String username) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();

		ArrayList<Admin> search = new ArrayList<Admin>();
		try {
			String sql = "select * from login where username like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + username + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
				admin.setPhone(rs.getString("phone"));
				admin.setIdcard(rs.getString("idcard"));
				admin.setPhoto(rs.getString("photo"));
				search.add(admin);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			db.close(con);
		}
		return search;
	}

	public boolean update(Admin admin) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("update login set username=?,password=?,phone=?,idcard=?,name=?,photo=? where id=?");
			pstmt.setString(1, admin.getUsername());
			pstmt.setString(2, admin.getPassword());
			pstmt.setString(3, admin.getPhone());
			pstmt.setString(4, admin.getIdcard());
			pstmt.setString(5, admin.getName());
			pstmt.setString(6, admin.getPhoto());
			pstmt.setInt(7, admin.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

	public Admin getById(int id) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();
		Admin admin = new Admin();
		try {
			String sql = "select * from login where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
				admin.setPhone(rs.getString("phone"));
				admin.setIdcard(rs.getString("idcard"));
				admin.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			return null;
		} finally {
			db.close(con);
		}
		return admin;
	}

	public Admin login(String username, String password) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();

		Admin admin = new Admin();
		try {
			String sql = "select * from login where username=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
				admin.setPhone(rs.getString("phone"));
				admin.setIdcard(rs.getString("idcard"));
				admin.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			return null;
		} finally {
			db.close(con);
		}
		return admin;
	}

	public boolean change(int id, String password) {
		DBUtil db = new DBUtil();
		Connection con = new DBUtil().getConnection();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("update login set password=? where id=?");
			pstmt.setString(1, password);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			db.close(con);
		}
		return true;
	}

}
