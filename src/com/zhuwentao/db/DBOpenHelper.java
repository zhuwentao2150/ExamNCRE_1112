package com.zhuwentao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{

	public DBOpenHelper(Context context) {
		// TODO Auto-generated constructor stub
		// ����1�������Ķ��󣬲���2�����ݿ�����ƣ�����3������Ϊ�գ�����4�����ݿ�İ汾��
		super(context, "examncre.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE myquestion(" +
				"id integer primary key autoincrement, " +
				"questiontitle varchar(255), " +
				"questionoptiona varchar(255), " +
				"questionoptionb varchar(255), " +
				"questionoptionc varchar(255), " +
				"questionoptiond varchar(255), " +
				"questionanswer varchar(255), " +
				"questionkind varchar(255)" +
				")");
		db.execSQL("CREATE TABLE myerror(" +
				"id integer primary key autoincrement, " +
				"questiontitle varchar(255), " +
				"questionoptiona varchar(255), " +
				"questionoptionb varchar(255), " +
				"questionoptionc varchar(255), " +
				"questionoptiond varchar(255), " +
				"questionanswer varchar(255), " +
				"questionkind varchar(255)" +
				")");
	}

	// �����ݿ�İ汾�����仯��ʱ�����ø÷���
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
