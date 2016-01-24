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

public class ActExamTwo extends Activity implements OnClickListener {
	
	private Button btn_examtwo_01;	// c
	private Button btn_examtwo_02;	// c++
	private Button btn_examtwo_03;	// vb
	private Button btn_examtwo_04;	// java
	private Button btn_examtwo_05; 	// access
	private Button btn_examtwo_06; 	// MS Office
	
	private Button btn_examtwo_07; 	// 错题按钮
	private Button btn_examtwo_08; 	// 收藏按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_two);
		MenuTop menutop = (MenuTop)findViewById(R.id.two_menutop);
		menutop.setTitleText("计算机二级");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
		
		infoView();
	}
	
	private void infoView() {
		btn_examtwo_01 = (Button) findViewById(R.id.btn_examtwo_01);
		btn_examtwo_02 = (Button) findViewById(R.id.btn_examtwo_02);
		btn_examtwo_03 = (Button) findViewById(R.id.btn_examtwo_03);
		btn_examtwo_04 = (Button) findViewById(R.id.btn_examtwo_04);
		btn_examtwo_05 = (Button) findViewById(R.id.btn_examtwo_05);
		btn_examtwo_06 = (Button) findViewById(R.id.btn_examtwo_06);
		btn_examtwo_07 = (Button) findViewById(R.id.btn_examtwo_07);
		btn_examtwo_08 = (Button) findViewById(R.id.btn_examtwo_08);

		btn_examtwo_01.setOnClickListener(this);
		btn_examtwo_02.setOnClickListener(this);
		btn_examtwo_03.setOnClickListener(this);
		btn_examtwo_04.setOnClickListener(this);
		btn_examtwo_05.setOnClickListener(this);
		btn_examtwo_06.setOnClickListener(this);
		btn_examtwo_07.setOnClickListener(this);
		btn_examtwo_08.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_examtwo_01:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "c");
			startActivity(intent);
			break;
		case R.id.btn_examtwo_02:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "c++");
			startActivity(intent);
			break;
		case R.id.btn_examtwo_03:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "vb");
			startActivity(intent);
			break;
		case R.id.btn_examtwo_04:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "java");
			startActivity(intent);
			break;
		case R.id.btn_examtwo_05:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "access");
			startActivity(intent);
			break;
		case R.id.btn_examtwo_06:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "mstwo");
			startActivity(intent);
			break;
		case R.id.btn_examtwo_07:
			showNCREdialogError();
			break;
		case R.id.btn_examtwo_08:
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
		.setSingleChoiceItems(new String[]{"c","c++","vb","java","access","MS Office"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String collectquestion_kind = "collectquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(ActExamTwo.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "c");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(ActExamTwo.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "c++");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(ActExamTwo.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "vb");
					startActivity(intent);
					finish();
					break;
				case 3:
					intent = new Intent(ActExamTwo.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "java");
					startActivity(intent);
					finish();
					break;
				case 5:
					intent = new Intent(ActExamTwo.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "access");
					startActivity(intent);
					finish();
					break;
				case 6:
					intent = new Intent(ActExamTwo.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "mstwo");
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
		.setSingleChoiceItems(new String[]{"c","c++","vb","java","access","MS Office"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String errorquestion_kind = "errorquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(ActExamTwo.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "c");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(ActExamTwo.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "c++");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(ActExamTwo.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "vb");
					startActivity(intent);
					finish();
					break;
				case 3:
					intent = new Intent(ActExamTwo.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "java");
					startActivity(intent);
					finish();
					break;
				case 4:
					intent = new Intent(ActExamTwo.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "access");
					startActivity(intent);
					finish();
					break;
				case 5:
					intent = new Intent(ActExamTwo.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "mstwo");
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
