package com.zhuwentao.bean;

public class ExamQuestionBean {
	
	private int id;	// 题目的id
	private String questionTitle;	// 题目的标题
	private String optionA;			// 题目选项A
	private String optionB;			// 题目选项B
	private String optionC;			// 题目选项C
	private String optionD;			// 选项D
	private String answer;			// 问题的正确答案
	
	private String questionkindid;			// 问题对应的试卷id
	private String questionkind;	// 问题的种类
	
	
	
	public ExamQuestionBean(){
		
	}
	
	
	
	
	public ExamQuestionBean(String questionTitle, String optionA,
			String optionB, String optionC, String optionD, String answer,
			String questionkind) {
		super();
		this.questionTitle = questionTitle;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
		this.questionkind = questionkind;
	}




	public ExamQuestionBean(int id, String questionTitle, String optionA,
			String optionB, String optionC, String optionD, String answer,
			String questionkindid, String questionkind) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
		this.questionkindid = questionkindid;
		this.questionkind = questionkind;
	}




	public String getQuestionkind() {
		return questionkind;
	}
	public void setQuestionkind(String questionkind) {
		this.questionkind = questionkind;
	}
	public String getQuestionkindid() {
		return questionkindid;
	}
	public void setQuestionkindid(String questionkindid) {
		this.questionkindid = questionkindid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
