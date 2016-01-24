package com.zhuwentao.tools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhuwentao.bean.ExamBean;
import com.zhuwentao.bean.ExamOutlineBean;
import com.zhuwentao.bean.ExamQuestionBean;
import com.zhuwentao.bean.ExamSkillBean;


public class JsonTools {

	/**
	 * 解析服务器端传递过来的试卷标题JSON数据
	 * @param examjson
	 * @return	List集合
	 */
	public static List<ExamBean> parseExamJson(String examjson){
		List<ExamBean> ebdata = new ArrayList<ExamBean>();
		try{
			// 把传递过来的数据变成jsonArray数组
			JSONArray jsonarr = new JSONArray(examjson);
			for(int i=0; i<jsonarr.length(); i++){
				JSONObject obj = jsonarr.getJSONObject(i);
				ExamBean eb = new ExamBean();
				eb.setId(obj.getInt("id"));
				eb.setExamtitle(obj.getString("examtitle"));
				ebdata.add(eb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ebdata;
	}

	/**
	 * 解析从服务器端传递过来的试卷题目信息
	 * @param examquestionjson
	 * @return
	 */
	public static List<ExamQuestionBean> parseExamQuestionJson(String examquestionjson){
		List<ExamQuestionBean> eqbdata = new ArrayList<ExamQuestionBean>();
		try{
			JSONArray jsonarr = new JSONArray(examquestionjson);
			for(int i = 0; i<jsonarr.length(); i++){
				JSONObject obj = jsonarr.getJSONObject(i);
				ExamQuestionBean eqb = new ExamQuestionBean();
				eqb.setId(obj.getInt("id"));
				eqb.setQuestionTitle(obj.getString("questionTitle"));
				eqb.setOptionA(obj.getString("optionA"));
				eqb.setOptionB(obj.getString("optionB"));
				eqb.setOptionC(obj.getString("optionC"));
				eqb.setOptionD(obj.getString("optionD"));
				eqb.setAnswer(obj.getString("answer"));
				eqb.setQuestionkindid(obj.getString("questionkindid"));
				eqbdata.add(eqb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return eqbdata;
	}
	
	
	/**
	 * 解析大纲数据
	 * @param outlinejson
	 * @return
	 */
	public static List<ExamOutlineBean> parseExamOutlineJson(String outlinejson){
		List<ExamOutlineBean> eobdata = new ArrayList<ExamOutlineBean>();
		try{
			JSONArray jsonarr = new JSONArray(outlinejson);
			for(int i=0; i<jsonarr.length(); i++){
				JSONObject obj = jsonarr.getJSONObject(i);
				ExamOutlineBean eob = new ExamOutlineBean();
				eob.setId(obj.getInt("id"));
				eob.setOutlinetitle(obj.getString("outlinetitle"));
				eob.setOutlinecontent(obj.getString("outlinecontent"));
				eob.setOutlinekind(obj.getString("outlinekind"));
				eobdata.add(eob);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return eobdata;
	}
	
	/**
	 * 解析考试技巧数据
	 * @param skilljson
	 * @return
	 */
	public static List<ExamSkillBean> parseExamSkillJson(String skilljson){
		List<ExamSkillBean> esbdata = new ArrayList<ExamSkillBean>();
		try{
			JSONArray jsonarr = new JSONArray(skilljson);
			for(int i=0; i<jsonarr.length(); i++){
				JSONObject obj = jsonarr.getJSONObject(i);
				ExamSkillBean esb = new ExamSkillBean();
				esb.setId(obj.getInt("id"));
				esb.setSkilltitle(obj.getString("skilltitle"));
				esb.setSkillcontent(obj.getString("skillcontent"));
				esbdata.add(esb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return esbdata;
	}

}
