package com.miduo.financialmanageclient.common;

import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.BankInfo;
import com.miduo.financialmanageclient.listener.GetBankLstListener;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class GetBankInfo {
	private Context _context;
	private GetBankInfoAsyncTask getBankInfoAsyncTask;
	private GetBankLstListener _getBankLstListener;

	public GetBankInfo(Context context, GetBankLstListener getBankLstListener) {
		this._context = context;
		this._getBankLstListener = getBankLstListener;
	}

	public void getData() {
		if (getBankInfoAsyncTask != null) {
			getBankInfoAsyncTask.cancel(true);
			getBankInfoAsyncTask = null;
		}
		ProgressDialogUtil.showProgress(_context);
		getBankInfoAsyncTask = new GetBankInfoAsyncTask();
		getBankInfoAsyncTask.execute();
	}

	private class GetBankInfoAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public GetBankInfoAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				return WebServiceClient.getBankLst();
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
				MToast.showToast(_context, "银行卡信息获取失败！");
				return;
			} else {
				List<BankInfo> bankLst = null;
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(_context, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						bankLst = JsonUtils.toBean(data, new TypeToken<List<BankInfo>>() {
						}.getType());
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(_context, StringUtil.isEmpty(msg) ? "登录失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(_context, "数据异常");
				} finally {
					if (_getBankLstListener != null) {
						_getBankLstListener.refresh(bankLst);
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
