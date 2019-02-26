package com.edu.kimschool.grade.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.kimschool.grade.entity.GradeEntity;

@Repository
public class GradeDao implements GradeMapper {

	
	// mybatis 연결시, sql전달목적
		@Autowired
		SqlSession sqlSession;

		@Override
		public int gradeInsert(GradeEntity gradeEntity) {
			GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
			int result = mapper.gradeInsert(gradeEntity);
			return result;
		}
			
		@Override
		public List<GradeEntity> gradeInfoList() {
			GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
			
			List<GradeEntity> gradeList = mapper.gradeInfoList();
			return gradeList;
		}

		@Override
		public int gradeUpdate(GradeEntity gradeEntity) {
			GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
			int result = mapper.gradeUpdate(gradeEntity);
			return result;
		}

		@Override
		public int gradeDelete(GradeEntity gradeEntity) {
			GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
			int result = mapper.gradeDelete(gradeEntity);
			return result;
		}

	
}
