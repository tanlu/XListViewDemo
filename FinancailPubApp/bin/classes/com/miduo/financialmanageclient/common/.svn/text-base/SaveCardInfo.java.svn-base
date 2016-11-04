package com.miduo.financialmanageclient.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.listener.SaveCardListener;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class SaveCardInfo {
	private Context _context;
	private SaveCardInfoAsyncTask saveCardInfoAsyncTask;
	private SaveCardListener _saveCardListener;
	private BankCardInfo _info;

	public SaveCardInfo(Context context, SaveCardListener saveCardListener, BankCardInfo info) {
		this._context = context;
		this._saveCardListener = saveCardListener;
		this._info = info;
	}

	public void saveData() {
		if (saveCardInfoAsyncTask != null) {
			saveCardInfoAsyncTask.cancel(true);
			saveCardInfoAsyncTask = null;
		}
		ProgressDialogUtil.showProgress(_context);
		saveCardInfoAsyncTask = new SaveCardInfoAsyncTask();
		saveCardInfoAsyncTask.execute();
	}

	private class SaveCardInfoAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public SaveCardInfoAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("bankId", String.valueOf(_info.getBankListId()));
				map.put("idCard", String.valueOf(_info.getIdcard()));
				map.put("cardNo", String.valueOf(_info.getCardNo()));
				map.put("realName", String.valueOf(_info.getRealName()));
				map.put("bankAddress", String.valueOf(_info.getBankAddress()));
				map.put("branchBank", String.valueOf(_info.getBranchBank()));
				return WebServiceClient.saveBankCard(map);
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
				MToast.showToast(_context, "银行卡信息保存失败！");
				return;
			} else {
				Integer data = null;
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(_context, msgStr);
						return;
					} else if (state == 1) {
						data = jo.getInt("data");
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(_context, StringUtil.isEmpty(msg) ? "银行卡信息保存失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(_context, "数据异常");
				} finally {
					if (_saveCardListener != null) {
						_saveCardListener.saveResult(data);
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
