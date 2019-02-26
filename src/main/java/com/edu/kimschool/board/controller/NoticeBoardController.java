package com.edu.kimschool.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.kimschool.board.dao.NoticeDao;
import com.edu.kimschool.board.entity.NoticeBoardEntity;
import com.edu.kimschool.board.entity.NoticeCommentEntity;
import com.edu.kimschool.common.constant.ConstEnum;
import com.edu.kimschool.util.PageNavigatorUtil;

// @RequestMapping("board"): View단 내, board폴더에 관련된 맵핑 설정
// @RequestMapping("board"): View側からの, boardファイルに繋がるマッピングの設定
@Controller
@RequestMapping("board")
public class NoticeBoardController {

	@Autowired
	NoticeDao noticeDao;

	// Get방식으로 noticeBordList.jsp파일로 맵핑
	// @RequestParam : value값에 대한 정보를 가져온다.(defaultValue :아무 정보가 없을때, 디폴트값을 설정)
	// Model : view단에 정보를 보낼 시, Model안에 넣어서 보낸다.
	@RequestMapping(value = "noticeBoardList", method = RequestMethod.GET)
	public String noticeBordList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "searchText", defaultValue = "") String searchText, Model model,
			HttpSession session) {

		// 전체 글 개수　全体のコンテンツ数
		int total = noticeDao.noticeBoardTotal(searchText);

		// 페이징 처리
		PageNavigatorUtil navi = new PageNavigatorUtil(ConstEnum.Page.TEN_CONTENTS_PER_PAGE.getValue(),
				ConstEnum.Page.FIVE_PAGES_PER_GROUP.getValue(), page, total);

		// 전체 게시글을 들고와서, rowbounds를 통해 10개씩 글을 분할하여 리스트를 취득
		List<NoticeBoardEntity> noticeBoardList = noticeDao.noticeBoardInfoList(searchText, navi.getStartRecord(),
				navi.getCountPerPage());

		// View단에 보낼 정보를 설정
		if (CollectionUtils.isNotEmpty(noticeBoardList)) {

			model.addAttribute("noticeBoardList", noticeBoardList);
		}
		model.addAttribute("navi", navi);
		model.addAttribute("searchText", searchText);

		// noticeBoardList.jsp가 있는 화면 주소값을 전달
		return "board/noticeBoardList";
	}

	// 글쓰기관련 맵핑 컨트롤러 생성
	@RequestMapping(value = "noticeBoardWrite", method = RequestMethod.GET)
	public String noticeBoardWrite() {
		return "board/noticeBoardWrite";
	}

	// 글쓰는 도중 작성한 데이터 받아오기 관련 맵핑 컨트롤러 생성
	// HttpSession 인터넷을 완전히 끄지 않는 한 일정시간동안 기본정보를 저장(로그인 정보의 구축을 위해 필요)
	@RequestMapping(value = "noticeBoardWrite", method = RequestMethod.POST)
	public String noticeBoardWriteData(String title, String content, Model model, HttpSession session) {

		// 글을 생성하기 전에 최신글에 대한 위치(postOrder)를 관리하기 위해 새로운 글을 postOrder처리하기 위해 기존 글위치들을 +1
		if (noticeDao.selectNoticeBoardList().size() != 0) {
			noticeDao.renewPostOrderBoard();
		}
		// 세션에서 로그인한 사용자의 아이디를 읽어서 Board객체의 작성자 정보에 세팅
		String memberId = (String) session.getAttribute("loginId");

		NoticeBoardEntity noticeBoardEntity = new NoticeBoardEntity(0, memberId, title, content, 0, 0);
		noticeDao.noticeBoardInsert(noticeBoardEntity);
		return "redirect:noticeBoardList";
	}

	// 해당 게시글 화면으로 이동
	@RequestMapping(value = "noticeBoardShowOne", method = RequestMethod.GET)
	public String noticereadForm(int boardNum, Model model, HttpSession session) {

		NoticeBoardEntity noticeBoard = noticeDao.oneNoticeBoardInfo(boardNum);

		// 게시글이 없을 경우 게시글 목록화면으로 보내줌
		if (noticeBoard == null) {
			return "redirect:noticeBoardList";
		}

		// 해당 게시글의 코멘트를 들고 옴
		List<NoticeCommentEntity> noticeCommentList = noticeDao.listNoticeComment(boardNum);

		model.addAttribute("noticeCommentList", noticeCommentList);
		model.addAttribute("noticeBoard", noticeBoard);
		return "board/noticeBoardShowOne";
	}

	@ResponseBody
	@RequestMapping(value = "noticeList", method = RequestMethod.POST)
	public List<NoticeCommentEntity> noticelist(int boardNum, Model model) {

		List<NoticeCommentEntity> noticereplylist = noticeDao.listNoticeComment(boardNum);

		// model.addAttribute("noticereplylist", noticereplylist);

		return noticereplylist;
	}

	@RequestMapping(value = "noticeDelete", method = RequestMethod.GET)
	public String delete(int boardNum, HttpSession session) {

		// pos 찾기 및 삭제할 게시판 내용 찾기
		NoticeBoardEntity noticeBoardEntity = noticeDao.oneNoticeBoardInfo(boardNum);

		noticeDao.noticeBoardDel(noticeBoardEntity);

		return "redirect:noticeBoardList";
	}

	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public String noticeupdateForm(int boardNum, HttpSession session, Model model) {
		NoticeBoardEntity noticeboardEntity = noticeDao.oneNoticeBoardInfo(boardNum);
		model.addAttribute("noticeBoard", noticeboardEntity);
		return "board/noticeBoardUpdate";
	}

	// 글 수정

	@RequestMapping(value = "noticeBoardUpdate", method = RequestMethod.POST)
	public String noticeupdate(/* int boardNum, String title, String text, */NoticeBoardEntity noticeBoardEntity,
			HttpSession session, Model model) {

		System.out.println("수정 버튼 눌림");
		System.out.println(noticeBoardEntity);
		String loginId = (String) session.getAttribute("loginId");

		noticeBoardEntity.setMemberId(loginId);
		noticeDao.noticeBoardUpdate(noticeBoardEntity);

		return "redirect:noticeBoardList?boardNum=" + noticeBoardEntity.getBoardNum();
	}

	// ajax
	@ResponseBody
	@RequestMapping(value = "noticeReplyWrite", method = RequestMethod.POST)
	public int replyWrited(int boardNum, String commentContent, HttpSession session, Model model) {
		System.out.println("들어옴");

		// 세션에서 로그인한 사용자의 아이디를 읽어서 Reply객체의 작성자 정보에 세팅

		if (noticeDao.listNoticeComment(boardNum).size() > 0) {
			noticeDao.renewPostOrderComment();
		}
		System.out.println("찾기");

		String loginId = (String) session.getAttribute("loginId");

		NoticeCommentEntity noticeCommentEntity = new NoticeCommentEntity();
		noticeCommentEntity.setMemberId(loginId);
		noticeCommentEntity.setBoardNum(boardNum);
		noticeCommentEntity.setCommentContent(commentContent);
		noticeCommentEntity.setDepth(0);
		noticeCommentEntity.setPostOrder(0);
		System.out.println(noticeCommentEntity);
		// 리플 정보를 DB에 저장
		int result = noticeDao.insertNoticeComment(noticeCommentEntity);

		// 읽던 게시글로 되돌아 감
		return result;
	}

	/**
	 * 리플 삭제 ajax
	 */
	@ResponseBody
	@RequestMapping(value = "noticeCommentDelete", method = RequestMethod.GET)
	public int deleteReply(int commentNum, int boardNum, HttpSession session) {

		//////////////////////////////
		// pos 찾기 및 삭제할 게시판 내용 찾기
		NoticeCommentEntity noticeCommentEntity = noticeDao.oneNoticeCommnetInfo(commentNum);

		// 삭제할 글에 대한 아이디와 로그인 아이디와 같은지
		int result = noticeDao.delNoticeComment(noticeCommentEntity);

		return result;
	}

	/**
	 * 리플 수정 처리 //ajax
	 */
	@ResponseBody
	@RequestMapping(value = "noticeCommentEdit", method = RequestMethod.POST)
	public int replyEdit(int commentNum, String commentContent, HttpSession session) {

		// 삭제할 리플 정보와 본인 글인지 확인할 로그인아이디
		String loginId = (String) session.getAttribute("loginId");
		NoticeCommentEntity noticeCommentEntity = new NoticeCommentEntity();
		noticeCommentEntity.setMemberId(loginId);
		noticeCommentEntity.setCommentNum(commentNum);
		noticeCommentEntity.setCommentContent(commentContent);
		// 리플 수정 처리
		int result = noticeDao.updateNoticeComment(noticeCommentEntity);
		// 원래의 글읽기 화면으로 이동
		return result;
	}

	// 리플의리플 작성
	// ajax
	@ResponseBody
	@RequestMapping(value = "noticeCommentReInsert", method = RequestMethod.POST)
	public int replyInsert(int commentNum, int boardNum, String commentContent, HttpSession session) {

		String loginId = (String) session.getAttribute("loginId");
		NoticeCommentEntity noticeCommentEntity = noticeDao.oneNoticeCommnetInfo(commentNum);
		noticeDao.setChildComment(noticeCommentEntity.getPostOrder());

		System.out.println(noticeCommentEntity);
		NoticeCommentEntity renoticeCommentEntity = new NoticeCommentEntity();
		renoticeCommentEntity.setBoardNum(boardNum);
		renoticeCommentEntity.setCommentNum(commentNum);
		renoticeCommentEntity.setCommentContent(commentContent);
		renoticeCommentEntity.setMemberId(loginId);
		renoticeCommentEntity.setPostOrder(noticeCommentEntity.getPostOrder() + 1);
		renoticeCommentEntity.setDepth(noticeCommentEntity.getDepth() + 1);
		int result = noticeDao.insertNoticeComment(renoticeCommentEntity);

		return result;
	}

}
