package com.phn.dao;


import org.junit.Test;

import com.phn.dao.impl.CommentDaoImpl;
import com.phn.po.Comment;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public class CommentDaoImplTest {

	private static ICommentDao commentDao = new CommentDaoImpl();

	/**
	 * Test method for
	 * {@link com.phn.dao.impl.CommentDaoImpl#save(com.phn.po.Comment)}.
	 */
//	 @Test
	public void testSave() {
		Comment comment = new Comment();
		comment.setCommentIP("10.28.23.159");
		comment.setCommentContent("好惊人的消息啊");
		comment.setCommentNewsId(3);
		// 这里其实需要同时更新新闻表的
		comment.setCommentAddress("湖南长沙");
		System.out.println(commentDao.save(comment));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.CommentDaoImpl#delete(int)}.
	 */
	// @Test
	public void testDelete() {
		// 这里其实需要同时更新新闻表的
		System.out.println(commentDao.delete(1));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.CommentDaoImpl#find(int)}.
	 */
//	@Test
	public void testFind() {
		// 这里其实需要同时更新新闻表的
		System.out.println(commentDao.find(2));
	}

	/**
	 * Test method for
	 * {@link com.phn.dao.impl.CommentDaoImpl#update(com.phn.po.Comment)}.
	 */
//	@Test
	public void testUpdate() {
		Comment comment = commentDao.find(2);
		comment.setCommentContent("假的吧！");
		System.out.println(commentDao.update(comment));
	}

	/**
	 * Test method for {@link com.phn.dao.impl.CommentDaoImpl#list()}.
	 */
	@Test
	public void testList() {
		System.out.println(commentDao.list());
	}

}
