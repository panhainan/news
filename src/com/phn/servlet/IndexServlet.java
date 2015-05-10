package com.phn.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phn.dto.CategoryList;
import com.phn.dto.NewsList;
import com.phn.dto.NewsLists;
import com.phn.dto.PicNews;
import com.phn.dto.TopLineNews;
import com.phn.service.ICategoryService;
import com.phn.service.INewsService;
import com.phn.service.impl.CategoryServiceImpl;
import com.phn.service.impl.NewsServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ICategoryService categoryService = new CategoryServiceImpl();
		INewsService newsService = new NewsServiceImpl();
		/*这是展示图片新闻的模块的内容*/
		boolean newsIsPicture = true;
		List<PicNews> listPicNews = new ArrayList<PicNews>();
		listPicNews= newsService.listPic(newsIsPicture);
		/*这是展示头条新闻的模块的内容*/
		boolean newsIsTopLine = true;
		TopLineNews topLineNews = new TopLineNews();
		topLineNews = newsService.getTopLine(newsIsTopLine);
		System.out.println(topLineNews);
		/*这是展示热门新闻的模块的内容*/
		boolean newsIsHot = true;
		List<NewsList> listHotNews = new ArrayList<NewsList>();
		listHotNews = newsService.listHot(newsIsHot);
		
		/*这里展示导航栏项目*/
		CategoryList[]  listCategoryList = categoryService.list();
		/*这是展示各个新闻类别及内容的模块的内容*/
		NewsLists[] listNewsLists = new NewsLists[listCategoryList.length];
		for (int i = 0; i < listCategoryList.length; i++) {
			listNewsLists[i]=new NewsLists(listCategoryList[i].getCategoryId(),listCategoryList[i].getCategoryName(),newsService.list(listCategoryList[i].getCategoryId()));
		}
		request.setAttribute("listCategoryList", listCategoryList);
		request.setAttribute("listPicNews", listPicNews);
		request.setAttribute("topLineNews", topLineNews);
		request.setAttribute("listHotNews", listHotNews);
		request.setAttribute("listNewsLists", listNewsLists);
		request.getRequestDispatcher("WEB-INF/index1.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
