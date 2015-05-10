package com.phn.po;

/**
 * @author phn
 * @TODO JavaBean Category : it is used to save the type of news
 * @date 2015-4-25
 */
public class Category {
	private int id;
	private String categoryName;
	/**
	 * This property is used to represent the priority of category
	 */
	private int categoryPriority;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Category \n[id=" + id + ", categoryName=" + categoryName
				+ ", categoryPriority=" + categoryPriority + "]";
	}

}
