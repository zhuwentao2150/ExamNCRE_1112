package com.zhuwentao.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuwentao.bean.ExamOutlineBean;
import com.zhuwentao.config.Common;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.thread.ExamOutlineThread;
import com.zhuwentao.tools.JsonTools;
import com.zhuwentao.widget.MenuTop;

public class ActOutlineContent extends Activity{

	private TextView outlineContent;
	
	private String outlinekey;
	
	private List<ExamOutlineBean> examoutlinedata = new ArrayList<ExamOutlineBean>();
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == Common.MSG_GET_EXAMOUTLINE){
				String json = (String) msg.obj;
				if(json == ""){
					Toast.makeText(getApplicationContext(), "Ã»ÓÐÍøÂç", Toast.LENGTH_SHORT).show();
				}else{
					examoutlinedata = JsonTools.parseExamOutlineJson(json);
					outlineContent.setText(examoutlinedata.get(0).getOutlinecontent());
				}
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_outline_content);
		MenuTop menutop = (MenuTop)findViewById(R.id.outlinecontent_menutop);
		menutop.setTitleText("¿¼ÊÔ´ó¸Ù");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
		outlineContent = (TextView) findViewById(R.id.tv_outline_content);
		outlineContent.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		outlinekey = getIntent().getStringExtra("outlinekey");
		
		showOutlineData(outlinekey);
	}
	
	private void showOutlineData(String key){
		new ExamOutlineThread(handler, Common.URL_GET_EXAMOUTLINE, key).start();
	}
}
