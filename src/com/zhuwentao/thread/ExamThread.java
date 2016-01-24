package com.zhuwentao.thread;

import java.net.URLEncoder;

import com.zhuwentao.config.Common;
import com.zhuwentao.tools.HttpUtil;

import android.os.Handler;
import android.os.Message;

// 获取试卷列表数据
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
		String result = "";	// 获取服务器端返回的数据
		try{
			// 1、发送请求的字段名action= 请求的种类，试卷还是试卷题目
			String actionkind = URLEncoder.encode(action, "UTF-8");
			sendjsonmessage = "actionkind="+actionkind;
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
