package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class UserManageServlet
 */
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String username="";
		String age="";
		String pageSize="";
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		if(pagenum != null && !"".equals(pagenum)){
			//点击页码查询
			pageNum=Integer.parseInt(pagenum);
			username = (String) request.getSession().getAttribute("username");
			age = (String) request.getSession().getAttribute("age");
			pageSize = (String)request.getSession().getAttribute("pageSize");
		}else{
			//点击按钮查询
			username = request.getParameter("username");
			age = request.getParameter("age");
			pageSize = request.getParameter("pageSize");
		}
		
		int ageNew=0;
		if(age != null && !"".equals(age)){
			ageNew = Integer.parseInt(age);
		}
		
		//查询
		try {
			//List<User> list= UserService.getInstance().selectUser(username, ageNew);
			//查询指定页数
			List<User> list = UserService.getInstance().selectPageUser(username, ageNew, Integer.parseInt(pageSize),pageNum);
			//查询页数
			int pagecount =  UserService.getInstance().selectPageCount(username, ageNew,Integer.parseInt(pageSize));
			request.setAttribute("resultList", list);
			request.setAttribute("pagecount", pagecount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("age", age);
			request.getSession().setAttribute("pageSize", pageSize);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 数据共享
		 * page
		 * request：请求作用域
		 * session：当前请求对应的绘画对象
		 * applicationContext：应用
		 * 原则：尽可能选择范围小的作用域、避免并非操作、多窗口同时操作时产生数据混乱的问题
		 */
		
		//跳转
		request.getRequestDispatcher("selectUser.jsp").forward(request, response);
		
	}

}
