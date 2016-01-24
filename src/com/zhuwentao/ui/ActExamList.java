package com.zhuwentao.ui;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhuwentao.adapter.AdpExamList;
import com.zhuwentao.bean.ExamBean;
import com.zhuwentao.config.Common;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.thread.ExamThread;
import com.zhuwentao.tools.JsonTools;
import com.zhuwentao.widget.MenuTop;

public class ActExamList extends Activity {

	private AdpExamList adpExamList;
	private ListView mlistview;
	private PullToRefreshListView mPullToRefreshListView;

	// ���ڴ���Ծ����
	private List<ExamBean> examtitledata = new ArrayList<ExamBean>();

	// ��Ŀ������
	private String kind;

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == Common.MSG_GET_EXAM){
				String json = (String) msg.obj;
				if(json == ""){
					// ��ʾ
					Toast.makeText(getApplicationContext(), "û���ҵ��Ծ�", Toast.LENGTH_SHORT).show();
				}else{
					// �ӷ������˻�ȡ����
					examtitledata = JsonTools.parseExamJson(json);
					// �����ݰ󶨵���������
					adpExamList = new AdpExamList(examtitledata, getApplicationContext());
					mlistview.setAdapter(adpExamList);
				}
			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_list);
		MenuTop menutop = (MenuTop) findViewById(R.id.exam_list_menutop);
		menutop.setTitleText("�Ծ�");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		// ��ʼ���ؼ�
		infoView();
		// ��ȡ������ʾ�Ŀ����Ծ�����
		kind = getIntent().getStringExtra("examtitle_kind");
		getExamTitle(kind);



		// ���õ���Ծ�
		mlistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �����idָ�������ݿ��ж�Ӧ���Ծ�id
				int examid = (int) id;
				Intent intent = new Intent(getApplicationContext(), ActExamQuestion.class);
				intent.putExtra("question_id", examid);
				intent.putExtra("question_kind", kind);
				startActivity(intent);
			}
		});

	}

	public void infoView(){
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_actexam_list);
		mlistview = mPullToRefreshListView.getRefreshableView();
	}



	public void getExamTitle(String kind){
		new ExamThread(handler, Common.URL_GET_EXAM, kind).start();
	}
}
