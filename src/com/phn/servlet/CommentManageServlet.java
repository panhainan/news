package com.phn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phn.dto.CategoryList;
import com.phn.dto.CommentLists;
import com.phn.service.ICommentService;
import com.phn.service.impl.CommentServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-28
 */
public class CommentManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			String currentPageString = request.getParameter("page");
			int pageSize = 3;
			int currentPage;
			Map<String, Object> map = null;
			try {
				ICommentService commentService = new CommentServiceImpl();
				if ("".equals(currentPageString) || null == currentPageString) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(currentPageString);
				}
				map = commentService.list(pageSize, currentPage);
				request.setAttribute("map", map);
				request.getRequestDispatcher("/WEB-INF/back/comment.jsp").forward(request,
						response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		}else if("delete".equals(action)){
			ICommentService commentService = new CommentServiceImpl();
			String commentIdString = request.getParameter("id");
			String commentNewsIdString = request.getParameter("newsId");
			try {
				int commentId = Integer.parseInt(commentIdString);
				int newsId =  Integer.parseInt(commentNewsIdString);
				if(true==commentService.delete(commentId,newsId)){
					response.sendRedirect("comment?action=list&page=1");
				}else{
					response.sendRedirect("/WEB-INF/back/error.jsp");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace(); 
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
