package com.zhuwentao.thread;

import java.net.URLEncoder;

import com.zhuwentao.config.Common;
import com.zhuwentao.tools.HttpUtil;

import android.os.Handler;
import android.os.Message;

// ��ȡ�Ծ��б�����
public class ExamQuestionThread extends Thread {

	private Handler handler;
	private String url;
	private int questionkindid;

	
	public ExamQuestionThread(Handler handler, String url, int questionkindid) {
		this.handler = handler;
		this.url = url;
		this.questionkindid = questionkindid;
	}

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		msg.what = Common.MSG_GET_EXAMQUESTION;
		String sendjsonmessage = "";
		String result = "";	// ��ȡ�������˷��ص�����
		try{
			sendjsonmessage = "questionkindid="+questionkindid;
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
