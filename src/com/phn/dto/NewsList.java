package com.phn.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class NewsList {
	private int newsId;
	private String newsTitle;
	private String newsPublishTime;

	public NewsList() {
	}

	public NewsList(int newsId, String newsTitle, Date newsPublishTime) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsPublishTime = new SimpleDateFormat("MM-dd ")
				.format(newsPublishTime);
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

	public String getNewsPublishTime() {
		return newsPublishTime;
	}

	public void setNewsPublishTime(String newsPublishTime) {
		this.newsPublishTime = newsPublishTime;
	}

	@Override
	public String toString() {
		return "NewsList [newsId=" + newsId + ", newsTitle=" + newsTitle
				+ ", newsPublishTime=" + newsPublishTime + "]";
	}
	
}
