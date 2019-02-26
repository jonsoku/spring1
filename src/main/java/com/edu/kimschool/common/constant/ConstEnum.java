package com.edu.kimschool.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통정수 클래스
 * 共通定数クラス
 * 상세 : 처리 안에서 사용하고 있는 정수를 다루는 클래스
 * 詳細；処理中で使用している定数を扱うクラス
 */
public class ConstEnum {

	/**
	 * 플래그
	 * フラグ
	 */
	@AllArgsConstructor
	public enum Flag {
		
		/** 플래그값이 존재하지 않음 
		 * フラグが存在しない  */
		NOT_EMPTY("0"),
		
		/**플래그값이 존재함 
		 * フラグが存在する  */
		EMPTY("1");
		
		@Getter
		private String value;
	}
	
	/**
	 * 페이지
	 * ページ
	 */
	@AllArgsConstructor
	public enum Page {
		
		/** 최조 페이지 
		 * 最初のページ  */
		FIRST_PAGE(1),
		
		/** 페이지 당 열개의 글 지정
		 * ページ当ごとに、10つのコンテンツを指定 */
		TEN_CONTENTS_PER_PAGE(10),
		
		/** 그룹 당 5개의 페이지 지정
		 * グループごとに、5つのページを指定  */
		FIVE_PAGES_PER_GROUP(5);
		
		@Getter
		private int value;
	}
	
	/**
	 * 문자열정수
	 * 文字列定数
	 */
	@AllArgsConstructor
	public enum StringConst {
		
		/** 비어있음
		 * 空 */
		EMPTY(""),
		
		/** 콤마
		 * コンマ  */
		COMMA(",");
		
		@Getter
		private String value;
	} 
}
