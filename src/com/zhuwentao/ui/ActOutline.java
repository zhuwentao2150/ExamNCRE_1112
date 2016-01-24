package com.zhuwentao.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;


// �ȼ����Դ��
public class ActOutline extends Activity implements OnClickListener{

	private ViewFlipper viewFlipper;
	private Button outline_01;
	private Button outline_02;
	private Button outline_03;
	private Button outline_04;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_outline);
		MenuTop menutop = (MenuTop)findViewById(R.id.outline_menutop);
		menutop.setTitleText("���Դ��");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();
		
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		viewFlipper.setInAnimation(this, R.anim.in_alpha);
		viewFlipper.setOutAnimation(this, R.anim.out_alpha);
		// �趨viewFlipper���л����
		viewFlipper.setFlipInterval(3000);
		// ��ʼ����
		viewFlipper.startFlipping();
		
		outline_01 = (Button) findViewById(R.id.outline_01);
		outline_02 = (Button) findViewById(R.id.outline_02);
		outline_03 = (Button) findViewById(R.id.outline_03);
		outline_04 = (Button) findViewById(R.id.outline_04);
		
		outline_01.setOnClickListener(this);
		outline_02.setOnClickListener(this);
		outline_03.setOnClickListener(this);
		outline_04.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.outline_01:
			showNCREdialogOne();
			break;
		case R.id.outline_02:
			showNCREdialogTwo();
			break;
		case R.id.outline_03:
			showNCREdialogThree();
			break;
		case R.id.outline_04:
			showNCREdialogFour();
			break;

		default:
			break;
			
		}
	}
	
	
	private void showNCREdialogOne(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("��ѡ������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"ps","wps","MS Office"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String outline_kind = "outlinekey";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "ps");
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "wps");
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "msone");
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		})
		.setNegativeButton("ȡ��", null)
		.show();
	}
	
	private void showNCREdialogTwo(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("��ѡ������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"c","c++","vb","java","access","MS Office"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String outline_kind = "outlinekey";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "c");
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "c++");
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "vb");
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "java");
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "access");
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "mstwo");
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		})
		.setNegativeButton("ȡ��", null)
		.show();
	}
	
	private void showNCREdialogThree(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("��ѡ������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"���缼��","���ݿ⼼��","������Լ���","��Ϣ��ȫ����","Ƕ��ʽϵͳ��������"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String outline_kind = "outlinekey";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "wljs");
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "sjkjs");
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "rjcsjs");
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "xxaqjs");
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "qrsxtkfjs");
					startActivity(intent);
				default:
					break;
				}
			}
		})
		.setNegativeButton("ȡ��", null)
		.show();
	}
	
	private void showNCREdialogFour(){
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
		.setTitle("��ѡ������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setSingleChoiceItems(new String[]{"���繤��ʦ","���ݿ⹤��ʦ","������Թ���ʦ","��Ϣ��ȫ����ʦ","Ƕ��ʽϵͳ��������ʦ"}, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				String outline_kind = "outlinekey";
				switch (which) {
				case 0:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "wlgcs");
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "sjkgcs");
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "rjcsgcs");
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "xxaqgcs");
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), ActOutlineContent.class);
					intent.putExtra(outline_kind, "qrsxtkfgcs");
					startActivity(intent);
				default:
					break;
				}
			}
		})
		.setNegativeButton("ȡ��", null)
		.show();
	}
}
