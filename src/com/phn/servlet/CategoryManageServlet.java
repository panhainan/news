package com.phn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phn.dto.CategoryList;
import com.phn.po.Category;
import com.phn.po.News;
import com.phn.service.ICategoryService;
import com.phn.service.impl.CategoryServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-27
 */
public class CategoryManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			ICategoryService categoryService = new CategoryServiceImpl();
			CategoryList[] listCategoryList = categoryService.list();
			request.setAttribute("listCategoryList", listCategoryList);
			request.getRequestDispatcher("/WEB-INF/back/category.jsp").forward(request,
					response);
		}else if("goadd".equals(action)){
				request.getRequestDispatcher("/WEB-INF/back/addcategory.jsp").forward(request,
						response);
		}else if("goupdate".equals(action)){
			ICategoryService categoryService = new CategoryServiceImpl();
			String categoryIdString = request
					.getParameter("id");
			int categoryId ;
			try {
				categoryId = Integer.parseInt(categoryIdString);
				CategoryList categoryList = categoryService.find(categoryId);
				request.setAttribute("categoryList", categoryList);
				request.getRequestDispatcher("/WEB-INF/back/updatecategory.jsp").forward(request,
						response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		}else if("delete".equals(action)){
			ICategoryService categoryService = new CategoryServiceImpl();
			String categoryIdString = request
					.getParameter("id");
			int categoryId ;
			try {
				categoryId = Integer.parseInt(categoryIdString);
				if(true==categoryService.delete(categoryId)){
					response.sendRedirect("category?action=list");
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
		String action = request.getParameter("action");
		if ("add".equals(action)) {
			ICategoryService categoryService = new CategoryServiceImpl();
			String categoryName = new String(request.getParameter("categoryName").getBytes("ISO-8859-1"),"UTF-8");
			String categoryPriorityString = request
					.getParameter("categoryPriority");
			int categoryPriority;
			try {
				categoryPriority = Integer.parseInt(categoryPriorityString);
				Category category = new Category();
				category.setCategoryName(categoryName);
				category.setCategoryPriority(categoryPriority);
				categoryService.add(category);
				response.sendRedirect("category?action=list");
			} catch (NumberFormatException e) {
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}

		}else if("update".equals(action)){
			ICategoryService categoryService = new CategoryServiceImpl();
			String categoryName = new String(request.getParameter("categoryName").getBytes("ISO-8859-1"),"UTF-8");
			String categoryPriorityString = request
					.getParameter("categoryPriority");
			String categoryIdString = request
					.getParameter("categoryId");
			int categoryId;
			int categoryPriority;
			try {
				categoryId  = Integer.parseInt(categoryIdString);
				categoryPriority = Integer.parseInt(categoryPriorityString);
				Category category = new Category();
				category.setId(categoryId);
				category.setCategoryName(categoryName);
				category.setCategoryPriority(categoryPriority);
				if(true==categoryService.update(category)){
					response.sendRedirect("category?action=list");
				}
				else{
					response.sendRedirect("/WEB-INF/back/error.jsp");
				}
			} catch (NumberFormatException e) {
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
