package com.miduo.financialmanageclient.ui;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.ReplacePlannerResult;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 确认更换理财师页面
 * 
 * @author huozhenpeng
 * 
 */
public class ConfirmReplaceFinancailPlannerActivity extends BaseActivity
		implements OnClickListener {

	private TextView tv_old;
	private TextView tv_new;
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private TextView tv_commit;
	private ReplacePlannerResult result;
	private Map<String,String> map;
	private EditText et_suggest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirmreplacefinancailplanner);
		initView();
		initEvent();
		initData();
	}

	private void initData() {
		if (MyApplication.old_planner != null
				&& MyApplication.new_planner != null) {
			if(MyApplication.old_planner.getLevelId()==2)//IFA
			{
				String old = "<font color='#666666'>将原理财顾问</font><font color='#f34d4d'>"
						+ MyApplication.old_planner.getName()
						+ "("
						+ MyApplication.old_planner.getMobile() + ")" + "</font>";
				tv_old.setText(Html.fromHtml(old));
			}
			else//FA或者SFA
			{
				String name = MyApplication.old_planner.getName();
				char[] chr = null;
				if (!TextUtils.isEmpty(name)) {
					chr = new char[name.length()];
					chr[0] = name.charAt(0);
					for (int i = 1; i < name.length(); i++) {
						chr[i] = '*';
					}
				}
				String oldMobile=MyApplication.old_planner.getMobile();
				oldMobile = oldMobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String old = "<font color='#666666'>将原理财顾问</font><font color='#f34d4d'>"
						+ new String(chr)
						 + "</font>";
				tv_old.setText(Html.fromHtml(old));
			}
			
			if(MyApplication.new_planner.getLevelId()==2)//IFA
			{
				String new_panner = "<font color='#666666'>更换为</font><font color='#2ea7e0'>"
						+ MyApplication.new_planner.getName()
						+ "("
						+ MyApplication.new_planner.getMobile() + ")" + "</font>";
				tv_new.setText(Html.fromHtml(new_panner));
			}
			else
			{
				String name = MyApplication.new_planner.getName();
				char[] chr = null;
				if (!TextUtils.isEmpty(name)) {
					chr = new char[name.length()];
					chr[0] = name.charAt(0);
					for (int i = 1; i < name.length(); i++) {
						chr[i] = '*';
					}
				}
				String newMobile=MyApplication.old_planner.getMobile();
				newMobile = newMobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String str = "<font color='#666666'>更换为</font><font color='#f34d4d'>"
						+ new String(chr)
						+ "</font>";
				tv_new.setText(Html.fromHtml(str));
			}
			
			
		}
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		tv_commit.setOnClickListener(this);
	}

	private void initView() {
		tv_old = (TextView) this.findViewById(R.id.tv_old);
		tv_new = (TextView) this.findViewById(R.id.tv_new);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		title_txt.setText("更换理财顾问");
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.INVISIBLE);
		tv_commit = (TextView) this.findViewById(R.id.tv_commit);
		et_suggest=(EditText) this.findViewById(R.id.et_suggest);
		map=new HashMap<String,String>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.tv_commit:
			map.clear();
			ProgressDialogUtil.showProgress(this);
			if (MyApplication.old_planner != null
					&& MyApplication.new_planner != null) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						map.put("ifaCode", MyApplication.new_planner.getCode());
						if(TextUtils.isEmpty(et_suggest.getText().toString()))
						{
							map.put("description", "");
						}
						else
						{
							map.put("description", et_suggest.getText().toString());
						}
						try {
							result=WebServiceClient.getReplacePlannerResult(map);
							handler.sendEmptyMessage(0x01);
						} catch (AppException e) {
							handler.sendEmptyMessage(0x02);
							e.printStackTrace();
						}
					}
				}).start();
			}
			break;

		default:
			break;
		}
	}

	private Handler handler=new Handler()
	{
		public void handleMessage(Message msg) {
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:
				if(result!=null)
				{
					MToast.showToast(ConfirmReplaceFinancailPlannerActivity.this, result.getMsg());
					if(result.getState()==1)
					{
						MToast.showToast(ConfirmReplaceFinancailPlannerActivity.this, result.getMsg());
						MyApplication.old_planner=null;
						MyApplication.new_planner=null;
						Intent intent=new Intent(ConfirmReplaceFinancailPlannerActivity.this,MyFaActivity.class);
						startActivity(intent);
						finish();
					}
					else
					{
						if(result.getState()==ConstantValues.LOGIN_ERROR)
						{
							MDialog.showPsdErrorDialog(ConfirmReplaceFinancailPlannerActivity.this, result.getMsg());
						}
						else
						{
							MToast.showToast(ConfirmReplaceFinancailPlannerActivity.this, result.getMsg());
						}
						
					}
				}
				break;
			case 0x02:
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("更换理财顾问"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("更换理财顾问"); //
		MobclickAgent.onPause(this);
	}

}
