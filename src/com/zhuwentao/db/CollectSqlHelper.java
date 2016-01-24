package com.zhuwentao.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhuwentao.adapter.QuestionAnsAdp;
import com.zhuwentao.bean.ExamQuestionBean;


// �����ղر�
public class CollectSqlHelper {
	
	private DBOpenHelper dbOpenHelper;
	
	public CollectSqlHelper(Context context) {
		// TODO Auto-generated constructor stub
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	
	

	/**
	 * ���Ӳ���
	 * @param eqb
	 */
	public void saveQuestionSql(ExamQuestionBean eqb){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String sql = "insert into myquestion(questiontitle, questionoptiona, questionoptionb, questionoptionc, questionoptiond, questionanswer, questionkind)" +
				" values(?,?,?,?,?,?,?)";
		db.execSQL(sql, new Object[]{eqb.getQuestionTitle(), eqb.getOptionA(), eqb.getOptionB(), eqb.getOptionC(), eqb.getOptionD(), eqb.getAnswer(), eqb.getQuestionkind()});
	}
	/**
	 * ��������������ȡ��Ӧ������
	 * @param kind
	 * @return
	 */
	public List<ExamQuestionBean> getQuestionSqlKind(String kind){
		String sql = "select * from myquestion where questionkind='"+kind+"'";
		return getQuestionSqlData(sql);
	}
	/**
	 * ɾ������
	 * @param eqb
	 */
	public void DeleteCollectQuestionSql(ExamQuestionBean eqb){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String sql = "delete from myquestion where id=?";
		db.execSQL(sql, new Object[]{eqb.getId()});
	}
	/**
	 * ��ȡ��Ŀ����
	 * @param sql 
	 * @return
	 */
	public List<ExamQuestionBean> getQuestionSqlData(String sql){
		List<ExamQuestionBean> examquestiondata = new ArrayList<ExamQuestionBean>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			ExamQuestionBean question = new ExamQuestionBean();
			
			question.setId(cursor.getInt(cursor.getColumnIndex("id")));
			question.setQuestionTitle(cursor.getString(cursor.getColumnIndex("questiontitle")));
			question.setOptionA(cursor.getString(cursor.getColumnIndex("questionoptiona")));
			question.setOptionB(cursor.getString(cursor.getColumnIndex("questionoptionb")));
			question.setOptionC(cursor.getString(cursor.getColumnIndex("questionoptionc")));
			question.setOptionD(cursor.getString(cursor.getColumnIndex("questionoptiond")));
			question.setAnswer(cursor.getString(cursor.getColumnIndex("questionanswer")));
			question.setQuestionkind(cursor.getString(cursor.getColumnIndex("questionkind")));
			
			examquestiondata.add(question);
		}
		cursor.close();
		return examquestiondata;
	}

}
