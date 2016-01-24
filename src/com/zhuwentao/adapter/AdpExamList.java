package com.zhuwentao.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhuwentao.bean.ExamBean;
import com.zhuwentao.examncre_1112.R;

public class AdpExamList extends BaseAdapter {

	private List<ExamBean> examBeans;
	private Context context;
	
	public AdpExamList(List<ExamBean> examBeans, Context context) {
		this.examBeans = examBeans;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return examBeans == null ? 0 : examBeans.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return examBeans == null ? null : examBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return examBeans.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.item_list_exam, null);
		
		TextView examTitleName = (TextView) v.findViewById(R.id.item_exam_title);
		examTitleName.setText(examBeans.get(position).getExamtitle());
		
		return v;
		
	}

}
