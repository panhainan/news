package com.phn.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.phn.dto.CategoryList;
import com.phn.dto.NewsDetails;
import com.phn.dto.NewsList;
import com.phn.po.News;
import com.phn.service.ICategoryService;
import com.phn.service.INewsService;
import com.phn.service.impl.CategoryServiceImpl;
import com.phn.service.impl.NewsServiceImpl;

/**
 * @author phn
 * @TODO
 * @date 2015-4-26
 */
public class NewsManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("goaddnews".equals(action)) {
			ICategoryService categoryService = new CategoryServiceImpl();
			CategoryList[] listCategoryList = categoryService.list();
			request.setAttribute("listCategoryList", listCategoryList);
			request.getRequestDispatcher("/WEB-INF/back/addnews.jsp").forward(request,
					response);
		} else if ("look".equals(action)) {
			int newsId = Integer.parseInt(request.getParameter("id"));
			INewsService newsService = new NewsServiceImpl();
			NewsDetails newsDetails = newsService.find(newsId);
			request.setAttribute("newsDetails", newsDetails);
			request.getRequestDispatcher("/WEB-INF/back/newsDetails.jsp").forward(
					request, response);
		} else if ("goupdate".equals(action)) {
			int newsId = Integer.parseInt(request.getParameter("id"));
			INewsService newsService = new NewsServiceImpl();
			NewsDetails newsDetails = newsService.find(newsId);
			ICategoryService categoryService = new CategoryServiceImpl();
			CategoryList[] listCategoryList = categoryService.list();
			request.setAttribute("newsDetails", newsDetails);
			request.setAttribute("listCategoryList", listCategoryList);
			request.getRequestDispatcher("/WEB-INF/back/updatenews.jsp").forward(
					request, response);
		} else if ("delete".equals(action)) {
			int newsId = Integer.parseInt(request.getParameter("id"));
			INewsService newsService = new NewsServiceImpl();
			if (true == newsService.delete(newsId)) {
				response.sendRedirect("news?action=listnews&page=1");
			} else {
				request.getRequestDispatcher("/WEB-INF/back/error.jsp").forward(
						request, response);
			}

		} else if ("search".equals(action)) {
			doPost(request, response);
		} else if ("listnews".equals(action)) {
			INewsService newsService = new NewsServiceImpl();
			ICategoryService categoryService = new CategoryServiceImpl();
			String categoryIdString = request.getParameter("cate");
			String currentPageString = request.getParameter("page");
			int pageSize = 6;
			int newsCategoryId;
			int currentPage;
			try {
				Map<String, Object> map = null;
				if ("".equals(currentPageString) || null == currentPageString) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(currentPageString);
				}

				if ("".equals(categoryIdString) || null == categoryIdString
						|| "0".equals(categoryIdString)) {
					map = newsService.list(pageSize, currentPage);
				} else {
					newsCategoryId = Integer.parseInt(categoryIdString);
					map = newsService.list(pageSize, currentPage,
							newsCategoryId);
				}
				CategoryList[] listCategoryList = categoryService.list();
				request.setAttribute("map", map);
				request.setAttribute("listCategoryList", listCategoryList);
				request.getRequestDispatcher("/WEB-INF/back/news.jsp").forward(request,
						response);
			} catch (Exception e) {
				System.out.println("error");
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("addnews".equals(action)) {
			INewsService newsService = new NewsServiceImpl();
			int newsId = newsService.add(upload(request, response));
			response.sendRedirect("news?action=look&id=" + newsId);
		} else if ("update".equals(action)) {
			INewsService newsService = new NewsServiceImpl();
			News news = upload(request, response);
			if (true == newsService.update(news)) {
				response.sendRedirect("news?action=look&id=" + news.getId());
			} else {
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		} else if ("search".equals(action)) {
			String newsCategoryName = new String(request.getParameter(
					"newsCategoryName").getBytes("ISO-8859-1"), "UTF-8");
			String newsTitle = new String(request.getParameter("newsTitle").getBytes(
							"ISO-8859-1"), "UTF-8");
			String newsTitleString = "%" + newsTitle+"%";
			String newsCategoryIdString = request
					.getParameter("newsCategoryId");
			int newsCategoryId;
			String currentPageString = request.getParameter("page");
			int pageSize = 2;
			int currentPage;
			try {
				Map<String, Object> map = null;
				INewsService newsService = new NewsServiceImpl();
				newsCategoryId = Integer.parseInt(newsCategoryIdString);
				currentPage = Integer.parseInt(currentPageString);
				if (0 != newsCategoryId) {
					map = newsService.search(pageSize, currentPage,
							newsCategoryId, newsTitleString);
					map.put("newsCategoryName", newsCategoryName);
				} else {
					map = newsService.search(pageSize, currentPage, newsTitleString);
					map.put("newsCategoryName", "");
				}
				map.put("newsTitle", newsTitle);
				ICategoryService categoryService = new CategoryServiceImpl();
				CategoryList[] listCategoryList = categoryService.list();
				request.setAttribute("listCategoryList", listCategoryList);
				request.setAttribute("map", map);
				request.getRequestDispatcher("/WEB-INF/back/newsSearchs.jsp").forward(
						request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("/WEB-INF/back/error.jsp");
			}
		}

	}

	private News upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String uploadPath = this.getServletContext().getRealPath("/")
				+ "\\upload\\image\\"; // 上传文件的目录
		File tempPathFile = null;
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Set factory constraints
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Set overall request size constraint
		upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		News news = new News(0, 0, new Date(), false, false, false);
		Iterator<FileItem> i = items.iterator();
		try {
			while (i.hasNext()) {
				FileItem item = (FileItem) i.next();
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					if ("newsTitle".equals(fieldName)) {
						news.setNewsTitle(item.getString("utf-8"));
					}
					if ("newsId".equals(fieldName)) {
						news.setId(Integer.parseInt(item.getString("utf-8")));
					}
					if ("newsAuthor".equals(fieldName)) {
						news.setNewsAuthor(item.getString("utf-8"));
					}
					if ("newsAuthorSite".equals(fieldName)) {
						news.setNewsAuthorSite(item.getString("utf-8"));
					}
					if ("newsContent".equals(fieldName)) {
						news.setNewsContent(item.getString("utf-8"));
					}
					if ("newsCategoryId".equals(fieldName)) {
						news.setNewsCategoryId(Integer.parseInt(item
								.getString("utf-8")));
					}
					if ("newsIsPicture".equals(fieldName)) {
						String newIsPicture = item.getString("utf-8");
						if ("true".equals(newIsPicture)) {
							news.setNewsIsPicture(true);
						}
					}
					if ("newsIsTopLine".equals(fieldName)) {
						String newsIsTopLine = item.getString("utf-8");
						if ("true".equals(newsIsTopLine)) {
							news.setNewsIsTopLine(true);
						}
					}
					if ("newsIsHot".equals(fieldName)) {
						String newsIsHot = item.getString("utf-8");
						if ("true".equals(newsIsHot)) {
							news.setNewsIsHot(true);
						}
					}
				} else if (!"".equals(item.getName())) {
					try {
						File fullFile = new File(item.getName());
						File savedFile = new File(uploadPath,
								fullFile.getName());
						item.write(savedFile);
						news.setNewsPictureSite(request.getScheme() + "://"
								+ request.getServerName() + ":"
								+ request.getServerPort()
								+ request.getContextPath() + "/"
								+ "upload/image/" + fullFile.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("/back/error.jsp");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response.sendRedirect("/back/error.jsp");
		}
		return news;
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
