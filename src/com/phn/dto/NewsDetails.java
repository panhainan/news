package com.phn.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class NewsDetails {
	private int newsId;
	private String newsTitle;
	private String newsAuthor;
	private String newsAuthorSite;
	private String newsPublishTime;
	private String newsContent;
	private int newsCategoryId;
	private String newsCategoryName;
	private int newsHitCount;
	private int newsCommentCount;

	public NewsDetails() {
	}
	public NewsDetails(int newsId, String newsTitle, String newsAuthor,
			String newsAuthorSite, Date newsPublishTime, String newsContent,
			int newsCategoryId, String newsCategoryName, int newsHitCount, int newsCommentCount) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsAuthor = newsAuthor;
		this.newsAuthorSite = newsAuthorSite;
		this.newsPublishTime = new SimpleDateFormat("MM-dd ")
		.format(newsPublishTime);
		this.newsContent = newsContent;
		this.newsCategoryId = newsCategoryId;
		this.newsCategoryName = newsCategoryName;
		this.newsHitCount = newsHitCount;
		this.newsCommentCount = newsCommentCount;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsAuthorSite() {
		return newsAuthorSite;
	}

	public void setNewsAuthorSite(String newsAuthorSite) {
		this.newsAuthorSite = newsAuthorSite;
	}

	public String getNewsPublishTime() {
		return newsPublishTime;
	}

	public void setNewsPublishTime(String newsPublishTime) {
		this.newsPublishTime = newsPublishTime;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public int getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(int newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}

	public String getNewsCategoryName() {
		return newsCategoryName;
	}

	public void setNewsCategoryName(String newsCategoryName) {
		this.newsCategoryName = newsCategoryName;
	}

	public int getNewsHitCount() {
		return newsHitCount;
	}

	public void setNewsHitCount(int newsHitCount) {
		this.newsHitCount = newsHitCount;
	}

	public int getNewsCommentCount() {
		return newsCommentCount;
	}

	public void setNewsCommentCount(int newsCommentCount) {
		this.newsCommentCount = newsCommentCount;
	}

}
