package com.neuedu.model.dao;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.User;
//��Ƴɽ�ں�ʵ����ķ�ʽ
public interface UserDAO {
	
	//ע��
	public void register(User user) throws SQLException;
		/*public void register(User user, Connection conn) throws SQLException {
			PreparedStatement ps=null;
			try {
				ps = conn.prepareStatement("insert into userinfo values(1,?,?,?,?,?)");
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getAge());
				ps.setString(4, user.getPhonenumber());
				ps.setString(5, user.getEmail());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ps.close();
		     }
		}*/
	//��ѯ�û�
	public List<User> selectUser(String username,int age);
	
	//��ҳ��ѯ
	public List<User> selectUser(String username, int age, int pageSize, int pageNum);
	//��ѯ��ҳ��
	public int selectPageCount(String username, int ageNew, int pageSize);
	//����ɾ��
	public void deleteUser(int[] ids) throws SQLException;
	
	public User getUserById(int userId) throws SQLException;
	public void updateUser(User u);
	public User validateUsername(String username);
}
