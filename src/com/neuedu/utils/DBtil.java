package com.neuedu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBtil {
	
	//��ȡ���ݿ�����
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
	
	//��������
	public static void biginTransaction(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
	}
	
	//�ύ����
	public static void summitTransaction(Connection conn) throws SQLException{
		conn.commit();
	}
	
	//�ع�
	public static void rollback(Connection conn) throws SQLException{
		conn.rollback();
	}
	
	//�ر�����
	public static void close(Connection conn) throws SQLException {
		conn.close();
	}
	
	//�ر�PS
	
}
