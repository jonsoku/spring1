package com.edu.kimschool.member.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.edu.kimschool.common.constant.MessageId;

/**
 *IDをチェックするクラス。
 * @author キムホヒョン
 *
 */
@Service
public class MemberIdFormCheckLogicImpl implements MemberIdFormCheckLogic{

	/**
	 * IDをチェックする。
	 * @param searchId
	 * @return
	 */
	@Override
	public String idFomcheck(String searchId) {
		String msg = MessageId.Common.USE_POSSIBLE.getMessage();
		
		// ID를 입력 했는지 검사 ㅡㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
				if (StringUtils.isEmpty(searchId)) {
					msg = MessageId.Id.EMPTY.getMessage();
					return msg;
				}

				// ID의 길이가 8~12글자인지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
				if (searchId.length() <= 7 || searchId.length() >= 13) {
					msg = MessageId.Common.SET_LENGTH.getMessage();
					return msg;
				}

				// ID에 영문과 숫자가 모두 있는지 검사 ㅡOKㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
				int ie = 0; // id english
				int inum = 0; // id number

				for (int i = 0; i < searchId.length(); i++) {
					if (searchId.charAt(i) <= 'z' && searchId.charAt(i) >= 'a') {
						ie++;
					}
					if (searchId.charAt(i) <= '9' && searchId.charAt(i) >= '0') {
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
