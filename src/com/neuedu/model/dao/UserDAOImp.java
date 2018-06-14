package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.neuedu.model.po.User;


public class UserDAOImp implements UserDAO{
	
	Connection conn;
	
	public UserDAOImp(Connection conn){
		this.conn=conn;
	}
	@Override
	public void register(User user) throws SQLException {
		
		PreparedStatement ps=null;
		
		//插入用户数据
		try {
			ps = conn.prepareStatement("insert into userinfo values(seq_userinfo.nextval,?,?,?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getAge());
			ps.setString(4, user.getPhonenumber());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhotourl());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ps.close();
	     }
	}
	@Override
	//用list保存查询到的数据
	public List<User> selectUser(String username, int age) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from userinfo where 1=1");
		if(username !=null && !"".equals(username)){
			sbf.append("and username like? ");
		}
		if(age != 0){
			sbf.append("and age =? ");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(username !=null && !"".equals(username)){
				ps.setString(index, "%"+username+"%");
				index++;
			}
			if(age != 0){
				ps.setInt(index, age);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setAge(rs.getInt("age"));
				u.setPhonenumber(rs.getString("phonenumber"));
				u.setEmail(rs.getString("email"));
				list.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	//分页查询
	public List<User> selectUser(String username, int age, int pageSize, int pageNum){
		List<User> list = new ArrayList<User>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select *  from  userinfo where 1=1  ");
		if(username != null && !"".equals(username)){
			sbf.append(" and username like ? ");
		}
		if(age != 0){
			sbf.append(" and age=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement( " select b.* from ( "
					+ " select a.*,rownum rw from ( "
					+ sbf.toString() +  "  ) a "
					+ " where rownum<= "+ (pageSize*pageNum) +" ) b  "
					+ " where rw>"+ pageSize*(pageNum-1));
			int index=1;
			if(username != null && !"".equals(username)){
				ps.setString(index, "%"+username+"%");
				index++;
			}
			if(age != 0){
				ps.setInt(index, age);
			}
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setAge(rs.getInt("age"));
				u.setPhonenumber(rs.getString("phonenumber"));
				u.setEmail(rs.getString("email"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询总页数
	public int selectPageCount(String username, int age, int pageSize) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  userinfo where 1=1  ");
		if(username != null && !"".equals(username)){
			sbf.append(" and username like ? ");
		}
		if(age != 0){
			sbf.append(" and age=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(username != null && !"".equals(username)){
				ps.setString(index, username);
				index++;
			}
			if(age != 0){
				ps.setInt(index, age);
			}
			//执行
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int pagecount = 0;
		if(count%pageSize==0){
			pagecount = count/pageSize;
		}else{
			pagecount = count/pageSize+1;
		}
		return pagecount;
	}
	
	//删除用户
	public void deleteUser(int[] ids) throws SQLException{
		String id = Arrays.toString(ids).replace('[', '(').replace(']', ')');
		PreparedStatement ps=null;
		try {
			ps = conn.prepareStatement(" delete from userinfo "
					+" where id in "+ id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	//修改用户
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User u = new User();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("  select *  from userinfo  where id=? ");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setAge(rs.getInt("age"));
				u.setPhonenumber(rs.getString("phonenumber"));
				u.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		PreparedStatement ps =null;
		try {
			ps = conn.prepareStatement(" update userinfo set username=?,age=?,phonenumber=?,email=?"
					+ " where id=?  ");
			ps.setString(1, u.getUsername());
			ps.setInt(2, u.getAge());
			ps.setString(3, u.getPhonenumber());
			ps.setString(4, u.getEmail());
			ps.setInt(5, u.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public User validateUsername(String username) {
		User u = null;
		PreparedStatement ps=null;
		try {
			ps = conn.prepareStatement("  select *  from userinfo  where username=? ");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				u=new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setAge(rs.getInt("age"));
				u.setPhonenumber(rs.getString("phonenumber"));
				u.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
}
