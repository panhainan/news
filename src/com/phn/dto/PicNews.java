package com.phn.dto;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class PicNews {
	private int newsId;
	private String newsTitle;
	private String newsPictureSite;

	public PicNews() {
	}

	public PicNews(int newsId, String newsTitle, String newsPictureSite) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsPictureSite = newsPictureSite;
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

	public String getNewsPictureSite() {
		return newsPictureSite;
	}

	public void setNewsPictureSite(String newsPictureSite) {
		this.newsPictureSite = newsPictureSite;
	}

}
