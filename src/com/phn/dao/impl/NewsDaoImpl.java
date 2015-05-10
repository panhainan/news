package com.phn.dao.impl;

import java.util.List;


import com.phn.dao.INewsDao;
import com.phn.po.News;

/**
 * @author phn
 * @TODO This class is used to implement the interface INewsDao
 * @date 2015-4-25
 */
public class NewsDaoImpl extends JDBCDaoSupport<News> implements INewsDao {
	public int save(News news) {
		String saveSql = "insert into t_news(newsTitle,newsAuthor,newsAuthorSite,newsPublishTime,newsContent,newsIsPicture,newsPictureSite,newsIsTopLine,newsIsHot,newsHitCount,newsCategoryId,newsCommentCount) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return super.executeInsert(saveSql, news.getNewsTitle(),
				news.getNewsAuthor(), news.getNewsAuthorSite(),
				news.getNewsPublishTime(), news.getNewsContent(),
				news.isNewsIsPicture(), news.getNewsPictureSite(),
				news.isNewsIsTopLine(), news.isNewsIsHot(),
				news.getNewsHitCount(), news.getNewsCategoryId(),
				news.getNewsCommentCount());
	}

	public int delete(int newsId) {
		String deleteSql = "delete from t_news where id  = ?";
		return super.executeUpdateAndDelete(deleteSql, newsId);
	}

	public News find(int newsId) {
		String findSql = "select * from t_news where id = ?";
		return super.executeGet(findSql, News.class, newsId);
	}

	public int update(News news) {
		String updateSql = "update t_news set newsTitle=?,newsAuthor=?,newsAuthorSite=?,newsContent=?,newsIsPicture=?,newsPictureSite=?,newsIsTopLine=?,newsIsHot=?,newsCategoryId=? where id =?";
		return super.executeUpdateAndDelete(updateSql, news.getNewsTitle(),
				news.getNewsAuthor(), news.getNewsAuthorSite(),
				news.getNewsContent(), news.isNewsIsPicture(),
				news.getNewsPictureSite(), news.isNewsIsTopLine(),
				news.isNewsIsHot(), news.getNewsCategoryId(), news.getId());
	}

	public List<News> list() {
		String listSql = "select * from t_news";
		return super.executeList(listSql, News.class);
	}

	public List<News> list(int newsCategoryId) {
		String listSql = "select * from t_news where newsCategoryId=? order by id desc limit 10";
		return super.executeList(listSql, News.class, newsCategoryId);
	}

	public List<News> listPic(boolean newsIsPicture) {
		String listSql = "select * from t_news where newsIsPicture=? order by id desc limit 4";
		return super.executeList(listSql, News.class, newsIsPicture);
	}

	public List<News> listHot(boolean newsIsHot) {
		String listSql = "select * from t_news where newsIsHot=? order by id desc limit 8";
		return super.executeList(listSql, News.class, newsIsHot);
	}
	public News getTopLine(boolean newsIsTopLine){
		String listSql = "select * from t_news where newsIsTopLine=? order by id desc";
		List<News> list =  super.executeList(listSql, News.class, newsIsTopLine);
		if(list.size() >0){
			return list.get(0);
		}
		return null;
	}
	public List<News> list(int startRecord,int pageSize,int newsCategoryId){
		String listSql = "select * from t_news where newsCategoryId=?  order by id desc limit ?,?";
		return super.executeList(listSql,News.class,newsCategoryId,startRecord,pageSize);
	}
	public List<News> list(int startRecord,int pageSize){
		String listSql = "select * from t_news  order by id desc limit ?,?";
		return super.executeList(listSql,News.class,startRecord,pageSize);
	}
	public int countRow(int newsCategoryId) {
		String getCountSql = "select count(*) from t_news where newsCategoryId=?";
		return super.getCountRow(getCountSql,newsCategoryId);
	}
	public int countRow() {
		String getCountSql = "select count(*) from t_news";
		return super.getCountRow(getCountSql);
	}
/**
 * 热门新闻
 */
	public List<News> listHot() {
		String listSql = "select * from t_news order by newsCommentCount*newsHitCount desc limit 20";
		return super.executeList(listSql, News.class);
	}

	public int countRow(int newsCategoryId, String newsTitle) {
		String getCountSql = "select count(*) from t_news where newsCategoryId=? and newsTitle like ? ";
		return super.getCountRow(getCountSql,newsCategoryId,newsTitle);
	}

	public int countRow(String newsTitle) {
		String getCountSql = "select count(*) from t_news where  newsTitle like ? ";
		return super.getCountRow(getCountSql,newsTitle);
	}

	public List<News> list(int startRecord, int pageSize, int newsCategoryId,
			String newsTitle) {
		String listSql = "select * from t_news where newsCategoryId=? and newsTitle like ? order by id desc limit ?,?";
		return super.executeList(listSql,News.class,newsCategoryId,newsTitle,startRecord,pageSize);
	}

	public List<News> list(int startRecord, int pageSize, String newsTitle) {
		String listSql = "select * from t_news where  newsTitle like ? order by id desc limit ?,?";
		return super.executeList(listSql,News.class,newsTitle,startRecord,pageSize);
	}

	public int changeCommentCount(int commentNewsId, String changeType) {
		String changeSql ="update t_news set newsCommentCount=newsCommentCount+? where id=?";
		int result = 0;
		if("plus".equals(changeType)){
			result=super.executeUpdateAndDelete(changeSql, +1,commentNewsId);
		}else if("minus".equals(changeType)){
			result=super.executeUpdateAndDelete(changeSql, -1,commentNewsId);
		}
		return result;
	}

	public int changeHitCount(int newsId) {
		String changeSql ="update t_news set newsHitCount=newsHitCount+1 where id=?";
		return super.executeUpdateAndDelete(changeSql, newsId);
	}
}
