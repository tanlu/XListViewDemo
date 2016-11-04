package com.miduo.financialmanageclient.util;


import com.miduo.financialmanageclient.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProgressDialogUtil {
	private static Dialog progress;
	private static boolean isClose = false;
	public static boolean isEndTime = false;
	public static void showProgress2(Context context,String string)
	{
		showProgress2(context, string, true);
	}

	public static void showProgress(Context context) {
		showProgress(context, "加载中...", true);
	}

	public static void showProgress(Context context, String string) {
		showProgress(context, string, false);
	}

	public static void showProgress(Context context, String string, boolean Cancelable) {
		if (progress != null && progress.isShowing()) {
			try {
				progress.dismiss();
				progress = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		progress = createLoadingDialog(context, string);
		progress.show();
		isEndTime = false;
		isClose = false;
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					isEndTime = true;
					close();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private static void close() {
		try {
			if (isEndTime && isClose) {
				if (progress != null && progress.isShowing() && progress.getContext() != null) {
					progress.dismiss();
					progress = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeProgress() {
		isClose = true;
		close();
	}

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// // 加载动画
		// Animation hyperspaceJumpAnimation =
		// AnimationUtils.loadAnimation(context, R.anim.load_animation);
		// // 使用ImageView显示动画
		// spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		// tipTextView.setText(msg);// 设置加载信息
		//
		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(false);// 不可以用“返回键”取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;
	}
	
	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog createLoadingDialog2(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView ttipTextView=(TextView) v.findViewById(R.id.tipTextView);
		ttipTextView.setText(msg);
		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
		loadingDialog.setCancelable(false);// 不可以用“返回键”取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;
	}
	
	public static void showProgress2(Context context, String string, boolean Cancelable) {
		if (progress != null && progress.isShowing()) {
			try {
				progress.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		progress = createLoadingDialog2(context, string);
		progress.show();
		isEndTime = false;
		isClose = false;
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					isEndTime = true;
					close();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
