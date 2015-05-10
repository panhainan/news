package com.phn.dao;

import java.util.List;


import com.phn.po.News;

/**
 * @author phn
 * @TODO This interface is used to handle related news between and news between database tables
 * @date 2015-4-25
 */
public interface INewsDao {
	int save(News news);
	int delete(int newsId);
	News find(int newsId);
	int update(News news);
	List<News> list();
	List<News> list(int newsCategoryId);
	List<News> listPic(boolean newsIsPicture);
	List<News> listHot(boolean newsIsHot);
	News getTopLine(boolean newsIsTopLine);
	List<News> list( int startRecord,int pageSize,int newsCategoryId);
	List<News> list(int startRecord,int pageSize);
	int countRow(int newsCategoryId);
	int countRow();
	List<News> listHot();
	int countRow(int newsCategoryId, String newsTitle);
	int countRow(String newsTitle);
	List<News> list(int startRecord, int pageSize, int newsCategoryId,
			String newsTitle);
	List<News> list(int startRecord, int pageSize,
			String newsTitle);
	int changeCommentCount(int commentNewsId, String string);
	int changeHitCount(int newsId);
}
