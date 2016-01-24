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

// �鿴�ղصĴ�����Ŀ
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



	private int questionpostion=0;	// ��ǰ����Ŀ


	// �����洢�����ݿ��л�ȡ������������
	private List<ExamQuestionBean> ExamQuestiondata = new ArrayList<ExamQuestionBean>();

	private ErrorSqlHelper errorSqlHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_error_list);
		MenuTop menu = (MenuTop) findViewById(R.id.error_list_menutop);
		menu.setTitleText("���⼯");
		menu.setCollectBackground(R.drawable.select_delete_collect);

		// ���ô��⿨��ť�ĵ���¼�
		menu.setAnswerCardListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPopuwindow(3);
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}
		});
		// �����ɾ�����ղ�
		menu.setCollectListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ɾ���ղص���Ŀ
				if(ExamQuestiondata.size() != 0){
					deleteErrorQuestion();
				}
			}
		});

		// ��ʼ���ؼ�
		init();
		// ��ʾ��Ŀ
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
	 * ��ʾ�������Ŀ
	 */
	public void showQuestion(){
		// 1�������ݿ��л�ȡ���ݲ��洢��ExamQuestiondata
		String kind = getIntent().getStringExtra("errorquestion_kind");
		ExamQuestiondata = errorSqlHelper.getErrorSqlKind(kind);
		// 2��������ݿ����Ƿ�������
		if(ExamQuestiondata.size() != 0){
			// 3���ѵ�ǰ������չʾ�ڽ�����
			changeQuestion(questionpostion);
		}else{
			Toast.makeText(getApplicationContext(), "��û������Ŷ", 0).show();
		}


		// ��һ��
		mbtnUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(ExamQuestiondata.size() != 0){
					if(questionpostion == 0){
						Toast.makeText(getApplicationContext(), "�Ѿ��ǵ�һ��", 0).show();
					}else{
						// ���ݲ��ܴ�0��ʼ
						questionpostion = questionpostion-1;
						changeQuestion(questionpostion);
					}
				}
			}
		});
		// �鿴��
		mbtnAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ExamQuestiondata.size() != 0){
					showQuestionAnswer();
				}
			}
		});
		// ��һ��
		mbtnDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ExamQuestiondata.size() != 0){
					if(questionpostion == ExamQuestiondata.size()-1){
						Toast.makeText(getApplicationContext(), "�Ѿ������һ��", 0).show();
					}else{
						// ���ݲ��ܴ�0��ʼ
						questionpostion = questionpostion+1;
						changeQuestion(questionpostion);
					}
				}
			}
		});
	}

	// �ı䵱ǰҳ�����Ŀ
	private void changeQuestion(int i){
		mTitle.setText(ExamQuestiondata.get(i).getQuestionTitle());
		mOptionA.setText(ExamQuestiondata.get(i).getOptionA());
		mOptionB.setText(ExamQuestiondata.get(i).getOptionB());
		mOptionC.setText(ExamQuestiondata.get(i).getOptionC());
		mOptionD.setText(ExamQuestiondata.get(i).getOptionD());
		// �Ѵ𰸴洢���ֶ���
		//			mbtnAnswer = ExamQuestiondata.get(i).getAnswer();
	}

	// ��ȡ���ݵ�ǰ��id����ʾ��Ŀ�Ĵ�
	private void showQuestionAnswer(){
		Toast.makeText(getApplicationContext(), "��ȷ��Ϊ��"+ExamQuestiondata.get(questionpostion).getAnswer(), 0).show();
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
		popupWindow.setFocusable(true);	// ȡ�ý���
		popupWindow.setBackgroundDrawable(new BitmapDrawable());	// ����հ׾ͻ�ر�pop����
		popupWindow.setAnimationStyle(R.style.animation);	// ����Ч��

		// ��ȡ���ݴ���Ҫ��ʾ�ĵط�����һ�������ļ���
		parent = this.findViewById(R.id.error_main);
	}

	// ɾ���ղ�
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
			mTitle.setText("��������");
			mOptionA.setText("��������");
			mOptionB.setText("��������");
			mOptionC.setText("��������");
			mOptionD.setText("��������");
		}
		errorSqlHelper.DeleteErrorSql(eqb);
	}


	// ���û��Ľ��㲻�ٵ����Ĵ��ڵ�ʱ����ô��ڹر�
	private final class ItemClickListener implements OnItemClickListener{
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			changeQuestion(position);
			questionpostion = position;
			if(popupWindow.isShowing()){
				popupWindow.dismiss();	// �ر�
			}
		}
	}



}
