package com.miduo.financialmanageclient.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.util.FileUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;

public class DialogProgressActivity extends Activity {
	/* 进度条与通知ui刷新的handler和msg常量 */
	private ProgressBar mProgress;
	private int progress;

	private Thread downLoadThread;
	// 返回的安装包url
	private String apkUrl = "";
	/* 下载包安装路径 */
	private String saveFileName = null;
	private static final int DOWN_UPDATE = 1;

	private static final int DOWN_OVER = 2;
	private boolean interceptFlag = false;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				installApk();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progress);

		DialogBean dialog = (DialogBean) getIntent().getSerializableExtra("dialog");
		apkUrl = getIntent().getStringExtra("url");
		TextView titleTxt = (TextView) findViewById(R.id.title_txt);
		mProgress = (ProgressBar) findViewById(R.id.progress);
		TextView submit_txt = (TextView) findViewById(R.id.submit_txt);
		titleTxt.setText(dialog.getTitle());
		submit_txt.setText(dialog.getSubmit());

		submit_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				interceptFlag = true;
				setResult(RESULT_OK);
				finish();
			}

		});
		downloadApk();
	}

	/**
	 * 下载apk
	 * 
	 * @param url
	 */

	public void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				saveFileName = FileUtil.getPath("UpdateDemoRelease.apk", ConstantValues.UPDATE_TYPE);
				File ApkFile = new File(saveFileName);
				FileOutputStream fos = new FileOutputStream(ApkFile);

				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载.

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		startActivity(i);
		SharePrefUtil.saveBoolean(DialogProgressActivity.this, "update_info", "is_first_in", true);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
