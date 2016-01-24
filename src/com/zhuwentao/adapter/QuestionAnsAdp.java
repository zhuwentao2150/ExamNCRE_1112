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

	// ��������
	private List<ExamQuestionBean> questiondata;
	private Context context;
	// �û��Ĵ�
	private Map<Integer, String> UserAnswers;
	// ��Ŀ����ȷ��
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
				// �𰸲�Ϊ��
				tv_number.setBackgroundResource(R.drawable.c_tupian2);
			}else{
				// ��Ϊ��
				
			}
		}else if(action == 2){
			if(UserAnswers.get(position).equals(QuestionAnswers.get(position))){
				// ��ͬ�Ĵ�
				tv_number.setBackgroundResource(R.drawable.c_r_tupian);
			}else{
				// ��ͬ�Ĵ�
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
