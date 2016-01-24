package com.zhuwentao.bean;

public class ExamQuestionBean {
	
	private int id;	// ��Ŀ��id
	private String questionTitle;	// ��Ŀ�ı���
	private String optionA;			// ��Ŀѡ��A
	private String optionB;			// ��Ŀѡ��B
	private String optionC;			// ��Ŀѡ��C
	private String optionD;			// ѡ��D
	private String answer;			// �������ȷ��
	
	private String questionkindid;			// �����Ӧ���Ծ�id
	private String questionkind;	// ���������
	
	
	
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
