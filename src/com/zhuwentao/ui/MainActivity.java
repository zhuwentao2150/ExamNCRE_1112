package com.zhuwentao.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

public class MainActivity extends Activity implements OnClickListener{

	private Button mbtn_g1;
	private Button mbtn_g2;
	private Button mbtn_g3;
	private Button mbtn_g4;
	private Button mbtn_g5; // 技巧
	private Button mbtn_g6; // 考试大纲
	private Button mbtn_g7; // 美文
	private Button mbtn_g8; // 关于软件


	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点
	private int currentItem = 0; // 当前图片的索引号
	private long mExitTime;
	private ScheduledExecutorService scheduledExecutorService;	// 定时执行某项任务


	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 切换当前显示的图片
			viewPager.setCurrentItem(currentItem);
		};
	};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MenuTop menutop = (MenuTop)findViewById(R.id.main_menutop);
		menutop.setTitleText("计算机等级考试");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		menutop.setMenuLeftGone();

		// 初始化控件
		infoView();
		// 图片轮播
		initViewPager();

	}

	private void infoView(){
		mbtn_g1 = (Button) findViewById(R.id.btn_main_01);
		mbtn_g2 = (Button) findViewById(R.id.btn_main_02);
		mbtn_g3 = (Button) findViewById(R.id.btn_main_03);
		mbtn_g4 = (Button) findViewById(R.id.btn_main_04);
		mbtn_g5 = (Button) findViewById(R.id.btn_main_05);
		mbtn_g6 = (Button) findViewById(R.id.btn_main_06);
		mbtn_g7 = (Button) findViewById(R.id.btn_main_07);
		mbtn_g8 = (Button) findViewById(R.id.btn_main_08);

		mbtn_g1.setOnClickListener(this);
		mbtn_g2.setOnClickListener(this);
		mbtn_g3.setOnClickListener(this);
		mbtn_g4.setOnClickListener(this);
		mbtn_g5.setOnClickListener(this);
		mbtn_g6.setOnClickListener(this);
		mbtn_g7.setOnClickListener(this);
		mbtn_g8.setOnClickListener(this);
	}


	// 初始化图片轮播
	private void initViewPager(){
		// 把图片资源ID放在数组中
		imageResId = new int[] { R.drawable.main_one, R.drawable.main_two, R.drawable.main_three, R.drawable.main_four, R.drawable.main_five };

		// 初始化图片资源
		imageViews = new ArrayList<ImageView>();
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.v_dot0));
		dots.add(findViewById(R.id.v_dot1));
		dots.add(findViewById(R.id.v_dot2));
		dots.add(findViewById(R.id.v_dot3));
		dots.add(findViewById(R.id.v_dot4));
		viewPager = (ViewPager) findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	// 设置小点的事件监听
	private class MyPageChangeListener implements OnPageChangeListener{

		private int oldPosition = 0;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			currentItem = position;
			/* tv_title.setText(titles[position]); */
			// 老页面的原点变成未选中状态
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			// 新页面的点变成选中状态
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}
	}

	// 填充ViewPager的适配器
	private class MyAdapter extends PagerAdapter {
		// 只需要实现前面的四个方法
		public int getCount() {
			return imageResId.length;
		}

		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}
		// 用于判断当前要显示的界面
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}


	}

	// 切换图片的线程
	private class ScrollTask implements Runnable {
		public void run() {
			// 加锁
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}
	}

	// 界面的点击事件
	@Override
	public void onClick(View v) {

		Intent intent = null;

		switch (v.getId()) {
		case R.id.btn_main_01:
			// 国一
			intent = new Intent(this, ActExamOne.class);
			break;
		case R.id.btn_main_02:
			// 国二
			intent = new Intent(this, ActExamTwo.class);
			break;
		case R.id.btn_main_03:
			// 国三
			intent = new Intent(this, ActExamThree.class);
			break;
		case R.id.btn_main_04:
			// 国四
			intent = new Intent(this, ActExamFour.class);
			break;
		case R.id.btn_main_05:
			// 考试技巧
			intent = new Intent(this, ActSkill.class);
			break;
		case R.id.btn_main_06:
			// 考试大纲
			intent = new Intent(this, ActOutline.class);
			break;
		case R.id.btn_main_07:
			// 美文
			intent = new Intent(this, ActStoryWeb.class);
			break;
		case R.id.btn_main_08:
			// 关于软件
			intent = new Intent(this, ActAbout.class);
			break;

		default:
			break;
		}
		startActivity(intent);
	}


	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		// 该方法是个定时执行的工具，参数1：要执行的线程，参数2：初始化延时,参数3：每次执行的时间间隔,参数4：计时单位
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
		super.onStart();
	}
	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}




	// 判断两次点击退出jj
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			if((System.currentTimeMillis() - mExitTime) > 2000){
				Toast.makeText(this, "再按一次退出程序", 0).show();
				mExitTime = System.currentTimeMillis();	// 更新时间
			}else{
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
