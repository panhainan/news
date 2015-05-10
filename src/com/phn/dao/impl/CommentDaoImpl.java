package com.phn.dao.impl;

import java.util.List;

import com.phn.dao.ICommentDao;
import com.phn.po.Comment;
import com.phn.po.News;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public class CommentDaoImpl extends JDBCDaoSupport<Comment> implements ICommentDao {

	public int save(Comment comment) {
		String saveSql = "insert into t_comment(commentIP,commentAddress,commentContent,commentPublishTime,commentNewsId) values(?,?,?,?,?)";
		return super.executeInsert(saveSql, comment.getCommentIP(),comment.getCommentAddress(),comment.getCommentContent(),comment.getCommentPublishTime(),comment.getCommentNewsId());
	}

	public int delete(int commentId) {
		String deleteSql ="delete from t_comment where id = ?";
		return super.executeUpdateAndDelete(deleteSql, commentId);
	}
	public void deleteByNewsId(int newsId) {
		String deleteSql ="delete from t_comment where commentNewsId = ?";
		super.executeUpdateAndDelete(deleteSql, newsId);
	}
	public Comment find(int commentId) {
		String findSql = "select * from t_comment where id =?";
		return super.executeGet(findSql, Comment.class, commentId);
	}

	public int update(Comment comment) {
		String updateSql = "update t_comment set commentIP=?,commentAddress=?,commentContent=?,commentPublishTime=?,commentNewsId=? where id=?";
		return super.executeUpdateAndDelete(updateSql, comment.getCommentIP(),comment.getCommentAddress(),comment.getCommentContent(),comment.getCommentPublishTime(),comment.getCommentNewsId(),comment.getId());
	}

	public List<Comment> list() {
		String listSql = "select * from t_comment";
		return super.executeList(listSql, Comment.class);
	}

	public List<Comment> list(int newsId) {
		String listSql = "select * from t_comment where commentNewsId=? order by id desc";
		return super.executeList(listSql, Comment.class,newsId);
	}

	public int countRow() {
		String getCountSql = "select count(*) from t_comment";
		return super.getCountRow(getCountSql);
	}

	public List<Comment> list(int startRecord, int pageSize) {
		String listSql = "select * from t_comment  order by id desc limit ?,?";
		return super.executeList(listSql,Comment.class,startRecord,pageSize);
	}

	

}
