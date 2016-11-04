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
 * �ͻ��˴���
 * 
 * Socket �׽��� java�е�һ������ͨ�ż����� Socketͨ�š������̣��׽��ֱ�̡�
 * 
 * ��ʱͨ�ţ�
 * 
 * ��������ݴ��䣺������IO��
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
			lists.add("����" + i);
		}

		// ʹ��xListview
		// ���_�_�P
		listView.setPullLoadEnable(true);
		// �O�ñO 
		listView.setXListViewListener(this);

		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, lists);
		listView.setAdapter(adapter);

	}

	// ������ˢ�µĕr���{��
	@Override
	public void onRefresh() {
		// ��Ӕ������L���W�j��������������
		new Thread() {
			public void run() {
				try {
					sleep(3000);
					// ��Ӕ���
					lists.add(0,"����ˢ�µĔ���");
					// ˢ��UI
					mHanler.sendEmptyMessage(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

	// �������d�����{��
	@Override
	public void onLoadMore() {
		// ��Ӕ������L���W�j��������������
				new Thread() {
					public void run() {
						try {
							sleep(3000);
							// ��Ӕ���
							lists.add("�������صĔ���");
							// ˢ��UI
							mHanler.sendEmptyMessage(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
	}

	// ����Ո���ꮅ���{��
	public void onLoad() {
		// �Y������ˢ��
		listView.stopRefresh();
		// �Y���������d
		listView.stopLoadMore();
	}

	// handler
	Handler mHanler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			adapter.notifyDataSetChanged();
			//������Ŀ����
			onLoad();
		};
	};
	private ArrayAdapter<String> adapter;

}
