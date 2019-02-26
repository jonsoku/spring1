package com.edu.kimschool.member.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.edu.kimschool.common.constant.ConstEnum;
import com.edu.kimschool.common.constant.MessageId;

/**
 * Emailをチェックするクラス。
 * 
 * @author キムホヒョン
 *
 */
@Service
public class MemberEmailFormCheckLogicImpl implements MemberEmailFormCheckLogic {

	/**
	 * Emailをチェックする。
	 * 
	 * @param email
	 * @return チェックした結果
	 */
	@Override
	public String emailFormCheck(String email) {
		String msg = ConstEnum.StringConst.EMPTY.getValue();

		if (StringUtils.isEmpty(email)) {
			msg = MessageId.Mail.EMPTY.getMessage();
			return msg;
		}

		// 이메일 형식인지 검사 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		int golcnt = 0;
		int dotcnt = 0;

		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				golcnt++;
			}
			if (email.charAt(i) == '.') {
				dotcnt++;
			}
		}

		if (golcnt != 1 || dotcnt != 1) {
			msg = MessageId.Mail.NOT_FORMAT.getMessage();
			return msg;
		}
		return msg;
	}

}
