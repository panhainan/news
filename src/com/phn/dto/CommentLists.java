package com.phn.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-28
 */
public class CommentLists {
	private int commentId;
	private String commentAddress;
	private String commentIP;
	private String commentContent;
	private String commentPublishTime;
	private int commentNewsId;
	
	public CommentLists() {
	}
	public CommentLists(int commentId, String commentAddress,String commentIP,
			String commentContent, Date commentPublishTime, int commentNewsId) {
		super();
		this.commentId = commentId;
		this.commentIP = commentIP;
		this.commentAddress = commentAddress;
		this.commentContent = commentContent;
		this.commentPublishTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm")
		.format(commentPublishTime);
		this.commentNewsId = commentNewsId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentAddress() {
		return commentAddress;
	}
	public void setCommentAddress(String commentAddress) {
		this.commentAddress = commentAddress;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentPublishTime() {
		return commentPublishTime;
	}
	public void setCommentPublishTime(String commentPublishTime) {
		this.commentPublishTime = commentPublishTime;
	}
	public int getCommentNewsId() {
		return commentNewsId;
	}
	public void setCommentNewsId(int commentNewsId) {
		this.commentNewsId = commentNewsId;
	}
	public String getCommentIP() {
		return commentIP;
	}
	public void setCommentIP(String commentIP) {
		this.commentIP = commentIP;
	}
	@Override
	public String toString() {
		return "CommentLists [commentId=" + commentId + ", commentAddress="
				+ commentAddress + ", commentIP=" + commentIP
				+ ", commentContent=" + commentContent
				+ ", commentPublishTime=" + commentPublishTime
				+ ", commentNewsId=" + commentNewsId + "]";
	}
	
}
