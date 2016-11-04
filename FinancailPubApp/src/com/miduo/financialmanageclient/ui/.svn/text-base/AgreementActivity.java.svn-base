package com.miduo.financialmanageclient.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.umeng.analytics.MobclickAgent;

@SuppressLint("JavascriptInterface")
public class AgreementActivity extends BaseActivity {

	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private WebView webView;
	private String agreementName;
	private MyWebChromeClient myWebChromeClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agreement_common);
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		rightTxt.setVisibility(View.GONE);

		webView = (WebView) findViewById(R.id.webview);
		webView.setBackgroundColor(0);
		int agreeType = getIntent().getIntExtra("agree_type", 0);
		if (agreeType == 0) {
			// 注册协议
			agreementName = "注册协议";
			webView.loadUrl("file:///android_asset/agreement.html");
		} else if (agreeType == 1) {
			// 转让协议
			agreementName = "产品转让协议";//
			 webView.loadUrl(ConstantValues.ROOT_IP_BARN + "/agreement/transfer.html");
		} else if (agreeType == 2) {
			// 转让协议
			agreementName = "购买转让单协议";
			webView.loadUrl(ConstantValues.ROOT_IP_BARN + "/agreement/buytransfer.html");
		} else if (agreeType == 3) {
			// 转让协议
			agreementName = "产品资料";
			String productId = getIntent().getStringExtra("productId");
			webView.loadUrl(ConstantValues.ROOT_IP_PRODUCT + "/app/product/detailFileShow.htm?deType=android&productId=" + productId);
			webView.addJavascriptInterface(this, "openPdf");
		}
		titleTxt.setText(agreementName);
		leftImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setJavaScriptCanOpenWindowsAutomatically(true);
		myWebChromeClient = new MyWebChromeClient();
		webView.setWebChromeClient(myWebChromeClient);
		webView.setWebViewClient(new MyWebViewClient());
		webView.addJavascriptInterface(this, "webView");
	}

	class MyWebChromeClient extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
			// TODO Auto-generated method stub
			return super.onJsAlert(view, url, message, result);
		}

	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			webView.loadUrl(url);
			return super.shouldOverrideUrlLoading(view, url);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("注册协议页面");
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("注册协议页面");
		MobclickAgent.onResume(this);
	}

	@JavascriptInterface
	public void funFromAndroid(String name) {		
		CommonUtil.downPdf(this, name);
	}
}
