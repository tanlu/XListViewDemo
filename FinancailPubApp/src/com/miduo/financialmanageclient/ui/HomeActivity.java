package com.miduo.financialmanageclient.ui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.igexin.sdk.PushManager;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.ui.fragment.ProductFragment;
import com.miduo.financialmanageclient.ui.fragment.TodayInfoFragment;
import com.miduo.financialmanageclient.ui.fragment.WarehouseFragment;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.widget.RadioItemView;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class HomeActivity extends BaseFragmentActivity implements
		OnClickListener {
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	// 今日
	private TodayInfoFragment todayFragment;
	// 产品
	private ProductFragment productFragment;
	// 米仓
	private WarehouseFragment warehouseFragment;
	private RadioItemView radioBtn0, radioBtn1, radioBtn2;
	private boolean isDestoryed = false;
	
	public RadioItemView getRadioBtn2() {
		return radioBtn2;
	}

	public void setRadioBtn2(RadioItemView radioBtn2) {
		this.radioBtn2 = radioBtn2;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		MyApplication.homeActivity = this;
		MobclickAgent.openActivityDurationTrack(false);
		setContentView(R.layout.activity_home);
		fragmentManager = getSupportFragmentManager();
		initUi();
		initEvent();

		radioBtn0.setChecked(false);
		radioBtn0.performClick();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this); // 统计时长
		int index = MyApplication.getInstance().home_index;
		if (index == 0) {			
			radioBtn0.performClick();
		} else if (index == 1) {
			radioBtn1.performClick();
		} else if (index == 2) {
			radioBtn2.performClick();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		if (SharePrefUtil.getBoolean(this, SharePrefUtil.IS_LOGIN, false)) {// 个推
			PushManager.getInstance().initialize(this.getApplicationContext());
		}
		radioBtn0 = (RadioItemView) findViewById(R.id.radio_button0);
		radioBtn1 = (RadioItemView) findViewById(R.id.radio_button1);
		radioBtn2 = (RadioItemView) findViewById(R.id.radio_button2);
	}

	private void initEvent() {
		radioBtn0.setOnClickListener(this);
		radioBtn1.setOnClickListener(this);
		radioBtn2.setOnClickListener(this);
	}

	private Calendar startCalendar = Calendar.getInstance();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startCalendar.add(Calendar.SECOND, 1);
			Calendar currentCalendar = Calendar.getInstance();
			if (currentCalendar.before(startCalendar)) {
				finish();
			} else {
				MToast.showToast(this, "再按一次返回键退出");
			}
			startCalendar = Calendar.getInstance();
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.radio_button0:
			MyApplication.index = 0;			
			if (radioBtn0.isChecked()) {
				if (!MyApplication.home_refresh) {
					return;
				}
			}
			MyApplication.home_refresh = false;
			MyApplication.getInstance().home_index = 0;
			radioBtn0.setChecked(true);
			radioBtn1.setChecked(false);
			radioBtn2.setChecked(false);
			fragmentChange(10, false);
			break;
		case R.id.radio_button1:
			if (radioBtn1.isChecked()) {
				return;
			}
			MyApplication.getInstance().home_index = 1;
			radioBtn0.setChecked(false);
			radioBtn1.setChecked(true);
			radioBtn2.setChecked(false);
			fragmentChange(20, false);
			break;
		case R.id.radio_button2:
			if (!CommonUtil.checkLogin(this)) {
				return;
			}
			MyApplication.index = 0;
			if (radioBtn2.isChecked()) {
				return;
			}
			MyApplication.getInstance().home_index = 2;
			radioBtn0.setChecked(false);
			radioBtn1.setChecked(false);
			radioBtn2.setChecked(true);
			fragmentChange(30, false);
			break;
		default:
			break;
		}
	}

	public void fragmentChange(int fragmentId, boolean isSave) {
		fragmentTransaction = fragmentManager.beginTransaction();
		// 想要显示一个fragment,先隐藏所有fragment，防止重叠
		hideFragments(fragmentTransaction);
		MyApplication.fragment_id = fragmentId;
		switch (fragmentId) {
		case 10:
			// 今日
			MyApplication.insureResult = null;
			MyApplication.fromIdLst.clear();
			removeFragments(fragmentTransaction);
			try {
				fragmentTransaction.commitAllowingStateLoss();
				fragmentTransaction = fragmentManager.beginTransaction();
				todayFragment = new TodayInfoFragment();
				fragmentTransaction.add(R.id.tab_content, todayFragment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// case 12:
		// // 客户详情
		// if (customerInfoFragment != null) {
		// if (isSave) {
		// fragmentTransaction.show(customerInfoFragment);
		// } else {
		// customerInfoFragment = new CustomerInfoFragment();
		// fragmentTransaction.add(R.id.tab_content, customerInfoFragment);
		// }
		// } else {
		// customerInfoFragment = new CustomerInfoFragment();
		// fragmentTransaction.add(R.id.tab_content, customerInfoFragment);
		// }
		// break;
		case 20:
			// 产品
			MyApplication.insureResult = null;
			MyApplication.fromIdLst.clear();
			removeFragments(fragmentTransaction);
			try {
				fragmentTransaction.commitAllowingStateLoss();
				fragmentTransaction = fragmentManager.beginTransaction();
				productFragment = new ProductFragment();
				fragmentTransaction.add(R.id.tab_content, productFragment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 30:
			// 米仓
			MyApplication.insureResult = null;
			MyApplication.fromIdLst.clear();
			removeFragments(fragmentTransaction);
			try {
				fragmentTransaction.commitAllowingStateLoss();
				fragmentTransaction = fragmentManager.beginTransaction();
				warehouseFragment = new WarehouseFragment();
				fragmentTransaction.add(R.id.tab_content, warehouseFragment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		if (!isDestoryed) {
			fragmentTransaction.addToBackStack(null);
			try {
				fragmentTransaction.commitAllowingStateLoss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void hideFragments(FragmentTransaction fragmentTransaction2) {
		// TODO Auto-generated method stub
		if (todayFragment != null && !todayFragment.isHidden()) {
			fragmentTransaction2.hide(todayFragment);
		}
		if (productFragment != null && !productFragment.isHidden()) {
			fragmentTransaction2.hide(productFragment);
		}
		if (warehouseFragment != null && !warehouseFragment.isHidden()) {
			fragmentTransaction2.hide(warehouseFragment);
		}
	}

	private void removeFragments(FragmentTransaction fragmentTransaction2) {
		// TODO Auto-generated method stub
		if (todayFragment != null) {
			fragmentTransaction2.remove(todayFragment);
		}
		if (productFragment != null) {
			fragmentTransaction2.remove(productFragment);
		}
		if (warehouseFragment != null) {
			fragmentTransaction2.remove(warehouseFragment);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		isDestoryed = true;
	}

	/**
	 * 推送，点击我知道了，设为已读
	 */
	public void submitRead() {
		if (MyApplication.message != null) {
			final Map<String, String> map = new HashMap<String, String>();
			map.put("uuid", MyApplication.message.getMsgUuid());
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						WebServiceClient.setMsgRead(map);
						handler.sendEmptyMessage(0x01);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x01:

				break;

			default:
				break;
			}
		};
	};
}
