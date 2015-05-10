package com.phn.po;

import java.util.Date;

/**
 * @author phn
 * @TODO JavaBean Comment : It is used to save the user comments
 * @date 2015-4-25
 */
public class Comment {
	private int id;
	private String commentIP;
	private String commentAddress;
	private String commentContent;
	private Date commentPublishTime;
	/**
	 * This property is used to express the comment is what news
	 */
	private int commentNewsId;

	public Comment() {
		this.commentPublishTime = new Date();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentIP() {
		return commentIP;
	}

	public void setCommentIP(String commentIP) {
		this.commentIP = commentIP;
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

	public Date getCommentPublishTime() {
		return commentPublishTime;
	}

	public void setCommentPublishTime(Date commentPublishTime) {
		this.commentPublishTime = commentPublishTime;
	}

	public int getCommentNewsId() {
		return commentNewsId;
	}

	public void setCommentNewsId(int commentNewsId) {
		this.commentNewsId = commentNewsId;
	}

	@Override
	public String toString() {
		return "Comment \n[id=" + id + ", commentIP=" + commentIP
				+ ", commentAddress=" + commentAddress + ", commentContent="
				+ commentContent + ", commentPublishTime=" + commentPublishTime
				+ ", commentNewsId=" + commentNewsId + "]";
	}

}
