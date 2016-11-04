package com.miduo.financialmanageclient.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.listener.HistoryShowListener;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class CheckTestCount {
	private Context _context;
	private GetCountAsyncTask getCountAsyncTask;
	private HistoryShowListener _historyShowListener;
	private Integer _type;

	public CheckTestCount(Context context, HistoryShowListener historyShowListener, Integer type) {
		this._context = context;
		this._historyShowListener = historyShowListener;
		this._type = type;
	}

	public void getData() {
		if (getCountAsyncTask != null) {
			getCountAsyncTask.cancel(true);
			getCountAsyncTask = null;
		}
		getCountAsyncTask = new GetCountAsyncTask();
		getCountAsyncTask.execute();
	}

	private class GetCountAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public GetCountAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("testType", String.valueOf(_type));
				return WebServiceClient.getTestCount(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			ProgressDialogUtil.closeProgress();
			Integer data = null;
			if (null != result) {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");					
					if (state == 1) {
						data = jo.getInt("data");
					}else if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(_context, msgStr);
						return;
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (_historyShowListener != null) {
				_historyShowListener.setIsShow(data);
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}
}
