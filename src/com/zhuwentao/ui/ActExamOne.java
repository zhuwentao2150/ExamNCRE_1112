package com.zhuwentao.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

public class ActExamOne extends Activity implements OnClickListener{

	private Button btn_examone_01;	// PS
	private Button btn_examone_02;	// wps
	private Button btn_examone_03;	// ms
	private Button btn_examone_04;	// 错题按钮
	private Button btn_examone_05; 	// 收藏按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_one);
		MenuTop menutop = (MenuTop)findViewById(R.id.one_menutop);
		menutop.setTitleText("计算机一级");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();

		// 初始化控件
		infoView();

	}

	private void infoView() {
		btn_examone_01 = (Button) findViewById(R.id.btn_examone_01);
		btn_examone_02 = (Button) findViewById(R.id.btn_examone_02);
		btn_examone_03 = (Button) findViewById(R.id.btn_examone_03);
		btn_examone_04 = (Button) findViewById(R.id.btn_examone_04);
		btn_examone_05 = (Button) findViewById(R.id.btn_examone_05);

		btn_examone_01.setOnClickListener(this);
		btn_examone_02.setOnClickListener(this);
		btn_examone_03.setOnClickListener(this);
		btn_examone_04.setOnClickListener(this);
		btn_examone_05.setOnClickListener(this);
		
	}

	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_examone_01:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "ps");
			startActivity(intent);
			break;
		case R.id.btn_examone_02:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "wps");
			startActivity(intent);
			break;
		case R.id.btn_examone_03:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "msone");
			startActivity(intent);
			break;
		case R.id.btn_examone_04:
			showNCREdialogError();
			break;
		case R.id.btn_examone_05:
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
		.setSingleChoiceItems(new String[]{"ps","wps","msone"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String collectquestion_kind = "collectquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(ActExamOne.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "ps");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(ActExamOne.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "wps");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(ActExamOne.this, ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "msone");
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
		.setSingleChoiceItems(new String[]{"ps","wps","msone"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String errorquestion_kind = "errorquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(ActExamOne.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "ps");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(ActExamOne.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "wps");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(ActExamOne.this, ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "msone");
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
