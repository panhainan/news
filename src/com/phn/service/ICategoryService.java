package com.phn.service;


import com.phn.dto.CategoryList;
import com.phn.po.Category;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public interface ICategoryService {
	boolean add(Category category);

	CategoryList[] list();
	boolean update(Category category);
	CategoryList find(int categoryId);

	boolean delete(int categoryId);

	boolean find(int categoryId, String categoryName);
}
