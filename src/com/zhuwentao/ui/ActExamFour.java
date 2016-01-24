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

	private Button btn_examfour_01;	// ���繤��ʦ	wlgcs
	private Button btn_examfour_02;	// ���ݿ⹤��ʦ	sjkgcs
	private Button btn_examfour_03;	// ������Թ���ʦ	rjcsgcs
	private Button btn_examfour_04;	// ��Ϣ��ȫ����ʦ	xxaqgcs
	private Button btn_examfour_05; 	// Ƕ��ʽϵͳ��������ʦ	qrsxtkfgcs
	
	private Button btn_examfour_06; 	// ����
	private Button btn_examfour_07; 	// �ղ� 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exam_four);
		MenuTop menutop = (MenuTop)findViewById(R.id.four_menutop);
		menutop.setTitleText("������ļ�");
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
			// ���ﵯ��һ���Ի���Ҫ���û�ѡ��Ҫ�鿴���ղ�����
			showNCREdialog();
			break;

		default:
			break;
		}
	}
	
	
	private void showNCREdialog(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("��ѡ������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"���繤��ʦ","���ݿ⹤��ʦ","������Թ���ʦ","��Ϣ��ȫ����ʦ","Ƕ��ʽϵͳ��������ʦ"}, -1, new DialogInterface.OnClickListener() {
			
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
		.setNegativeButton("ȡ��", null)
		.show();
	}
	
	private void showNCREdialogError(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("��ѡ������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"���繤��ʦ","���ݿ⹤��ʦ","������Թ���ʦ","��Ϣ��ȫ����ʦ","Ƕ��ʽϵͳ��������ʦ"}, -1, new DialogInterface.OnClickListener() {
			
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
		.setNegativeButton("ȡ��", null)
		.show();
	}
}
