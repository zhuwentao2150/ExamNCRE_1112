package com.zhuwentao.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhuwentao.config.Common;
import com.zhuwentao.examncre_1112.R;
import com.zhuwentao.widget.MenuTop;

public class ActStoryWeb extends Activity{

	private WebView webview;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_storyweb);
		MenuTop menutop = (MenuTop)findViewById(R.id.storyweb_menutop);
		menutop.setTitleText("美文鉴赏");
		menutop.setMenuAnswerCardGone();
		menutop.setMenuCollectGone();

		progressBar = (ProgressBar) findViewById(R.id.vocweb_pb);
		webview = (WebView) findViewById(R.id.webview);
		// 设置javascript可用
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(Common.NET_GET_EXAMSTORY);

		webview.setWebViewClient(new WebViewClient(){

			// 网页加载开始时调用，显示加载提示旋转进度条
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				progressBar.setVisibility(View.VISIBLE);
			}

			// 网页加载完成时调用，隐藏加载提示旋转进度条
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
			}

			// 网页加载失败时调用，隐藏加载提示旋转进度条
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
				progressBar.setVisibility(View.VISIBLE);
			}
		});
	}
}
