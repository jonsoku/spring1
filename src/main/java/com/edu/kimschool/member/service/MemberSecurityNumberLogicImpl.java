package com.edu.kimschool.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * メールに送る認証番号を作るクラス。
 * @author キムホヒョン
 *
 */
@Service
public class MemberSecurityNumberLogicImpl implements MemberSecurityNumberLogic {

	/**
	 * メールに送る認証番号を作るメッソド。
	 * @return 認証番号
	 * 
	 */
	@Override
	public String securityNumberMake() {
		
		// 인증 키 만들기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<Character> list = new ArrayList<Character>();

		StringBuffer keySet = new StringBuffer();

		// 0~9 넣기
		for (int i = 48; i <= 57; i++) {
			list.add((char) i);
		}

		// A~Z넣기
		for (int i = 65; i <= 90; i++) {
			list.add((char) i);
		}

		// 10자리 비밀번호 생성
		for (int i = 0; i <= 9; i++) {

			int index = (int) (Math.random() * list.size());
			keySet.append(list.get(index));
		}
		
		// list의 내용이 존재하는 첫번째 건을 취득
		Optional<Character> listCheck = list.stream().filter(result -> result != null).findFirst();
		
		// 존재한다면
		if(listCheck.isPresent()) {
		
			// 존재한다면 해당 값을 리턴
			return keySet.toString();
		}
		return null;
	}
}
