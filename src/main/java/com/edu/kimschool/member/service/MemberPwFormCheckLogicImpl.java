package com.edu.kimschool.member.service;

import org.springframework.stereotype.Service;

import com.edu.kimschool.common.constant.ConstEnum;
import com.edu.kimschool.common.constant.MessageId;

/**
 * 暗証番号の刑をチェックのクラス。
 * @author キムホヒョン
 *
 */
@Service
public class MemberPwFormCheckLogicImpl implements MemberPwFormCheckLogic{
	
	/**
	 * 暗証番号の刑をチェック。
	 * @return キムホヒョン
	 */
	@Override
	public String pwFormCheck(String pw) {

		String msg = MessageId.Common.USE_POSSIBLE.getMessage();
	
		// 비밀번호 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (pw.equals(ConstEnum.StringConst.EMPTY.getValue())) {
			msg = MessageId.Pwd.EMPTY.getMessage();
			return msg;
		}

		// 비밀번호의 길이가 8~12글자인지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (pw.length() <= 7 || pw.length() >= 13) {
			msg = MessageId.Common.SET_LENGTH.getMessage();
			return msg;
		}

		// ID에 영문과 숫자가 모두 있는지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		int ie = 0; // id english
		int inum = 0; // id number

		for (int i = 0; i < pw.length(); i++) {
			if (pw.charAt(i) <= 'z' && pw.charAt(i) >= 'a') {
				ie++;
			}
			if (pw.charAt(i) <= '9' && pw.charAt(i) >= '0') {
				inum++;
			}
		}

		if (ie == 0 || inum == 0) {
			msg = MessageId.Common.SET_PATTERN.getMessage();
			return msg;
		}
		
		return msg;
	}
}
