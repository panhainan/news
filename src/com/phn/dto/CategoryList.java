package com.phn.dto;

/**
 * @author phn
 * @TODO
 * @date 2015-4-26
 */
public class CategoryList {
	private int categoryId;
	private String categoryName;
	private int categoryPriority;

	public CategoryList() {
	}

	public CategoryList(int categoryId, String categoryName,int categoryPriority) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryPriority = categoryPriority;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryPriority() {
		return categoryPriority;
	}

	public void setCategoryPriority(int categoryPriority) {
		this.categoryPriority = categoryPriority;
	}

	@Override
	public String toString() {
		return "CategoryList [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryPriority=" + categoryPriority + "]";
	}
	
}
