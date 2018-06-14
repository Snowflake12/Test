package com.neuedu.model.dao;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.User;
//设计成借口和实现类的方式
public interface UserDAO {
	
	//注册
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
	//查询用户
	public List<User> selectUser(String username,int age);
	
	//分页查询
	public List<User> selectUser(String username, int age, int pageSize, int pageNum);
	//查询总页数
	public int selectPageCount(String username, int ageNew, int pageSize);
	//批量删除
	public void deleteUser(int[] ids) throws SQLException;
	
	public User getUserById(int userId) throws SQLException;
	public void updateUser(User u);
	public User validateUsername(String username);
}
