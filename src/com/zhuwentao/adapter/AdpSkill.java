package com.zhuwentao.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.zhuwentao.bean.ExamSkillBean;
import com.zhuwentao.examncre_1112.R;

public class AdpSkill extends BaseAdapter {
	
	private List<ExamSkillBean> examSkills;
	private Context context;
	
	public AdpSkill(List<ExamSkillBean> examSkills, Context context) {
		this.examSkills = examSkills;
		this.context = context;
	}
	public void getSkillData(List<ExamSkillBean> examSkills){
		this.examSkills = examSkills;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return examSkills == null ? 0 : examSkills.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return examSkills == null ? null : examSkills.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 一个item布局
		View view = View.inflate(context, R.layout.item_list_skill, null);
		// 文章标题
		TextView skilltitle = (TextView) view.findViewById(R.id.item_skill_title);
		skilltitle.setText(examSkills.get(position).getSkilltitle());
		
		TextView itemskillcontent = (TextView) view.findViewById(R.id.tv_itemskill_content);
		itemskillcontent.setText(examSkills.get(position).getSkillcontent());
		
//		// 文章图片o
//		ImageView skillIcon = (ImageView) v.findViewById(R.id.item_img_skill);
//		BitmapUtils bitmaputils = new BitmapUtils(context);
//		bitmaputils.display(schoolIcon, examSkills.get(position).getSchoolimagesrc());
		
		return view;
	}

}
