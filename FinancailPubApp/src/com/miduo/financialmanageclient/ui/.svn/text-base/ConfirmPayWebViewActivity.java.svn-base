package com.miduo.financialmanageclient.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;

/**
 * 确认支付加载webview页面
 * 
 * @author huozhenpeng
 * 
 */
public class ConfirmPayWebViewActivity extends GesterSetBaseActivity {

	private WebView wv_web;
	private Intent intent;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirmpaywebview);
		initView();
		initEvent();
		initData();
	}

	private void initEvent() {

	}

	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	private void initData() {
		// 调用getSettings()设置一些浏览器的属性，调用setJavaScriptEnabled方法来让WebView支持javascript脚本
		wv_web.getSettings().setJavaScriptEnabled(true);
		/*
		 * 调用setWebViewClient()方法，并传入了WebViewClient的匿名类作为参数，然后重写了
		 * shouldOverrideUrlLoading方法，这就表明当需要从一个网页跳转到另一个网页时，我们希望目标
		 * 网页仍然在当前WebView中显示，而不是打开系统浏览器。
		 */
		wv_web.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);// 根据传入的参数在去加载新的网页
				return true;// 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				ProgressDialogUtil.showProgress(ConfirmPayWebViewActivity.this);
				super.onPageStarted(view, url, favicon);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				ProgressDialogUtil.closeProgress();
				super.onPageFinished(view, url);
			}

		});
		wv_web.addJavascriptInterface(this, "android");
		// 调用loadUrl()方法，并将网址传入，即可展示相应的网页内容
		intent = getIntent();
		if (intent != null) {
			// url = intent.getStringExtra("url")+"?" +
			// "mobile="+intent.getStringExtra("mobile") + "&"
			// + "orderNo="+intent.getStringExtra("orderNo") + "&"
			// + "bankcardId="+intent.getStringExtra("bankcardId") + "&"
			// +
			// "smsCode="+intent.getStringExtra("smsCode");//+"&"+"session="+MyApplication.cus_sessionid;
			String mobile = SharePrefUtil.getString(this,
					SharePrefUtil.ACCOUNT_MOBILE, "");
			url = intent.getStringExtra("url") + "?" + "orderNo="
					+ intent.getStringExtra("orderNo") + "&" + "bankcardId="
					+ intent.getStringExtra("bankcardId") +"&"+ "mobile=" + mobile+"&"+"_cus_sessionid="+ MyApplication.cus_sessionid;
			// Log.e("abc", url);
			wv_web.loadUrl(url);
			// wv_web.loadUrl("file:///android_asset/pay.html");
		}
	}

	private void initView() {
		wv_web = (WebView) this.findViewById(R.id.wv_web);
	}

	/**
	 * 如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身，如果希望浏览的网
	 * 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_web.canGoBack()) {
			wv_web.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 支付成功，跳米仓
	 */
	@JavascriptInterface
	public void payCompleteSuccess() {
		MyApplication.finishPurchaseActivity();
		MyApplication.getInstance().home_index = 2;
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * 支付失败，重新支付
	 */
	@JavascriptInterface
	public void payCompleteFailure() {
		finish();
	}

}
