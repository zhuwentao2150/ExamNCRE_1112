package com.zhuwentao.bean;

public class ExamBean {

	private int id;	// 试卷ID
	private String examtitle;	// 试卷标题
	private String examkind;	// 试卷的种类
	
	public ExamBean(){
		
	}
	public ExamBean(int id, String examtitle, String examkind) {
		super();
		this.id = id;
		this.examtitle = examtitle;
		this.examkind = examkind;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExamkind() {
		return examkind;
	}
	public void setExamkind(String examkind) {
		this.examkind = examkind;
	}
	public String getExamtitle() {
		return examtitle;
	}
	public void setExamtitle(String examtitle) {
		this.examtitle = examtitle;
	}
	
	
}
