package com.edu.kimschool.grade.dao;

import java.util.List;

import com.edu.kimschool.grade.entity.GradeEntity;

public interface GradeMapper {

	// 성적을 입력하는 메소드
	public int gradeInsert(GradeEntity gradeEntity);
	
	// 성적을 검색하는 메소드
	public List<GradeEntity> gradeInfoList();
	
	//성적을 갱신하는 메소드
	public int gradeUpdate(GradeEntity gradeEntity);
	
	//성적을 삭제하는 메소드
	public int gradeDelete(GradeEntity gradeEntity);
	
}
