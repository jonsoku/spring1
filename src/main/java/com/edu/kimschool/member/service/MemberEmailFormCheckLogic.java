package com.edu.kimschool.member.service;

/**
 * Emailをチェックするインターフェース。
 * @author キムホヒョン
 *
 */
public interface MemberEmailFormCheckLogic {

	/**
	 * Emailをチェックする。
	 * @param email
	 * @return　チェックした結果
	 */
	public String emailFormCheck(String email);
}
