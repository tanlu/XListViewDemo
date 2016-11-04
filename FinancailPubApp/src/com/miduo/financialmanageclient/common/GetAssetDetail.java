package com.miduo.financialmanageclient.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.listener.GetCompleteListener;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class GetAssetDetail {
	private Context _context;
	private GetAssetDetailAsyncTask getAssetDetailAsyncTask;
	private GetCompleteListener _getCompleteListener;
	private String _assetId;

	public GetAssetDetail(Context context, GetCompleteListener getCompleteListener, String assetId) {
		this._context = context;
		this._getCompleteListener = getCompleteListener;
		this._assetId = assetId;
	}

	public void getData() {
		if (getAssetDetailAsyncTask != null) {
			getAssetDetailAsyncTask.cancel(true);
			getAssetDetailAsyncTask = null;
		}
		ProgressDialogUtil.showProgress(_context);
		getAssetDetailAsyncTask = new GetAssetDetailAsyncTask();
		getAssetDetailAsyncTask.execute();
	}

	private class GetAssetDetailAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public GetAssetDetailAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				Map<String, String> map = new HashMap<String, String>();	
//				map.put("userId", "1093");
				map.put("assetNo", _assetId);
				map.put("assetSource", "android");
				return WebServiceClient.GetAssetDetail(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(_context);
					return;
				}
				MToast.showToast(_context, "资产信息获取失败！");
				return;
			} else {
				String data = null;
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						data = jo.getString("data");
					}else if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(_context, msgStr);
						return;
					}  else {
						String msg = jo.getString("msg");
						MToast.showToast(_context, StringUtil.isEmpty(msg) ? "资产信息获取失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(_context, "数据异常");
				} finally {
					if (_getCompleteListener != null) {
						_getCompleteListener.refreshData(data);
					}
				}
			}

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}
}
