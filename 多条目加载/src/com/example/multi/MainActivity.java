package com.example.multi;

import com.example.multi.XListView.IXListViewListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity implements IXListViewListener {

	private com.example.multi.XListView xListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		xListView = (XListView) findViewById(R.id.xListView);
		Button button = (Button) findViewById(R.id.button);
		// 激活上拉加载更多
		xListView.setPullLoadEnable(true);
		// xListView上拉下拉监听
		xListView.setXListViewListener(this);
		//开始就要加载数据
		onRefresh();
	}

	@Override
	public void onRefresh() {
		System.out.println("jaizai ----------");
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}
}
