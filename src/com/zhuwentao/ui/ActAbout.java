package com.zhuwentao.ui;

import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

import android.app.Activity;
import android.os.Bundle;


// �������
public class ActAbout extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about);
		MenuTop menutop = (MenuTop)findViewById(R.id.about_menutop);
		menutop.setTitleText("�������");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
	}
	
}
