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
	private Button mbtn_g5; // ����
	private Button mbtn_g6; // ���Դ��
	private Button mbtn_g7; // ����
	private Button mbtn_g8; // �������


	private ViewPager viewPager; // android-support-v4�еĻ������
	private List<ImageView> imageViews; // ������ͼƬ����
	private int[] imageResId; // ͼƬID
	private List<View> dots; // ͼƬ�������ĵ���Щ��
	private int currentItem = 0; // ��ǰͼƬ��������
	private long mExitTime;
	private ScheduledExecutorService scheduledExecutorService;	// ��ʱִ��ĳ������


	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// �л���ǰ��ʾ��ͼƬ
			viewPager.setCurrentItem(currentItem);
		};
	};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MenuTop menutop = (MenuTop)findViewById(R.id.main_menutop);
		menutop.setTitleText("������ȼ�����");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		menutop.setMenuLeftGone();

		// ��ʼ���ؼ�
		infoView();
		// ͼƬ�ֲ�
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


	// ��ʼ��ͼƬ�ֲ�
	private void initViewPager(){
		// ��ͼƬ��ԴID����������
		imageResId = new int[] { R.drawable.main_one, R.drawable.main_two, R.drawable.main_three, R.drawable.main_four, R.drawable.main_five };

		// ��ʼ��ͼƬ��Դ
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
		// ����һ������������ViewPager�е�ҳ��ı�ʱ����
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	// ����С����¼�����
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
			// ��ҳ���ԭ����δѡ��״̬
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			// ��ҳ��ĵ���ѡ��״̬
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}
	}

	// ���ViewPager��������
	private class MyAdapter extends PagerAdapter {
		// ֻ��Ҫʵ��ǰ����ĸ�����
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
		// �����жϵ�ǰҪ��ʾ�Ľ���
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}


	}

	// �л�ͼƬ���߳�
	private class ScrollTask implements Runnable {
		public void run() {
			// ����
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // ͨ��Handler�л�ͼƬ
			}
		}
	}

	// ����ĵ���¼�
	@Override
	public void onClick(View v) {

		Intent intent = null;

		switch (v.getId()) {
		case R.id.btn_main_01:
			// ��һ
			intent = new Intent(this, ActExamOne.class);
			break;
		case R.id.btn_main_02:
			// ����
			intent = new Intent(this, ActExamTwo.class);
			break;
		case R.id.btn_main_03:
			// ����
			intent = new Intent(this, ActExamThree.class);
			break;
		case R.id.btn_main_04:
			// ����
			intent = new Intent(this, ActExamFour.class);
			break;
		case R.id.btn_main_05:
			// ���Լ���
			intent = new Intent(this, ActSkill.class);
			break;
		case R.id.btn_main_06:
			// ���Դ��
			intent = new Intent(this, ActOutline.class);
			break;
		case R.id.btn_main_07:
			// ����
			intent = new Intent(this, ActStoryWeb.class);
			break;
		case R.id.btn_main_08:
			// �������
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
		// ��Activity��ʾ������ÿ�������л�һ��ͼƬ��ʾ
		// �÷����Ǹ���ʱִ�еĹ��ߣ�����1��Ҫִ�е��̣߳�����2����ʼ����ʱ,����3��ÿ��ִ�е�ʱ����,����4����ʱ��λ
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
		super.onStart();
	}
	@Override
	protected void onStop() {
		// ��Activity���ɼ���ʱ��ֹͣ�л�
		scheduledExecutorService.shutdown();
		super.onStop();
	}




	// �ж����ε���˳�jj
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			if((System.currentTimeMillis() - mExitTime) > 2000){
				Toast.makeText(this, "�ٰ�һ���˳�����", 0).show();
				mExitTime = System.currentTimeMillis();	// ����ʱ��
			}else{
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
