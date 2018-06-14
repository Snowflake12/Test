package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
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
		String action = request.getParameter("action");
		if("edit".equals(action)){
			try {
				doEditUser(request,response);
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
		}else if("update".equals(action)){
			doUpdateUSer(request,response);
		}
	}

	private void doUpdateUSer(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("update");
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		String phonenumber = request.getParameter("phonenumber");
		String email = request.getParameter("email");
		User u = new User();
		u.setId(Integer.parseInt(id));
		u.setUsername(username);
		u.setAge(Integer.parseInt(age));
		u.setPhonenumber(phonenumber);
		u.setEmail(email);
		try {
			UserService.getInstance().updateUser(u);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			response.sendRedirect(request.getContextPath()+"/UserManageServlet?pageNum="+pageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doEditUser(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		User u = UserService.getInstance().getUserById(Integer.parseInt(userId));
		request.setAttribute("editUser", u);
		try {
			request.getRequestDispatcher("/editUser.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
