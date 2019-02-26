package com.edu.kimschool.member.service;

/**
 *닉네임をチェックするインターフェース。
 * @author キムホヒョン
 *
 */
public interface MemberNicNameFormCheckLogic {

	/**
	 * 닉네임をチェックする。
	 * @param searchId
	 * @return
	 */
	public String nicNameFomcheck(String searchName);
}
