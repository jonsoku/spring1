package com.edu.kimschool.grade.entity;

import java.io.Serializable;

public class GradeEntity implements Serializable {

	/**
	 * 데이타 베이스 transaction 관리.
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	
	private String name;
	
	private int itScore;
	
	private int japScore;
	
	
	
	public GradeEntity() {
		
	}
	
		
	public GradeEntity(String memberId, String name, int itScore, int japScore) {
		this.memberId = memberId;
		this.name = name;
		this.itScore = itScore;
		this.japScore = japScore;
	}

	
	public String getMemberId() {
		return memberId;
	}

	public void setMember_Id(String member_Id) {
		this.memberId = member_Id;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItScore() {
		return itScore;
	}

	public void setItScore(int itScore) {
		this.itScore = itScore;
	}

	public int getJapScore() {
		return japScore;
	}

	public void setJapScore(int japScore) {
		this.japScore = japScore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "GradeEntity [memberId=" + memberId + ", name=" + name + ", itScore=" + itScore + ", japScore=" + japScore + "]";
	}


}
