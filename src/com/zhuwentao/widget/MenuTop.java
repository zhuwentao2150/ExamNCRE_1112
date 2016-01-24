package com.zhuwentao.widget;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhuwentao.examncre_1112.R;

public class MenuTop extends FrameLayout{

	private Button mBtnLeft;
	
	private TextView mTopTitle;
	
	// �������ϵİ�ť
	private Button menu_answer_card;	// ���⿨��ť
	private Button menu_collect;	// �ղذ�ť

	public MenuTop(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.menu_top, this);


		mTopTitle = (TextView)findViewById(R.id.tv_menutop_title);
		mBtnLeft = (Button) findViewById(R.id.btn_menutop_left);
		menu_answer_card = (Button) findViewById(R.id.menu_answer_card);
		menu_collect = (Button) findViewById(R.id.menu_collect);

		//��ߵ������ ����ҳ��
		mBtnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity)getContext()).finish();
			}
		});
	}
	
	// ���ñ���ͼƬ
	public void setCollectBackground(int drawable){
		menu_collect.setBackgroundResource(drawable);
	}
	
	// ���ô��⿨�ĵ���¼�����
	public void setAnswerCardListener(OnClickListener listener){
		menu_answer_card.setOnClickListener(listener);
	}
	// ���õ���ղذ�ť�¼�����
	public void setCollectListener(OnClickListener listener){
		menu_collect.setOnClickListener(listener);
	}
	

	// �����м�Ҫ��ʾ�ı���
	public void setTitleText(String s){
		mTopTitle.setText(s);
	}
	// �����ұ߰�ť�ĵ���¼�
	public void setTitleLeftMenuGone(){
		menu_answer_card.setVisibility(View.GONE);
	}
	
	
	// ���ô��⿨��ť�ɼ�
	public void setMenuAnswerCardVisible(){
		menu_answer_card.setVisibility(View.VISIBLE);
	}
	// ���ô��⿨��ť���ɼ�
	public void setMenuAnswerCardGone(){
		menu_answer_card.setVisibility(View.GONE);
	}
	
	// �����ղذ�ť�ɼ�
	public void setMenuCollectVisible(){
		menu_collect.setVisibility(View.VISIBLE);
	}
	// �����ղذ�ť���ɼ�
	public void setMenuCollectGone(){
		menu_collect.setVisibility(View.GONE);
	}
	
	// ���÷��ذ�ťΪ���ɼ�
	public void setMenuLeftGone(){
		mBtnLeft.setVisibility(View.GONE);
	}

}
