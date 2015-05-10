package com.phn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phn.po.Admin;
import com.phn.service.IAdminService;
import com.phn.service.impl.AdminServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-28
 */
public class ManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		System.out.println(action);
		if ("gologin".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/back/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			String adminName = request.getParameter("adminName");
			String adminPass = request.getParameter("adminPass");
			System.out.println(adminName);
			Admin admin = new Admin();
			admin.setAdminName(adminName);
			admin.setAdminPass(adminPass);
			System.out.println(admin);
			IAdminService adminService = new AdminServiceImpl();
			if (true == adminService.getByName(admin)) {
				HttpSession session = request.getSession();
				session.setAttribute("adminName", adminName);
				session.setMaxInactiveInterval(60 * 15);
				request.getRequestDispatcher("/WEB-INF/back/manage.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/back/login.jsp").forward(request, response);
			}
		} 

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
