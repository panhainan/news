package com.phn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.phn.dao.ICommentDao;
import com.phn.dao.INewsDao;
import com.phn.dao.impl.CommentDaoImpl;
import com.phn.dao.impl.NewsDaoImpl;
import com.phn.dto.CommentLists;
import com.phn.dto.Page;
import com.phn.po.Comment;
import com.phn.service.ICommentService;

/**
 * @author phn
 * @TODO
 * @date 2015-4-28
 */
public class CommentServiceImpl implements ICommentService {

	ICommentDao commentDao = new CommentDaoImpl();
	INewsDao newsDao = new NewsDaoImpl();

	public boolean add(Comment comment) {
		if (1 == newsDao.changeCommentCount(comment.getCommentNewsId(), "plus")) {
			if (commentDao.save(comment) >= 1) {
				return true;
			}
		}
		return false;
	}

	public CommentLists[] list(int newsId) {
		List<Comment> lists = commentDao.list(newsId);
		CommentLists[] commentLists = new CommentLists[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			commentLists[i] = new CommentLists(lists.get(i).getId(), lists.get(
					i).getCommentAddress(), lists.get(i).getCommentIP(), lists
					.get(i).getCommentContent(), lists.get(i)
					.getCommentPublishTime(), lists.get(i).getCommentNewsId());
		}
		return commentLists;
	}

	public Map<String, Object> list(int pageSize, int current) {
		Page pageBean = new Page();
		int allRecords = commentDao.countRow();
		int totalPages = pageBean.calculateTotalPage(pageSize, allRecords);
		int currentPage = pageBean.judgeCurrentPageIsLegal(current, totalPages);
		if (currentPage == 0) {
			return null;
		}
		int startRecord = pageBean.calculateCurrentPageStartRecord(pageSize,
				currentPage);
		pageBean.init(allRecords, currentPage, pageSize, totalPages);
		List<Comment> comments = commentDao.list(startRecord, pageSize);
		CommentLists[] commentList = new CommentLists[comments.size()];
		for (int i = 0; i < comments.size(); i++) {
			commentList[i] = new CommentLists(comments.get(i).getId(), comments
					.get(i).getCommentAddress(),
					comments.get(i).getCommentIP(), comments.get(i)
							.getCommentContent(), comments.get(i)
							.getCommentPublishTime(), comments.get(i)
							.getCommentNewsId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listCommentList", commentList);
		map.put("pageBean", pageBean);
		return map;
	}

	public boolean delete(int commentId, int newsId) {
		if (1 == newsDao.changeCommentCount(newsId, "minus")) {
			if (1 == commentDao.delete(commentId)) {
				return true;
			}
		}
		return false;
	}

}
