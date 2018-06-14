package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		System.out.println("进入servlet");
		//设定编码格式
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if("validate".equals(action)){
			String username = request.getParameter("username");
			try {
				boolean flag = UserService.getInstance().validateUsername(username);
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.print(flag);
				pw.close();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//获取表单参数
			/*String username = request.getParameter("username");
			String password = request.getParameter("password");
			String age = request.getParameter("age");
			String phonenumber = request.getParameter("phonenumber");
			String email = request.getParameter("email");*/
			String username = "";
			String password = "";
			String age = "";
			String phonenumber = "";
			String email = "";
			String newName="";
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//构建upload
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(1024*1024*2);//以字节为单位
				
				try {
					List<FileItem> fileItems= upload.parseRequest(request);
					Iterator<FileItem> iter=fileItems.iterator();
					while(iter.hasNext()){
						FileItem item=iter.next();
						if(item.isFormField()){
							//普通的表单元素
							//表单元素的名称
							String name = item.getFieldName();
							//表单元素的值
							String value = new String( item.getString().getBytes("iso8859-1"),"UTF-8");
							if("username".equals(name)){
								username = value;
							}
							if("password".equals(name)){
								password = value;
							}
							if("age".equals(name)){
								age = value;
							}
							if("phonenumber".equals(name)){
								phonenumber = value;
							}
							if("email".equals(name)){
								email = value;
							}
						}else{
							//要上传的文件
							String filename = item.getName();
							//重命名
							newName = new Date().getTime()+filename.substring(filename.indexOf('.'));
							
							File file = new File("d:/photo/",newName);
							try {
								//上传文件
								item.write(file);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//校验
			
			
			//封装数据
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setAge(Integer.parseInt(age));
			user.setPhonenumber(phonenumber);
			user.setEmail(email);
			
			//调用模型层
			try {
				try {
					UserService.getInstance().register(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * 响应
			 * 页面跳转的方式
			 * 请求转发:URL不发生变化
			 * 	路径可以以/开头，也可以不带/，都是相对于当前路径进行跳转
			 * 	不支持站外跳转
			 * 过程中只有一个request
			 * 
			 * 重定向:URL发生变化
			 * 	路径不能以/开头，否则相对于tomcat服务器进行跳转，建议写成绝对路径的形式
			 * 	支持站外跳转
			 * 	产生两个request对象
			 * 	数据共享的方式
			 */
			
			//请求转发
			//request.getRequestDispatcher("selectUser.jsp").forward(request, response);
			
			//重定向
			response.sendRedirect(request.getContextPath()+"/selectUser.jsp");
		}
	}
		
}
