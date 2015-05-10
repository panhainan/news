package com.phn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.phn.dao.ICategoryDao;
import com.phn.dao.ICommentDao;
import com.phn.dao.INewsDao;
import com.phn.dao.impl.CategoryDaoImpl;
import com.phn.dao.impl.CommentDaoImpl;
import com.phn.dao.impl.NewsDaoImpl;
import com.phn.dto.NewsDetails;
import com.phn.dto.NewsList;
import com.phn.dto.Page;
import com.phn.dto.PicNews;
import com.phn.dto.TopLineNews;
import com.phn.po.News;
import com.phn.service.INewsService;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class NewsServiceImpl implements INewsService {

	private INewsDao newsDao = new NewsDaoImpl();
	private ICategoryDao categoryDao = new CategoryDaoImpl();
	private ICommentDao commentDao = new CommentDaoImpl();
	public NewsList[] list(int newsCategoryId) {
		List<News> listNews = newsDao.list(newsCategoryId);
		NewsList[] listNewsList = new NewsList[listNews.size()];
		for (int i = 0; i < listNews.size(); i++) {
			listNewsList[i] = new NewsList(listNews.get(i).getId(), listNews
					.get(i).getNewsTitle(), listNews.get(i)
					.getNewsPublishTime());
		}
		return listNewsList;
	}

	public List<PicNews> listPic(boolean newsIsPicture) {
		List<PicNews> listPicNews =null;
		List<News> listNews = newsDao.listPic(newsIsPicture);
		if(listNews.size()>0){
			listPicNews = new ArrayList<PicNews>();
			for (int i = 0; i < listNews.size(); i++) {
				listPicNews.add(new PicNews(listNews.get(i).getId(), listNews
						.get(i).getNewsTitle(), listNews.get(i)
						.getNewsPictureSite()));
			}
		}
		return listPicNews;
	}

	public List<NewsList> listHot(boolean newsIsHot) {
		List<NewsList> listNewsList = new ArrayList<NewsList>();
		List<News> listNews = newsDao.listHot(newsIsHot);
		for (int i = 0; i < listNews.size(); i++) {
			listNewsList.add(new NewsList(listNews.get(i).getId(), listNews
					.get(i).getNewsTitle(), listNews.get(i)
					.getNewsPublishTime()));
		}
		return listNewsList;
	}

	public TopLineNews getTopLine(boolean newsIsTopLine) {
		News news = null;
		news = newsDao.getTopLine(newsIsTopLine);
		if(news!=null){
			return new TopLineNews(news.getId(), news.getNewsTitle(),
					new StringBuffer(news.getNewsContent()));
		}
		return null;
	}

	public NewsDetails find(int newsId) {
		News news = null;
		news = newsDao.find(newsId);
		if(null!=news){
			newsDao.changeHitCount(newsId);
			String categoryName = categoryDao.find(news.getNewsCategoryId())
					.getCategoryName();
			return new NewsDetails(news.getId(), news.getNewsTitle(),
					news.getNewsAuthor(), news.getNewsAuthorSite(),
					news.getNewsPublishTime(), news.getNewsContent(),
					news.getNewsCategoryId(), categoryName, news.getNewsHitCount(),
					news.getNewsCommentCount());
		}else{
			return null;
		}
		
	}

	public NewsList[] list() {
		List<News> newss = newsDao.list();
		NewsList[] newsList = new NewsList[newss.size()];
		for (int i = 0; i < newss.size(); i++) {
			newsList[i] = new NewsList(newss.get(i).getId(), newss.get(i)
					.getNewsTitle(), newss.get(i).getNewsPublishTime());
		}
		return newsList;
	}

	public int add(News news) {
		return newsDao.save(news);
	}

	public Map<String, Object> list(int pageSize, int current,
			int newsCategoryId) {
		Page pageBean = new Page();
		int allRecords = newsDao.countRow(newsCategoryId);
		int totalPages = pageBean.calculateTotalPage(pageSize, allRecords);
		int currentPage = pageBean.judgeCurrentPageIsLegal(current, totalPages);
		if (currentPage == 0) {
			return null;
		}
		int startRecord = pageBean.calculateCurrentPageStartRecord(pageSize,
				currentPage);
		pageBean.init(allRecords, currentPage, pageSize, totalPages);
		List<News> newss = newsDao.list(startRecord, pageSize, newsCategoryId);
		NewsList[] newsList = new NewsList[newss.size()];
		for (int i = 0; i < newss.size(); i++) {
			newsList[i] = new NewsList(newss.get(i).getId(), newss.get(i)
					.getNewsTitle(), newss.get(i).getNewsPublishTime());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNewsList", newsList);
		map.put("newsCategoryId", newsCategoryId);
		map.put("pageBean", pageBean);
		return map;
	}

	public Map<String, Object> list(int pageSize, int current) {
		Page pageBean = new Page();
		int allRecords = newsDao.countRow();
		int totalPages = pageBean.calculateTotalPage(pageSize, allRecords);
		int currentPage = pageBean.judgeCurrentPageIsLegal(current, totalPages);
		if (currentPage == 0) {
			return null;
		}
		int startRecord = pageBean.calculateCurrentPageStartRecord(pageSize,
				currentPage);
		pageBean.init(allRecords, currentPage, pageSize, totalPages);
		List<News> newss = newsDao.list(startRecord, pageSize);
		NewsList[] newsList = new NewsList[newss.size()];
		for (int i = 0; i < newss.size(); i++) {
			newsList[i] = new NewsList(newss.get(i).getId(), newss.get(i)
					.getNewsTitle(), newss.get(i).getNewsPublishTime());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNewsList", newsList);
		map.put("pageBean", pageBean);
		return map;
	}

	public boolean update(News news) {
		if (1 == newsDao.update(news)) {
			return true;
		}
		return false;
	}

	public boolean delete(int newsId) {
		commentDao.deleteByNewsId(newsId);
		if (1 == newsDao.delete(newsId)) {
			return true;
		}
		return false;
	}

	public NewsList[] listHot() {
		List<News> newss = newsDao.listHot();
		NewsList[] newsList = new NewsList[newss.size()];
		for (int i = 0; i < newss.size(); i++) {
			newsList[i] = new NewsList(newss.get(i).getId(), newss.get(i)
					.getNewsTitle(), newss.get(i).getNewsPublishTime());
		}
		return newsList;
	}

	public Map<String, Object> search(int pageSize, int current,
			int newsCategoryId, String newsTitle) {
		Page pageBean = new Page();
		int allRecords = newsDao.countRow(newsCategoryId,newsTitle);
		int totalPages = pageBean.calculateTotalPage(pageSize, allRecords);
		int currentPage = pageBean.judgeCurrentPageIsLegal(current, totalPages);
		if (currentPage == 0) {
			return null;
		}
		int startRecord = pageBean.calculateCurrentPageStartRecord(pageSize,
				currentPage);
		pageBean.init(allRecords, currentPage, pageSize, totalPages);
		List<News> newss = newsDao.list(startRecord, pageSize, newsCategoryId,newsTitle);
		NewsList[] newsList = new NewsList[newss.size()];
		for (int i = 0; i < newss.size(); i++) {
			newsList[i] = new NewsList(newss.get(i).getId(), newss.get(i)
					.getNewsTitle(), newss.get(i).getNewsPublishTime());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNewsList", newsList);
		map.put("newsCategoryId", newsCategoryId);
		map.put("pageBean", pageBean);
		return map;
	}

	public Map<String, Object> search(int pageSize, int current,
			String newsTitle) {
		Page pageBean = new Page();
		int allRecords = newsDao.countRow(newsTitle);
		int totalPages = pageBean.calculateTotalPage(pageSize, allRecords);
		int currentPage = pageBean.judgeCurrentPageIsLegal(current, totalPages);
		if (currentPage == 0) {
			return null;
		}
		int startRecord = pageBean.calculateCurrentPageStartRecord(pageSize,
				currentPage);
		pageBean.init(allRecords, currentPage, pageSize, totalPages);
		List<News> newss = newsDao.list(startRecord, pageSize,newsTitle);
		NewsList[] newsList = new NewsList[newss.size()];
		for (int i = 0; i < newss.size(); i++) {
			newsList[i] = new NewsList(newss.get(i).getId(), newss.get(i)
					.getNewsTitle(), newss.get(i).getNewsPublishTime());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNewsList", newsList);
		map.put("pageBean", pageBean);
		return map;
	}

}
