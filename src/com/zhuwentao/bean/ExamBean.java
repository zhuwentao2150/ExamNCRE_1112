package com.zhuwentao.bean;

public class ExamBean {

	private int id;	// �Ծ�ID
	private String examtitle;	// �Ծ����
	private String examkind;	// �Ծ������
	
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
