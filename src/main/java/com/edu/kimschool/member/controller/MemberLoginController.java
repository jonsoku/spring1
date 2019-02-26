package com.edu.kimschool.member.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.kimschool.member.dao.MemberDao;
import com.edu.kimschool.member.entity.MemberEntity;
import com.edu.kimschool.member.service.MemberEmailFormCheckLogic;
import com.edu.kimschool.member.service.MemberSecurityNumberLogic;

/**
 * 회원 로그인, 로그아웃 처리 콘트롤러
 */
@Controller
@RequestMapping("member")
public class MemberLoginController {

	String masterCode = "";

	@Autowired
	MemberDao dao;

	@Autowired
	MemberSecurityNumberLogic memberSecurityNumberLogic;

	@Autowired
	MemberEmailFormCheckLogic memberEmailFormCheckLogic;
	
	/**
	 * 로그인 폼 보기
	 */
	@RequestMapping(value = "memberLogin", method = RequestMethod.GET)
	public String loginForm() {
		return "member/memberLogin";
	}

	/** 로그인 ajax */
	@ResponseBody
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	public String loginCheck(String searchId, String searchPW) {

		String msg = "";

		MemberEntity memberEntity = dao.searchMemberById(searchId);

		// TODO 삭제 시 처리
		if (memberEntity !=null && memberEntity.getdelFlg() == 1) {
			msg = "아이디가 잘못되었습니다.";
		}

		// 데이터베이스에 아이디가 없는경우, 비밀번호가 맞지 않는 경우
		if (memberEntity == null || !memberEntity.getPassword().equals(searchPW)) {
			msg = "아이디나 비밀 번호가 잘못되었습니다.";

		}

		return msg;
	}

	/** 로그인 처리 */
	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public String login(String memberId, String password, Model model, HttpSession session) {

		MemberEntity memberEntity = dao.searchMemberById(memberId);
		System.out.println(memberEntity);
		session.setAttribute("memberEntity", memberEntity);
		session.setAttribute("loginId", memberEntity.getMemberId());

		return "redirect:../";

	}

	/**
	 * 로그아웃 처리
	 * 
	 * @param session HttpSession객체
	 */
	@RequestMapping(value = "memberLogout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("memberEntity", "");
		session.setAttribute("loginId", "");
		session.invalidate();
		return "redirect:../";
	}

	/**
	 * 아이디 찾기 폼 보기
	 */
	@RequestMapping(value = "memberIdfind", method = RequestMethod.GET)
	public String idfindForm() {
		return "member/memberIdfindMail";
	}

	// ajax id 찾기 이메일 발송 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@ResponseBody
	@RequestMapping(value = "idFindMail", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	public String idFindMail(String email) {
		// ID 검색
		String msg = "";

		MemberEntity memberEntity = dao.searchMemberByMail(email);
		
		//이메일 폼형태 체크
		msg =memberEmailFormCheckLogic.emailFormCheck(email);


		if (memberEntity == null) {
			msg = "이메일로 가입한 이력이 없습니다.";
			return msg;
		}
		
		if (msg.equals("")) {			
			msg = memberEntity.getMemberId();
		}

		return msg;
	}

	/**
	 * 비번 찾기 폼 보기
	 */
	@RequestMapping(value = "memberPwFind", method = RequestMethod.GET)
	public String pwfindForm() {

		return "member/memberPwFindMail";
	}

	// ajax 비번 찾기 이메일 발송 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	@ResponseBody
	@RequestMapping(value = "pwFindMail", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	public String pwFindMail(String memberId, String email, String code) {
		// ID 검색
		String msg = "";

		if (memberId.equals("")) {
			msg = "memberId을 입력하세요.";
			return msg;
		}

		if (email.equals("")) {
			msg = "email을 입력하세요.";
			return msg;
		}

		MemberEntity memberEntity = dao.searchMemberById(memberId);

		if (memberEntity == null) {
			msg = "가입하지 않은 아이디입니다.";
			return msg;
		}

		memberEntity = dao.searchMemberByMail(email);

		if (memberEntity == null) {
			msg = "가입하지 않은 이메일입니다.";
			return msg;
		}

		if (!memberEntity.getEmail().equals(email)) {
			msg = "아이디와 이메일이 일치하지 않습니다.";
			return msg;
		}
	
		//인증번호 생성
		msg = memberSecurityNumberLogic.securityNumberMake();

		return msg;
	}

	// ajax 인증번호 확인 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	@ResponseBody
	@RequestMapping(value = "pwCodeCheck", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String pwemailCheck(String code) {

		String msg = "인증번호를 확인하였습니다.";

		// 인증번호 입력 했는지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (code.equals("")) {
			msg = "인증번호 입력하세요.";
			return msg;
		}

		// 인증번호 길이가 10글자인지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (code.length() != 10) {
			msg = "인증번호 10자 모두 입력하세요.";
			return msg;
		}

		// 인증번호가 일치하는지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (!masterCode.equals(code)) {
			msg = "인증번호가 일치하지 않습니다.";
			return msg;
		}
		return msg;
	}

	@RequestMapping(value = "codeModify", method = RequestMethod.POST)
	public String pwCodeCheck(Model model, String memberId) {
		MemberEntity memberEntity = dao.searchMemberById(memberId);
		model.addAttribute("memberEntity", memberEntity);
		return "member/memberUpdateForm";
	}

}
