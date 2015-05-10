package com.phn.service;


import org.junit.Test;

import com.phn.po.Category;
import com.phn.service.impl.CategoryServiceImpl;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public class CategoryServiceImplTest {
	private  ICategoryService categoryService = new CategoryServiceImpl();
	/**
	 * Test method for {@link com.phn.service.impl.CategoryServiceImpl#add(com.phn.po.Category)}.
	 */
	@Test
	public void testAdd() {
		Category category = new Category();
		category.setCategoryName("八卦新闻");
		category.setCategoryPriority(3);
		System.out.println(categoryService.add(category));
	}

}
