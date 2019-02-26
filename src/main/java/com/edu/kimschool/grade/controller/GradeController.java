package com.edu.kimschool.grade.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.kimschool.grade.dao.GradeDao;
import com.edu.kimschool.grade.entity.GradeEntity;

@RequestMapping("grade")
@Controller
public class GradeController {

	@Autowired
	GradeDao gradeDao;

	// request mapping의 벨류는 url를 가르킨다.
	@RequestMapping(value = "gradeInsert", method = RequestMethod.GET)
	public String gradeTest(Model model) {
		GradeEntity gradeEntity = new GradeEntity();
		gradeEntity.setMemberId("sting003");
		gradeEntity.setName("준열");
		gradeEntity.setItScore(100);
		gradeEntity.setJapScore(60);

		model.addAttribute("gradeEntity", gradeEntity);
		return "grade/gradeInsertForm";
	}

	@RequestMapping(value = "gradeInsert", method = RequestMethod.POST)
	public String grade(String memberId, String name, int itScore, int japScore) {
		System.out.println(memberId);
		System.out.println(name);
		System.out.println(itScore);
		System.out.println(japScore);
		GradeEntity gradeEntity = new GradeEntity();
//	gradeEntity.setMemberId(memberId);
//	gradeEntity.setMemberId(name);
//	gradeEntity.setMemberId(itScore);
//	gradeEntity.setMemberId(japScore);
//전부 1.setmemberId라서 오류 2.String itScore,String japScore　→int itScore, int japScore
		gradeEntity.setMemberId(memberId);
		gradeEntity.setName(name);
		gradeEntity.setItScore(itScore);
		gradeEntity.setJapScore(japScore);

		gradeDao.gradeInsert(gradeEntity);
		return "redirect:../";

	}
	@RequestMapping(value = "selectGrade", method = RequestMethod.GET)
	public String selectGrade(Model model) {
		
		
		System.out.println("selectGrade하면 이동");
		
		// db에서 자바까지 데이터를 받아옴
		List<GradeEntity> gradeEntityList = gradeDao.gradeInfoList();
		
		//컨트롤러에서 화면으로 보낼 정보를 모델에 저장하는 작업		
		model.addAttribute("gradeEntityList",gradeEntityList);
		
		
		return "grade/selectGrade";
	}
	@RequestMapping(value = "gradeDelete", method = RequestMethod.GET)
	public String gradeDelete(String memberId,String name) {
		
		System.out.println("go to gradeDelete");
		
		// 삭제할 내용 찾기
	//	GradeEntity gradeEntity = (GradeEntity) gradeDao.gradeInfoList();
		
		GradeEntity gradeEntity = new GradeEntity();
		gradeEntity.setMemberId(memberId);
		gradeEntity.setName(name);
		
		int result = gradeDao.gradeDelete(gradeEntity);
		
		if (0==result) {
			// 삭제실패시
			return "redirect:../";
		
		} else {
			// 삭제성공시
			
			return "redirect:selectGrade";
		}
		
	}
	
	@RequestMapping(value = "gradeDeleteGamenPractice", method = RequestMethod.GET)
	public String selectGrade1(Model model) {
		
		
		System.out.println("selectGrade하면 이동");
		
		// db에서 자바까지 데이터를 받아옴
		List<GradeEntity> gradeEntityList = gradeDao.gradeInfoList();
		
		//컨트롤러에서 화면으로 보낼 정보를 모델에 저장하는 작업		
		model.addAttribute("gradeEntityList",gradeEntityList);
		
		
		return "grade/selectGrade";
	}
	
	@RequestMapping(value = "gradeDeleteGamen", method = RequestMethod.GET)
	public String gradeDeleteGamen() {
		
		
		return "grade/gradeDeleteGamen";
	}
	
}
