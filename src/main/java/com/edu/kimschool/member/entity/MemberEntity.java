package com.edu.kimschool.member.entity;

import java.io.Serializable;

public class MemberEntity implements Serializable {

	/**
	 * 데이타 베이스 transaction 관리.
	 */
	private static final long serialVersionUID = 1L;
	
	// 회원 아이디
	private String memberId;
	// 비번
	private String password;
	// 회원 이름
	private String fullName;
	// 회원 닉네임
	private String name;
	// 회원 이메일주소
	private String email;
	// 회원 전화번호
	private String phone;
	// 회원 등록일
	private String regDate;
	// 회원 등급
	private int authority;
	// 회원 삭제 플러그
	private int delFlg;

	/**
	 * 
	 */
	public MemberEntity() {
		super();
	}
	
	/**
	 * @param memberId
	 * @param password
	 */
	public MemberEntity(String memberId, String password) {
		super();
		this.memberId = memberId;
		this.password = password;
	}

	/**
	 * @param memberId
	 * @param password
	 * @param fullName
	 * @param name
	 * @param email
	 * @param phone
	 * @param authority
	 */
	public MemberEntity(String memberId, String password, String fullName, String name, String email, String phone,
			int authority) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.fullName = fullName;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.authority = authority;
	}
	
	/**
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}


	/**
	 * @param memberId セットする memberId
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

    public void setMember_id(String member_id) {
    	this.memberId=member_id;
    }

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password セットする password
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return fullName
	 */
	public String getFullName() {
		return fullName;
	}


	/**
	 * @param fullName セットする fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setFull_name(String full_name) {
		this.fullName=full_name;
	}


	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setNick_name(String nick_name) {
		this.name=nick_name;
	}


	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email セットする email
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * @param phone セットする phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}



	/**
	 * @return regDate
	 */
	public String getRegDate() {
		return regDate;
	}



	/**
	 * @param regDate セットする regDate
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}



	/**
	 * @return authority
	 */
	public int getAuthority() {
		return authority;
	}



	/**
	 * @param authority セットする authority
	 */
	public void setAuthority(int authority) {
		this.authority = authority;
	}



	/**
	 * @return delFlg
	 */
	public int getdelFlg() {
		return delFlg;
	}
	

	/**
	 * @param delFlg セットする delFlg
	 */
	public void setdelFlg(int delFlg) {
		this.delFlg = delFlg;
	}

	public void setDel_flg(int del_flg) {
		this.delFlg=del_flg;
	}
	
	
	/* 객체에 넣는 것을 다시 보내기 위해 toString 처리
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberEntity [memberId=" + memberId + ", password=" + password + ", fullName=" + fullName + ", name="
				+ name + ", email=" + email + ", phone=" + phone + ", regDate=" + regDate + ", authority=" + authority
				+ ", delFlg=" + delFlg + "]";
	}
}
