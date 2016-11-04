package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class InsureThreeActivity extends BaseActivity implements OnClickListener {
	private ImageView left_img;
	private TextView title_txt;
	private TextView next_txt;
	private ImageView jianshao;
	private ImageView zengjia;
	private TextView zinvshu;
	private LinearLayout zinv1;
	private LinearLayout zinv2;
	private LinearLayout zinv3;
	private int data, data1, data2, data3;
	private EditText et_1;
	private EditText et_2;
	private EditText et_3;
	private int a = 0, b = 0, c = 0;
	private int num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insurethree);

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

		jianshao = (ImageView) findViewById(R.id.jianshao);
		zengjia = (ImageView) findViewById(R.id.zengjia);
		zinvshu = (TextView) findViewById(R.id.zinvshu);
		zinvshu.setText("0");

		et_1 = (EditText) findViewById(R.id.et_1);
		et_2 = (EditText) findViewById(R.id.et_2);
		et_3 = (EditText) findViewById(R.id.et_3);

		zinv1 = (LinearLayout) findViewById(R.id.zinv1);
		zinv2 = (LinearLayout) findViewById(R.id.zinv2);
		zinv3 = (LinearLayout) findViewById(R.id.zinv3);
		zinv1.setVisibility(View.GONE);
		zinv2.setVisibility(View.GONE);
		zinv3.setVisibility(View.GONE);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		next_txt.setOnClickListener(this);
		zengjia.setOnClickListener(this);
		jianshao.setOnClickListener(this);
		et_1.setOnFocusChangeListener(new OnFocusChangeEvent());
		et_2.setOnFocusChangeListener(new OnFocusChangeEvent());
		et_3.setOnFocusChangeListener(new OnFocusChangeEvent());
	}

	private class OnFocusChangeEvent implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (!hasFocus) {
				switch (v.getId()) {
				case R.id.et_1:
					// 年龄
					if (!checkAge(et_1.getText().toString())) {
						MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					}
					break;
				case R.id.et_2:
					// 年龄
					if (!checkAge(et_2.getText().toString())) {
						MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					}
					break;
				case R.id.et_3:
					// 年龄
					if (!checkAge(et_3.getText().toString())) {
						MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private boolean checkAge(String age) {
		if (StringUtil.isEmpty(age)) {
			return false;
		}
		int data = Integer.parseInt(age);
		if (data < 1 || data > 22) {
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
			String age1 = et_1.getText().toString();
			String age2 = et_1.getText().toString();
			String age3 = et_1.getText().toString();
			num = Integer.parseInt(zinvshu.getText().toString());
			switch (num) {
			case 0:
				break;
			case 1:
				if (!checkAge(age1)) {
					MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					return;
				}
				break;
			case 2:
				if (!checkAge(age1)) {
					MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					return;
				}
				if (!checkAge(age2)) {
					MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					return;
				}
				break;
			case 3:
				if (!checkAge(age1)) {
					MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					return;
				}
				if (!checkAge(age2)) {
					MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					return;
				}
				if (!checkAge(age3)) {
					MToast.showToast(getApplicationContext(), "测试年龄应介于1~22岁之间");
					return;
				}
				break;
			default:
				break;
			}
			saveInfo();
			Intent intent = new Intent();
			intent.setClass(this, InsureFourActivity.class);
			startActivity(intent);
			break;
		case R.id.zengjia:
			// 增加
			if (num == 3) {
				return;
			}
			num++;
			switch (num) {
			case 1:
				zinv1.setVisibility(View.VISIBLE);
				break;
			case 2:
				zinv2.setVisibility(View.VISIBLE);
				break;
			case 3:
				zinv3.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
			zinvshu.setText(String.valueOf(num));
			break;
		case R.id.jianshao:
			// 减少
			if (num == 0) {
				return;
			}
			num--;
			switch (num) {
			case 2:
				zinv3.setVisibility(View.GONE);
				et_3.setText("");
				break;
			case 1:
				zinv2.setVisibility(View.GONE);
				et_2.setText("");
				break;
			case 0:
				zinv1.setVisibility(View.GONE);
				et_1.setText("");
				break;
			default:
				break;
			}
			zinvshu.setText(String.valueOf(num));
			break;
		default:
			break;
		}
	}

	private void saveInfo() {
		// TODO Auto-generated method stub
		num = Integer.parseInt(zinvshu.getText().toString());
		MyApplication.insureBean.setChildNum(num);
		String age1 = et_1.getText().toString();
		String age2 = et_2.getText().toString();
		String age3 = et_3.getText().toString();
		switch (num) {
		case 0:
			MyApplication.insureBean.setChildAge1(null);
			MyApplication.insureBean.setChildAge2(null);
			MyApplication.insureBean.setChildAge3(null);
			break;
		case 1:
			if (StringUtil.isEmpty(age1)) {
				MyApplication.insureBean.setChildAge1(null);
			} else {
				MyApplication.insureBean.setChildAge1(Integer.parseInt(age1));
			}
			MyApplication.insureBean.setChildAge2(null);
			MyApplication.insureBean.setChildAge3(null);
			break;
		case 2:
			if (StringUtil.isEmpty(age1)) {
				MyApplication.insureBean.setChildAge1(null);
			} else {
				MyApplication.insureBean.setChildAge1(Integer.parseInt(age1));
			}
			if (StringUtil.isEmpty(age2)) {
				MyApplication.insureBean.setChildAge2(null);
			} else {
				MyApplication.insureBean.setChildAge2(Integer.parseInt(age2));
			}
			MyApplication.insureBean.setChildAge3(null);
			break;
		case 3:
			if (StringUtil.isEmpty(age1)) {
				MyApplication.insureBean.setChildAge1(null);
			} else {
				MyApplication.insureBean.setChildAge1(Integer.parseInt(age1));
			}
			if (StringUtil.isEmpty(age2)) {
				MyApplication.insureBean.setChildAge2(null);
			} else {
				MyApplication.insureBean.setChildAge2(Integer.parseInt(age2));
			}
			if (StringUtil.isEmpty(age3)) {
				MyApplication.insureBean.setChildAge3(null);
			} else {
				MyApplication.insureBean.setChildAge3(Integer.parseInt(age3));
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投保页面3");
		MobclickAgent.onResume(this);

		if (MyApplication.insureBean != null) {
			Integer num = MyApplication.insureBean.getChildNum();
			Integer age1 = MyApplication.insureBean.getChildAge1();
			Integer age2 = MyApplication.insureBean.getChildAge2();
			Integer age3 = MyApplication.insureBean.getChildAge3();
			if (num != null && num > 0) {
				zinvshu.setText(String.valueOf(num));
				switch (num) {
				case 1:
					zinv1.setVisibility(View.VISIBLE);
					zinv2.setVisibility(View.GONE);
					zinv3.setVisibility(View.GONE);
					if (age1 == null) {
						et_1.setText("");
					} else {
						et_1.setText(String.valueOf(age1));
					}
					break;
				case 2:
					zinv1.setVisibility(View.VISIBLE);
					zinv2.setVisibility(View.VISIBLE);
					zinv3.setVisibility(View.GONE);
					if (age1 == null) {
						et_1.setText("");
					} else {
						et_1.setText(String.valueOf(age1));
					}
					if (age2 == null) {
						et_2.setText("");
					} else {
						et_2.setText(String.valueOf(age2));
					}
					break;
				case 3:
					zinv1.setVisibility(View.VISIBLE);
					zinv2.setVisibility(View.VISIBLE);
					zinv3.setVisibility(View.VISIBLE);
					if (age1 == null) {
						et_1.setText("");
					} else {
						et_1.setText(String.valueOf(age1));
					}
					if (age2 == null) {
						et_2.setText("");
					} else {
						et_2.setText(String.valueOf(age2));
					}
					if (age3 == null) {
						et_3.setText("");
					} else {
						et_3.setText(String.valueOf(age3));
					}
					break;
				default:
					break;
				}
				et_1.requestFocus();
				et_1.setSelection(et_1.getText().length());	
			} else {
				et_1.setText("");
				et_2.setText("");
				et_3.setText("");
				zinv1.setVisibility(View.GONE);
				zinv2.setVisibility(View.GONE);
				zinv3.setVisibility(View.GONE);
				zinvshu.setText("0");
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("如何投保页面3");
		MobclickAgent.onPause(this);
	}
}
