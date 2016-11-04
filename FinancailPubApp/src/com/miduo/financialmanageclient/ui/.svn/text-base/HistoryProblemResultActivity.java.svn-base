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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.bean.TestRecord;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.ui.adapter.RecordAdapter;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class HistoryProblemResultActivity extends GesterSetBaseActivity implements OnClickListener, OnScrollListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt, del_txt, cancel_txt;
	private ListView record_listview;
	private RelativeLayout bottom_layout;
	private LinearLayout all_layout;
	private boolean selAll = false;
	private boolean isEdit = false;
	private ImageView all_img;
	private RecordAdapter recordAdapter;
	private List<TestRecord> orderLst = new ArrayList<TestRecord>();
	// 0 投资 1投保
	private int type = 0;
	private int selCount = 0;
	private int page = 1, size = 20;
	private View loadMoreView;
	private TextView loadMoreTxt;
	private boolean isLastRow = false;
	private RecordListAsyncTask recordListAsyncTask;
	private DelRecordListAsyncTask delRecordListAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_problem_result);
		type = getIntent().getIntExtra("type", 0);

		initUi();
		initEvent();
		page = 1;
		ProgressDialogUtil.showProgress(this);
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("投资投保测记录"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("投资投保测记录"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		rightTxt.setText("编辑");
		bottom_layout = (RelativeLayout) findViewById(R.id.bottom_layout);
		all_layout = (LinearLayout) findViewById(R.id.all_layout);
		bottom_layout.setVisibility(View.GONE);
		del_txt = (TextView) findViewById(R.id.del_txt);
		cancel_txt = (TextView) findViewById(R.id.cancel_txt);
		all_img = (ImageView) findViewById(R.id.all_img);

		record_listview = (ListView) findViewById(R.id.record_listview);
		recordAdapter = new RecordAdapter(HistoryProblemResultActivity.this, orderLst);
		record_listview.setAdapter(recordAdapter);
		loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
		loadMoreTxt = (TextView) loadMoreView.findViewById(R.id.load_more_txt);
		record_listview.addFooterView(loadMoreView); // 设置列表底部视图
		loadMoreView.setVisibility(View.GONE);
		loadMoreView.setPadding(0, -loadMoreView.getHeight(), 0, 0);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		rightTxt.setOnClickListener(this);
		del_txt.setOnClickListener(this);
		cancel_txt.setOnClickListener(this);
		all_layout.setOnClickListener(this);
		record_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (isEdit) {
					// 编辑状态
					boolean flag = !(orderLst.get(arg2).isSel());
					orderLst.get(arg2).setSel(flag);
					recordAdapter.notifyDataSetChanged();
					checkSelCount();
					if (flag) {
						selCount++;
					} else {
						selCount--;
					}
				} else {
					Intent intent = new Intent();
					intent.putExtra("tid", orderLst.get(arg2).getRecordId());
					if (type == 0) {
						intent.setClass(HistoryProblemResultActivity.this, InvestItemActivity.class);
					} else {
						intent.setClass(HistoryProblemResultActivity.this, InsureItemActivity.class);
					}
					startActivity(intent);
				}

			}
		});
		record_listview.setOnScrollListener(this); // 添加滑动监听
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

	private void checkSelCount() {
		int selCount = 0;
		for (TestRecord item : orderLst) {
			if (item.isSel()) {
				selCount++;
			}
		}
		if (selCount == 0) {
			selAll = false;
			all_img.setImageResource(R.drawable.un_select);
		} else if (selCount == orderLst.size()) {
			selAll = true;
			all_img.setImageResource(R.drawable.select);
		} else {
			selAll = false;
			all_img.setImageResource(R.drawable.un_select);
		}
	}

	private void initData() {
		// TODO Auto-generated method stub
		if (type == 0) {
			titleTxt.setText("投资测试记录");
		} else {
			titleTxt.setText("投保测试记录");
		}
		if (recordListAsyncTask != null) {
			recordListAsyncTask.cancel(true);
			recordListAsyncTask = null;
		}
		recordListAsyncTask = new RecordListAsyncTask();
		recordListAsyncTask.execute();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			if (isEdit) {
				isEdit = false;
				bottom_layout.setVisibility(View.GONE);
				rightTxt.setText("编辑");
				if (selCount != 0) {
					for (int i = 0; i < orderLst.size(); i++) {
						if (orderLst.get(i).isSel()) {
							orderLst.get(i).setSel(false);
						}
					}
					recordAdapter.notifyDataSetChanged();
					selCount = 0;
				}
			} else {
				isEdit = true;
				bottom_layout.setVisibility(View.VISIBLE);
				rightTxt.setText("完成");
			}
			recordAdapter.setVisible(isEdit);
			break;
		case R.id.del_txt:
			if (selCount == 0) {
				MToast.showToast(this, "请选择一条记录");
			} else {
				DialogBean data = new DialogBean();
				data.setContent("确认将这" + selCount + "个测试记录删除？");
				data.setCancel("取消");
				data.setSubmit("确定");
				DialogEventListener lister = new DialogEventListener() {

					@Override
					public void submit() {
						// TODO Auto-generated method stub
						if (delRecordListAsyncTask != null) {
							delRecordListAsyncTask.cancel(true);
							delRecordListAsyncTask = null;
						}
						delRecordListAsyncTask = new DelRecordListAsyncTask();
						delRecordListAsyncTask.execute();
					}

					@Override
					public void cancel() {
						// TODO Auto-generated method stub

					}
				};
				data.setDialogEvent(lister);
				MDialog.showDialog2(this, data);
			}
			break;
		case R.id.cancel_txt:
			isEdit = false;
			bottom_layout.setVisibility(View.GONE);
			rightTxt.setText("编辑");
			if (selCount != 0) {
				for (int i = 0; i < orderLst.size(); i++) {
					if (orderLst.get(i).isSel()) {
						orderLst.get(i).setSel(false);
					}
				}
				recordAdapter.notifyDataSetChanged();
				selCount = 0;
			}
			break;
		case R.id.all_layout:
			selAll = !selAll;
			for (int i = 0; i < orderLst.size(); i++) {
				orderLst.get(i).setSel(selAll);
			}
			recordAdapter.notifyDataSetChanged();
			if (selAll) {
				selCount = orderLst.size();
				selAll = true;
				all_img.setImageResource(R.drawable.select);
			} else {
				selCount = 0;
				selAll = false;
				all_img.setImageResource(R.drawable.un_select);
			}
			break;
		default:
			break;
		}
	}

	private class RecordListAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public RecordListAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pageIndex", String.valueOf(page));
			map.put("pageSize", String.valueOf(size));
			try {
				ex = null;
				return WebServiceClient.getRecordLst(map, type);
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
					ex.makeToast(HistoryProblemResultActivity.this);
					return;
				}
				MToast.showToast(HistoryProblemResultActivity.this, "获取信息失败！");
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(HistoryProblemResultActivity.this, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						List<TestRecord> dateLst = JsonUtils.toBean(data, new TypeToken<List<TestRecord>>() {
						}.getType());
						showData(dateLst);
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(HistoryProblemResultActivity.this, StringUtil.isEmpty(msg) ? "获取信息失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(HistoryProblemResultActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	private void showData(List<TestRecord> dateLst) {
		// TODO Auto-generated method stub
		if (dateLst != null && dateLst.size() > 0) {
			orderLst.addAll(dateLst);
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
		recordAdapter.notifyDataSetChanged();
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

	private class DelRecordListAsyncTask extends AsyncTask<Void, Void, ReturnMsg> {
		private AppException ex;

		public DelRecordListAsyncTask() {
		}

		@Override
		protected ReturnMsg doInBackground(Void... arg0) {
			StringBuffer param = new StringBuffer();
			int index = 0;
			for (int i = 0; i < orderLst.size(); i++) {
				if (orderLst.get(i).isSel()) {
					if (index == 0) {
						param.append("?tid=" + orderLst.get(i).getRecordId());
					} else {
						param.append("&tid=" + orderLst.get(i).getRecordId());
						index++;
					}
					index++;
				}
			}

			try {
				ex = null;
				return WebServiceClient.delRecordLst(param.toString(), type);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(ReturnMsg result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(HistoryProblemResultActivity.this);
					return;
				}
				MToast.showToast(HistoryProblemResultActivity.this, "删除信息失败！");
				return;
			} else {
				MToast.showToast(HistoryProblemResultActivity.this, "删除信息成功！");
				if (result.getState() == 1) {
					for (int i = 0; i < orderLst.size(); i++) {
						if (orderLst.get(i).isSel()) {
							orderLst.remove(i);
							i--;
						}
					}
					recordAdapter.notifyDataSetChanged();
					selCount = 0;
				} else {
					String msg = result.getMsg();
					MToast.showToast(HistoryProblemResultActivity.this, StringUtil.isEmpty(msg) ? "删除信息失败！" : msg);
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}
}
