package com.example.day07_listview_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		
		
		WebView webView = (WebView) findViewById(R.id.webView);
		
		
		webView.loadUrl(url);
		
		WebSettings settings = webView.getSettings();
		
		settings.setJavaScriptEnabled(true);
		
		
		webView.setWebViewClient(new WebViewClient());
		
		
	}
}
