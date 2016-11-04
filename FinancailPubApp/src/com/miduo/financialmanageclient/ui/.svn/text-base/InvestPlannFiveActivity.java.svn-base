package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.MToast;
import com.umeng.analytics.MobclickAgent;

public class InvestPlannFiveActivity extends BaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView next_txt;
	private RadioButton radio_btn0, radio_btn1, radio_btn2, radio_btn3;
	private RadioGroup radioGroup;
	private Integer index = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invest_plann_five);

		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投资第五题"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
		
		if (MyApplication.investBean != null) {
			Integer index = MyApplication.investBean.getHowDo();
			if (index != null) {
				switch (index.intValue()) {
				case 1:
					radio_btn0.setChecked(true);
					break;
				case 2:
					radio_btn1.setChecked(true);
					break;
				case 3:
					radio_btn2.setChecked(true);
					break;
				case 4:
					radio_btn3.setChecked(true);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("如何投资第五题"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		next_txt = (TextView) findViewById(R.id.next_txt);
		radio_btn0 = (RadioButton) findViewById(R.id.radio_btn0);
		radio_btn1 = (RadioButton) findViewById(R.id.radio_btn1);
		radio_btn2 = (RadioButton) findViewById(R.id.radio_btn2);
		radio_btn3 = (RadioButton) findViewById(R.id.radio_btn3);
		titleTxt.setText("如何投资");
		rightTxt.setVisibility(View.GONE);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		next_txt.setOnClickListener(this);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio_btn0:
					index = 1;
					break;
				case R.id.radio_btn1:
					index = 2;
					break;
				case R.id.radio_btn2:
					index = 3;
					break;
				case R.id.radio_btn3:
					index = 4;
					break;
				default:
					break;
				}
				MyApplication.investBean.setHowDo(index);
			}
		});		
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.next_txt:
			if(index==null){		
				MToast.showToast(this, "请选择一个最接近实际情况的选项");
				return;
			}
			Intent intent = new Intent(this, InvestPlannResultActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
