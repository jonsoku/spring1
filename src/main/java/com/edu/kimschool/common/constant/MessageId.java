package com.edu.kimschool.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통 메시지처리 클래스.
 * 共通メッセージ処理クラス.
 * 상세 : 처리 안에서 사용하고 있는 정수를 다루는 클래스
 * 詳細:処理中で使用しているメッセージ処理を扱うクラス
 */
public class MessageId {
	
	@AllArgsConstructor
	public enum Common {
		
		/** 문제없음
		 * 問題なし 
		 *  */
		NO_PROBLEM("0","success"),
		
		/** 사용가능 할 경우  
		 *  設定した値が使用可能である場合、
		 * */
		USE_POSSIBLE("1","This value can use right now."),
		
		/** 글자수가 8~12자가 아닌 경우
		 *文字数が8~12文字以外の場合
		 * */
		SET_LENGTH("2","Please enter the Value between 8 letters and 12 letters."),
		
		/** 영문 소문자와 숫자로 이루어지지 않은 경우
		 *  英語の小文字と数字で構成されてない場合
		 * */
		SET_PATTERN("3","Please use lowercase English and numbers for Value.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
	
	@AllArgsConstructor
	public enum Title {
		
		/** 제목이 없을 경우  
		 * Titleがない場合
		 * */
		EMPTY("0","Please enter the title."),
		
		/** 제목이 5자 미만일 경우 
		 * Titleが5文字より少ない場合、
		 *  */
		LESS_5_LETTERS("1","Please enter the title over 5 letters."),
		
		/** 제목이 30자 넘을 경우  
		 * Titleが30文字を超える場合
		 * */
		OVER_30_LETTERS("2","Please enter the title witin 30 letters.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
	
	@AllArgsConstructor
	public enum Content {
		
		/** 내용이 없을 경우 
		 * Contentが空の場合 
		 *  */
		EMPTY("0","Please enter the content's messages."),
		
		/** 내용이 10자 미만일 경우 
		 *  Contentが10文字より少ない場合
		 * */
		LESS_10_LETTERS("1","Please enter the content over 10 letters."),
		
		/** 내용이 3000자 넘을 경우 
		 * Contentが3000文字を超える場合
		 *  */
		OVER_3000_LETTERS("2","Please enter the content within 3000 letters.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
	
	@AllArgsConstructor
	public enum Id {
		
		/** 아이디를 입력 안 할 경우  
		 * IDが入力されてない場合
		 * */
		EMPTY("0","Please enter the ID."),
		
		/** 아이디가 이미 존재하는 경우  
		 * IDが既に存在する場合
		 * */
		ALREADY_EXISTS("1","This ID already exists.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
	
	@AllArgsConstructor
	public enum Authentication {
		
		/** 인증번호가 확인된 경우
		 *  mailへの認証番号が確認された場合
		 * */
		CHECKED("0","Authentication number checked."),
		
		/** 이메일 인증번호를 입력하지 않은 경우
		 *  mailへの認証番号を入力しない場合
		 * */
		EMPTY("1","Please enter the authentication number."),
		
		/** 인증번호 10자를 입력하지 않은 경우
		 *  mailへの認証番号が10文字ではない場合
		 * */
		NOT_10_CHAR("2","Please enter all 10 characters of authentication number."),
		
		/** 인증번호를 이메일로 보낸 경우	
		 *  mailへ認証番号を転送した場合
		 * */
		SEND_BY_MAIL("3","You have sent the authentication number by email."),
		
		/** 인증번호가 일치하지 않은 경우	
		 *  mailへの認証番号が一致しない場合
		 * */
		NOT_MATCH("4","The authentication number does not match.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
	
	@AllArgsConstructor
	public enum Mail {
		
		/** 이메일 주소를 입력하지 않은 경우
		 *  mailアドレスが入力されていない場合
		 * */
		EMPTY("0","Please enter the email."),
		
		/** 이메일 형식이 아닌 경우
		 *  mailの方ではない場合
		 * */
		NOT_FORMAT("1","This is not in email format."),
		
		/** 이메일주소가 이미 존재하는 경우,
		 *  mailアドレスが既に存在する場合
		* */
		ALREADY_EXISTS("2","This email already exists.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
		
	}
	@AllArgsConstructor
	public enum Pwd {
		
		/** 패스워드를 입력 안한 경우
		 *  Passwordが入力されていない場合
		 * */
		EMPTY("12","please enter the password."),
		
		/** 패스워드가 일치하지 않은 경우
		 *  Passwordが一致しない場合
		 * */
		NOT_EQUAL("13","The password does not match.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
	
	@AllArgsConstructor
	public enum NickName {
		
		/** 닉네임이 입력 안된 경우
		 * Nicknameが入力されていない場合
		 * */
		EMPTY("14","Please enter the Nickname."),
		
		/** 닉네임이 이미 존재하는 경우
		 *  Nicknameが既に存在する場合
		 * */
		ALREADY_EXISTS("15","This Nickname already exists.");
		
		@Getter
		private String messageId;
		
		@Getter
		private String message;
	}
}
