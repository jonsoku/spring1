package com.edu.kimschool.board.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.edu.kimschool.board.entity.NoticeBoardEntity;
import com.edu.kimschool.board.entity.NoticeCommentEntity;

/**
 * 공지사항 게시판 인터페이스
 */

public interface NoticeMapper {

	// 1.게시글 전체 구성
	// 게시글 추가 메소드
	public int noticeBoardInsert(NoticeBoardEntity noticBoardEntity);

	// 조회수 증감 메소드
	public int boardCount(int boardNum);

	// 검색한 게시글 수 메소드
	public int noticeBoardTotal(String searchText);

	// 검색한 게시글 정보를 가져오는 메소드
	// RowBounds : 페이징 처리를 위한 한 페이지당 글 게시수 설정 클래스
	public List<NoticeBoardEntity> noticeBoardInfoList(String searchText, RowBounds rb);

	// 게시글 수정 메소드
	public int noticeBoardUpdate(NoticeBoardEntity noticBoardEntity);

	// 공지게시판Bean을 담은 리스트 반환형 메소드
	public List<NoticeBoardEntity> selectNoticeBoardList();

	// 게시글 삭제 반환형 메소드
	public int noticeBoardDel(NoticeBoardEntity noticBoardEntity);

	// 글 순서정렬 번호로 연관된 게시글 지우는 메소드
	public int delPostOrder(int postOrder);

	// 게시물등록(insert)후, 게시글 재정렬(글 순서정렬 번호) 메소드
	public int renewPostOrderBoard();

	// 한개 단위의 게시글 정보취득 메소드
	public NoticeBoardEntity oneNoticeBoardInfo(int boardNum);

	// postorder값을 통해 게시글 찾기
	public NoticeBoardEntity searchPosNoticeBoard(int postOrder);

	// 2.게시글 내 댓글구성
	// 댓글 등록 메소드
	public int insertNoticeComment(NoticeCommentEntity noticeCommentEntity);

	// 한 게시물의 댓글목록
	public List<NoticeCommentEntity> listNoticeComment(int boardNum);

	// 댓글등록(insert)후, 게시글 재정렬(글 순서정렬 번호) 메소드
	public int renewPostOrderComment();

	// 한 개 단위의 댓글 정보취득 메소드
	public NoticeCommentEntity oneNoticeCommnetInfo(int commentNum);

	// 댓글(부모)에 대한 re댓글(자식)을 등록하기 위해 자식댓글의 위치선정 메소드(부모댓글위치+1)
	public int setChildComment(int postOrder);

	// 댓글 삭제 메소드
	public int delNoticeComment(NoticeCommentEntity noticeCommentEntity);

	// 삭제된 부모댓글에 대한 자식댓글삭제 메소드
	public int delChildComment(int postOrder);

	// 댓글 수정
	public int updateNoticeComment(NoticeCommentEntity noticeCommentEntity);

	// commentNum값을 통해 댓글찾기
	public NoticeCommentEntity searchPosNoticeComment(int postOrder);
}
