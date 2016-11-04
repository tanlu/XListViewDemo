package com.miduo.financialmanageclient.ui;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.util.StringUtil;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Dialog1Activity extends BaseDialogActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_layout1);
		DialogBean dialog = (DialogBean) getIntent().getSerializableExtra("dialog");

		TextView titleTxt = (TextView) findViewById(R.id.title_txt);
		TextView contentTxt = (TextView) findViewById(R.id.content_txt);
		TextView submit_txt = (TextView) findViewById(R.id.submit_txt);
		titleTxt.setText(dialog.getTitle());
		contentTxt.setText(dialog.getContent());
		submit_txt.setText(dialog.getSubmit());
		if(StringUtil.isEmpty(titleTxt.getText().toString())){
			titleTxt.setVisibility(View.GONE);
		}
		if(StringUtil.isEmpty(contentTxt.getText().toString())){
			contentTxt.setVisibility(View.GONE);
		}
		submit_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
