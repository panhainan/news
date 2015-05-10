package com.phn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phn.dto.CategoryList;
import com.phn.dto.NewsList;
import com.phn.dto.NewsLists;
import com.phn.service.ICategoryService;
import com.phn.service.INewsService;
import com.phn.service.impl.CategoryServiceImpl;
import com.phn.service.impl.NewsServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-26
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		/* 这里展示导航栏项目 */
		ICategoryService categoryService = new CategoryServiceImpl();
		CategoryList[] listCategoryList = categoryService.list();
		request.setAttribute("listCategoryList", listCategoryList);

		INewsService newsService = new NewsServiceImpl();
		if ("look".equals(action)) {
			String currentPageString = request.getParameter("page");
			String categoryIdString = request.getParameter("id");
			String newsCategoryName = new String(request.getParameter("name")
					.getBytes("ISO-8859-1"), "UTF-8");
			int pageSize = 18;
			int currentPage;
			int newsCategoryId;
			Map<String, Object> map = null;
			try {
				if ("".equals(currentPageString) || null == currentPageString) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(currentPageString);
				}
				newsCategoryId = Integer.parseInt(categoryIdString);
				if(true==categoryService.find(newsCategoryId,newsCategoryName)){
					map = newsService.list(pageSize, currentPage, newsCategoryId);
					map.put("newsCategoryName", newsCategoryName);
					request.setAttribute("map", map);
					NewsList[] newsList = newsService.listHot();
					request.setAttribute("newsList", newsList);
					request.getRequestDispatcher("/WEB-INF/category.jsp").forward(request,
							response);
				}else{
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
							response);
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("error");
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
						response);
			}

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
