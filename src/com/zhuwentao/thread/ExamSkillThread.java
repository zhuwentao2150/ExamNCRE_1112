package com.zhuwentao.thread;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.Handler;
import android.os.Message;

import com.zhuwentao.config.Common;
import com.zhuwentao.tools.HttpUtil;

public class ExamSkillThread extends Thread{
	
	private Handler handler;
	private String url;
	private int startRows;
	
	public ExamSkillThread(Handler handler, String url, int startRows){
		this.handler = handler;
		this.url = url;
		this.startRows = startRows;
	}

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		msg.what = Common.MSG_GET_EXAMSKILL;
		String startmessage = "";
		try {
			startmessage = "startRows="+URLEncoder.encode(String.valueOf(startRows), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String result = "";
		try {
			result = HttpUtil.sendPost(url,startmessage);
		} catch (IOException e) {
			result = "";
		}
		msg.obj = result;
		handler.sendMessage(msg);
	}
}
