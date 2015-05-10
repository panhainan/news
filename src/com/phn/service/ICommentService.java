package com.phn.service;

import java.util.Map;

import com.phn.dto.CommentLists;
import com.phn.po.Comment;


/**
 * @author phn
 * @TODO
 * @date 2015-4-28
 */
public interface ICommentService {
	boolean add(Comment comment);
	CommentLists[] list(int newsId);
	Map<String, Object> list(int pageSize, int currentPage);
	boolean delete(int commentId, int newsId);
}
