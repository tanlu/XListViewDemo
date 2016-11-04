package com.miduo.financialmanageclient.common;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.NOrderBean;
import com.miduo.financialmanageclient.bean.UserAssetInfoForAppVo;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.ui.AddBankCardActivity;
import com.miduo.financialmanageclient.ui.AllTakeActivity;
import com.miduo.financialmanageclient.ui.ChangeOutActivity;
import com.miduo.financialmanageclient.ui.HoldFixedActivity;
import com.miduo.financialmanageclient.ui.HoldInfoActivity;
import com.miduo.financialmanageclient.ui.SelectedBankCardActivity;
import com.miduo.financialmanageclient.util.MDialog;

public class SkipUtils {

	private Activity activity;
	private Intent intent;
	private NOrderBean nOrderBean;
	private UserAssetInfoForAppVo item;

	public void skipInstruction(Activity activity, UserAssetInfoForAppVo item) {
		this.activity = activity;
		int isfixedincome = item.getBuyStyle();
		this.item = item;
		String status = item.getState() + "";
		if (intent == null)
			intent = new Intent();
		if ("10".equals(status))// 待支付
		{
			if (item.getOrderType() == 0) {
				// 是购买产品
				MyApplication.isBuy = true;
			} else {
				// 购买转让单
				MyApplication.isBuy = false;
			}
			delayPay();
		} else if ("1".equals(status) || "2".equals(status))// 1已购买 2赎回中
		{
			switch (isfixedincome) {
			case 0:// 固定收益
				intent.putExtra("status", status);
				intent.putExtra("assetNo", item.getOrderNo());
				intent.setClass(activity, HoldFixedActivity.class);
				activity.startActivity(intent);
				break;
			case 1:
				// 对冲
				intent.putExtra("status", status);
				intent.putExtra("assetNo", item.getOrderNo());
				intent.setClass(activity, HoldInfoActivity.class);
				activity.startActivity(intent);
			default:
				break;
			}

		} else if ("4".equals(status))// 4转让中.
		{
			if (item.isOrder())// 退款中
			{
				return;
			}
			intent.putExtra("status", status);
			intent.putExtra("assetNo", item.getOrderNo());
			intent.setClass(activity, ChangeOutActivity.class);
			activity.startActivity(intent);

		} else if ("6".equals(status))// 6转出中
		{
			intent.putExtra("status", status);
			intent.putExtra("assetNo", item.getOrderNo());
			intent.setClass(activity, ChangeOutActivity.class);
			activity.startActivity(intent);
		} else if ("3".equals(status) || "7".equals(status))// 7已转出。3已赎回
		{
			intent.putExtra("assetNo", item.getOrderNo());
			intent.setClass(activity, AllTakeActivity.class);
			activity.startActivity(intent);
		} else if ("5".equals(status))// 转入中
		{

		} else if ("8".equals(status))// 退款中
		{

		} else if ("0".equals(status))// 冻结
		{
			DialogBean dialogbean = new DialogBean();
			dialogbean.setSubmit("立即拨打");
			dialogbean.setCancel("我知道了");
			dialogbean.setContent("为了您的资产安全，此订单已被冻结，如有疑问请拨打米多客服热线400-6666-766。");
			dialogbean.setDialogEvent(new DialogEventListener() {

				@Override
				public void submit() {
					callPhone();
				}

				@Override
				public void cancel() {

				}
			});
			MDialog.showDialog2(activity, dialogbean);
		}
	}

	private void callPhone() {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "4006666766"));
		activity.startActivity(intent);
	}

	private void delayPay() {
		if (nOrderBean == null)
			nOrderBean = new NOrderBean();
		nOrderBean.setAmount(item.getAmount().floatValue());
		nOrderBean.setOrderNo(item.getOrderNo());
		nOrderBean.setProductTitle(item.getProductName());
		nOrderBean.setProductId(item.getProductId());
		nOrderBean.setUrl(item.getPayUrl());
		if (!item.isHasbankcarList())// 无卡
		{
			intent.setClass(activity, AddBankCardActivity.class);
			intent.putExtra("ORDER", nOrderBean);
			activity.startActivity(intent);
		} else {
			intent.setClass(activity, SelectedBankCardActivity.class);
			intent.putExtra("ORDER", nOrderBean);
			activity.startActivity(intent);
		}
	}

}
