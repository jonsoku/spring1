package com.edu.kimschool.grade.dto;

import java.io.Serializable;

public class NoticeBoardEntity implements Serializable {

	/**
	 * 데이타 베이스 transaction 관리.
	 */
	private static final long serialVersionUID = 1L;

	/** 게시판 글번호 */
	private int boardNum;

	/** 작성자 */
	private String memberId;

	/** 글 제목 */
	private String title;

	/** 글 내용 */
	private String content;

	/** 작성일 */
	private String regDate;

	/** 조회수 */
	private int boardCount;

	/** 글 순서정렬 */
	private int postOrder;

	/** 답글 설정치 */
	private int depth;
	
	/** 삭제 플러그*/
	private int delFlg;

	public NoticeBoardEntity() {

	}
	
	/**
	 * DB에 저장할 정보를 모으는 생성자 
	 * boardNum 게시판 글번호 
	 * memberId 작성자 
	 * title 제목 
	 * content 내용
	 * postOrder 글 순서정렬값 
	 * depth 답글 설정값
	 */
	public NoticeBoardEntity(int boardNum, String memberId, String title, String content,
			 int postOrder, int depth) {
		this.boardNum = boardNum;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.postOrder = postOrder;
		this.depth = depth;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public void setBoard_num(int board_num) {
		this.boardNum = board_num;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public void setMember_id(String member_id) {
		this.memberId = member_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	
	public void setBoard_count(int board_count) {
		this.boardCount = board_count;
	}

	public int getPostOrder() {
		return postOrder;
	}

	public void setPostOrder(int postOrder) {
		this.postOrder = postOrder;
	}

	public int getDepth() {
		return depth;
	}

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
		return "NoticeBoardEntity [boardNum=" + boardNum + ", memberId=" + memberId + ", title=" + title + ", content="
				+ content + ", regDate=" + regDate + ", boardCount=" + boardCount + ", postOrder=" + postOrder
				+ ", depth=" + depth + ", delFlg=" + delFlg + "]";
	}

}
