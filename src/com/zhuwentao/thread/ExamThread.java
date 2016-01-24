package com.zhuwentao.thread;

import java.net.URLEncoder;

import com.zhuwentao.config.Common;
import com.zhuwentao.tools.HttpUtil;

import android.os.Handler;
import android.os.Message;

// ��ȡ�Ծ��б�����
public class ExamThread extends Thread {

	private Handler handler;
	private String url;
	private String action;
	private int examquestionid = -1;

	
	public ExamThread(Handler handler, String url, String action) {
		this.handler = handler;
		this.url = url;
		this.action = action;
	}
	

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		msg.what = Common.MSG_GET_EXAM;
		String sendjsonmessage = "";
		String result = "";	// ��ȡ�������˷��ص�����
		try{
			// 1������������ֶ���action= ��������࣬�Ծ����Ծ���Ŀ
			String actionkind = URLEncoder.encode(action, "UTF-8");
			sendjsonmessage = "actionkind="+actionkind;
			// ��ȡ�������˷��ػ�����json����
			result = HttpUtil.sendPost(url, sendjsonmessage);
		}catch(Exception e){
			result = "";
			e.printStackTrace();
		}
		msg.obj = result;
		handler.sendMessage(msg);
	}
}
