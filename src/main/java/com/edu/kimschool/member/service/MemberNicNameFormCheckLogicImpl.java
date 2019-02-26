package com.edu.kimschool.member.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.edu.kimschool.common.constant.MessageId;

/**
 *닉네임をチェックするインターフェース。
 * @author キムホヒョン
 *
 */
@Service
public class MemberNicNameFormCheckLogicImpl implements MemberNicNameFormCheckLogic{

	/**
	 * 닉네임をチェックする。
	 * @param searchId
	 * @return
	 */
	@Override
	public String nicNameFomcheck(String searchName) {
		
		String msg = MessageId.Common.USE_POSSIBLE.getMessage();
		
		// name 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (StringUtils.isEmpty(msg)) {
			msg = MessageId.NickName.EMPTY.getMessage();
			return msg;
		}

		// name의 길이가 8~12글자인지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if (searchName.length() < 2 || searchName.length() > 8) {
			msg = MessageId.Common.SET_LENGTH.getMessage();
			return msg;
		}
		return msg;
	}
}
