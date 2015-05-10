package com.phn.dao;

import java.util.List;

import com.phn.po.Comment;

/**
 * @author phn
 * @TODO
 * @date 2015-4-25
 */
public interface ICommentDao {
	int save(Comment comment);

	int delete(int commentId);

	Comment find(int commentId);

	int update(Comment comment);

	List<Comment> list();

	List<Comment> list(int newsId);

	void deleteByNewsId(int newsId);

	int countRow();

	List<Comment> list(int startRecord, int pageSize);
}
