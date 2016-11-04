package com.miduo.financialmanageclient.widget;

import com.miduo.financialmanageclient.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class RadioItemView extends View {
	private boolean checked = false;
	private int img_checked;
	private int img_unchecked;
	private int textcolor_checked;
	private int textcolor_unchecked;
	private float text_size;
	private Paint mPaint;
	private String text_content;
	// 绘制时控制文本绘制的范围
	private Rect mBound;

	public RadioItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		mPaint = new Paint();
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.custom_radio, 0, 0);
		checked = typedArray.getBoolean(R.styleable.custom_radio_checked, false);
		img_checked = typedArray.getResourceId(R.styleable.custom_radio_img_checked, R.drawable.f_1_2);
		img_unchecked = typedArray.getResourceId(R.styleable.custom_radio_img_unchecked, R.drawable.f_1_1);
		textcolor_checked = typedArray
				.getColor(R.styleable.custom_radio_textcolor_checked, Color.parseColor("#3a70a9"));
		textcolor_unchecked = typedArray.getColor(R.styleable.custom_radio_textcolor_unchecked,
				Color.parseColor("#929292"));
		text_size = typedArray.getDimension(R.styleable.custom_radio_text_size, R.dimen.px2sp_28);
		text_content = typedArray.getString(R.styleable.custom_radio_text_content);
		typedArray.recycle();// 一定要调用，否则这次的设定会对下次的使用造成影响

		mPaint.setTextSize(text_size);
		mBound = new Rect();
		// 获取到文字的宽度和高度，然后调用了drawText()方法去进行绘制
		mPaint.getTextBounds(text_content, 0, text_content.length(), mBound);
	}

	public RadioItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RadioItemView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width, height;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			mPaint.setTextSize(text_size);
			// 获取到文字的宽度和高度，然后调用了drawText()方法去进行绘制
			mPaint.getTextBounds(text_content, 0, text_content.length(), mBound);
			float textWidth = mBound.width();
			int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
			width = desired;
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			mPaint.setTextSize(text_size);
			// 获取到文字的宽度和高度，然后调用了drawText()方法去进行绘制
			mPaint.getTextBounds(text_content, 0, text_content.length(), mBound);
			float textHeight = mBound.height();
			Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(img_checked)).getBitmap();
			int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom()
					+ getResources().getDimension(R.dimen.px2dp_20) * 3 + bitmap.getHeight());
			height = desired;
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Bitmap bitmap = null;
		if (checked) {
			mPaint.setColor(textcolor_checked);
			bitmap = ((BitmapDrawable) getResources().getDrawable(img_checked)).getBitmap();
		} else {
			mPaint.setColor(textcolor_unchecked);
			bitmap = ((BitmapDrawable) getResources().getDrawable(img_unchecked)).getBitmap();
		}
		canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2,
				getResources().getDimension(R.dimen.px2dp_20), mPaint);
		canvas.drawText(text_content, getWidth() / 2 - mBound.width() / 2, getResources()
				.getDimension(R.dimen.px2dp_20) * 2 + bitmap.getHeight() + mBound.height(), mPaint);
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
		invalidate();
	}

	public void setImgBg(int img_checked, int img_unchecked) {
		this.img_checked = img_checked;
		this.img_unchecked = img_unchecked;
		invalidate();
	}
}
