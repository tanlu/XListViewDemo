package com.miduo.financialmanageclient.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.miduo.financialmanageclient.listener.LoginListener;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class Login {
	private Context _context;
	private LoginAsyncTask loginAsyncTask;
	private LoginListener _loginListener;

	public Login(Context context, LoginListener loginListener) {
		this._context = context;
		this._loginListener = loginListener;
	}

	public void userLogin() {
		String loginName = SharePrefUtil.getString(_context, SharePrefUtil.ACCOUNT_MOBILE, "");
		String loginPwd = SharePrefUtil.getString(_context, SharePrefUtil.ACCOUNT_PSD, "");
		if (loginAsyncTask != null) {
			loginAsyncTask.cancel(true);
			loginAsyncTask = null;
		}		
		loginAsyncTask = new LoginAsyncTask(loginName, loginPwd);
		loginAsyncTask.execute();
	}

	private class LoginAsyncTask extends AsyncTask<Void, Void, String> {

		private String _mobile;
		private String _password;

		public LoginAsyncTask(String mobile, String password) {
			this._mobile = mobile;
			this._password = password;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobile", _mobile);
			map.put("password", _password);
			try {
				return WebServiceClient.login(map);
			} catch (AppException e) {
				// TODO Auto-generated catch block
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {			
			String data = null;
			if (null != result) {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						data = jo.getString("data");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (_loginListener != null) {
				_loginListener.login(data);
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}
}
