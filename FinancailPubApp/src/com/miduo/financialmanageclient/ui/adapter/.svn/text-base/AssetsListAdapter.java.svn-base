package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.UserAssetInfoForAppVo;
import com.miduo.financialmanageclient.util.FloatUtil;

/**
 * 米多资产列表使用
 * 
 * @author huozhenpeng
 * 
 */
public class AssetsListAdapter extends BaseAdapter {

	private Context context;
	private List<UserAssetInfoForAppVo> lists;
	private UserAssetInfoForAppVo bean;
	private boolean flag;

	public AssetsListAdapter(Context context, List<UserAssetInfoForAppVo> lists) {
		this.context = context;
		this.lists = lists;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		bean = lists.get(position);
		String status = lists.get(position).getState() + "";
		boolean isExpire = lists.get(position).isExpire();
		// 0，冻结，1、正常、2、赎回中 3、已赎回 4、转让中 5、转入中 6、 转出中 7、已转让 8.退款中 9.退款完成,10.待支付
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_assetslist, null);
			holder = new ViewHolder();
			holder.tv_proname = (TextView) convertView
					.findViewById(R.id.tv_proname);
			holder.tv_status = (TextView) convertView
					.findViewById(R.id.tv_status);
			holder.tv_tip = (TextView) convertView.findViewById(R.id.tv_tip);
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.tv_money = (TextView) convertView
					.findViewById(R.id.tv_money);
			holder.tv_redemption = (TextView) convertView
					.findViewById(R.id.tv_redemption);
			holder.tv_redemptiontime = (TextView) convertView
					.findViewById(R.id.tv_redemptiontime);
			holder.tv_redemptionday = (TextView) convertView
					.findViewById(R.id.tv_redemptionday);
			holder.rl_redeem = (RelativeLayout) convertView
					.findViewById(R.id.rl_redeem);
			holder.rl_pay = (RelativeLayout) convertView
					.findViewById(R.id.rl_pay);
			holder.iv_pic2 = (ImageView) convertView.findViewById(R.id.iv_pic2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_redemption.setVisibility(View.VISIBLE);
		holder.iv_pic2.setVisibility(View.VISIBLE);
		holder.tv_redemptionday.setVisibility(View.VISIBLE);
		if (isExpire) {
			holder.tv_status.setText("已到期");
			holder.tv_status.setTextColor(Color.parseColor("#cccccc"));
			holder.tv_status.setBackgroundResource(R.drawable.frame_bg_gray);
			holder.rl_pay.setVisibility(View.GONE);
			holder.rl_redeem.setVisibility(View.VISIBLE);
			holder.tv_redemption.setText("到期日：");
			holder.tv_redemptionday.setVisibility(View.INVISIBLE);
			holder.iv_pic2.setVisibility(View.INVISIBLE);
			holder.tv_redemptiontime.setText(bean.getProductEndDate());
		} else {
			if ("10".equals(status))// 待支付
			{
				holder.tv_status.setText("待支付");
				holder.tv_status.setTextColor(Color.parseColor("#f34d4d"));
				holder.tv_status.setBackgroundResource(R.drawable.frame_bg_red);
				holder.tv_time.setText(getCountDown(lists.get(position)
						.getOrderRemainderTime() * 1000));
				holder.rl_pay.setVisibility(View.VISIBLE);
				holder.rl_redeem.setVisibility(View.GONE);
			} else if ("1".equals(status))// 已购买
			{
				holder.tv_status.setText("已购买");
				holder.tv_status.setTextColor(Color.parseColor("#8aba62"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_green);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("最近赎回日：");
				holder.tv_redemptionday.setVisibility(View.VISIBLE);
				holder.iv_pic2.setVisibility(View.VISIBLE);
				if ("可随时赎回".equals(bean.getRedeemTimeCon())) {
					holder.tv_redemption.setVisibility(View.GONE);
					holder.iv_pic2.setVisibility(View.INVISIBLE);
					holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				}
				if("今天".equals(bean.getRedeemTimeCon()))
				{
					holder.tv_redemption.setVisibility(View.VISIBLE);
					holder.iv_pic2.setVisibility(View.GONE);
					holder.tv_redemptionday.setVisibility(View.GONE);
				}
						
				if(!TextUtils.isEmpty(bean.getRedeemTimeCon()))//最近赎回日
				{
					holder.tv_redemptiontime.setText(bean.getRedeemTimeCon());
					if(bean.getRedeemRemainderTime()==null)
					{
						holder.tv_redemptionday.setText("剩余0天");
					}
					else
					{
						holder.tv_redemptionday.setText("剩余"
								+ bean.getRedeemRemainderTime() + "天");
					}
					
				}
				else if(!TextUtils.isEmpty(bean.getProductEndDate()))//产品到期日
				{
					holder.tv_redemption.setText("产品到期日：");
					holder.tv_redemptiontime.setText(bean.getProductEndDate());
					holder.tv_redemptionday.setText("剩余"
							+ bean.getProductEndDays()+ "天");
				}
				else if(!TextUtils.isEmpty(bean.getProjectStartDate()))//产品购买日
				{
					holder.tv_redemption.setText("产品购买日：");
					holder.tv_redemptiontime.setText(bean.getProjectStartDate());
					holder.iv_pic2.setVisibility(View.INVISIBLE);
					holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				}
				
			} else if ("2".equals(status))// 赎回中
			{
				holder.tv_status.setText("赎回中");
				holder.tv_status.setTextColor(Color.parseColor("#2da8df"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_blue);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("申请赎回日：");
				holder.tv_redemptionday.setVisibility(View.VISIBLE);
				holder.iv_pic2.setVisibility(View.VISIBLE);
				holder.tv_redemptiontime.setText(bean.getApplyRedeemDate());
				holder.tv_redemptionday.setText("剩余"
						+ bean.getRedeemRemainderTime() + "天");
				holder.iv_pic2.setVisibility(View.INVISIBLE);
				holder.tv_redemptionday.setVisibility(View.INVISIBLE);
			} else if ("4".equals(status))// 转让中
			{
				if (lists.get(position).isOrder())// 退款中
				{
					holder.tv_status.setText("退款中");
					holder.tv_status.setTextColor(Color.parseColor("#f34d4d"));
					holder.tv_status
							.setBackgroundResource(R.drawable.frame_bg_red);
					holder.rl_pay.setVisibility(View.GONE);
					holder.rl_redeem.setVisibility(View.VISIBLE);
					holder.tv_redemption.setText("产品购买日：");
					holder.tv_redemptionday.setVisibility(View.INVISIBLE);
					holder.iv_pic2.setVisibility(View.INVISIBLE);
					holder.tv_redemptiontime
							.setText(bean.getProjectStartDate());
				} else {
					holder.tv_status.setText("转出中");
					holder.tv_status.setTextColor(Color.parseColor("#2da8df"));
					holder.tv_status
							.setBackgroundResource(R.drawable.frame_bg_blue);
					holder.rl_pay.setVisibility(View.GONE);
					holder.rl_redeem.setVisibility(View.VISIBLE);
					holder.tv_redemption.setText("最近赎回日：");
					holder.tv_redemptionday.setVisibility(View.VISIBLE);
					holder.iv_pic2.setVisibility(View.VISIBLE);
					if ("可随时赎回".equals(bean.getRedeemTimeCon())) {
						holder.tv_redemption.setVisibility(View.GONE);
						holder.iv_pic2.setVisibility(View.INVISIBLE);
						holder.tv_redemptionday.setVisibility(View.INVISIBLE);
					}
					if("今天".equals(bean.getRedeemTimeCon()))
					{
						holder.tv_redemption.setVisibility(View.VISIBLE);
						holder.iv_pic2.setVisibility(View.GONE);
						holder.tv_redemptionday.setVisibility(View.GONE);
					}
					if(!TextUtils.isEmpty(bean.getRedeemTimeCon()))//最近赎回日
					{
						if(bean.getRedeemRemainderTime()==null)
						{
							holder.tv_redemptionday.setText("剩余0天");
						}
						else
						{
							holder.tv_redemptionday.setText("剩余"
									+ bean.getRedeemRemainderTime() + "天");
						}
						holder.tv_redemptiontime.setText(bean.getRedeemTimeCon());
					}
					else if(!TextUtils.isEmpty(bean.getProductEndDate()))//产品到期日
					{
						holder.tv_redemption.setText("最近赎回日：");
						holder.tv_redemptiontime.setText(bean.getProductEndDate());
						holder.tv_redemptionday.setText("剩余"
								+ bean.getProductEndDays() + "天");
					}
					else if(!TextUtils.isEmpty(bean.getProductEndDate()))//产品购买日
					{
						holder.tv_redemption.setText("产品购买日：");
						holder.tv_redemptiontime.setText(bean.getProjectStartDate());
						holder.iv_pic2.setVisibility(View.INVISIBLE);
						holder.tv_redemptionday.setVisibility(View.INVISIBLE);
					}
					
				}
			} else if ("6".equals(status))// 转出中
			{
				holder.tv_status.setText("转出中");
				holder.tv_status.setTextColor(Color.parseColor("#2da8df"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_blue);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("最近赎回日：");
				holder.tv_redemptionday.setVisibility(View.VISIBLE);
				holder.iv_pic2.setVisibility(View.VISIBLE);
				holder.tv_redemptiontime.setText(bean.getRedeemTimeCon());
				holder.tv_redemptionday.setText("剩余"
						+ bean.getRedeemRemainderTime() + "天");
			} else if ("7".equals(status))// 已转让
			{
				holder.tv_status.setText("已转出");
				holder.tv_status.setTextColor(Color.parseColor("#cccccc"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_gray);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("转出日：");
				holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				holder.iv_pic2.setVisibility(View.INVISIBLE);
				holder.tv_redemptiontime.setText(bean.getTransferDate());
			} else if ("3".equals(status))// 已赎回
			{
				holder.tv_status.setText("已赎回");
				holder.tv_status.setTextColor(Color.parseColor("#cccccc"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_gray);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("赎回日：");
				holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				holder.iv_pic2.setVisibility(View.INVISIBLE);
				holder.tv_redemptiontime.setText(bean.getRedeemDate());
			} else if ("5".equals(status))// 转入中
			{
				holder.tv_status.setText("转入中");
				holder.tv_status.setTextColor(Color.parseColor("#8aba62"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_green);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("产品购买日：");
				holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				holder.iv_pic2.setVisibility(View.INVISIBLE);
				holder.tv_redemptiontime.setText(bean.getProjectStartDate());
			} else if ("8".equals(status))// 退款中
			{
				holder.tv_status.setText("退款中");
				holder.tv_status.setTextColor(Color.parseColor("#f34d4d"));
				holder.tv_status.setBackgroundResource(R.drawable.frame_bg_red);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("产品购买日：");
				holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				holder.iv_pic2.setVisibility(View.INVISIBLE);
				holder.tv_redemptiontime.setText(bean.getProjectStartDate());
			} else if ("0".equals(status))// 冻结
			{
				holder.tv_status.setText("已冻结");
				holder.tv_status.setTextColor(Color.parseColor("#cccccc"));
				holder.tv_status
						.setBackgroundResource(R.drawable.frame_bg_gray);
				holder.rl_pay.setVisibility(View.GONE);
				holder.rl_redeem.setVisibility(View.VISIBLE);
				holder.tv_redemption.setText("产品购买日：");
				holder.tv_redemptionday.setVisibility(View.INVISIBLE);
				holder.iv_pic2.setVisibility(View.INVISIBLE);
				holder.tv_redemptiontime.setText(bean.getRedeemDate());
			}
		}
		holder.tv_proname.setText(bean.getProductName());
		holder.tv_money.setText("10".equals(status)?FloatUtil.toTwoDianStringSeparator(bean.getAmount()):FloatUtil.toTwoDianStringSeparator(bean.getTotalIncome()));

		return convertView;
	}

	public class ViewHolder {
		public TextView tv_proname, tv_status, tv_tip, tv_time, tv_money,
				tv_redemption, tv_redemptiontime, tv_redemptionday,
				tv_expiredate, tv_expireday, tv_expiretime;// 产品名称
		public RelativeLayout rl_redeem, rl_pay;
		public ImageView iv_pic2;
	}

	public String getCountDown(int time1) {
		if(time1<=0)
		{
			return "0分0秒";
		}

		return (time1 % (1000 * 60 * 60)) / (1000 * 60) + "分"
				+ (time1 % (1000 * 60)) / 1000 + "秒";
	}

	int result = 0;
	private Thread thread;

	public void start() {
		thread = new Thread() {
			public void run() {
				while (flag) {
					try {
						if (lists == null || result == lists.size()) {
							continue;
						}
						sleep(1000);
						handler.sendEmptyMessage(0x01);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};
		thread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			for (int i = 0; i < lists.size(); i++) {
				try {
					lists.get(i).getOrderRemainderTime();
				} catch (Exception e) {
					return;
				}
				if ("10".equals(lists.get(i).getState() + "")) {
					lists.get(i)
							.setOrderRemainderTime(
									(lists.get(i)
											.getOrderRemainderTime()-1> 0 ? lists
											.get(i)
											.getOrderRemainderTime()-1
											: 0));

				}
			}
			notifyDataSetChanged();
			super.handleMessage(msg);
		}
	};
	
	public void setThreadStartOrEnd(boolean flag)
	{
		this.flag=flag;
	}

}
