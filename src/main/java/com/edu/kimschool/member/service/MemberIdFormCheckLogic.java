package com.edu.kimschool.member.service;

/**
 *IDをチェックするインターフェース。
 * @author キムホヒョン
 *
 */
public interface MemberIdFormCheckLogic {

	/**
	 * IDをチェックする。
	 * @param searchId
	 * @return
	 */
	public String idFomcheck(String searchId);
}
