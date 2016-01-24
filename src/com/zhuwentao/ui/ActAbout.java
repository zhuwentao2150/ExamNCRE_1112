package com.zhuwentao.ui;

import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

import android.app.Activity;
import android.os.Bundle;


// 关于软件
public class ActAbout extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about);
		MenuTop menutop = (MenuTop)findViewById(R.id.about_menutop);
		menutop.setTitleText("关于软件");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
	}
	
}
