package com.edu.kimschool.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.kimschool.common.constant.ConstEnum;
import com.edu.kimschool.common.constant.MessageId;

//@RequestMapping("board"): View단 내, board폴더에 관련된 맵핑 설정
@Controller
@RequestMapping("board")
public class BoardController {

	/**
	 * 작성글 내용 확인 作成コンテンツの内容確認 AJax는 모든 게시판에서 같이 이용하기에 메소드공통화
	 * AJaxは全ての掲示板で一緒に利用するため、メッソドを共通化
	 */
	// @ResponseBody: AJax를 사용하기 위한 어노테이션
	// @ResponseBody: AJaxを使用するためのアノテーション
	@ResponseBody
	@RequestMapping(value = "boardWriteCheck", method = RequestMethod.POST, produces = "application/test;charset=utf8")
	public String boardWriteCheck(String title, String content) {

		System.out.println(title);

		String msg = MessageId.Common.NO_PROBLEM.getMessage();

		// 경고메시지 띄우는 순서 중요
		// 警告メッセージを出力させる手順が重要
		// 내용이 짧은 경우
		// 内容が短い場合
		if (content.length() < 10) {
			msg = MessageId.Content.LESS_10_LETTERS.getMessage();
		}
		// 내용이 긴 경우
		// 内容が長い場合
		if (content.length() > 3000) {
			msg = MessageId.Content.OVER_3000_LETTERS.getMessage();
		}
		// 제목이 짧을 경우
		// タイトルが短い場合
		if (title.length() < 5) {
			msg = MessageId.Title.LESS_5_LETTERS.getMessage();
		}
		// 제목이 긴 경우
		// タイトルが長い場合
		if (title.length() > 30) {
			msg = MessageId.Title.OVER_30_LETTERS.getMessage();
		}
		// 내용이 없는 경우
		// 内容がない場合
		if (ConstEnum.StringConst.EMPTY.getValue().equals(content)) {
			msg = MessageId.Content.EMPTY.getMessage();
		}
		// 제목이 없는 경우
		// タイトルがない場合
		if (ConstEnum.StringConst.EMPTY.getValue().equals(title)) {
			msg = MessageId.Title.EMPTY.getMessage();
		}
		return msg;
	}
}
