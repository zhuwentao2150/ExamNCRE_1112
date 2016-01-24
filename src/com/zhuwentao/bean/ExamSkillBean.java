package com.zhuwentao.bean;

import java.io.Serializable;

public class ExamSkillBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String skilltitle;
	private String skillcontent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSkilltitle() {
		return skilltitle;
	}
	public void setSkilltitle(String skilltitle) {
		this.skilltitle = skilltitle;
	}
	public String getSkillcontent() {
		return skillcontent;
	}
	public void setSkillcontent(String skillcontent) {
		this.skillcontent = skillcontent;
	}
	
	
	

}
