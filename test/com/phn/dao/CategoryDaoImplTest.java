package com.phn.dao;

import org.junit.Test;

import com.phn.dao.impl.CategoryDaoImpl;
import com.phn.po.Category;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class CategoryDaoImplTest {
	private  ICategoryDao categoryDao = new CategoryDaoImpl();

	/**
	 * Test method for
	 * {@link com.phn.dao.impl.CategoryDaoImpl#save(com.phn.po.Category)}.
	 */
	// @Test
	public void testSave() {
		Category category = new Category();
		category.setCategoryName("时事新闻");
		category.setCategoryPriority(2);
		System.out.println(categoryDao.save(category));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.CategoryDaoImpl#delete(int)}.
	 */
	// @Test
	public void testDelete() {
		System.out.println(categoryDao.delete(2));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.CategoryDaoImpl#find(int)}.
	 */
	// @Test
	public void testFind() {
		System.out.println(categoryDao.find(1));
	}

	/**
	 * Test method for
	 * {@link com.phn.dao.impl.CategoryDaoImpl#update(com.phn.po.Category)}.
	 */
	// @Test
	public void testUpdate() {
		Category category = categoryDao.find(1);
		category.setCategoryName("政治新闻");
		System.out.println(categoryDao.update(category));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.CategoryDaoImpl#list()}.
	 */
	@Test
	public void testList() {
		System.out.println(categoryDao.list());
	}

}
