package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.cuc.cart.CartItem;
import com.cuc.dao.IOrderDAO;
import com.cuc.model.Order;
import com.cuc.util.DBUtil;
import com.cuc.util.StringUtil;

public class OrderDAO implements IOrderDAO {
	
	public int insert(Order order) {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		
		int orderid = 0;
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt1 = con
					.prepareStatement("insert into orders(webuserid,name,email,phone,address,saletime,status) values(?,?,?,?,?,?,?)");
			pstmt1.setInt(1, order.getWebuserid());
			pstmt1.setString(2, order.getName());
			pstmt1.setString(3, order.getEmail());
			pstmt1.setString(4, order.getPhone());
			pstmt1.setString(5, order.getAddress());
			pstmt1.setDate(6, order.getSaletime());
			pstmt1.setString(7, order.getStatus());
			pstmt1.executeUpdate();

			PreparedStatement pstmt2 = con
					.prepareStatement("select orderid from orders where webuserid=? order by orderid desc");
			pstmt2.setInt(1, order.getWebuserid());
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				orderid = rs.getInt("orderid");
			}

			PreparedStatement pstmt3 = con
					.prepareStatement("insert into orderinfo(orderid,isbn,number,saleprice) values(?,?,?,?)");
			ArrayList<CartItem> itemList = order.getItemList();
			for (int i = 0; i < itemList.size(); i++) {
				CartItem item = itemList.get(i);
				pstmt3.setInt(1, orderid);
				pstmt3.setString(2, item.getGoodId());
				pstmt3.setInt(3, item.getNumber());
				pstmt3.setFloat(4, item.getPrice());
				pstmt3.executeUpdate();
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			return 0;
		}finally {
			db.close(con);
		}
		return orderid;
	}

	public ArrayList<Order> searchAll() {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("select * from orders order by saletime desc");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderid(rs.getInt("orderid"));
				order.setAddress(rs.getString("address"));
				order.setEmail(rs.getString("email"));
				order.setName(rs.getString("name"));
				order.setPhone(rs.getString("phone"));
				order.setSaletime(rs.getDate("saletime"));
				order.setStatus(rs.getString("status"));
				order.setWebuserid(rs.getInt("webuserid"));
				list.add(order);
			}
		} catch (SQLException e) {
			return null;
		}finally {
			db.close(con);
		}
		return list;
	}

	public ArrayList searchOrderInfo(int orderid) {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		
		ArrayList list=new ArrayList();
		ArrayList<String[]> orderinfo=new ArrayList<String[]>();
		int totalNum=0;
		float totalPrice=0;
		try {
			PreparedStatement pstmt = con
					.prepareStatement("select bookinfo.isbn,bookname,orderinfo.number,saleprice from bookinfo,orderinfo where bookinfo.isbn=orderinfo.isbn and orderid=?");
			pstmt.setInt(1, orderid);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				String s[]=new String[4];
				s[0]=rs.getString("bookinfo.isbn");
				s[1]=rs.getString("bookname");
				s[2]=rs.getString("orderinfo.number");
				s[3]=rs.getString("saleprice");
				orderinfo.add(s);
			}
			pstmt = con.prepareStatement("select sum(number) as totalnum,sum(saleprice) as totalprice from orderinfo where orderid=?");
			pstmt.setInt(1, orderid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				totalNum=rs.getInt("totalnum");
				totalPrice=rs.getFloat("totalprice");
			}
			list.add(orderinfo);
			list.add(totalNum);
			list.add(totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			db.close(con);
		}
		return list;
	}
	
	public int getOrderCount(int userid) {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		int rsCount=0;
		try {
			String sql = "select count(*) as rscount from orders WHERE webuserid =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
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
	
	public int getOrderCount() {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		int rsCount=0;
		try {
			String sql = "select count(*) as rscount from orderinfo";
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
	
	public ArrayList<Order> GetUserOrders(int userid,int skip,int take,Date startDate,Date endDate) {
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			String sql = "SELECT * FROM `orders` WHERE webuserid=? limit ?,?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
			pstmt.setInt(2, skip);
			pstmt.setInt(3, take);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderid(rs.getInt("orderid"));
				order.setAddress(rs.getString("address"));
				order.setEmail(rs.getString("email"));
				order.setName(rs.getString("name"));
				order.setPhone(rs.getString("phone"));
				order.setSaletime(rs.getDate("saletime"));
				order.setStatus(rs.getString("status"));
				order.setWebuserid(rs.getInt("webuserid"));
				list.add(order);
			}
			pstmt.close();
		} catch (SQLException e) {
			return null;
		}finally {
			db.close(con);
		}
		return list;
	}
	
	public ArrayList<Order> GetAllOrders(int skip,int take,Date startDate,Date endDate){
		DBUtil db=new DBUtil();
		Connection con=new DBUtil().getConnection();
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			String sql = "SELECT * FROM `orders` limit ?,?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, skip);
			pstmt.setInt(2, take);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderid(rs.getInt("orderid"));
				order.setAddress(rs.getString("address"));
				order.setEmail(rs.getString("email"));
				order.setName(rs.getString("name"));
				order.setPhone(rs.getString("phone"));
				order.setSaletime(rs.getDate("saletime"));
				order.setStatus(rs.getString("status"));
				order.setWebuserid(rs.getInt("webuserid"));
				list.add(order);
			}
			pstmt.close();
		} catch (SQLException e) {
			return null;
		}finally {
			db.close(con);
		}
		return list;
	}

}
