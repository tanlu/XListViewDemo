package com.miduo.financialmanageclient.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;

/**
 * 
 * Toast替换
 * 
 */
public class MToast {
	private static Toast mToast;

	public static void showToast(Context context, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			View layout = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
			TextView contentTxt = (TextView) layout.findViewById(R.id.cotent_txt);
			contentTxt.setText(text);

			mToast = new Toast(context);
			mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setView(layout);
		} else {
			TextView contentTxt = (TextView) mToast.getView().findViewById(R.id.cotent_txt);
			contentTxt.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public static void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

}
