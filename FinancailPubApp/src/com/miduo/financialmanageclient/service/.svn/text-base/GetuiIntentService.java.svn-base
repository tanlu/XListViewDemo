package com.miduo.financialmanageclient.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.miduo.financialmanageclient.ws.WebServiceClient;

import android.app.IntentService;
import android.content.Intent;

/**
 * 
 * @author huozhenpeng www.miduo.com 2015-5-12
 * 
 */
public class GetuiIntentService extends IntentService {
	Map<String, String> map = new HashMap<String, String>();
	public GetuiIntentService() {
		super("GetuiIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		// 向服务器提交clientid，uid等参数（不用再new线程了，本身就运行于子线程）
		String clientId = intent.getStringExtra("CLIENTID");
		map.clear();
//		String deviceName=android.os.Build.MODEL;
//		String appType="1";
		map.put("cliendId", clientId);
//		map.put("deviceName", deviceName);
//		map.put("appType", appType);
		try {
			String result = WebServiceClient.submitClientID(map);
			JSONObject jo = null;

			jo = new JSONObject(result);
			int state = jo.getInt("state");
			if (state == 1) {
				//成功
			} else {
				//失败

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		showDialog(clientId);

	}


}
