package com.zhuwentao.test;

import java.util.ArrayList;
import java.util.List;

import com.zhuwentao.bean.ExamBean;
import com.zhuwentao.bean.ExamQuestionBean;

public class ExamDataTest {
	
	
	// �Ծ�����
	public static List<ExamBean> getExamTitleData(){
		List<ExamBean> exambean = new ArrayList<ExamBean>();
		for (int i = 1; i <= 10; i++) {
			ExamBean eb = new ExamBean(i, "PS�����Ծ�"+i, "ps");
			exambean.add(eb);
		}
		return exambean;
	}
	
	// ��Ŀ����
	public static List<ExamQuestionBean> getExamQuestionData(){
		List<ExamQuestionBean> examquestionbean = new ArrayList<ExamQuestionBean>();
		for (int i = 1; i < 10; i++) {
			ExamQuestionBean eqb = new ExamQuestionBean(i, "��Ŀ����"+i, "ѡ��A"+i, "ѡ��B"+i, "ѡ��C"+i, "ѡ��D"+i, "A", "1", "ps");
			examquestionbean.add(eqb);
		}
		return examquestionbean;
	}

}
