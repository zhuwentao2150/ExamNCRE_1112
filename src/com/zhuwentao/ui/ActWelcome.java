package com.zhuwentao.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.zhuwentao.examncre_1112.R;

public class ActWelcome extends Activity{

	private View view;
	private Context context;
	private Animation animation;
	
	private static int TIME = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.act_welcome, null);
		setContentView(view);
		context = this;
	}

	@Override
	protected void onResume() {
		init();
		super.onResume();
	}
	private void init(){
		animation = AnimationUtils.loadAnimation(this, R.anim.main_dongzuo);
		// 给view设置动画效果
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			@Override
			public void onAnimationEnd(Animation arg0) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent;
						intent = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(intent);
						// 设置Activity的切换效果
						overridePendingTransition(R.anim.welcome_in_right, R.anim.welcome_out_right);
						ActWelcome.this.finish();
					}
				}, TIME);
			}
		});
	}
}
