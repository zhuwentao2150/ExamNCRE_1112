package com.zhuwentao.test;

import java.util.ArrayList;
import java.util.List;

import com.zhuwentao.bean.ExamBean;
import com.zhuwentao.bean.ExamQuestionBean;

public class ExamDataTest {
	
	
	// 试卷数据
	public static List<ExamBean> getExamTitleData(){
		List<ExamBean> exambean = new ArrayList<ExamBean>();
		for (int i = 1; i <= 10; i++) {
			ExamBean eb = new ExamBean(i, "PS考试试卷"+i, "ps");
			exambean.add(eb);
		}
		return exambean;
	}
	
	// 题目数据
	public static List<ExamQuestionBean> getExamQuestionData(){
		List<ExamQuestionBean> examquestionbean = new ArrayList<ExamQuestionBean>();
		for (int i = 1; i < 10; i++) {
			ExamQuestionBean eqb = new ExamQuestionBean(i, "题目标题"+i, "选项A"+i, "选项B"+i, "选项C"+i, "选项D"+i, "A", "1", "ps");
			examquestionbean.add(eqb);
		}
		return examquestionbean;
	}

}
