package com.edu.kimschool.board.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.kimschool.board.entity.NoticeBoardEntity;
import com.edu.kimschool.board.entity.NoticeCommentEntity;

// mybatis 연결할 때 사용하는 어노테이션
// mybatisと連動させるため、使用するアノテーション
@Repository
public class NoticeDao {

	@Autowired
	SqlSession sqlSession;

	public int noticeBoardInsert(NoticeBoardEntity noticeBoardEntity) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.noticeBoardInsert(noticeBoardEntity);
		return result;
	}

	public int noticeBoardTotal(String searchText) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.noticeBoardTotal(searchText);
		return result;
	}

	/**
	 * 검색 게시판 메소드 searchText 검색하고자하는 내용 startRecord 전체 레코드 중 현재 페이지의 첫 글의 위치값(한페이지가
	 * 10개 일시 1,11,21) countPerPage 페이지 당 글 목록수
	 */
	public List<NoticeBoardEntity> noticeBoardInfoList(String searchText, int startRecord, int countPerPage) {
		// sql에서 가져온 내용을 인터페이스상 존재하는 메소드에 입력
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		// 첫글위치값과 페이지당 글목록수를 파라메터로 페이징처리
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		// 검색내용값과 페이징처리값을 인수로 리스트완성
		List<NoticeBoardEntity> noticeList = mapper.noticeBoardInfoList(searchText, rb);

		return noticeList;
	}

	public int noticeBoardUpdate(NoticeBoardEntity noticeBoardEntity) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.noticeBoardUpdate(noticeBoardEntity);
		return result;
	}

	public List<NoticeBoardEntity> selectNoticeBoardList() {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		List<NoticeBoardEntity> resultList = mapper.selectNoticeBoardList();
		return resultList;
	}

	public int noticeBoardDel(NoticeBoardEntity noticBoardEntity) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.noticeBoardDel(noticBoardEntity);
		return result;
	}

	public int delPostOrder(int postOrder) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.delPostOrder(postOrder);
		return result;
	}

	public int renewPostOrderBoard() {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.renewPostOrderBoard();
		return result;
	}

	/**
	 * 한개 단위의 게시글 정보취득 메소드 boardNum 게시글번호
	 */
	public NoticeBoardEntity oneNoticeBoardInfo(int boardNum) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		// 한개 단위의 게시글 정보취득
		NoticeBoardEntity noticeBoard = mapper.oneNoticeBoardInfo(boardNum);
		// 게시글 번호에 조회수 증감 반영
		mapper.boardCount(boardNum);

		return noticeBoard;
	}

	public NoticeBoardEntity searchPosNoticeBoard(int postOrder) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		NoticeBoardEntity resultEntity = mapper.searchPosNoticeBoard(postOrder);
		return resultEntity;
	}

	public int insertNoticeComment(NoticeCommentEntity noticeCommentEntity) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.insertNoticeComment(noticeCommentEntity);
		return result;
	}

	public List<NoticeCommentEntity> listNoticeComment(int boardNum) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		List<NoticeCommentEntity> resultList = mapper.listNoticeComment(boardNum);
		return resultList;
	}

	public int renewPostOrderComment() {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.renewPostOrderComment();
		return result;
	}

	public NoticeCommentEntity oneNoticeCommnetInfo(int commentNum) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		NoticeCommentEntity resultEntity = mapper.oneNoticeCommnetInfo(commentNum);
		return resultEntity;
	}

	public int setChildComment(int postOrder) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.setChildComment(postOrder);
		return result;
	}

	public int delNoticeComment(NoticeCommentEntity noticeCommentEntity) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.delNoticeComment(noticeCommentEntity);
		return result;
	}

	public int delChildComment(int postOrder) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.delChildComment(postOrder);
		return result;
	}

	public int updateNoticeComment(NoticeCommentEntity noticeCommentEntity) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		int result = mapper.updateNoticeComment(noticeCommentEntity);
		return result;
	}

	public NoticeCommentEntity searchPosNoticeCommnet(int postOrder) {
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		NoticeCommentEntity resultEntity = mapper.searchPosNoticeComment(postOrder);
		return resultEntity;
	}

}
