package com.zhuwentao.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhuwentao.bean.ExamQuestionBean;
import com.zhuwentao.examncre_1112.R;

public class QuestionAnsAdp extends BaseAdapter{

	// 问题数据
	private List<ExamQuestionBean> questiondata;
	private Context context;
	// 用户的答案
	private Map<Integer, String> UserAnswers;
	// 题目的正确答案
	private Map<Integer, String> QuestionAnswers;

	private int action;

	public QuestionAnsAdp(Context context, List<ExamQuestionBean> questiondata, Map<Integer, String> UserAnswers, Map<Integer, String> QuestionAnswers, int action){
		this.context = context;
		this.questiondata = questiondata;
		this.UserAnswers = UserAnswers;
		this.QuestionAnswers = QuestionAnswers;
		this.action = action;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return questiondata == null ? 0 : questiondata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return questiondata == null ? null : questiondata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = View.inflate(context, R.layout.grid_item, null);
		TextView tv_number = (TextView) v.findViewById(R.id.textView);
		//		tv_number.setText(String.valueOf(questiondata.get(position).getId()));
		tv_number.setText(String.valueOf(position+1));


		if(action == 1){
			if(UserAnswers.get(position) != null){
				// 答案不为空
				tv_number.setBackgroundResource(R.drawable.c_tupian2);
			}else{
				// 答案为空
				
			}
		}else if(action == 2){
			if(UserAnswers.get(position).equals(QuestionAnswers.get(position))){
				// 相同的答案
				tv_number.setBackgroundResource(R.drawable.c_r_tupian);
			}else{
				// 不同的答案
				if(UserAnswers.get(position).equals("E")){

				}else{
					tv_number.setBackgroundResource(R.drawable.c_e_tupian);
				}
			}
		}else if(action == 3){
			
		}


		return v;
	}

}
