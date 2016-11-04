package com.miduo.financialmanageclient.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.NPlannerInfo;
import com.miduo.financialmanageclient.bean.NPlannerInfo.Education;
import com.miduo.financialmanageclient.bean.NPlannerInfo.NPlannerBean;
import com.miduo.financialmanageclient.bean.NPlannerInfo.NPlannerBean.IfaDiplomaListEntity;
import com.miduo.financialmanageclient.bean.NPlannerInfo.NPlannerBean.IfaJobListEntity;
import com.miduo.financialmanageclient.bean.NPlannerTempBean;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.ui.adapter.MypagerAdapter;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SocialShareUtil;
import com.miduo.financialmanageclient.widget.CircleImageView;
import com.miduo.financialmanageclient.widget.MyListView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.analytics.MobclickAgent;

public class MyFaActivity extends GesterSetBaseActivity implements
		OnClickListener {
	private TextView dizhi, phone, jianjie, zhiye, name;
	private LinearLayout xueli;
	private MyListView lv_jingli;
	private ViewPager vp_pic;
	private Context mContext;
	private TextView huanyiwei;
	private TextView page_num_txt;
	int is_hide = 0;
	private RelativeLayout rl_01, rl_02, rl_03;
	private View v_01, v_02, v_03;
	private Button button;
	private ImageView xiaoxi;
	private ImageView dadianhua, left_img;
	private NPlannerInfo nPlannerInfo;
	private NPlannerBean nPlannerBean;
	private CircleImageView head_circleimg;
	private List<IfaJobListEntity> ifajobList;
	private List<IfaDiplomaListEntity> ifaDiploList;
	private IfaJobListEntity ifaJobBean;
	private MypagerAdapter mypagerAdapter;
	private ImageLoader imageloader = null;
	private List<ImageView> imageLists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfa);
		mContext = this;
		init();
		initEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("我的理财顾问"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("我的理财顾问"); //
		MobclickAgent.onPause(this);
	}

	private void init() {
		imageloader = ImageLoader.getInstance();
		imageloader.init(ImageLoaderConfiguration.createDefault(this));
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("我的理财顾问");
		left_img = (ImageView) findViewById(R.id.left_img);
		left_img.setOnClickListener(this);
		TextView right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);

		name = (TextView) findViewById(R.id.name);
		zhiye = (TextView) findViewById(R.id.zhiye);
		dizhi = (TextView) findViewById(R.id.dizhi);
		phone = (TextView) findViewById(R.id.phone);
		jianjie = (TextView) findViewById(R.id.jianjie);
		xueli = (LinearLayout) findViewById(R.id.xueli);
		lv_jingli = (MyListView) findViewById(R.id.lv_jingli);
		vp_pic = (ViewPager) findViewById(R.id.vp_pic);
		huanyiwei = (TextView) findViewById(R.id.huanyiwei);
		page_num_txt = (TextView) findViewById(R.id.page_num_txt);
		xiaoxi = (ImageView) findViewById(R.id.xiaoxi);
		dadianhua = (ImageView) findViewById(R.id.dadianhua);


		// 要隐藏部分布局
		head_circleimg = (CircleImageView) findViewById(R.id.head_circleimg);
		rl_01 = (RelativeLayout) findViewById(R.id.rl_01);
		rl_02 = (RelativeLayout) findViewById(R.id.rl_02);
		rl_03 = (RelativeLayout) findViewById(R.id.rl_03);
		button = (Button) findViewById(R.id.button);
		v_01 = findViewById(R.id.v_01);
		v_02 = findViewById(R.id.v_02);
		v_03 = findViewById(R.id.v_03);
		hideAll(View.INVISIBLE);
		imageLists = new ArrayList<ImageView>();
	}
	
	private void hideAll(int visible)
	{
		head_circleimg.setVisibility(visible);
		zhiye.setVisibility(visible);
		dizhi.setVisibility(visible);
		rl_01.setVisibility(visible);
		rl_02.setVisibility(visible);
		v_02.setVisibility(visible);
		v_01.setVisibility(visible);
		v_03.setVisibility(visible);
		xueli.setVisibility(visible);
		lv_jingli.setVisibility(visible);
		rl_03.setVisibility(visible);
		button.setVisibility(visible);
		huanyiwei.setVisibility(visible);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		huanyiwei.setOnClickListener(this);
		xiaoxi.setOnClickListener(this);
		dadianhua.setOnClickListener(this);
		button.setOnClickListener(this);
	}

	private void initDate() {
		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nPlannerInfo = WebServiceClient.getPlannerInfo();					
				} catch (AppException e) {
					e.printStackTrace();
				}finally{
					handler.sendEmptyMessage(0x01);
				}

			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		DialogBean data;
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.huanyiwei:
			startActivity(new Intent(this,
					ReplaceFinancailPlannerActivity.class));
			break;
		case R.id.xiaoxi:
			// 发送短信
			String message3 = "是否对" + phone.getText() + "发送消息？";
			data = new DialogBean();
			data.setContent(message3);
			data.setSubmit("立即发送");
			data.setCancel("取消");

			DialogEventListener lister = new DialogEventListener() {

				@Override
				public void submit() {
					Intent intent = new Intent(Intent.ACTION_SENDTO,
							Uri.parse("smsto:" + phone.getText()));
					intent.putExtra("sms_body", "");
					startActivity(intent);
				}

				@Override
				public void cancel() {

				}
			};
			data.setDialogEvent(lister);
			MDialog.showDialog2(this, data);

			break;
		case R.id.dadianhua:
			// 拨打电话
			String message2 = "是否对" + phone.getText() + "拨打电话？";
			data = new DialogBean();
			data.setContent(message2);
			data.setSubmit("立即拨打");
			data.setCancel("取消");

			DialogEventListener lister2 = new DialogEventListener() {

				@Override
				public void submit() {
					Intent intent = new Intent(Intent.ACTION_CALL,
							Uri.parse("tel:" + phone.getText()));
					startActivity(intent);
				}

				@Override
				public void cancel() {

				}
			};
			data.setDialogEvent(lister2);
			MDialog.showDialog2(this, data);
			break;
		case R.id.button:
			SocialShareUtil.share(this, nPlannerBean.getShareUrl(), "米多独立理财顾问——"+nPlannerBean.getRealName(), "为您提供中立、客观、公正的个性化财富管理服务");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** 使用SSO授权必须添加如下代码 */
		SocialShareUtil.closeShare(requestCode, resultCode, data);
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:
				if (nPlannerInfo != null) {
					if (nPlannerInfo.getState() == 1) {
						nPlannerBean = nPlannerInfo.getData();
						MyApplication.old_planner = new NPlannerTempBean(
								nPlannerBean.getRealName(),
								nPlannerBean.getFaCode(),
								nPlannerBean.getContact(),nPlannerBean.getLevelId());
						if (nPlannerBean != null) {
							hideAll(View.VISIBLE);
							switch (nPlannerBean.getLevelId()) {
							case 1:// FA
							case 3:// SFA
								hideLayout();
								String realname = nPlannerBean.getRealName();
								char[] chr = null;
								if (!TextUtils.isEmpty(realname)) {
									chr = new char[realname.length()];
									chr[0] = realname.charAt(0);
									for (int i = 1; i < realname.length(); i++) {
										chr[i] = '*';
									}
								}
								name.setText(new String(chr));
								break;
							default:
							case 2:// IFA
								showIfa();
								imageloader.displayImage(
										nPlannerBean.getAvatar(),
										head_circleimg);
								break;

							}
						}
					} else {
						if (nPlannerInfo.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(MyFaActivity.this,
									nPlannerInfo.getMsg());
						} else {
							MToast.showToast(MyFaActivity.this,
									nPlannerInfo.getMsg());
						}

					}
				}

				break;

			default:
				break;
			}
		};
	};
	private MyAdapter myAdapter;

	/**
	 * 是FA或者SFA时隐藏部分布局
	 */
	private void hideLayout() {
		head_circleimg.setBackgroundResource(R.drawable.fa_big_new);
		zhiye.setVisibility(View.GONE);
		dizhi.setVisibility(View.GONE);
		rl_01.setVisibility(View.GONE);
		rl_02.setVisibility(View.GONE);
		v_02.setVisibility(View.GONE);
		v_01.setVisibility(View.GONE);
		v_03.setVisibility(View.GONE);
		xueli.setVisibility(View.GONE);
		lv_jingli.setVisibility(View.GONE);
		rl_03.setVisibility(View.GONE);
		button.setVisibility(View.GONE);
	}

	/**
	 * ifa显示信息
	 */
	private void showIfa() {
		ifajobList = nPlannerBean.getIfaJobList();
		ifaDiploList = nPlannerBean.getIfaDiplomaList();
		name.setText(nPlannerBean.getRealName());
		String certificate = "";
		for (int i = 0; i < ifaDiploList.size(); i++) {
			certificate += ifaDiploList.get(i).getDiplomaName() + "\u3000";
		}
		zhiye.setText(certificate);
		dizhi.setText(nPlannerBean.getProvince() + nPlannerBean.getCity()
				+ nPlannerBean.getArea());
		phone.setText(nPlannerBean.getContact());
		rl_02.setVisibility(View.VISIBLE);
		if (TextUtils.isEmpty(nPlannerBean.getDescription())) {
			rl_02.setVisibility(View.GONE);
		} else {
			jianjie.setText(nPlannerBean.getDescription());
		}

		// 动态设置学历
		xueli.setVisibility(View.VISIBLE);
		if (nPlannerBean.getIfaEduVoList().size() > 0) {
			Education education = null;
			LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.setMargins(0,
					(int) getResources().getDimension(R.dimen.px2dp_20), 0, 0);
			for (int i = 0; i < nPlannerBean.getIfaEduVoList().size(); i++) {
				education = nPlannerBean.getIfaEduVoList().get(i);
				TextView textView2 = new TextView(this);
				textView2.setText(education.getSchoolName() + "\u3000"
						+ education.getProfession() + "\u3000"
						+ education.getDescription());
				textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						getResources().getDimension(R.dimen.px2sp_32));
				textView2.setMarqueeRepeatLimit((int) getResources()
						.getDimension(R.dimen.px2dp_10));
				textView2.setLayoutParams(layoutParams);
				textView2.setTextColor(0xff333333);
				xueli.addView(textView2);
			}
		} else {
			xueli.setVisibility(View.GONE);
		}
		if(ifajobList.size()>=0)
		{
			lv_jingli.setVisibility(View.VISIBLE);
		}
		else
		{
			lv_jingli.setVisibility(View.GONE);
		}
		if(ifajobList.size()==0&&nPlannerBean.getIfaEduVoList().size()==0)
		{
			v_01.setVisibility(View.GONE);
		}
		else
		{
			v_01.setVisibility(View.VISIBLE);
		}
		if (myAdapter == null) {
			myAdapter = new MyAdapter();
			lv_jingli.setAdapter(myAdapter);
		} else {
			myAdapter.notifyDataSetChanged();
		}
		// 初始化图片的个数显示
		page_num_txt.setText(1 + "/" + ifaDiploList.size());
		for (int i = 0; i < ifaDiploList.size(); i++) {
			ImageView iv=new ImageView(this);
			iv.setScaleType(ScaleType.FIT_XY);
			imageLists.add(iv);
		}
		if (mypagerAdapter == null) {
			mypagerAdapter = new MypagerAdapter(mContext, ifaDiploList,
					imageloader, imageLists);
			vp_pic.setAdapter(mypagerAdapter);
			vp_pic.setOnPageChangeListener(new OnPageChangeListener() {
				private TextView textView3;

				@Override
				public void onPageSelected(int arg0) {
					page_num_txt.setText((arg0 + 1) + "/" + ifaDiploList.size());
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {

				}
			});
		} else {
			mypagerAdapter.notifyDataSetChanged();
		}
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return ifajobList.size();
		}

		@Override
		public Object getItem(int position) {
			return ifajobList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHoder viewHoder;
			ifaJobBean = ifajobList.get(position);
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(),
						R.layout.my_fa_list, null);
				viewHoder = new ViewHoder();
				convertView.setTag(viewHoder);
			} else {
				viewHoder = (ViewHoder) convertView.getTag();
			}
			viewHoder.date = (TextView) convertView.findViewById(R.id.date);
			viewHoder.gongsi = (TextView) convertView.findViewById(R.id.gongsi);
			viewHoder.date.setText(parseData(ifaJobBean.getStartTime()) + "-"
					+ parseData(ifaJobBean.getEndTime()));
			viewHoder.gongsi.setText(ifaJobBean.getCompany()
					+ ifaJobBean.getPosition());
			return convertView;
		}

	}

	class ViewHoder {

		TextView date, gongsi;

	}

	Calendar c = null;
	SimpleDateFormat sdf = null;
	Date date = null;

	@SuppressLint("SimpleDateFormat")
	private String parseData(String milles) {
		long mille = Long.valueOf(milles);
		if (c == null) {
			c = Calendar.getInstance();
		}
		if (sdf == null) {
			sdf = new SimpleDateFormat("yyyy.mm");
		}
		if (date == null) {
			date = new Date();
			if (sdf.format(mille).equals(sdf.format(date.getTime()))) {
				return "至今";
			}
		}
		return sdf.format(mille);

	}

}
