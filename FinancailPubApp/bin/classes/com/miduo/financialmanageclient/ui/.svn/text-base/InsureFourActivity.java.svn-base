package com.miduo.financialmanageclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class InsureFourActivity extends BaseActivity implements OnClickListener {
	private ImageView left_img;
	private TextView title_txt;
	private TextView next_txt;
	private EditText muqin_age;
	private EditText fuqin_age;
	private CheckBox muqin_box;
	private CheckBox fuqin_box;
	private EditText xiaojingfei;
	private LinearLayout ll_xiaojingfei;
	private int manAge = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insurefour);
		int manAge = MyApplication.insureBean.getManAge().intValue();
		int spouseAage = MyApplication.insureBean.getWifeAge() == null ? 0 : MyApplication.insureBean.getWifeAge()
				.intValue();
		manAge = spouseAage >= spouseAage ? manAge : spouseAage;
		init();
		initEvent();
	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("如何投保");
		next_txt = (TextView) findViewById(R.id.next_txt);
		TextView right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);

		muqin_age = (EditText) findViewById(R.id.muqin_age);
		fuqin_age = (EditText) findViewById(R.id.fuqin_age);

		muqin_box = (CheckBox) findViewById(R.id.muqin_box);
		fuqin_box = (CheckBox) findViewById(R.id.fuqin_box);

		xiaojingfei = (EditText) findViewById(R.id.xiaojingfei);
		ll_xiaojingfei = (LinearLayout) findViewById(R.id.ll_xiaojingfei);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		next_txt.setOnClickListener(this);
		fuqin_age.setOnFocusChangeListener(new OnFocusChangeEvent());
		muqin_age.setOnFocusChangeListener(new OnFocusChangeEvent());

		fuqin_box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				fuqin_age.setText("");
				if (isChecked) {
					// 如果被选中
					if (!muqin_box.isChecked()) {
						ll_xiaojingfei.setVisibility(View.VISIBLE);
						xiaojingfei.setText("");
					}
					fuqin_age.setEnabled(true);
				} else {
					// 没有被选中
					fuqin_age.setEnabled(false);
					if (!muqin_box.isChecked()) {
						ll_xiaojingfei.setVisibility(View.GONE);
						xiaojingfei.setText("");
					}
				}
			}

		});

		muqin_box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				muqin_age.setText("");
				if (isChecked) {
					// 如果被选中
					if (!fuqin_box.isChecked()) {
						ll_xiaojingfei.setVisibility(View.VISIBLE);
						xiaojingfei.setText("");
					}
					muqin_age.setEnabled(true);
				} else {
					// 没有被选中
					muqin_age.setEnabled(false);
					if (!fuqin_box.isChecked()) {
						ll_xiaojingfei.setVisibility(View.GONE);
						xiaojingfei.setText("");
					}
				}

			}

		});
	}

	private class OnFocusChangeEvent implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (!hasFocus) {
				switch (v.getId()) {
				case R.id.fuqin_age:
					// 父亲年龄
					if (!checkFatherAge(fuqin_age.getText().toString())) {
						MToast.showToast(getApplicationContext(), "父亲年龄应在" + manAge + "~80岁之间");
					}
					break;
				case R.id.muqin_age:
					// 母亲年龄
					if (!checkMotherAge(muqin_age.getText().toString())) {
						MToast.showToast(getApplicationContext(), "母亲年龄应在" + manAge + "~84岁之间");
					}
					break;
				case R.id.xiaojingfei:
					// 孝亲费
					if (!checkMoney(xiaojingfei.getText().toString())) {
						MToast.showToast(getApplicationContext(), "孝亲费应在0~9999万之间");
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private boolean checkFatherAge(String age) {
		if (StringUtil.isEmpty(age)) {
			return false;
		}
		int data = Integer.parseInt(age);
		if (data < manAge || data > 80) {
			return false;
		}
		return true;
	}

	private boolean checkMotherAge(String age) {
		if (StringUtil.isEmpty(age)) {
			return false;
		}
		int data = Integer.parseInt(age);
		if (data < manAge || data > 84) {
			return false;
		}
		return true;
	}

	private boolean checkMoney(String money) {
		if (StringUtil.isEmpty(money)) {
			return false;
		}
		int data = Integer.parseInt(money);
		if (data < 0 || data > 9999) {
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			saveInfo();
			finish();
			break;
		case R.id.next_txt:
			String fuqinAge = fuqin_age.getText().toString();
			String muqinAge = muqin_age.getText().toString();
			String xiaoqinfei = xiaojingfei.getText().toString();
			if (fuqin_box.isChecked()) {
				if (!checkFatherAge(fuqinAge)) {
					MToast.showToast(getApplicationContext(), "请正确输入信息");
					return;
				}
			} else {

			}
			if (muqin_box.isChecked()) {
				if (!checkMotherAge(muqinAge)) {
					MToast.showToast(getApplicationContext(), "请正确输入信息");
					return;
				}
			}
			if (fuqin_box.isChecked() || muqin_box.isChecked()) {
				if (!checkMoney(xiaojingfei.getText().toString())) {
					MToast.showToast(getApplicationContext(), "请正确输入信息");
					return;
				}
			}
			saveInfo();
			startActivity(new Intent(this, InsureFiveActivity.class));
			break;
		default:
			break;
		}
	}

	private void saveInfo() {
		// TODO Auto-generated method stub
		String fuqinAge = fuqin_age.getText().toString();
		String muqinAge = muqin_age.getText().toString();
		String xiaoqinfei = xiaojingfei.getText().toString();
		if (fuqin_box.isChecked()) {
			MyApplication.insureBean.setHasFather(true);
			if (StringUtil.isEmpty(fuqinAge)) {
				MyApplication.insureBean.setParentFatherAge(null);
			} else {
				MyApplication.insureBean.setParentFatherAge(Integer.parseInt(fuqinAge));
			}
		} else {
			MyApplication.insureBean.setHasFather(false);
			MyApplication.insureBean.setParentFatherAge(null);
		}
		if (muqin_box.isChecked()) {
			MyApplication.insureBean.setHasMother(true);
			if (StringUtil.isEmpty(muqinAge)) {
				MyApplication.insureBean.setParentMotherAge(null);
			} else {
				MyApplication.insureBean.setParentMotherAge(Integer.parseInt(muqinAge));
			}
		} else {
			MyApplication.insureBean.setHasMother(false);
			MyApplication.insureBean.setParentMotherAge(null);
		}
		if (StringUtil.isEmpty(xiaoqinfei)) {
			MyApplication.insureBean.setParentMaintenance(null);
		} else {
			MyApplication.insureBean.setParentMaintenance(Integer.parseInt(xiaoqinfei));
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投保页面5");
		MobclickAgent.onResume(this);

		if (MyApplication.insureBean != null) {
			if (MyApplication.insureBean.isHasFather()) {
				fuqin_box.setChecked(true);
			} else {
				fuqin_box.setChecked(false);
			}

			if (MyApplication.insureBean.isHasMother()) {
				muqin_box.setChecked(true);
			} else {
				muqin_box.setChecked(false);
			}
			Integer fatherAge = MyApplication.insureBean.getParentFatherAge();
			Integer motherAge = MyApplication.insureBean.getParentMotherAge();
			Integer maintenance = MyApplication.insureBean.getParentMaintenance();
			if (fatherAge == null) {
				fuqin_age.setText("");
			} else {
				fuqin_age.setText(String.valueOf(fatherAge.intValue()));
			}
			if (motherAge == null) {
				muqin_age.setText("");
			} else {
				muqin_age.setText(String.valueOf(fatherAge.intValue()));
			}
			if (MyApplication.insureBean.isHasMother() || MyApplication.insureBean.isHasMother()) {
				ll_xiaojingfei.setVisibility(View.VISIBLE);
			} else {
				ll_xiaojingfei.setVisibility(View.GONE);
			}
			if (maintenance == null) {
				xiaojingfei.setText("");
			} else {
				xiaojingfei.setText(String.valueOf(maintenance.intValue()));
			}
			if (MyApplication.insureBean.isHasFather()) {
				fuqin_age.requestFocus();
				fuqin_age.setSelection(fuqin_age.getText().length());
			} else if (MyApplication.insureBean.isHasFather()) {
				muqin_age.requestFocus();
				muqin_age.setSelection(muqin_age.getText().length());
			}

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("如何投保页面5");
		MobclickAgent.onPause(this);
	}

}
