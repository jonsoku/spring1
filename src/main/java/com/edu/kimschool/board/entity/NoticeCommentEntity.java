package com.edu.kimschool.board.entity;

import java.io.Serializable;

public class NoticeCommentEntity implements Serializable {
	
	/**
	 * 데이타 베이스 transaction 관리.
	 */
	private static final long serialVersionUID = 1L;
	/** 댓글 번호 */
	private int commentNum;
	/** 게시글 번호 */
	private int boardNum;
	/** 댓글 내용 */
	private String commentContent;
	/** 회원 아이디 */
	private String memberId;
	/** 댓글 입력 시간 */
	private String commentDate;
	/** 글 순서정렬 */
	private int postOrder;
	/** 답글 설정치 */
	private int depth;
	/**삭제 플러그*/
	private int delFlg;
	
	public NoticeCommentEntity() {
		super();
	}
	
	public NoticeCommentEntity(int commentNum, int boardNum, String commentContent, String memberId, int postOrder, int depth) {
		super();
		this.commentNum = commentNum;
		this.boardNum = boardNum;
		this.commentContent = commentContent;
		this.memberId = memberId;
		this.postOrder = postOrder;
		this.depth = depth;
	}
	
	/**
	 * @return commentNum
	 */
	public int getCommentNum() {
		return commentNum;
	}
	/**
	 * @param commentNum セットする commentNum
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	public void setComment_num(int comment_num) {
		this.commentNum = comment_num;
	}
	/**
	 * @return boardNum
	 */
	public int getBoardNum() {
		return boardNum;
	}
	/**
	 * @param boardNum セットする boardNum
	 */
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	
	public void setBoard_num(int board_num) {
		this.boardNum = board_num;
	}
	
	/**
	 * @return commentContent
	 */
	public String getCommentContent() {
		return commentContent;
	}
	/**
	 * @param commentContent セットする commentContent
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public void setComment_content(String comment_content) {
		this.commentContent = comment_content;
	}
	/**
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId セットする memberId
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public void setMember_id(String member_id) {
		this.memberId = member_id;
	}

	public String getCommentDate() {
		return commentDate;
	}
	/**
	 * @param commentDate セットする commentDate
	 */
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	/**
	 * @return postOrder
	 */
	public int getPostOrder() {
		return postOrder;
	}
	/**
	 * @param postOrder セットする postOrder
	 */
	public void setPostOrder(int postOrder) {
		this.postOrder = postOrder;
	}
	/**
	 * @return depth
	 */
	public int getDepth() {
		return depth;
	}
	/**
	 * @param depth セットする depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}
	
	public void setDel_flg(int del_flg) {
		this.delFlg = del_flg;
	}

	@Override
	public String toString() {
		return "NoticeCommentEntity [commentNum=" + commentNum + ", boardNum=" + boardNum + ", commentContent="
				+ commentContent + ", memberId=" + memberId + ", commentDate=" + commentDate + ", postOrder="
				+ postOrder + ", depth=" + depth + ", delFlg=" + delFlg + "]";
	}
	
}
