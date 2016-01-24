package com.zhuwentao.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuwentao.adapter.QuestionAnsAdp;
import com.zhuwentao.bean.ExamQuestionBean;
import com.zhuwentao.db.ErrorSqlHelper;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

// 查看收藏的错误题目
public class ActErrorQuestion extends Activity {

	private TextView mTitle;
	private TextView mOptionA;
	private TextView mOptionB;
	private TextView mOptionC;
	private TextView mOptionD;
	private Button mbtnUp;
	private Button mbtnAnswer;
	private Button mbtnDown;


	private TextView tv_R_question;
	private TextView tv_E_question;
	private TextView tv_M_question;
	private QuestionAnsAdp questionAnsAdp;
	private PopupWindow popupWindow;
	private View parent;



	private int questionpostion=0;	// 当前的题目


	// 用来存储从数据库中获取到的问题数据
	private List<ExamQuestionBean> ExamQuestiondata = new ArrayList<ExamQuestionBean>();

	private ErrorSqlHelper errorSqlHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_error_list);
		MenuTop menu = (MenuTop) findViewById(R.id.error_list_menutop);
		menu.setTitleText("错题集");
		menu.setCollectBackground(R.drawable.select_delete_collect);

		// 设置答题卡按钮的点击事件
		menu.setAnswerCardListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPopuwindow(3);
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}
		});
		// 点击后删除该收藏
		menu.setCollectListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 删除收藏的题目
				if(ExamQuestiondata.size() != 0){
					deleteErrorQuestion();
				}
			}
		});

		// 初始化控件
		init();
		// 显示题目
		showQuestion();
	}

	private void init(){
		mTitle = (TextView) findViewById(R.id.tv_title_error);
		mOptionA = (TextView) findViewById(R.id.x_a_error);
		mOptionB = (TextView) findViewById(R.id.x_b_error);
		mOptionC = (TextView) findViewById(R.id.x_c_error);
		mOptionD = (TextView) findViewById(R.id.x_d_error);
		mbtnUp = (Button) findViewById(R.id.up_question_error);
		mbtnAnswer = (Button) findViewById(R.id.answer_error);
		mbtnDown = (Button) findViewById(R.id.down_question_error);

		errorSqlHelper = new ErrorSqlHelper(getApplicationContext());
	}

	/**
	 * 显示错误的题目
	 */
	public void showQuestion(){
		// 1、从数据库中获取数据并存储在ExamQuestiondata
		String kind = getIntent().getStringExtra("errorquestion_kind");
		ExamQuestiondata = errorSqlHelper.getErrorSqlKind(kind);
		// 2、检查数据库中是否有数据
		if(ExamQuestiondata.size() != 0){
			// 3、把当前的数据展示在界面中
			changeQuestion(questionpostion);
		}else{
			Toast.makeText(getApplicationContext(), "还没有数据哦", 0).show();
		}


		// 上一题
		mbtnUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(ExamQuestiondata.size() != 0){
					if(questionpostion == 0){
						Toast.makeText(getApplicationContext(), "已经是第一题", 0).show();
					}else{
						// 数据不能从0开始
						questionpostion = questionpostion-1;
						changeQuestion(questionpostion);
					}
				}
			}
		});
		// 查看答案
		mbtnAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ExamQuestiondata.size() != 0){
					showQuestionAnswer();
				}
			}
		});
		// 下一题
		mbtnDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ExamQuestiondata.size() != 0){
					if(questionpostion == ExamQuestiondata.size()-1){
						Toast.makeText(getApplicationContext(), "已经是最后一题", 0).show();
					}else{
						// 数据不能从0开始
						questionpostion = questionpostion+1;
						changeQuestion(questionpostion);
					}
				}
			}
		});
	}

	// 改变当前页面的题目
	private void changeQuestion(int i){
		mTitle.setText(ExamQuestiondata.get(i).getQuestionTitle());
		mOptionA.setText(ExamQuestiondata.get(i).getOptionA());
		mOptionB.setText(ExamQuestiondata.get(i).getOptionB());
		mOptionC.setText(ExamQuestiondata.get(i).getOptionC());
		mOptionD.setText(ExamQuestiondata.get(i).getOptionD());
		// 把答案存储在字段中
		//			mbtnAnswer = ExamQuestiondata.get(i).getAnswer();
	}

	// 获取数据当前的id并显示题目的答案
	private void showQuestionAnswer(){
		Toast.makeText(getApplicationContext(), "正确答案为："+ExamQuestiondata.get(questionpostion).getAnswer(), 0).show();
	}


	/**
	 * 显示泡泡窗口
	 * @param action 1=答题卡，2=试卷结果
	 */
	private void showPopuwindow(int action){
		View contentView = getLayoutInflater().inflate(R.layout.popwindow, null);
		GridView gridView = (GridView) contentView.findViewById(R.id.gridView);
		tv_R_question = (TextView) contentView.findViewById(R.id.tv_r_question);
		tv_E_question = (TextView) contentView.findViewById(R.id.tv_e_question);
		tv_M_question = (TextView) contentView.findViewById(R.id.tv_m_question);
		if(action == 1 || action == 3){
			tv_R_question.setVisibility(View.GONE);
			tv_E_question.setVisibility(View.GONE);
			tv_M_question.setVisibility(View.GONE);
		}else if(action == 2){

		}
		questionAnsAdp = new QuestionAnsAdp(this, ExamQuestiondata, null, null, action);
		gridView.setAdapter(questionAnsAdp);

		gridView.setOnItemClickListener(new ItemClickListener());

		popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);	// 取得焦点
		popupWindow.setBackgroundDrawable(new BitmapDrawable());	// 点击空白就会关闭pop窗口
		popupWindow.setAnimationStyle(R.style.animation);	// 动画效果

		// 获取泡泡窗口要显示的地方在哪一个布局文件中
		parent = this.findViewById(R.id.error_main);
	}

	// 删除收藏
	private void deleteErrorQuestion(){
		ExamQuestionBean eqb = new ExamQuestionBean();
		eqb.setId(ExamQuestiondata.get(questionpostion).getId());
		ExamQuestiondata.remove(questionpostion);
		if(questionpostion > 0){
			questionpostion = questionpostion-1;
		}
		if(ExamQuestiondata.size() != 0){
			changeQuestion(questionpostion);
		}else{
			mTitle.setText("暂无数据");
			mOptionA.setText("暂无数据");
			mOptionB.setText("暂无数据");
			mOptionC.setText("暂无数据");
			mOptionD.setText("暂无数据");
		}
		errorSqlHelper.DeleteErrorSql(eqb);
	}


	// 当用户的焦点不再弹出的窗口的时候就让窗口关闭
	private final class ItemClickListener implements OnItemClickListener{
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			changeQuestion(position);
			questionpostion = position;
			if(popupWindow.isShowing()){
				popupWindow.dismiss();	// 关闭
			}
		}
	}



}
