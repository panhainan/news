package com.phn.po;

import java.util.Date;

/**
 * @author phn
 * @TODO JavaBean News : it is used to save news
 * @date 2015-4-25
 */
public class News {
	private int id;
	private String newsTitle;
	private String newsAuthor;
	private String newsAuthorSite;
	private Date newsPublishTime;
	private String newsContent;
	/**
	 * This property is used to express news is a news in pictures
	 */
	private boolean newsIsPicture;
	private String newsPictureSite;
	/**
	 * This property is used to represent the news headlines are
	 */
	private boolean newsIsTopLine;
	/**
	 * This property is used to express news is a hot news
	 */
	private boolean newsIsHot;
	private int newsCategoryId;
	private int newsHitCount;
	/**
	 * This property is used to express the news is what category
	 */
	private int newsCommentCount;

	public News() {
	}

	public News(int newsHitCount, int newsCommentCount, Date newsPublishTime,
			boolean newsIsPicture, boolean newsIsTopLine, boolean newsIsHot) {
		this.newsHitCount = newsHitCount;
		this.newsCommentCount = newsCommentCount;
		this.newsPublishTime = newsPublishTime;
		this.newsIsPicture = newsIsPicture;
		this.newsIsTopLine = newsIsTopLine;
		this.newsIsHot = newsIsHot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getNewsPublishTime() {
		return newsPublishTime;
	}

	public void setNewsPublishTime(Date newsPublishTime) {
		this.newsPublishTime = newsPublishTime;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public boolean isNewsIsPicture() {
		return newsIsPicture;
	}

	public void setNewsIsPicture(boolean newsIsPicture) {
		this.newsIsPicture = newsIsPicture;
	}

	public String getNewsPictureSite() {
		return newsPictureSite;
	}

	public void setNewsPictureSite(String newsPictureSite) {
		this.newsPictureSite = newsPictureSite;
	}

	public boolean isNewsIsTopLine() {
		return newsIsTopLine;
	}

	public void setNewsIsTopLine(boolean newsIsTopLine) {
		this.newsIsTopLine = newsIsTopLine;
	}

	public boolean isNewsIsHot() {
		return newsIsHot;
	}

	public void setNewsIsHot(boolean newsIsHot) {
		this.newsIsHot = newsIsHot;
	}

	public int getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(int newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
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

	@Override
	public String toString() {
		return "News \n[id=" + id + ", newsTitle=" + newsTitle
				+ ", newsAuthor=" + newsAuthor + ", newsAuthorSite="
				+ newsAuthorSite + ", newsPublishTime=" + newsPublishTime
				+ ", newsContent=" + newsContent + ", newsIsPicture="
				+ newsIsPicture + ", newsPictureSite=" + newsPictureSite
				+ ", newsIsTopLine=" + newsIsTopLine + ", newsIsHot="
				+ newsIsHot + ", newsCategoryId=" + newsCategoryId
				+ ", newsHitCount=" + newsHitCount + ", newsCommentCount="
				+ newsCommentCount + "]";
	}
}
