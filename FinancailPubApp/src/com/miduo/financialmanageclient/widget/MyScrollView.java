package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	public InputMethodManager imm;
	public MyScrollView(Context context) {
		this(context,null);

	}

	public MyScrollView(Context context, AttributeSet attrs) {
		this(context, attrs,0);

	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	private void init() {
		imm = (InputMethodManager) getContext().getSystemService(
				Context.INPUT_METHOD_SERVICE);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			imm.hideSoftInputFromWindow(getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
		return super.onTouchEvent(event);
	}


}
