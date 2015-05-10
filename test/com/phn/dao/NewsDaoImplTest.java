package com.phn.dao;


import org.junit.Test;

import com.phn.dao.impl.NewsDaoImpl;
import com.phn.po.News;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public class NewsDaoImplTest {
	private static INewsDao newsDao  = new NewsDaoImpl();
	/**
	 * Test method for {@link com.phn.dao.impl.NewsDaoImpl#save(com.phn.po.News)}.
	 */
//	@Test
	public void testSave() {
		News news = new News();
		news.setNewsTitle("标题2");
		news.setNewsAuthor("潘海南");
		news.setNewsAuthorSite("http://panhainan.com");
		news.setNewsContent("内容");
		news.setNewsIsHot(true);
		news.setNewsIsTopLine(true);
		news.setNewsIsPicture(true);
		news.setNewsPictureSite("http://www.img.com/panhainan.jpg");
		news.setNewsCategoryId((byte) 1);
		int newsId = newsDao.save(news);
		System.out.println(newsId);
	}

	/**
	 * Test method for {@link com.phn.dao.impl.NewsDaoImpl#delete(int)}.
	 */
//	@Test
	public void testDelete() {
		System.out.println(newsDao.delete(1));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.NewsDaoImpl#find(int)}.
	 */
//	@Test
	public void testFind() {
		System.out.println(newsDao.find(2));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.NewsDaoImpl#update(com.phn.po.News)}.
	 */
	@Test
	public void testUpdate() {
		News news = newsDao.find(3);
		news.setNewsAuthor("hah");
		System.out.println(newsDao.update(news));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.NewsDaoImpl#list()}.
	 */
//	@Test
	public void testList() {
		System.out.println(newsDao.list());
	}

}
