package com.edu.kimschool.member.service;

/**
 * メールに送る認証番号を作るインターフェース。
 * @author キムホヒョン
 *
 */
public interface MemberSecurityNumberLogic {

	
/**
 * メールに送る認証番号を作るmethod。
 * @return 認証番号
 * 
 */
public String securityNumberMake();

}
