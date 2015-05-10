package com.phn.service.impl;

import java.util.List;

import com.phn.dao.ICategoryDao;
import com.phn.dao.impl.CategoryDaoImpl;
import com.phn.dto.CategoryList;
import com.phn.dto.NewsList;
import com.phn.po.Category;
import com.phn.service.ICategoryService;
import com.phn.service.INewsService;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public class CategoryServiceImpl implements ICategoryService {

	private ICategoryDao categoryDao = new CategoryDaoImpl();
	INewsService newsService = new NewsServiceImpl();
	public boolean add(Category category) {
		int resultId;
		try {
			resultId = categoryDao.save(category);
			if(resultId <1 ){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public CategoryList[] list() {
		List<Category> listCategory = null;
		listCategory = categoryDao.list();
		if(listCategory.size()>0){
			CategoryList[] listCategoryList = new CategoryList[listCategory.size()];
			for (int i = 0; i < listCategory.size(); i++) {
				listCategoryList[i]=new CategoryList(listCategory.get(i).getId(),listCategory.get(i).getCategoryName(),listCategory.get(i).getCategoryPriority());
			}
			return listCategoryList;
		}
		return new CategoryList[0];
	}

	public boolean update(Category category) {
		if(categoryDao.update(category)==1){
			return true;
		}
		return false;
	}

	public CategoryList find(int categoryId) {
		Category category = categoryDao.find(categoryId);
		CategoryList categoryList = new CategoryList(category.getId(),category.getCategoryName(),category.getCategoryPriority());
		return categoryList;
	}

	public boolean delete(int categoryId) {
		NewsList[] newsList = newsService.list(categoryId);
		for(int i =0;i<newsList.length;i++){
			if(false==newsService.delete(newsList[i].getNewsId())){
				return false;
			}
		}
		if(1==categoryDao.delete(categoryId)){
			return true;
		}
		return false;
	}

	public boolean find(int categoryId, String categoryName) {
		Category category = null;
		category = categoryDao.find(categoryId);
		if(null!=category && categoryName.equals(category.getCategoryName())){
			return true;
		}
		return false;
	}

}
