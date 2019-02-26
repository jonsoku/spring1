package com.edu.kimschool.member.dao;

import com.edu.kimschool.member.entity.MemberEntity;

public interface MemberMapper {
	
	// 회원 정보 저장
	public int insertMemberInfo(MemberEntity memberEntity);
	
	// ID로 해당 회원 정보 검색
	public MemberEntity searchMemberById(String memberId);
	
	// 회원 닉네임으로 해당 회원정보 검색
	public MemberEntity searchMemberByName(String name);
	
	// email로 해당 회원정보 검색
	public MemberEntity	searchMemberByMail(String email);
	
	// 회원정보 수정
	public int updateMemberInfo(MemberEntity memberEntity);
	
	// 회원 탈퇴(논리)
	public int delMemberInfo(MemberEntity memberEntity);
	
	// 회원 물리 삭제
	public int delMemberData(MemberEntity memberEntity);
	
}
