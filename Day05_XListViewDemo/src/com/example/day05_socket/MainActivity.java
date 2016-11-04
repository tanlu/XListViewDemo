package com.example.day05_socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.example.day05_socket.views.XListView;
import com.example.day05_socket.views.XListView.IXListViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebIconDatabase.IconListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

/**
 * 
 * 客户端代码
 * 
 * Socket 套接字 java中的一项网络通信技术。 Socket通信。网络编程，套接字编程。
 * 
 * 即时通信，
 * 
 * 网络间数据传输：基础是IO流
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity implements IXListViewListener {

	private ArrayList<String> lists = new ArrayList<String>();
	private XListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (XListView) findViewById(R.id.listview);

		for (int i = 0; i < 50; i++) {
			lists.add("數據" + i);
		}

		// 使用xListview
		// 打開開關
		listView.setPullLoadEnable(true);
		// 設置監聽
		listView.setXListViewListener(this);

		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, lists);
		listView.setAdapter(adapter);

	}

	// 當下拉刷新的時候調用
	@Override
	public void onRefresh() {
		// 添加數據，訪問網絡，數據。。。。
		new Thread() {
			public void run() {
				try {
					sleep(3000);
					// 添加數據
					lists.add(0,"下拉刷新的數據");
					// 刷新UI
					mHanler.sendEmptyMessage(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

	// 上啦加載更多調用
	@Override
	public void onLoadMore() {
		// 添加數據，訪問網絡，數據。。。。
				new Thread() {
					public void run() {
						try {
							sleep(3000);
							// 添加數據
							lists.add("上拉加载的數據");
							// 刷新UI
							mHanler.sendEmptyMessage(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
	}

	// 數據請求完畢，調用
	public void onLoad() {
		// 結束下拉刷新
		listView.stopRefresh();
		// 結束上啦加載
		listView.stopLoadMore();
	}

	// handler
	Handler mHanler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			adapter.notifyDataSetChanged();
			//下拉条目缩回
			onLoad();
		};
	};
	private ArrayAdapter<String> adapter;

}
