package com.zhuwentao.thread;

import java.net.URLEncoder;

import com.zhuwentao.config.Common;
import com.zhuwentao.tools.HttpUtil;

import android.os.Handler;
import android.os.Message;

// 获取试卷列表数据
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
		String result = "";	// 获取服务器端返回的数据
		try{
			sendjsonmessage = "questionkindid="+questionkindid;
			// 获取服务器端返回回来的json数据
			result = HttpUtil.sendPost(url, sendjsonmessage);
		}catch(Exception e){
			result = "";
			e.printStackTrace();
		}
		msg.obj = result;
		handler.sendMessage(msg);
	}
}
