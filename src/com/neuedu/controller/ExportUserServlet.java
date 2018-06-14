package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Servlet implementation class ExportUserServlet
 */
public class ExportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportUserServlet() {
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
		// �趨��Ӧ������
		response.setHeader("Content-disposition", "attachment;filename=userinfo.xls");
		response.setHeader("pragma", "no-cache");
		//�趨�������Ϊexcel
		response.setContentType("application/mxexcel");
		//��ȡ�����
		ServletOutputStream os = response.getOutputStream();
		
		//����Excel
		//����������
		WritableWorkbook workbook=Workbook.createWorkbook(os);
		//����sheet
		WritableSheet sheet = workbook.createSheet("userdata", 0);
		
		//���ɱ��ⵥԪ��
		//�趨��ʽ
		WritableFont font1 = new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD);
		//��ʽǶ�뵽��Ԫ����ʽ��
		WritableCellFormat format1 = new WritableCellFormat(font1);
		//������Ԫ��
		Label cell = new Label(0,0,"userdata",format1);
		try {
			sheet.addCell(cell);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�趨������ʽ
		WritableFont font2 = new WritableFont(WritableFont.TIMES,12,WritableFont.NO_BOLD);
		//��ʽǶ�뵽��Ԫ����ʽ��
		WritableCellFormat format2 = new WritableCellFormat(font2);
		try {
			sheet.addCell(new Label(0,1,"username",format2));
			sheet.addCell(new Label(1,1,"age",format2));
			sheet.addCell(new Label(2,1,"phonenumber",format2));
			sheet.addCell(new Label(4,1,"email",format2));
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ȡ��ѯ��������ѯ������������������
		String username = (String) request.getSession().getAttribute("username");
		String age = (String) request.getSession().getAttribute("age");
		int ageNew=0;
		if(age != null && !"".equals(age)){
			ageNew = Integer.parseInt(age);
		}
		try {
			List<User> list = UserService.getInstance().selectUser(username, ageNew);
			int index=2;
			if(list != null && list.size()>0){
				for(User u:list){
					sheet.addCell(new Label(0,index,u.getUsername(),format2));
					sheet.addCell(new Label(1,index,String.valueOf(u.getAge()),format2));
					sheet.addCell(new Label(2,index,u.getPhonenumber(),format2));
					sheet.addCell(new Label(3,index,u.getEmail(),format2));
					index++;
				}
			}
			workbook.write();
			workbook.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
