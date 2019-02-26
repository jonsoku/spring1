package com.edu.kimschool.member;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.edu.kimschool.member.service.MemberSecurityNumberLogicImpl;

public class MemberSecurityNumberTest {

	MemberSecurityNumberLogicImpl memberSecurityNumberLogicImpl =null;

	@Before
	public void setUp() {
		memberSecurityNumberLogicImpl = new MemberSecurityNumberLogicImpl();
	}
	
	@Test
	public void test1() {

		String code = memberSecurityNumberLogicImpl.securityNumberMake();
		assertEquals(10, code.length());
	
	}
}
