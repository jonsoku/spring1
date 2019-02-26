package com.edu.kimschool.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.kimschool.member.dao.MemberMapper;
import com.edu.kimschool.member.entity.MemberEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml"})
public class MemberMapperTest{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	public void test1() throws Exception {
		
		// MemberMapper가 @Autowired되었는지 확인
		assertNotNull(memberMapper);
		
		 // id=isk0930 사용자의 이름이 "이석훈" 맞는지 확인
		MemberEntity user = memberMapper.searchMemberById("isk0930");
		assertEquals("이석훈",user.getFullName());
		
		// 사용자이름이 "이종석"인 레코드가 있는지 확인
		user = memberMapper.searchMemberByName("이종석");
		
		// 레코드가 존재하지 않는 것이 정상
		assertNull(user);
	}
}
