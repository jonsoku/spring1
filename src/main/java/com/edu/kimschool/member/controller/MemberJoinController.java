package com.edu.kimschool.member.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.kimschool.common.constant.ConstEnum;
import com.edu.kimschool.common.constant.MessageId;
import com.edu.kimschool.member.dao.MemberDao;
import com.edu.kimschool.member.entity.MemberEntity;
import com.edu.kimschool.member.service.MemberEmailFormCheckLogic;
import com.edu.kimschool.member.service.MemberIdFormCheckLogic;
import com.edu.kimschool.member.service.MemberNicNameFormCheckLogic;
import com.edu.kimschool.member.service.MemberPwFormCheckLogic;
import com.edu.kimschool.member.service.MemberSecurityNumberLogic;

@Controller
@RequestMapping("member")
public class MemberJoinController {
	
	String masterCode = ConstEnum.StringConst.EMPTY.getValue();

	@Autowired
	MemberDao memberDao;

	@Autowired
	MemberSecurityNumberLogic memberSecurityNumberLogic;

	@Autowired
	MemberPwFormCheckLogic memberPwCheckLogic;

	@Autowired
	MemberIdFormCheckLogic memberIdFormCheckLogic;

	@Autowired
	MemberEmailFormCheckLogic memberEmailFormCheckLogic;

	@Autowired
	MemberNicNameFormCheckLogic memberNicNameFormCheckLogic;

	/**
	 * 회원가입상에 a태그에 있는 url값을 받아서 jsp파일로 보내준다.
	 * 
	 * @return
	 */
	@RequestMapping(value = "memberJoin", method = RequestMethod.GET)
	public String sendMemberJoin() {

		return "member/memberJoin";
	}

	/**
	 * 회원가입상에 jsp파일에서 form태그의 post형태로 데이터 입력한 것을 받아온다.
	 * 
	 * @return
	 */
	@RequestMapping(value = "memberJoin", method = RequestMethod.POST)
	public String receiveMemberJoin(MemberEntity memberEntity) {

		// insert결과 초기화
		int result = 0;

		boolean insertFlag = false;

		int delResult = 0;
		
		if (memberEntity != null) {

			
			MemberEntity beforeMember = memberDao.searchMemberById(memberEntity.getMemberId());

			if (beforeMember == null) {
				insertFlag = true;

			} else {
				if (beforeMember.getdelFlg() == 1) {
					// 논리삭제 처리
					delResult = memberDao.delMemberData(beforeMember);
					if (delResult ==1) {						
						insertFlag = true;
					}
				}
			}
		}

		if (insertFlag) {
			result = memberDao.insertMemberInfo(memberEntity);
		}

		if (result != 1 || !insertFlag) {
			// TODO : 실패할 경우 메시지 입력
			return "member/memberJoin";
		}
		// 회원가입 완료 후 로그인 페이지로 이동
		return "redirect:../member/memberLogin";
	}

	// ajax : ID 중복 검사
	@ResponseBody
	@RequestMapping(value = "idCheck", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String idcheck(String searchId) {

		String msg = MessageId.Common.USE_POSSIBLE.getMessage();

		// ID를 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		msg = memberIdFormCheckLogic.idFomcheck(searchId);

		// ID 검색
		MemberEntity memberEntity = memberDao.searchMemberById(searchId);

		// 중복 확인 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

		if (!ObjectUtils.isEmpty(memberEntity) && memberEntity.getdelFlg() == 0) {
			msg = MessageId.Id.ALREADY_EXISTS.getMessage();
			return msg;
		}

		return msg;
	}

	// ajax : 비번 검사
	@ResponseBody
	@RequestMapping(value = "pwCheck1", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String pwCheck1(String pw) {

		String msg = memberPwCheckLogic.pwFormCheck(pw);

		return msg;
	}

	// ajax : 비번확인 검사 : 회원가입할 때, 정보 수정할 때 같이 쓰는 부분 !

	@ResponseBody
	@RequestMapping(value = "pwCheck2", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String pw2Check(String pw, String pw2) {
		// ID 검색
		String msg = MessageId.Common.USE_POSSIBLE.getMessage();

		if (StringUtils.isEmpty(pw2)) {
			msg = MessageId.Pwd.EMPTY.getMessage();
			return msg;
		}

		// 비밀번호와 비밀번호 확인이 일치하는지 여부 ㅡㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡ
		if (!pw.equals(pw2)) {
			msg = MessageId.Pwd.NOT_EQUAL.getMessage();
			return msg;
		}

		return msg;
	}

	// ajax : 닉네임 중복검사
	@ResponseBody
	@RequestMapping(value = "nameCheck", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String namecheck(String searchName) {
		// ID 검색
		String msg = MessageId.Common.USE_POSSIBLE.getMessage();

		// 닉네임 형식 체크
		msg = memberNicNameFormCheckLogic.nicNameFomcheck(searchName);

		// 닉네임으로 해당 회원정보 검색
		MemberEntity memberEntity = memberDao.searchMemberByName(searchName);

		// 중복 확인 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

		if (!ObjectUtils.isEmpty(memberEntity)) {
			msg = MessageId.NickName.ALREADY_EXISTS.getMessage();
			return msg;
		}

		return msg;
	}

	// ajax : 이메일 검사

	@ResponseBody
	@RequestMapping(value = "emailSend", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String emailSend(String email) {
		// 초기화
		masterCode = ConstEnum.StringConst.EMPTY.getValue();

		String msg = MessageId.Authentication.SEND_BY_MAIL.getMessage();

		// 이메일 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (StringUtils.isEmpty(email)) {
			msg = MessageId.Mail.EMPTY.getMessage();
			return msg;
		}
		System.out.println("이메일 입력했는지검사 ");
		// 이메일 형식 체크
		String result = memberEmailFormCheckLogic.emailFormCheck(email);

		System.out.println("형식체크 완료");
		if (StringUtils.isNotEmpty(result)) {
			msg = result;
		}

		// 이메일 중복 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		MemberEntity memberEntity = memberDao.searchMemberByMail(email);

		if (!ObjectUtils.isEmpty(memberEntity)) {
			msg = MessageId.Mail.ALREADY_EXISTS.getMessage();
			return msg;
		}

		// 인증키 만들기
		masterCode = memberSecurityNumberLogic.securityNumberMake();
		return masterCode;
	}

	// ajax : 인증번호 검사

	@ResponseBody
	@RequestMapping(value = "emailCheck", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	// json이면 produces="application/json;charset=utf8"
	public String emailCheck(String code) {

		String msg = MessageId.Authentication.CHECKED.getMessage();

		// 인증번호 입력 했는지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (StringUtils.isEmpty(msg)) {
			msg = MessageId.Authentication.EMPTY.getMessage();
			return msg;
		}

		// 인증번호 길이가 10글자인지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (code.length() != 10) {
			msg = MessageId.Authentication.NOT_10_CHAR.getMessage();
			return msg;
		}

		// 인증번호가 일치하는지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (!masterCode.equals(code)) {
			msg = MessageId.Authentication.NOT_MATCH.getMessage();
			return msg;
		}
		return msg;
	}
}
