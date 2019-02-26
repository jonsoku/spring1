package com.edu.kimschool.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.kimschool.member.entity.MemberEntity;

/**
 * 회원정보관련Dao
 */
@Repository
public class MemberDao implements MemberMapper {
	
	// SqlSession : Mybatis를 통해 연결통로 구축
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 회원가입처리
	 * @param memberEntity 회원정보
	 * @return
	 */
	@Override
	public int insertMemberInfo(MemberEntity memberEntity) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = 0;
		try {
			result = mapper.insertMemberInfo(memberEntity);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 아이디로 회원정보 검색
	 * @param memberId 회원아이디
	 * @return
	 */
	@Override
	public MemberEntity searchMemberById(String memberId) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		MemberEntity resultEntity = mapper.searchMemberById(memberId);
		return resultEntity;
	}

	/**
	 * 닉네임으로 회원정보 검색
	 * @param name 회원닉네임
	 * @return
	 */
	@Override
	public MemberEntity searchMemberByName(String name) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		MemberEntity resultEntity = mapper.searchMemberByName(name);
		return resultEntity;
	}

	/**
	 * e메일로 회원정보 검색
	 * @param email 회원이메일
	 * @return
	 */
	@Override
	public MemberEntity searchMemberByMail(String email) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		MemberEntity resultEntity = mapper.searchMemberByMail(email);
		return resultEntity;
	}

	/**
	 * 회원정보 수정
	 * @param memberEntity 회원정보
	 * @return
	 */
	@Override
	public int updateMemberInfo(MemberEntity memberEntity) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = mapper.updateMemberInfo(memberEntity);
		return result;
	}

	/**
	 * 회원정보 논리 삭제
	 * @param memberEntity 회원정보
	 * @return
	 */
	@Override
	public int delMemberInfo(MemberEntity memberEntity) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = mapper.delMemberInfo(memberEntity);
		return result;
	}
	
	/**
	 * 회원정보 물리삭제
	 * @param memberEntity 회원정보
	 * @return
	 */
	@Override
	public int delMemberData(MemberEntity memberEntity) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = mapper.delMemberData(memberEntity);
		return result;
	}

}
