package com.miduo.financialmanageclient.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class DialogLoginActivity extends BaseActivity {
	private EditText ed_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_login);

		DialogBean dialog = (DialogBean) getIntent().getSerializableExtra("dialog");

		TextView titleTxt = (TextView) findViewById(R.id.title_txt);
		TextView contentTxt = (TextView) findViewById(R.id.content_txt);
		TextView submit_txt = (TextView) findViewById(R.id.submit_txt);
		TextView cancel_txt = (TextView) findViewById(R.id.cancel_txt);
		ed_content = (EditText) findViewById(R.id.ed_content);
		titleTxt.setText(dialog.getTitle());
		contentTxt.setText(dialog.getContent());
		submit_txt.setText(dialog.getSubmit());
		cancel_txt.setText(dialog.getCancel());

		if (StringUtil.isEmpty(titleTxt.getText().toString())) {
			titleTxt.setVisibility(View.GONE);
		}
		if (StringUtil.isEmpty(contentTxt.getText().toString())) {
			contentTxt.setVisibility(View.GONE);
		}
		submit_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(ed_content.getText())) {
					boolean flag = checkLoginPassword(ed_content.getText().toString());
					if (flag) {
						setResult(RESULT_OK);
						finish();
					} else {
						MToast.showToast(DialogLoginActivity.this, "密码错误");
					}
				} else {
					MToast.showToast(DialogLoginActivity.this, "请输入密码");
				}
			}

		});
		cancel_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
	}

	private boolean checkLoginPassword(String psd) {
		String temp = SharePrefUtil.getString(DialogLoginActivity.this, SharePrefUtil.ACCOUNT_PSD, "");
		return temp.equals(psd);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
