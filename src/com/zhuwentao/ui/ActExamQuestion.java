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
	private RadioButton btn_moren;	// �����ť���ý���Ĭ�ϵ��е�
	private Button btn_up;	// ��һ��İ�ť
	private Button btn_down;	// ��һ��İ�ť
	private Button btn_answer;	// ����Ľ���

	private int questionpostion=0;	// ��ǰ����Ŀ
	private String userAnswer;	// �û��Ĵ�
	private String questionAnswer;	// ����Ĵ��
	private int UserF=0;	// �û��ĵ÷�
	private int UserE=0;	// �û������
	private int UserM=0; 	// �û�û����

	private String kind;	// �Ծ������

	private boolean kzq = true; 	// ���ƴ���洢���ݵĴ���

	private TextView tv_R_question;
	private TextView tv_E_question;
	private TextView tv_M_question;
	private QuestionAnsAdp questionAnsAdp;
	private PopupWindow popupWindow;
	private View parent;

	private ErrorSqlHelper errorSqlHelper;


	// ��������
	private List<ExamQuestionBean> questiondata = new ArrayList<ExamQuestionBean>();
	// �û��Ĵ�
	private Map<Integer, String> UserAnswers = new HashMap<Integer, String>();
	// ��Ŀ����ȷ��
	private Map<Integer, String> QuestionAnswers = new HashMap<Integer, String>();

	//	private TextView tv_R_question;
	//	private TextView tv_E_question;
	//	private TextView tv_M_question;


	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == Common.MSG_GET_EXAMQUESTION){
				String json = (String) msg.obj;
				if(json == ""){
					Toast.makeText(getApplicationContext(), "û������", Toast.LENGTH_SHORT).show();
				}else{
					questiondata = JsonTools.parseExamQuestionJson(json);
					// ������Ĵ𰸴洢��QuestionAnswer��
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
		menu.setTitleText("����");
		// ���ô��⿨��ť�ĵ���¼�
		menu.setAnswerCardListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPopuwindow(1);
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}
		});
		// �����ղذ�ť�ĵ���¼�
		menu.setCollectListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCollectHint(questionpostion, kind);
			}
		});

		errorSqlHelper = new ErrorSqlHelper(getApplicationContext());

		// ��ʼ���ؼ�
		Init();
		// ��ʾ��Ŀ
		showQuestion();


	}

	// ��ʼ���ؼ�
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



	// ��ʾ����Ŀ
	private void showQuestion(){

		// ����Ծ�id
		int id = getIntent().getIntExtra("question_id", 0);
		// ������ȡ���ݵ��߳�
		new ExamQuestionThread(handler, Common.URL_GET_EXAMQUESTION, id).start();

		rg.setOnCheckedChangeListener(this);

		// ������߰�ť�ĵ���¼�
		btn_up.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(questionpostion == 0){
					Toast.makeText(getApplicationContext(), "�Ѿ��ǵ�һ��", 0).show();
				}else{
					// ���ݲ��ܴ�0��ʼ
					questionpostion = questionpostion-1;
					changeQuestion(questionpostion);

					if(UserAnswers.get(questionpostion) == null){
						changeRadioButtonEnabledTrue();
						changeRadioButtonCheckedOff();
					}else{
						// ������һ���û�����İ�ť
						changeUserRodioButtonChecked();
						changeRadioButtonEnabledFalse();
					}
				}

			}
		});

		// �����ұ߰�ť�ĵ���¼�
		btn_down.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// ����Ĭ���Ǵ�0��ʼ�ģ�������Ҫ��1
				if(questionpostion == questiondata.size()-1){
					Toast.makeText(getApplicationContext(), "�Ѿ������һ��", 0).show();
				}else{
					// ���ڵ�һ����ͷ��0������Ҫ��1
					questionpostion = questionpostion+1;
					changeQuestion(questionpostion);
					if(UserAnswers.get(questionpostion) == null){
						changeRadioButtonEnabledTrue();
						changeRadioButtonCheckedOff();
					}else{
						// ������һ���û�����İ�ť
						changeUserRodioButtonChecked();
						changeRadioButtonEnabledFalse();
					}
				}
			}
		});

		// ���û��������е���Ŀ�󣬾���ʾ�������ť���������ִ��⿨ҳ�棬�����û��÷�
		btn_answer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �û���ǰ�ڴ����Ŀ����Ϊ���ɵ��
				changeRadioButtonEnabledFalse();

				if(kzq){
					// ���������ж��û��Ĵ𰸺���Ŀ�Ĵ��Ƿ�һ��
					for(int i=0; i < QuestionAnswers.size(); i++){
						// ����û�û����д�𰸣���Ѵ𰸱��E�����Ǵ������˼
						if(UserAnswers.get(i) == null){
							UserAnswers.put(i, "E");
							UserM++;	// û������������
						}
						if(UserAnswers.get(i).equals(QuestionAnswers.get(i))){
							// ��ͬ�Ĵ𰸣���������������û��ķ���
							UserF++;	// ���Ե�������
						}else{
							// TODO
							// ��δ���ִֻ��һ��
							ExamQuestionBean eqb = questiondata.get(i);
							eqb.setQuestionkind(kind);
							errorSqlHelper.saveErrorSql(eqb);
						}
					}
					kzq = false;
				}
				// ������û��������Ŀ��
				UserE = QuestionAnswers.size()-(UserF+UserM);
				// ��ʾ���ݴ���
				showPopuwindow(2);
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}
		});
	}

	// ���ѡ��
	private void changeRadioButtonCheckedOff(){
		// ��������صİ�ťĬ�ϵ��У����������İ�ť�Ϳ���ѡ����
		btn_moren.setChecked(true);
	}

	// ���ð�ťΪ��ѡ״̬
	private void changeRadioButtonEnabledTrue(){
		btn_xA.setEnabled(true);
		btn_xB.setEnabled(true);
		btn_xC.setEnabled(true);
		btn_xD.setEnabled(true);
	}

	// ���ð�ťΪ����ѡ��״̬
	private void changeRadioButtonEnabledFalse(){
		btn_xA.setEnabled(false);
		btn_xB.setEnabled(false);
		btn_xC.setEnabled(false);
		btn_xD.setEnabled(false);
	}

	// �û��Ѿ�ѡ���˵�ѡ��
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

	// �ı䵱ǰҳ�����Ŀ
	private void changeQuestion(int i){
		tv_title.setText(questiondata.get(i).getQuestionTitle());
		btn_xA.setText(questiondata.get(i).getOptionA());
		btn_xB.setText(questiondata.get(i).getOptionB());
		btn_xC.setText(questiondata.get(i).getOptionC());
		btn_xD.setText(questiondata.get(i).getOptionD());
		questionAnswer = questiondata.get(i).getAnswer();
	}

	// ���û�����ļ��뵽�����У���˳��������ʹ����ϵ��ʱ����ӣ�ÿ���û�����𰸺�Ͷ��û��Ĵ�𰸽����ж�
	private void judgeUserAnswer(String userAnswer, String questionAnswer){
		// UserAnswers�����洢�û�����Ĵ�
		UserAnswers.put(questionpostion, userAnswer);
		// �ж��ǲ������һ�⣬��������һ�����ʾ�ύ
		if(questionpostion == questiondata.size()-1){
			//				btn_down.setClickable(false);	// ����Ϊ���ɵ����״̬
			btn_answer.setVisibility(View.VISIBLE);
		}
	}

	// ���ð�ť�Ŀؼ��ĵ���¼�
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
	 * �ղظ���Ŀ
	 * @param postion Ҫ�ղص���Ŀid
	 */
	private void showCollectHint(int postion, String kind){
		String questionTitle = questiondata.get(postion).getQuestionTitle();
		String questionOptionA = questiondata.get(postion).getOptionA();
		String questionOptionB = questiondata.get(postion).getOptionB();
		String questionOptionC = questiondata.get(postion).getOptionC();
		String questionOptionD = questiondata.get(postion).getOptionD();
		String questionAnswer = questiondata.get(postion).getAnswer();
		String questionkind = kind;

		// ����ִ�����ݿ�Ĳ������
		CollectSqlHelper colsqlHelper = new CollectSqlHelper(getApplicationContext());
		ExamQuestionBean eqb = new ExamQuestionBean(questionTitle, questionOptionA, questionOptionB, questionOptionC, questionOptionD, questionAnswer, questionkind);
		colsqlHelper.saveQuestionSql(eqb);
		Toast.makeText(getApplicationContext(), "�ղسɹ���", 0).show();
	}



	/**
	 * ��ʾ���ݴ���
	 * @param action 1=���⿨��2=�Ծ���
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
		popupWindow.setFocusable(true);	// ȡ�ý���
		popupWindow.setBackgroundDrawable(new BitmapDrawable());	// ����հ׾ͻ�ر�pop����
		popupWindow.setAnimationStyle(R.style.animation);	// ����Ч��

		// ��ȡ���ݴ���Ҫ��ʾ�ĵط�����һ�������ļ���
		parent = this.findViewById(R.id.main);
	}

	// ���û��Ľ��㲻�ٵ����Ĵ��ڵ�ʱ����ô��ڹر�
	private final class ItemClickListener implements OnItemClickListener{
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			changeQuestion(position);
			questionpostion = position;
			if(UserAnswers.get(questionpostion) == null){
				changeRadioButtonEnabledTrue();
				changeRadioButtonCheckedOff();
			}else{
				// ������һ���û�����İ�ť
				changeUserRodioButtonChecked();
				changeRadioButtonEnabledFalse();
			}
			if(popupWindow.isShowing()) popupWindow.dismiss();	// �ر�
		}
	}



}
