package com.zhuwentao.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhuwentao.adapter.AdpSkill;
import com.zhuwentao.bean.ExamSkillBean;
import com.zhuwentao.config.Common;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.thread.ExamSkillThread;
import com.zhuwentao.tools.JsonTools;
import com.zhuwentao.widget.MenuTop;

public class ActSkill extends Activity{

	private int startRows=0;	// ��ҳ������

	private AdpSkill adpSkill;	// ������

	private ListView mlistview;

	private List<ExamSkillBean> skilldatas = new ArrayList<ExamSkillBean>();
	private PullToRefreshListView mPullToRefreshListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_skill);
		MenuTop menutop = (MenuTop)findViewById(R.id.skill_menutop);
		menutop.setTitleText("���Լ���");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();

		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_actskill_list);
		mlistview = mPullToRefreshListView.getRefreshableView();
		adpSkill = new AdpSkill(skilldatas, getApplicationContext());

		// �����̻߳�ȡ����
		new ExamSkillThread(handler, Common.URL_GET_EXAMSKILL, startRows).start();

		mlistview.setAdapter(adpSkill);


		mPullToRefreshListView.setMode(Mode.PULL_FROM_END);
		// ���������¼�
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				if(refreshView.isHeaderShown()){
					// ����ˢ��,��Mode����ΪPULL_FROM_END��ʱ��ִ��
				}else{
					new ExamSkillThread(handler, Common.URL_GET_EXAMSKILL, startRows).start();
					adpSkill.notifyDataSetChanged();
					// ˢ�����ر�
					new FinishReftesh().execute();
				}
			}
		});



		// ���õ������
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ת������ҳ��
				Intent intent = new Intent(getApplicationContext(), ActSkillContent.class);
				intent.putExtra("xz_skill", skilldatas.get((int)id));
				startActivity(intent);
				
			}
		});
	}

	private Handler handler = new Handler(){

		public void handleMessage(android.os.Message msg) {
			if(msg.what == Common.MSG_GET_EXAMSKILL){
				String json = (String) msg.obj;
				if(json == ""){

				}else{
					List<ExamSkillBean> skills = new ArrayList<ExamSkillBean>();
					skills = JsonTools.parseExamSkillJson(json);
					for(int i=0; i<skills.size(); i++){
						skilldatas.add(skills.get(i));
					}
					adpSkill.getSkillData(skilldatas);
					adpSkill.notifyDataSetChanged();
					startRows = adpSkill.getCount();
				}
			}
		}
	};











	// ������������ݺ�ر�
	private class FinishReftesh extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mPullToRefreshListView.onRefreshComplete();
		}

	}
}
