package com.zhuwentao.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

public class ActExamThree extends Activity implements OnClickListener{
	
	
	private Button btn_examthree_01;	// 网络技术	wljs
	private Button btn_examthree_02;	// 数据库技术	sjkjs
	private Button btn_examthree_03;	// 软件测试技术	rjcsjs
	private Button btn_examthree_04;	// 信息安全技术	xxaqjs
	private Button btn_examthree_05; 	// 嵌入式系统开发技术	qrsxtkfjs
	
	private Button btn_examthree_06; 	// 错题
	private Button btn_examthree_07; 	// 收藏 
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_three);
		MenuTop menutop = (MenuTop)findViewById(R.id.three_menutop);
		menutop.setTitleText("计算机三级");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
		infoView();
	}
	
	
	private void infoView() {
		btn_examthree_01 = (Button) findViewById(R.id.btn_examthree_01);
		btn_examthree_02 = (Button) findViewById(R.id.btn_examthree_02);
		btn_examthree_03 = (Button) findViewById(R.id.btn_examthree_03);
		btn_examthree_04 = (Button) findViewById(R.id.btn_examthree_04);
		btn_examthree_05 = (Button) findViewById(R.id.btn_examthree_05);
		btn_examthree_06 = (Button) findViewById(R.id.btn_examthree_06);
		btn_examthree_07 = (Button) findViewById(R.id.btn_examthree_07);

		btn_examthree_01.setOnClickListener(this);
		btn_examthree_02.setOnClickListener(this);
		btn_examthree_03.setOnClickListener(this);
		btn_examthree_04.setOnClickListener(this);
		btn_examthree_05.setOnClickListener(this);
		btn_examthree_06.setOnClickListener(this);
		btn_examthree_07.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_examthree_01:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "wljs");
			startActivity(intent);
			break;
		case R.id.btn_examthree_02:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "sjkjs");
			startActivity(intent);
			break;
		case R.id.btn_examthree_03:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "rjcsjs");
			startActivity(intent);
			break;
		case R.id.btn_examthree_04:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "xxaqjs");
			startActivity(intent);
			break;
		case R.id.btn_examthree_05:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "qrsxtkfjs");
			startActivity(intent);
			break;
		case R.id.btn_examthree_06:
			showNCREdialogError();
			break;
		case R.id.btn_examthree_07:
			// 这里弹出一个对话框，要求用户选择要查看的收藏种类
			showNCREdialog();
			break;

		default:
			break;
		}
	}
	
	private void showNCREdialog(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("请选择种类")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"网络技术","数据库技术","软件测试技术","信息安全技术","嵌入式系统开发技术"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String collectquestion_kind = "collectquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "wljs");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "sjkjs");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "rjcsjs");
					startActivity(intent);
					finish();
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "xxaqjs");
					startActivity(intent);
					finish();
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "qrsxtkfjs");
					startActivity(intent);
					finish();
					break;

				default:
					break;
				}
				
			}
		})
		.setNegativeButton("取消", null)
		.show();
	}
	
	private void showNCREdialogError(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("请选择种类")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"网络技术","数据库技术","软件测试技术","信息安全技术","嵌入式系统开发技术"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String errorquestion_kind = "errorquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "wljs");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "sjkjs");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "rjcsjs");
					startActivity(intent);
					finish();
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "xxaqjs");
					startActivity(intent);
					finish();
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "qrsxtkfjs");
					startActivity(intent);
					finish();
					break;

				default:
					break;
				}
			}
		})
		.setNegativeButton("取消", null)
		.show();
	}
}
