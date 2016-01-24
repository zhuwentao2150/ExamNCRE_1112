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
	
	// 标题栏上的按钮
	private Button menu_answer_card;	// 答题卡按钮
	private Button menu_collect;	// 收藏按钮

	public MenuTop(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.menu_top, this);


		mTopTitle = (TextView)findViewById(R.id.tv_menutop_title);
		mBtnLeft = (Button) findViewById(R.id.btn_menutop_left);
		menu_answer_card = (Button) findViewById(R.id.menu_answer_card);
		menu_collect = (Button) findViewById(R.id.menu_collect);

		//左边点击返回 结束页面
		mBtnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity)getContext()).finish();
			}
		});
	}
	
	// 设置背景图片
	public void setCollectBackground(int drawable){
		menu_collect.setBackgroundResource(drawable);
	}
	
	// 设置答题卡的点击事件监听
	public void setAnswerCardListener(OnClickListener listener){
		menu_answer_card.setOnClickListener(listener);
	}
	// 设置点击收藏按钮事件监听
	public void setCollectListener(OnClickListener listener){
		menu_collect.setOnClickListener(listener);
	}
	

	// 设置中间要显示的标题
	public void setTitleText(String s){
		mTopTitle.setText(s);
	}
	// 设置右边按钮的点击事件
	public void setTitleLeftMenuGone(){
		menu_answer_card.setVisibility(View.GONE);
	}
	
	
	// 设置答题卡按钮可见
	public void setMenuAnswerCardVisible(){
		menu_answer_card.setVisibility(View.VISIBLE);
	}
	// 设置答题卡按钮不可见
	public void setMenuAnswerCardGone(){
		menu_answer_card.setVisibility(View.GONE);
	}
	
	// 设置收藏按钮可见
	public void setMenuCollectVisible(){
		menu_collect.setVisibility(View.VISIBLE);
	}
	// 设置收藏按钮不可见
	public void setMenuCollectGone(){
		menu_collect.setVisibility(View.GONE);
	}
	
	// 设置返回按钮为不可见
	public void setMenuLeftGone(){
		mBtnLeft.setVisibility(View.GONE);
	}

}
