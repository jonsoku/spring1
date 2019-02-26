package com.edu.kimschool.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.edu.kimschool.member.dao.MemberDao;
import com.edu.kimschool.member.entity.MemberEntity;

public class MockitoTest {

	@Mock
	MemberDao memberDao =new MemberDao();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * mock은 기존 값 무시하고 설정한 thenReturn(값)만 들고옴
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test_001() {

		// mock설정
		List<Object> mockedList = mock(List.class);
		 
		// mock 객체 사용
		 
		mockedList.add("one");
		 
		mockedList.add("two");

		// stubbing
		 
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenReturn("double");
		
		

		// 결과 확인	
		assertEquals("first", mockedList.get(0));
		assertEquals("double", mockedList.get(1));
		//기본 값 0으로 설정
		assertEquals(0, mockedList.size()); 	
	
	
	}

	/**
	 * spy는 실제 함수 호출함
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void test_002() {
	 
		//spy설정
    		
		List list = new LinkedList();
		List spy = Mockito.spy(list);

		// stubbing
		//when(spy.size()).thenReturn(100); 

		spy.add("one");
		spy.add("two");

		
		//결과 확인
		assertEquals("one",spy.get(0));
		assertEquals("two",spy.get(1));
		//stubb설정 x 원래 객체의 값 나옴
		assertEquals(2,spy.size());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_003() {
		 
		//spy설정
		   List<Object> mockList = Mockito.mock(List.class);
		    ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
		 
		    mockList.add("one");
		    Mockito.verify(mockList).add(arg.capture());
		 
		//결과 확인
		assertEquals("one", arg.getValue());


	}
	
	/**
	 * ArgumentCaptor는 insert,update문 등 제대로 데이터를 입력했는지 확인하기 위해 설정해서 sql문 실행하기전 확인하는 기능
	 */
	@Test
	public void test_004() {
	 
		//spy설정
    		
		MemberEntity memberEntity =new MemberEntity();
		memberEntity.setMemberId("kimhohyeon");
		memberEntity.setPassword("kim123");
		memberEntity.setName("kim");
		memberEntity.setEmail("kim@naver.com");
		
		ArgumentCaptor<MemberEntity> memberCaptor =ArgumentCaptor.forClass(MemberEntity.class);
		// stubbing
		doReturn(1).when(memberDao).insertMemberInfo(memberCaptor.capture());
		
		memberDao.insertMemberInfo(memberEntity);
		
		
		MemberEntity result=memberCaptor.getValue();
		//결과 확인
		assertEquals("kimhohyeon", result.getMemberId());
	}
}
