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

public class ActExamFour extends Activity implements OnClickListener{

	private Button btn_examfour_01;	// 网络工程师	wlgcs
	private Button btn_examfour_02;	// 数据库工程师	sjkgcs
	private Button btn_examfour_03;	// 软件测试工程师	rjcsgcs
	private Button btn_examfour_04;	// 信息安全工程师	xxaqgcs
	private Button btn_examfour_05; 	// 嵌入式系统开发工程师	qrsxtkfgcs
	
	private Button btn_examfour_06; 	// 错题
	private Button btn_examfour_07; 	// 收藏 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_four);
		MenuTop menutop = (MenuTop)findViewById(R.id.four_menutop);
		menutop.setTitleText("计算机四级");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
		infoView();
		
	}
	
	
	private void infoView() {
		btn_examfour_01 = (Button) findViewById(R.id.btn_examfour_01);
		btn_examfour_02 = (Button) findViewById(R.id.btn_examfour_02);
		btn_examfour_03 = (Button) findViewById(R.id.btn_examfour_03);
		btn_examfour_04 = (Button) findViewById(R.id.btn_examfour_04);
		btn_examfour_05 = (Button) findViewById(R.id.btn_examfour_05);
		btn_examfour_06 = (Button) findViewById(R.id.btn_examfour_06);
		btn_examfour_07 = (Button) findViewById(R.id.btn_examfour_07);

		btn_examfour_01.setOnClickListener(this);
		btn_examfour_02.setOnClickListener(this);
		btn_examfour_03.setOnClickListener(this);
		btn_examfour_04.setOnClickListener(this);
		btn_examfour_05.setOnClickListener(this);
		btn_examfour_06.setOnClickListener(this);
		btn_examfour_07.setOnClickListener(this);
	}





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_examfour_01:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "wlgcs");
			startActivity(intent);
			break;
		case R.id.btn_examfour_02:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "sjkgcs");
			startActivity(intent);
			break;
		case R.id.btn_examfour_03:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "rjcsgcs");
			startActivity(intent);
			break;
		case R.id.btn_examfour_04:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "xxaqgcs");
			startActivity(intent);
			break;
		case R.id.btn_examfour_05:
			intent = new Intent(this, ActExamList.class);
			intent.putExtra("examtitle_kind", "qrsxtkfgcs");
			startActivity(intent);
			break;
		case R.id.btn_examfour_06:
			showNCREdialogError();
			break;
		case R.id.btn_examfour_07:
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
		.setSingleChoiceItems(new String[]{"网络工程师","数据库工程师","软件测试工程师","信息安全工程师","嵌入式系统开发工程师"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String collectquestion_kind = "collectquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "wlgcs");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "sjkgcs");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "rjcsgcs");
					startActivity(intent);
					finish();
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "xxaqgcs");
					startActivity(intent);
					finish();
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActCollectQuestion.class);
					intent.putExtra(collectquestion_kind, "qrsxtkfgcs");
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
		.setSingleChoiceItems(new String[]{"网络工程师","数据库工程师","软件测试工程师","信息安全工程师","嵌入式系统开发工程师"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String errorquestion_kind = "errorquestion_kind";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "wlgcs");
					startActivity(intent);
					finish();
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "sjkgcs");
					startActivity(intent);
					finish();
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "rjcsgcs");
					startActivity(intent);
					finish();
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "xxaqgcs");
					startActivity(intent);
					finish();
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActErrorQuestion.class);
					intent.putExtra(errorquestion_kind, "qrsxtkfgcs");
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
