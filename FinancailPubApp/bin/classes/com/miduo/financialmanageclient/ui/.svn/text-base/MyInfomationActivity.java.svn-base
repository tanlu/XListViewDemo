package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.MsgBean;
import com.miduo.financialmanageclient.ui.adapter.MsgTypeAdapter;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.MessageView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 
 * @author huozhenpeng （修改）
 * 
 */
public class MyInfomationActivity extends GesterSetBaseActivity implements OnClickListener, OnScrollListener {

	private ListView msgListView;
	private List<MsgBean> msgLst = new ArrayList<MsgBean>();
	private ImageView left_img;
	private TextView title_txt;
	private MessageView messageview;
	private LinearLayout ll_bottom;
	private ImageView right_txt;
	private TextView tv_read;
	private TextView tv_cancel;
	private int page = 1, size = 20;
	private GetMsgListAsyncTask getMsgListAsyncTask;
	private View loadMoreView;
	private TextView loadMoreTxt;
	private boolean isLastRow = false;
	private UpdateAllMsgAsyncTask updateAllMsgAsyncTask;
	private MsgTypeAdapter msgTypeAdapter;
	private int notReadCount = 0;
	private LinearLayout nodata_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfomation);
		init();
		initEvent();
		page = 1;
		notReadCount = getIntent().getIntExtra("not_read_count", 0);
		ProgressDialogUtil.showProgress(this);
		initData();
	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("我的消息");
		messageview = (MessageView) findViewById(R.id.messageview);
		ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
		right_txt = (ImageView) findViewById(R.id.right_txt);
		tv_read = (TextView) findViewById(R.id.tv_read);
		tv_cancel = (TextView) findViewById(R.id.tv_cancel);

		msgListView = (ListView) findViewById(R.id.lv);
		msgTypeAdapter = new MsgTypeAdapter(this, msgLst);
		msgListView.setAdapter(msgTypeAdapter);
		loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
		loadMoreTxt = (TextView) loadMoreView.findViewById(R.id.load_more_txt);
		msgListView.addFooterView(loadMoreView); // 设置列表底部视图
		loadMoreView.setVisibility(View.GONE);
		loadMoreView.setPadding(0, -loadMoreView.getHeight(), 0, 0);
		nodata_layout = (LinearLayout) findViewById(R.id.nodata_layout);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		title_txt.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		tv_read.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);

		msgListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (ll_bottom.getVisibility() == View.VISIBLE) {
					return;
				}
				if (!StringUtil.isEmpty(msgLst.get(arg2).getIsRead()) && msgLst.get(arg2).getIsRead().equals("1")) {
					Intent intent = new Intent(MyInfomationActivity.this, MyNewsInfoActivity.class);
					intent.putExtra("msg", msgLst.get(arg2));
					startActivity(intent);
					return;
				}
				ProgressDialogUtil.showProgress(MyInfomationActivity.this);
				new AsyncTask<String, Integer, String>() {
					private AppException ex;
					private int index;

					@Override
					protected String doInBackground(String... params) {
						try {
							ex = null;
							index = Integer.parseInt(params[0]);
							Map<String, String> map = new HashMap<String, String>();
							map.put("uuid", msgLst.get(index).getUuid());
							return WebServiceClient.setMsgRead(map);
						} catch (AppException e) {
							this.ex = e;
						}
						return null;
					}

					@Override
					protected void onPostExecute(String result) {
						ProgressDialogUtil.closeProgress();
						if (null == result) {
							if (ex != null) {
								ex.makeToast(MyInfomationActivity.this);
							} else {
								MToast.showToast(MyInfomationActivity.this, "消息读取失败");
							}
						} else {
							try {
								JSONObject jo = new JSONObject(result);
								int state = jo.getInt("state");
								if (state == 1) {
									MsgBean item = msgLst.get(index);
									msgLst.get(index).setIsRead("1");
									msgTypeAdapter.notifyDataSetChanged();
									Intent intent = new Intent(MyInfomationActivity.this, MyNewsInfoActivity.class);
									intent.putExtra("msg", msgLst.get(index));
									startActivity(intent);
									notReadCount--;
									messageview.setText(String.valueOf(notReadCount));
									if (notReadCount == 0) {
										right_txt.setVisibility(View.INVISIBLE);
									} else {
										right_txt.setVisibility(View.VISIBLE);
									}
								} else {
									String msg = jo.getString("msg");
									if (!StringUtil.isEmpty(msg)) {
										MToast.showToast(MyInfomationActivity.this, msg);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								MToast.showToast(MyInfomationActivity.this, "消息读取失败");
							}
						}
						super.onPostExecute(result);
					}

				}.execute(String.valueOf(arg2));
			}
		});
		msgListView.setOnScrollListener(this); // 添加滑动监听
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		// visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
		// 判断是否滚到最后一行
		if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
			isLastRow = true;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		// int itemsLastIndex = orderLst.size() - 1; // 数据集最后一项的索引
		// int lastIndex = itemsLastIndex + 1; // 加上底部的loadMoreView项
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && isLastRow) {
			// 如果是自动加载,可以在这里放置异步加载数据的代码
			isLastRow = false;
			this.loadMoreView.setVisibility(View.VISIBLE);
			this.loadMoreView.setPadding(0, 0, 0, 0);
			loadMoreTxt.setText("加载中...");
			page++;
			initData();
		}
	}

	private void initData() {
		// TODO Auto-generated method stub
		if (notReadCount == 0) {
			messageview.setText("");
			right_txt.setVisibility(View.INVISIBLE);
		} else {
			messageview.setText(String.valueOf(notReadCount));
			right_txt.setVisibility(View.VISIBLE);
		}
		if (getMsgListAsyncTask != null) {
			getMsgListAsyncTask.cancel(true);
			getMsgListAsyncTask = null;
		}
		getMsgListAsyncTask = new GetMsgListAsyncTask();
		getMsgListAsyncTask.execute();
	}

	private class GetMsgListAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public GetMsgListAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pageIndex", String.valueOf(page));
			map.put("pageSize", String.valueOf(size));
			map.put("roleId", "1");
			map.put("client", "4");
			try {
				ex = null;
				return WebServiceClient.getMsgLst(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(MyInfomationActivity.this);
					return;
				}
				MToast.showToast(MyInfomationActivity.this, "获取信息失败！");
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						String data = jo.getString("data");
						List<MsgBean> dateLst = JsonUtils.toBean(data, new TypeToken<List<MsgBean>>() {
						}.getType());
						showData(dateLst);
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(MyInfomationActivity.this, StringUtil.isEmpty(msg) ? "获取信息失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(MyInfomationActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			Intent intent = new Intent();
			intent.putExtra("not_read_count", notReadCount);
			setResult(RESULT_OK, intent);
			finish();
			break;
		case R.id.right_txt:
			if (notReadCount == 0) {
				return;
			}
			ll_bottom.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_read:
			ll_bottom.setVisibility(View.GONE);
			ProgressDialogUtil.showProgress(this);
			if (updateAllMsgAsyncTask != null) {
				updateAllMsgAsyncTask.cancel(true);
				updateAllMsgAsyncTask = null;
			}
			updateAllMsgAsyncTask = new UpdateAllMsgAsyncTask();
			updateAllMsgAsyncTask.execute();
			break;
		case R.id.tv_cancel:
			ll_bottom.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("我的消息");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageStart("我的消息");
		MobclickAgent.onPause(this);
	}

	private void showData(List<MsgBean> dateLst) {
		// TODO Auto-generated method stub
		if ((dateLst == null || dateLst.size() == 0) && page == 1) {
			nodata_layout.setVisibility(View.VISIBLE);
			msgListView.setVisibility(View.GONE);
		} else {
			nodata_layout.setVisibility(View.GONE);
			msgListView.setVisibility(View.VISIBLE);
		}
		if (dateLst != null && dateLst.size() > 0) {
			msgLst.addAll(dateLst);
			if (page > 1) {
				loadMoreView.setVisibility(View.GONE);
				loadMoreView.setPadding(0, -loadMoreView.getHeight(), 0, 0);
			}
		} else {
			if (page > 1) {
				loadMoreTxt.setText("数据全部加载完");
				new Thread() {
					public void run() {
						try {
							sleep(1000); // 等待三秒,自动进入软件主窗口
							handler.sendEmptyMessage(500);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// progressDialog.dismiss();
					}
				}.start();
			}
		}
		msgTypeAdapter.notifyDataSetChanged();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == 500) {
				loadMoreView.setVisibility(View.GONE);
				loadMoreView.setPadding(0, -loadMoreView.getHeight(), 0, 0);
			}
		}
	};

	private class UpdateAllMsgAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public UpdateAllMsgAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleId", "1");
				map.put("client", "4");
				return WebServiceClient.setAllMsgRead(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(MyInfomationActivity.this);
				} else {
					MToast.showToast(MyInfomationActivity.this, "更新信息失败！");
				}
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(MyInfomationActivity.this, msgStr);
						return;
					} else if (state == 1) {
						notReadCount = 0;
						if (notReadCount == 0) {
							right_txt.setVisibility(View.INVISIBLE);
						} else {
							right_txt.setVisibility(View.VISIBLE);
						}
						messageview.setText("");
						page = 1;
						msgLst.clear();
						initData();
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(MyInfomationActivity.this, StringUtil.isEmpty(msg) ? "更新信息失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(MyInfomationActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.putExtra("not_read_count", notReadCount);
			setResult(RESULT_OK, intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
