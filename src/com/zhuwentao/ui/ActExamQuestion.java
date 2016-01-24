package com.zhuwentao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuwentao.adapter.QuestionAnsAdp;
import com.zhuwentao.bean.ExamQuestionBean;
import com.zhuwentao.config.Common;
import com.zhuwentao.db.CollectSqlHelper;
import com.zhuwentao.db.ErrorSqlHelper;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.thread.ExamQuestionThread;
import com.zhuwentao.tools.JsonTools;
import com.zhuwentao.widget.MenuTop;

public class ActExamQuestion extends Activity implements OnCheckedChangeListener{

	private TextView tv_title;
	private RadioGroup rg;
	private RadioButton btn_xA;
	private RadioButton btn_xB;
	private RadioButton btn_xC;
	private RadioButton btn_xD;
	private RadioButton btn_moren;	// 这个按钮是让界面默认点中的
	private Button btn_up;	// 上一题的按钮
	private Button btn_down;	// 下一题的按钮
	private Button btn_answer;	// 问题的解释

	private int questionpostion=0;	// 当前的题目
	private String userAnswer;	// 用户的答案
	private String questionAnswer;	// 问题的打答案
	private int UserF=0;	// 用户的得分
	private int UserE=0;	// 用户做错的
	private int UserM=0; 	// 用户没做的

	private String kind;	// 试卷的种类

	private boolean kzq = true; 	// 控制错题存储数据的次数

	private TextView tv_R_question;
	private TextView tv_E_question;
	private TextView tv_M_question;
	private QuestionAnsAdp questionAnsAdp;
	private PopupWindow popupWindow;
	private View parent;

	private ErrorSqlHelper errorSqlHelper;


	// 问题数据
	private List<ExamQuestionBean> questiondata = new ArrayList<ExamQuestionBean>();
	// 用户的答案
	private Map<Integer, String> UserAnswers = new HashMap<Integer, String>();
	// 题目的正确答案
	private Map<Integer, String> QuestionAnswers = new HashMap<Integer, String>();

	//	private TextView tv_R_question;
	//	private TextView tv_E_question;
	//	private TextView tv_M_question;


	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == Common.MSG_GET_EXAMQUESTION){
				String json = (String) msg.obj;
				if(json == ""){
					Toast.makeText(getApplicationContext(), "没有数据", Toast.LENGTH_SHORT).show();
				}else{
					questiondata = JsonTools.parseExamQuestionJson(json);
					// 把问题的答案存储在QuestionAnswer中
					for(int i=0; i<questiondata.size(); i++){
						QuestionAnswers.put(i, questiondata.get(i).getAnswer());
					}
					changeQuestion(questionpostion);
				}
			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_question);

		kind = getIntent().getStringExtra("question_kind");


		MenuTop menu = (MenuTop) findViewById(R.id.examquestion_menutop);
		menu.setTitleText("答题");
		// 设置答题卡按钮的点击事件
		menu.setAnswerCardListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPopuwindow(1);
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}
		});
		// 设置收藏按钮的点击事件
		menu.setCollectListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCollectHint(questionpostion, kind);
			}
		});

		errorSqlHelper = new ErrorSqlHelper(getApplicationContext());

		// 初始化控件
		Init();
		// 显示题目
		showQuestion();


	}

	// 初始化控件
	private void Init(){
		tv_title = (TextView) findViewById(R.id.tv_title);
		rg = (RadioGroup) findViewById(R.id.rg_test);
		btn_xA = (RadioButton) findViewById(R.id.x_rg_a);
		btn_xB = (RadioButton) findViewById(R.id.x_rg_b);
		btn_xC = (RadioButton) findViewById(R.id.x_rg_c);
		btn_xD = (RadioButton) findViewById(R.id.x_rg_d);
		btn_moren = (RadioButton) findViewById(R.id.x_rg_moren);
		btn_answer = (Button) findViewById(R.id.answer);
		btn_up = (Button) findViewById(R.id.up_question);
		btn_down = (Button) findViewById(R.id.down_question);
	}



	// 显示出题目
	private void showQuestion(){

		// 获得试卷id
		int id = getIntent().getIntExtra("question_id", 0);
		// 启动获取数据的线程
		new ExamQuestionThread(handler, Common.URL_GET_EXAMQUESTION, id).start();

		rg.setOnCheckedChangeListener(this);

		// 设置左边按钮的点击事件
		btn_up.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(questionpostion == 0){
					Toast.makeText(getApplicationContext(), "已经是第一题", 0).show();
				}else{
					// 数据不能从0开始
					questionpostion = questionpostion-1;
					changeQuestion(questionpostion);

					if(UserAnswers.get(questionpostion) == null){
						changeRadioButtonEnabledTrue();
						changeRadioButtonCheckedOff();
					}else{
						// 返回上一次用户点击的按钮
						changeUserRodioButtonChecked();
						changeRadioButtonEnabledFalse();
					}
				}

			}
		});

		// 设置右边按钮的点击事件
		btn_down.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// 数组默认是从0开始的，所以需要减1
				if(questionpostion == questiondata.size()-1){
					Toast.makeText(getApplicationContext(), "已经是最后一题", 0).show();
				}else{
					// 由于第一个开头是0，所以要减1
					questionpostion = questionpostion+1;
					changeQuestion(questionpostion);
					if(UserAnswers.get(questionpostion) == null){
						changeRadioButtonEnabledTrue();
						changeRadioButtonCheckedOff();
					}else{
						// 返回上一次用户点击的按钮
						changeUserRodioButtonChecked();
						changeRadioButtonEnabledFalse();
					}
				}
			}
		});

		// 当用户做完所有的题目后，就显示出这个按钮，点击后出现答题卡页面，计算用户得分
		btn_answer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 用户当前在答的题目设置为不可点击
				changeRadioButtonEnabledFalse();

				if(kzq){
					// 点击这个后，判断用户的答案和题目的答案是否一样
					for(int i=0; i < QuestionAnswers.size(); i++){
						// 如果用户没有填写答案，则把答案变成E，就是错误的意思
						if(UserAnswers.get(i) == null){
							UserAnswers.put(i, "E");
							UserM++;	// 没有做的题增加
						}
						if(UserAnswers.get(i).equals(QuestionAnswers.get(i))){
							// 相同的答案，在这里可以增加用户的分数
							UserF++;	// 做对的题增加
						}else{
							// TODO
							// 这段代码只执行一次
							ExamQuestionBean eqb = questiondata.get(i);
							eqb.setQuestionkind(kind);
							errorSqlHelper.saveErrorSql(eqb);
						}
					}
					kzq = false;
				}
				// 计算出用户做错的题目数
				UserE = QuestionAnswers.size()-(UserF+UserM);
				// 显示泡泡窗口
				showPopuwindow(2);
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}
		});
	}

	// 清除选项
	private void changeRadioButtonCheckedOff(){
		// 让这个隐藏的按钮默认点中，这样其它的按钮就可以选择了
		btn_moren.setChecked(true);
	}

	// 设置按钮为可选状态
	private void changeRadioButtonEnabledTrue(){
		btn_xA.setEnabled(true);
		btn_xB.setEnabled(true);
		btn_xC.setEnabled(true);
		btn_xD.setEnabled(true);
	}

	// 设置按钮为不可选的状态
	private void changeRadioButtonEnabledFalse(){
		btn_xA.setEnabled(false);
		btn_xB.setEnabled(false);
		btn_xC.setEnabled(false);
		btn_xD.setEnabled(false);
	}

	// 用户已经选择了的选项
	private void changeUserRodioButtonChecked(){
		String question = UserAnswers.get(questionpostion);
		if(question != null){
			if(question.equals("A")){
				btn_xA.setChecked(true);
			}
			if(question.equals("B")){
				btn_xB.setChecked(true);
			}
			if(question.equals("C")){
				btn_xC.setChecked(true);
			}
			if(question.equals("D")){
				btn_xD.setChecked(true);
			}
			if(question.equals("E")){
				btn_moren.setChecked(true);
			}
		}
	}

	// 改变当前页面的题目
	private void changeQuestion(int i){
		tv_title.setText(questiondata.get(i).getQuestionTitle());
		btn_xA.setText(questiondata.get(i).getOptionA());
		btn_xB.setText(questiondata.get(i).getOptionB());
		btn_xC.setText(questiondata.get(i).getOptionC());
		btn_xD.setText(questiondata.get(i).getOptionD());
		questionAnswer = questiondata.get(i).getAnswer();
	}

	// 把用户点击的加入到数组中，这端程序可以在使用联系的时候添加，每次用户给完答案后就对用户的打答案进行判断
	private void judgeUserAnswer(String userAnswer, String questionAnswer){
		// UserAnswers用来存储用户输入的答案
		UserAnswers.put(questionpostion, userAnswer);
		// 判断是不是最后一题，如果是最后一题就显示提交
		if(questionpostion == questiondata.size()-1){
			//				btn_down.setClickable(false);	// 设置为不可点击的状态
			btn_answer.setVisibility(View.VISIBLE);
		}
	}

	// 设置按钮的控件的点击事件
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {


		switch (checkedId) {
		case R.id.x_rg_a:
			userAnswer = "A";
			judgeUserAnswer(userAnswer, questionAnswer);
			break;
		case R.id.x_rg_b:
			userAnswer = "B";
			judgeUserAnswer(userAnswer, questionAnswer);
			break;
		case R.id.x_rg_c:
			userAnswer = "C";
			judgeUserAnswer(userAnswer, questionAnswer);
			break;
		case R.id.x_rg_d:
			userAnswer = "D";
			judgeUserAnswer(userAnswer, questionAnswer);
			break;
		default:
			break;
		}

	}

	/**
	 * 收藏该题目
	 * @param postion 要收藏的题目id
	 */
	private void showCollectHint(int postion, String kind){
		String questionTitle = questiondata.get(postion).getQuestionTitle();
		String questionOptionA = questiondata.get(postion).getOptionA();
		String questionOptionB = questiondata.get(postion).getOptionB();
		String questionOptionC = questiondata.get(postion).getOptionC();
		String questionOptionD = questiondata.get(postion).getOptionD();
		String questionAnswer = questiondata.get(postion).getAnswer();
		String questionkind = kind;

		// 这里执行数据库的操作语句
		CollectSqlHelper colsqlHelper = new CollectSqlHelper(getApplicationContext());
		ExamQuestionBean eqb = new ExamQuestionBean(questionTitle, questionOptionA, questionOptionB, questionOptionC, questionOptionD, questionAnswer, questionkind);
		colsqlHelper.saveQuestionSql(eqb);
		Toast.makeText(getApplicationContext(), "收藏成功！", 0).show();
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
		if(action == 1){
			tv_R_question.setVisibility(View.GONE);
			tv_E_question.setVisibility(View.GONE);
			tv_M_question.setVisibility(View.GONE);
		}else if(action == 2){
			tv_R_question.setText(String.valueOf(UserF));
			tv_E_question.setText(String.valueOf(UserE));
			tv_M_question.setText(String.valueOf(UserM));
		}
		questionAnsAdp = new QuestionAnsAdp(this, questiondata, UserAnswers, QuestionAnswers, action);
		gridView.setAdapter(questionAnsAdp);

		gridView.setOnItemClickListener(new ItemClickListener());

		popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);	// 取得焦点
		popupWindow.setBackgroundDrawable(new BitmapDrawable());	// 点击空白就会关闭pop窗口
		popupWindow.setAnimationStyle(R.style.animation);	// 动画效果

		// 获取泡泡窗口要显示的地方在哪一个布局文件中
		parent = this.findViewById(R.id.main);
	}

	// 当用户的焦点不再弹出的窗口的时候就让窗口关闭
	private final class ItemClickListener implements OnItemClickListener{
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			changeQuestion(position);
			questionpostion = position;
			if(UserAnswers.get(questionpostion) == null){
				changeRadioButtonEnabledTrue();
				changeRadioButtonCheckedOff();
			}else{
				// 返回上一次用户点击的按钮
				changeUserRodioButtonChecked();
				changeRadioButtonEnabledFalse();
			}
			if(popupWindow.isShowing()) popupWindow.dismiss();	// 关闭
		}
	}



}
