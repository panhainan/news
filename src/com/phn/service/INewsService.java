package com.phn.service;

import java.util.List;
import java.util.Map;

import com.phn.dto.NewsDetails;
import com.phn.dto.NewsList;
import com.phn.dto.PicNews;
import com.phn.dto.TopLineNews;
import com.phn.po.News;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public interface INewsService {
	int add(News news);
	NewsList[] list(int newsCategoryId);
	List<PicNews> listPic(boolean newsIsPicture);
	List<NewsList> listHot(boolean newsIsTopLine);
	TopLineNews getTopLine(boolean newsIsTopLine);
	NewsDetails find(int newsId);
	NewsList[] list();
	Map<String, Object> list(int pageSize,int currentPage,int newsCategoryId);
	Map<String, Object> list(int pageSize,int currentPage);
	boolean update(News news);
	boolean delete(int newsId);
	NewsList[] listHot();
	Map<String, Object>  search(int pageSize, int currentPage, int newsCategoryId,
			String newsTitle);
	Map<String, Object> search(int pageSize, int currentPage, String newsTitle);
}
