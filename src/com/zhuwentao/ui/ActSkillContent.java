package com.zhuwentao.ui;

import com.zhuwentao.bean.ExamSkillBean;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;


// ¿¼ÊÔ¼¼ÇÉµÄÏêÏ¸ÄÚÈÝ
public class ActSkillContent extends Activity{
	
	private TextView xz_content;
	
	private ExamSkillBean examSkillBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_skill_content);
		MenuTop menutop = (MenuTop)findViewById(R.id.skillcontent_menutop);
		menutop.setTitleText("¿¼ÊÔ¼¼ÇÉ");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
		xz_content = (TextView) findViewById(R.id.tv_skill_content);
		xz_content.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		examSkillBean = (ExamSkillBean) getIntent().getSerializableExtra("xz_skill");
		
		xz_content.setText(examSkillBean.getSkillcontent());
		
	}
	
}
