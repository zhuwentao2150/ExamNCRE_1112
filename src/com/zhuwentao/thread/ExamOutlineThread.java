package com.zhuwentao.thread;

import java.net.URLEncoder;

import com.zhuwentao.config.Common;
import com.zhuwentao.tools.HttpUtil;

import android.os.Handler;
import android.os.Message;

// 获取试卷列表数据
public class ExamOutlineThread extends Thread {

	private Handler handler;
	private String url;
	private String action;

	
	public ExamOutlineThread(Handler handler, String url, String action) {
		this.handler = handler;
		this.url = url;
		this.action = action;
	}
	

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		msg.what = Common.MSG_GET_EXAMOUTLINE;
		String sendjsonmessage = "";
		String result = "";	// 获取服务器端返回的数据
		try{
			String actionkind = URLEncoder.encode(action, "UTF-8");
			sendjsonmessage = "actionkind="+actionkind;
			result = HttpUtil.sendPost(url, sendjsonmessage);
		}catch(Exception e){
			result = "";
			e.printStackTrace();
		}
		msg.obj = result;
		handler.sendMessage(msg);
	}
}
