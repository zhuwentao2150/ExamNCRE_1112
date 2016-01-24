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

	// 用于存放试卷标题
	private List<ExamBean> examtitledata = new ArrayList<ExamBean>();

	// 题目的种类
	private String kind;

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == Common.MSG_GET_EXAM){
				String json = (String) msg.obj;
				if(json == ""){
					// 提示
					Toast.makeText(getApplicationContext(), "没有找到试卷", Toast.LENGTH_SHORT).show();
				}else{
					// 从服务器端获取数据
					examtitledata = JsonTools.parseExamJson(json);
					// 把数据绑定到适配器中
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
		menutop.setTitleText("试卷");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		// 初始化控件
		infoView();
		// 获取请求显示的考试试卷种类
		kind = getIntent().getStringExtra("examtitle_kind");
		getExamTitle(kind);



		// 设置点击试卷
		mlistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里的id指的是数据库中对应的试卷id
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
