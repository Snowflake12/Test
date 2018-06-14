package com.neuedu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBtil {
	
	//获取数据库连接
	public static Connection getConn() throws ClassNotFoundException,SQLException{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//开启事务
	public static void biginTransaction(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
	}
	
	//提交事务
	public static void summitTransaction(Connection conn) throws SQLException{
		conn.commit();
	}
	
	//回滚
	public static void rollback(Connection conn) throws SQLException{
		conn.rollback();
	}
	
	//关闭连接
	public static void close(Connection conn) throws SQLException {
		conn.close();
	}
	
	//关闭PS
	
}
