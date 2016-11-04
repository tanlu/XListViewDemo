package com.example.day07_listview_;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

import com.example.day07_listview_.XListView.IXListViewListener;
import com.example.day07_listview_.bean.Bean;
import com.example.day07_listview_.bean.Data;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

public class MainActivity extends Activity implements IXListViewListener {
	String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_society&count=20&min_behot_time=1476067842&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1476067224&loc_mode=5&lac=4527&cid=28883&iid=5567581950&device_id=23365548172&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=352284041184817&openudid=1111111111110000";
	// 区分请求下来的数据是做什么用的
	// 下拉刷新
	public static final int REFRESH = 1;
	// 上拉加载更多
	public static final int LOADMORE = 2;
	// 初始化
	public static final int INIT = 3;

	private MyBaseAdapter myBaseAdapter;

	List<Data> list = new ArrayList<Data>();
	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			// 取出数据
			Bean bean = (Bean) msg.obj;
			int tag = msg.arg1;

			switch (tag) {
			// 第一次加载数据
			case INIT:
				// 把有数据的集合添加到另一个集合里
				list.addAll(bean.data);
				myBaseAdapter = new MyBaseAdapter(MainActivity.this, list);
				xListView.setAdapter(myBaseAdapter);
				break;
			// 刷新数据
			case REFRESH:
				// 清空集合
				list.clear();
				// 把有数据的集合添加到另一个集合里
				list.addAll(bean.data);
				myBaseAdapter.notifyDataSetChanged();
				stopXListView();
				break;
			// 加载更多
			case LOADMORE:
				// 把有数据的集合添加到另一个集合里
				list.addAll(bean.data);
				myBaseAdapter.notifyDataSetChanged();
				stopXListView();
				break;

			}

		};

	};
	private XListView xListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		xListView = (XListView) findViewById(R.id.xListView);
		Button button = (Button) findViewById(R.id.button);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onRefresh();
				
			}
		});
		
		
		// 激活上拉加载更多
		xListView.setPullLoadEnable(true);
		// xListView上拉下拉监听
		xListView.setXListViewListener(this);
		// 第一次获取数据
		getData(INIT);

		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String url = list.get(position-1).share_url;

				startActivity(new Intent(MainActivity.this,
						SecondActivity.class).putExtra("url", url));

			}
		});
        //listView滑动状态时,imageloader不加载图片
		xListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true));
			
	}

	@Override
	// 下拉刷新
	public void onRefresh() {

		getData(REFRESH);

	}

	@Override
	// 上拉加载
	public void onLoadMore() {
		getData(LOADMORE);
	}

	public void stopXListView() {

		xListView.stopLoadMore();
		xListView.stopRefresh();

		xListView.setRefreshTime("2016-10-10 11:11:11");

	}

	// 联网获取数据
	public void getData(final int tag) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.configCurrentHttpCacheExpiry(0);
		httpUtils.send(HttpMethod.GET, path, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 取出json
				String s = arg0.result;
				System.out.println(s);
				// 解析json
				Gson gson = new Gson();
				Bean bean = gson.fromJson(s, Bean.class);

				// 发送json
				Message msg = Message.obtain();
				msg.obj = bean;
				msg.arg1 = tag;
				handler.sendMessage(msg);
			}
		});
	}

}
