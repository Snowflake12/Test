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
		System.out.println("����servlet");
		//�趨�����ʽ
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
			//��ȡ������
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
				//����upload
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(1024*1024*2);//���ֽ�Ϊ��λ
				
				try {
					List<FileItem> fileItems= upload.parseRequest(request);
					Iterator<FileItem> iter=fileItems.iterator();
					while(iter.hasNext()){
						FileItem item=iter.next();
						if(item.isFormField()){
							//��ͨ�ı�Ԫ��
							//��Ԫ�ص�����
							String name = item.getFieldName();
							//��Ԫ�ص�ֵ
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
							//Ҫ�ϴ����ļ�
							String filename = item.getName();
							//������
							newName = new Date().getTime()+filename.substring(filename.indexOf('.'));
							
							File file = new File("d:/photo/",newName);
							try {
								//�ϴ��ļ�
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
			//У��
			
			
			//��װ����
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setAge(Integer.parseInt(age));
			user.setPhonenumber(phonenumber);
			user.setEmail(email);
			
			//����ģ�Ͳ�
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
			 * ��Ӧ
			 * ҳ����ת�ķ�ʽ
			 * ����ת��:URL�������仯
			 * 	·��������/��ͷ��Ҳ���Բ���/����������ڵ�ǰ·��������ת
			 * 	��֧��վ����ת
			 * ������ֻ��һ��request
			 * 
			 * �ض���:URL�����仯
			 * 	·��������/��ͷ�����������tomcat������������ת������д�ɾ���·������ʽ
			 * 	֧��վ����ת
			 * 	��������request����
			 * 	���ݹ���ķ�ʽ
			 */
			
			//����ת��
			//request.getRequestDispatcher("selectUser.jsp").forward(request, response);
			
			//�ض���
			response.sendRedirect(request.getContextPath()+"/selectUser.jsp");
		}
	}
		
}
