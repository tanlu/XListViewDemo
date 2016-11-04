package com.miduo.financialmanageclient.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.ui.LoginActivity;

public class MDialog {

	public static void showDialog2(Context context, final DialogBean data) {
		// 布局文件转换为view对象
		LayoutInflater inflater = LayoutInflater.from(context);

		RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.dialog_two_button, null);
		// 对话框
		final Dialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView titleTxt = (TextView) layout.findViewById(R.id.title_txt);		
		TextView content = (TextView) layout.findViewById(R.id.content_txt);
		content.setText(data.getContent());
		if (StringUtil.isEmpty(data.getTitle())) {
			titleTxt.setVisibility(View.GONE);
			content.setGravity(Gravity.CENTER_HORIZONTAL);
		} else {
			titleTxt.setVisibility(View.VISIBLE);
			titleTxt.setText(data.getTitle());
			content.setGravity(Gravity.LEFT);
		}
		// 取消按钮
		TextView btnCancel = (TextView) layout.findViewById(R.id.cancel_txt);
		btnCancel.setText(data.getCancel());
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (data.getDialogEvent() != null) {
					data.getDialogEvent().cancel();
				}
				dialog.dismiss();
			}
		});

		// 确定按钮
		TextView btnConfirm = (TextView) layout.findViewById(R.id.confirm_txt);
		btnConfirm.setText(data.getSubmit());
		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (data.getDialogEvent() != null) {
					data.getDialogEvent().submit();
				}
				dialog.dismiss();
			}
		});
	}

	public static void showDialog1(Context context, final DialogBean data) {
		// 布局文件转换为view对象
		LayoutInflater inflaterDl = LayoutInflater.from(context);

		RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.dialog_one_button, null);
		// 对话框
		final Dialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView titleTxt = (TextView) layout.findViewById(R.id.title_txt);		
		TextView content = (TextView) layout.findViewById(R.id.content_txt);
		content.setText(data.getContent());
		if (StringUtil.isEmpty(data.getTitle())) {
			titleTxt.setVisibility(View.GONE);		
			content.setGravity(Gravity.CENTER_HORIZONTAL);
		} else {
			titleTxt.setVisibility(View.VISIBLE);
			titleTxt.setText(data.getTitle());
			content.setGravity(Gravity.LEFT);
		}

		// 确定按钮
		TextView btnConfirm = (TextView) layout.findViewById(R.id.confirm_txt);
		btnConfirm.setText(data.getSubmit());
		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (data.getDialogEvent() != null) {
					data.getDialogEvent().submit();
				}
				dialog.dismiss();
			}
		});
	}

	public static void showDialogInput(final Context context, final DialogBean data) {
		// 布局文件转换为view对象
		LayoutInflater inflaterDl = LayoutInflater.from(context);

		RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.dialog_input, null);
		// 对话框
		final Dialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		TextView titleTxt = (TextView) layout.findViewById(R.id.title_txt);
		titleTxt.setText(data.getTitle());
		TextView content = (TextView) layout.findViewById(R.id.content_txt);
		content.setText(data.getContent());
		final EditText psdTxt = (EditText) layout.findViewById(R.id.ed_content);

		// 确定按钮
		TextView btnConfirm = (TextView) layout.findViewById(R.id.confirm_txt);
		btnConfirm.setText(data.getSubmit());
		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(psdTxt.getText())) {
					String temp = SharePrefUtil.getString(context, SharePrefUtil.ACCOUNT_PSD, "");
					boolean flag = temp.equals(psdTxt.getText().toString());
					if (flag) {
						if (data.getDialogEvent() != null) {
							data.getDialogEvent().submit();
							dialog.dismiss();
						}
					} else {
						MToast.showToast(context, "密码错误");
					}
				} else {
					MToast.showToast(context, "请输入密码");
				}
			}
		});
		// 取消按钮
		TextView btnCancel = (TextView) layout.findViewById(R.id.cancel_txt);
		btnCancel.setText(data.getCancel());
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (data.getDialogEvent() != null) {
					data.getDialogEvent().cancel();
				}
				dialog.dismiss();
			}
		});
	}

	public static void showPsdErrorDialog(final Context context, final String msg) {
		// 布局文件转换为view对象
		LayoutInflater inflaterDl = LayoutInflater.from(context);

		RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.dialog_one_button, null);
		// 对话框
		final Dialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView titleTxt = (TextView) layout.findViewById(R.id.title_txt);
		titleTxt.setText("注意");
		TextView content = (TextView) layout.findViewById(R.id.content_txt);
		content.setText(msg);

		// 确定按钮
		TextView btnConfirm = (TextView) layout.findViewById(R.id.confirm_txt);
		btnConfirm.setText("确定");
		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, LoginActivity.class);
				context.startActivity(intent);
				dialog.dismiss();
			}
		});
	}

}
