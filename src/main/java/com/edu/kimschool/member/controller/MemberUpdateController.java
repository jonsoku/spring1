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


@Controller
@RequestMapping("member")
public class MemberUpdateController {


	@Autowired
	MemberDao dao;		//회원 관련 데이터 처리 객체
	
	/**
	 * 회원 정보 수정을 위한 비번 체크 
	 */
	@RequestMapping (value="modifyInfo", method=RequestMethod.GET)
	public String pwCheckForUpdate(HttpSession session, Model model) {
		//세션의 로그인ID로 개인정보를 검색하여 모델에 저장
		MemberEntity memberEntity = (MemberEntity) session.getAttribute("MemberEntity");
		
		 memberEntity = dao.searchMemberById(memberEntity.getMemberId());
	
		model.addAttribute("memberEntity", memberEntity);
		
		return "member/memberPwCheck";
	}

	

	/** updatePWCheck  ajax	 */
	@ResponseBody
	@RequestMapping(value="updatePWCheck", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String loginCheck(String searchId, String searchPW) {
		
		String msg ="인증완료";
		
		MemberEntity memberEntity = dao.searchMemberById(searchId);
		
		// 비밀번호를 입력하지 않은 경우 
		if(searchPW.equals("")) {
			msg="비밀 번호를 입력하세요.";
		}
		
		
		// 비밀번호가 맞지 않는 경우 
		if(memberEntity ==  null || !memberEntity.getPassword().equals(searchPW)) {
			msg="비밀 번호가 틀렸습니다.";
		}
		
		return msg;
	}
	
	
	/**
	 * 회원 정보 수정 폼 보기 = > 비번 인증 후 이동 
	 */
	@RequestMapping (value="memberUpdateForm", method=RequestMethod.POST)
	public String updateForm(HttpSession session, Model model) {
		//세션의 로그인ID로 개인정보를 검색하여 모델에 저장
		MemberEntity memberEntity = (MemberEntity) session.getAttribute("MemberEntity");
		
		memberEntity = dao.searchMemberById(memberEntity.getMemberId());
	
		model.addAttribute("memberEntity", memberEntity);
		return "member/memberUpdateForm";
	}

	
	

	
	// 0420수정 시작 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	// ajax : 닉네임 중복검사
	@ResponseBody
	@RequestMapping(value="nameCheckUpdate", method=RequestMethod.POST,produces="application/text;charset=utf8")
	//json이면 produces="application/json;charset=utf8"
	public String namecheck(String name,HttpSession session) {
		//ID 검색
		String msg ="사용가능합니다.";
		System.out.println(name);
		
		MemberEntity memberEntity = dao.searchMemberByName(name);
		
		System.out.println(msg);

		// name 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if(name.equals("")) {
			msg = "닉네임을 입력하세요.";
			return msg;
		}
		
		// name의 길이가 8~12글자인지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (name.length() < 2 || name.length() > 8) {
			msg = "닉네임은 2~8글자 사이로 입력하세요.";
			return msg;
		}
		
		// 중복 확인 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		
		
		
		String loginId = (String) session.getAttribute("loginId");
		
		
		if(loginId != null) { // 로그인 한 경우 
		

			if(memberEntity != null && loginId.equals(memberEntity.getName()) ) {
				System.out.println(" 정보 수정시 ");
				msg="이미 있는 닉네임입니다. ";
				return msg;
			}
			
		} else {  // 로그인 안 한 경우 
		
			if(memberEntity != null ) {
				System.out.println(" 회원 가입시 ");

				msg="이미 있는 닉네임입니다. ";
				return msg;
			}
		}
		
	return msg;
	}
	
	// 0420수정 끝 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	
	
	
	
	

	// ajax : 비번 검사
	@ResponseBody
	@RequestMapping(value="pw1UpdateCheck", method=RequestMethod.POST,produces="application/text;charset=utf8")
	//json이면 produces="application/json;charset=utf8"
	public String pw1UpdateCheck(String pw, Model model, HttpSession session) {
		
		String nowPw = (String) session.getAttribute("userpw");
		
		System.out.println(nowPw);
		
		
		String msg ="사용가능합니다.";

		// 비밀번호 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if(pw.equals("")) {
			msg = "비밀번호를 입력하세요.";
			return msg;
		}
		

		// 기존의 비밀번호인 경우 다시 입력 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (nowPw.equals(pw)) {
			msg = "기존의 비밀번호는 사용할 수 없습니다.";
			return msg;
		}
		
		// 비밀번호의 길이가 8~12글자인지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (pw.length() <= 7 || pw.length() >= 13) {
			msg = "비밀번호는 8~12자를 사용하세요.";
			return msg;
		}
		

		// ID에 영문과 숫자가 모두 있는지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		int ie = 0;   // id english
		int inum = 0; // id number
		
		for(int i = 0; i < pw.length(); i++) {
			if( pw.charAt(i) <= 'z' && pw.charAt(i) >= 'a' ) {
				ie++;		
			}
			if( pw.charAt(i) <= '9' && pw.charAt(i) >= '0' ) {
				inum++;	
			}
		}
		
		System.out.println(ie + " " + inum);
		
		if (ie == 0 || inum == 0) {
			msg  = "비밀번호는 소문자 영문과 숫자사용해주세요.";
			return msg;
		}
		
	return msg;
	}

	
	
	
	/**
	 * 회원 정보 수정 처리
	 */
	@RequestMapping (value="memberUpdate", method=RequestMethod.POST)
	public String update(MemberEntity memberEntity,	Model model, HttpSession session) {
		

		int result = dao.updateMemberInfo(memberEntity);
		
		// 업데이트 실패 
		if (result != 1) {
			return "member/memberUpdateForm";
		}
	
		
		session.setAttribute("memberEntity", memberEntity);
		session.setAttribute("loginId", memberEntity.getMemberId());
		session.setAttribute("userpw", memberEntity.getPassword());

		
		// 회원 정보 수정 처리 완료
		return "redirect:../";
	}

	/**
	 * 회원탈퇴 폼 보기
	 */
	@RequestMapping (value="delete", method=RequestMethod.GET)
	public String joinForm() {
			
		return "member/memberDelete";
	}
	
	
	/** 회원탈퇴 ajax	 */
	@ResponseBody
	@RequestMapping(value="deleteCheck", method=RequestMethod.POST,produces="application/text;charset=utf8")
	public String deleteCheck(String memberId, String password) {
		
		String msg ="탈퇴가능";
		
		MemberEntity memberEntity = dao.searchMemberById(memberId);
		
		// 데이터베이스에 아이디가 없는경우, 비밀번호가 맞지 않는 경우 
		if(memberEntity ==  null || !memberEntity.getPassword().equals(password)) {
			msg="아이디나 비밀 번호가 잘못되었습니다.";
		}
		return msg;
	}
	
	

	/** 회원탈퇴 처리	 */
	@RequestMapping (value="deleteComplete", method=RequestMethod.POST)
	public String deletecomplete(MemberEntity memberEntity,HttpSession session) {
		
		
		
		
		int result = dao.delMemberInfo(memberEntity);
		
		// 회원탈퇴 실패 
		if (result != 1) {
			return "membere/memberDelete";
		}
		
		// 회원 탈퇴 처리 완료
		
		session.invalidate();	
		
		return "redirect:../";
		
		
	}
	
}
