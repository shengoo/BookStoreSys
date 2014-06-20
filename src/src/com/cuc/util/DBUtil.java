package com.cuc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	public Connection getConnection(){
		Connection con=null;
		try {
			//数据源对象可以理解为连接池的管理者，通过他可以获取数据库的连接
			//DataSource ds = null;
			//通过在WebRoot/META_INF目录下的context.xml文件，设定的数据源对象的名字，获取数据源对象
			//同时需要将数据库驱动程序放置在Tomcat 5.5\common\lib目录下
			//Context context = new InitialContext();
			//ds = (DataSource) context.lookup("java:/comp/env/jdbc/BookStoreSysDS");
			//con=ds.getConnection();

			
			//MySQL数据库传统连接方式
			Class.forName("org.gjt.mm.mysql.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/bookstore?user=root&password=root&useUnicode=true&characterEncoding=utf-8");
		
			//Access数据库传统连接方式
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=d:\\bookshopping.mdb", "", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		//} catch (NamingException e) {
			//e.printStackTrace();
		}
		return con;
	}
	public void close(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
