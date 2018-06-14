package com.neuedu.model.service;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.UserDAO;
import com.neuedu.model.dao.UserDAOImp;
import com.neuedu.model.po.User;
import com.neuedu.utils.DBtil;
//数据库的连接和提交
public class UserService {
		
	//单例模式
		private UserService(){
			
		}
		
		private static UserService service = new UserService();
		
		public static UserService getInstance(){
			return service;
		}
		/*
		 * Service
		 * 复杂的业务逻辑的处理
		 * 事务处理
		 */
		//注册
		@SuppressWarnings("static-access")
		public void register(User user) throws ClassNotFoundException,SQLException{
			
			DBtil dbtil = new DBtil();
			//获取与数据库的连接
			Connection conn=dbtil.getConn();

			//开启事务
			dbtil.biginTransaction(conn);
			try {
				UserDAO dao = new UserDAOImp(conn);
				dao.register(user);
				
				//提交
				dbtil.summitTransaction(conn);
			} catch (Exception e) {
				// 回滚
				dbtil.rollback(conn);
			}finally{
				dbtil.close(conn);
			}
		}
		
		//组合查询
		public List<User> selectUser(String username,int age) throws ClassNotFoundException, SQLException{
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.selectUser(username, age);
		}
		
		//分页查询
		public List<User> selectPageUser(String username, int age,int pageSize, int pageNum) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.selectUser(username, age,pageSize,pageNum);
		}
		
		//查询页数
		public int selectPageCount(String username, int ageNew, int pageSize) throws ClassNotFoundException, SQLException{
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.selectPageCount(username, ageNew,pageSize);
		}
		
		//批量删除
		@SuppressWarnings("static-access")
		public void deleteUser(int[] ids) throws SQLException, ClassNotFoundException{
			
			DBtil dbtil = new DBtil();
			//获取与数据库的连接
			Connection conn=dbtil.getConn();

			//开启事务
			dbtil.biginTransaction(conn);
			try {
				UserDAO dao = new UserDAOImp(conn);
				dao.deleteUser(ids);;
				
				//提交
				dbtil.summitTransaction(conn);
			} catch (Exception e) {
				// 回滚
				dbtil.rollback(conn);
			}finally{
				dbtil.close(conn);
			}
		}
		public User getUserById(int userId) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.getUserById(userId);
		}
		@SuppressWarnings("static-access")
		public void updateUser(User u) throws SQLException, ClassNotFoundException {
			DBtil dbtil = new DBtil();
			//获取与数据库的连接
			Connection conn=dbtil.getConn();

			//开启事务
			DBtil.biginTransaction(conn);
			try {
				UserDAO dao = new UserDAOImp(conn);
				dao.updateUser(u);
				
				//提交
				dbtil.summitTransaction(conn);
			} catch (Exception e) {
				// 回滚
				dbtil.rollback(conn);
			}finally{
				dbtil.close(conn);
			}
		}
		
		//判断用户名是否重复
		public boolean validateUsername(String username) throws ClassNotFoundException, SQLException{
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			User u = dao.validateUsername(username);
			DBtil.close(conn);
			if(u==null){
				return false;
			}else{
				return true;
			}	
		}
		
}
