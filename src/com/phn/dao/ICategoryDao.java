package com.phn.dao;

import java.util.List;

import com.phn.po.Category;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public interface ICategoryDao {
	int save(Category category);
	int delete(int categoryId);
	Category find(int categoryId);
	int update(Category category);
	List<Category> list();
}
