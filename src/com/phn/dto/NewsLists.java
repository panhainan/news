package com.phn.dto;


/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class NewsLists {
	private int newsCategoryId;
	private String newsCategoryName;

	private NewsList[] listCategoryNews;

	public NewsLists() {
	}

	public NewsLists(int newsCategoryId, String newsCategoryName,
			NewsList[] listCategoryNews) {
		this.newsCategoryId = newsCategoryId;
		this.newsCategoryName = newsCategoryName;
		this.listCategoryNews = listCategoryNews;
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

	public NewsList[] getListCategoryNews() {
		return listCategoryNews;
	}

	public void setListCategoryNews(NewsList[] listCategoryNews) {
		this.listCategoryNews = listCategoryNews;
	}

}
