package com.miduo.financialmanageclient.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.RedeemInfo;
import com.miduo.financialmanageclient.listener.ReeemResultListener;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class GetRedeemInfo {
	private Context _context;
	private GetRedeemInfoAsyncTask etRedeemInfoAsyncTask;
	private ReeemResultListener _reeemResultListener;
	private Integer _assetId;

	public GetRedeemInfo(Context context, ReeemResultListener reeemResultListener, Integer assetId) {
		this._context = context;
		this._reeemResultListener = reeemResultListener;
		this._assetId = assetId;
	}

	public void getData() {
		if (etRedeemInfoAsyncTask != null) {
			etRedeemInfoAsyncTask.cancel(true);
			etRedeemInfoAsyncTask = null;
		}
		etRedeemInfoAsyncTask = new GetRedeemInfoAsyncTask();
		etRedeemInfoAsyncTask.execute();
	}

	private class GetRedeemInfoAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public GetRedeemInfoAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("assetId", String.valueOf(_assetId));
				return WebServiceClient.GetRedeemInfo(map);
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
				MToast.showToast(_context, "赎回信息获取失败！");
				return;
			} else {
				RedeemInfo redeemInfo = null;
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(_context, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						redeemInfo = JsonUtils.toBean(data, new TypeToken<RedeemInfo>() {
						}.getType());
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(_context, StringUtil.isEmpty(msg) ? "赎回信息获取失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(_context, "数据异常");
				} finally {
					if (_reeemResultListener != null) {
						_reeemResultListener.refreshData(redeemInfo);
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
