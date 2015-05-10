package com.phn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phn.dto.CategoryList;
import com.phn.dto.CommentLists;
import com.phn.dto.NewsDetails;
import com.phn.dto.NewsList;
import com.phn.po.Comment;
import com.phn.service.ICategoryService;
import com.phn.service.ICommentService;
import com.phn.service.INewsService;
import com.phn.service.impl.CategoryServiceImpl;
import com.phn.service.impl.CommentServiceImpl;
import com.phn.service.impl.NewsServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class NewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
			look(request, response, newsService);
		}
	}

	private void look(HttpServletRequest request, HttpServletResponse response,
			INewsService newsService) throws NumberFormatException,
			ServletException, IOException {
		String newsIdString = request.getParameter("id");
		try {
			int newsId = Integer.parseInt(newsIdString);
			NewsDetails newsDetails = null;
			newsDetails = newsService.find(newsId);
			System.out.println(newsDetails);
			if(null!=newsDetails){
				ICommentService commentService = new CommentServiceImpl();
				CommentLists[] commentLists = commentService.list(newsId);
				request.setAttribute("commentLists", commentLists);
				NewsList[] newsList = newsService.listHot();
				request.setAttribute("newsList", newsList);
				request.setAttribute("newsDetails", newsDetails);
				request.getRequestDispatcher("/WEB-INF/newsDetails.jsp").forward(request,
						response);
			}else{
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
						response);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
					response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String action = request.getParameter("action");
		if ("comment".equals(action)) {
			ICommentService commentService = new CommentServiceImpl();
			String newsIdString = request.getParameter("newsId");
			String commentContent =  new String(request.getParameter("commentContent").getBytes("ISO-8859-1"),"UTF-8");
			String userIP=request.getRemoteAddr();
			try {
				int newsId = Integer.parseInt(newsIdString);
				Comment comment = new Comment();
				comment.setCommentNewsId(newsId);
				comment.setCommentContent(commentContent);
				comment.setCommentIP(userIP);
				comment.setCommentAddress(userIP);
				if(true==commentService.add(comment)){
					response.sendRedirect("news?action=look&id="+newsId);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		}
	}

	public void init() throws ServletException {
	}

}
