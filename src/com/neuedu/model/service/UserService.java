package com.neuedu.model.service;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.UserDAO;
import com.neuedu.model.dao.UserDAOImp;
import com.neuedu.model.po.User;
import com.neuedu.utils.DBtil;
//���ݿ�����Ӻ��ύ
public class UserService {
		
	//����ģʽ
		private UserService(){
			
		}
		
		private static UserService service = new UserService();
		
		public static UserService getInstance(){
			return service;
		}
		/*
		 * Service
		 * ���ӵ�ҵ���߼��Ĵ���
		 * ������
		 */
		//ע��
		@SuppressWarnings("static-access")
		public void register(User user) throws ClassNotFoundException,SQLException{
			
			DBtil dbtil = new DBtil();
			//��ȡ�����ݿ������
			Connection conn=dbtil.getConn();

			//��������
			dbtil.biginTransaction(conn);
			try {
				UserDAO dao = new UserDAOImp(conn);
				dao.register(user);
				
				//�ύ
				dbtil.summitTransaction(conn);
			} catch (Exception e) {
				// �ع�
				dbtil.rollback(conn);
			}finally{
				dbtil.close(conn);
			}
		}
		
		//��ϲ�ѯ
		public List<User> selectUser(String username,int age) throws ClassNotFoundException, SQLException{
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.selectUser(username, age);
		}
		
		//��ҳ��ѯ
		public List<User> selectPageUser(String username, int age,int pageSize, int pageNum) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.selectUser(username, age,pageSize,pageNum);
		}
		
		//��ѯҳ��
		public int selectPageCount(String username, int ageNew, int pageSize) throws ClassNotFoundException, SQLException{
			Connection conn = DBtil.getConn();
			UserDAO dao = new UserDAOImp(conn);
			return dao.selectPageCount(username, ageNew,pageSize);
		}
		
		//����ɾ��
		@SuppressWarnings("static-access")
		public void deleteUser(int[] ids) throws SQLException, ClassNotFoundException{
			
			DBtil dbtil = new DBtil();
			//��ȡ�����ݿ������
			Connection conn=dbtil.getConn();

			//��������
			dbtil.biginTransaction(conn);
			try {
				UserDAO dao = new UserDAOImp(conn);
				dao.deleteUser(ids);;
				
				//�ύ
				dbtil.summitTransaction(conn);
			} catch (Exception e) {
				// �ع�
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
			//��ȡ�����ݿ������
			Connection conn=dbtil.getConn();

			//��������
			DBtil.biginTransaction(conn);
			try {
				UserDAO dao = new UserDAOImp(conn);
				dao.updateUser(u);
				
				//�ύ
				dbtil.summitTransaction(conn);
			} catch (Exception e) {
				// �ع�
				dbtil.rollback(conn);
			}finally{
				dbtil.close(conn);
			}
		}
		
		//�ж��û����Ƿ��ظ�
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
