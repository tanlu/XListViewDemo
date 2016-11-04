package com.miduo.financialmanageclient.widget;

import com.miduo.financialmanageclient.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @author huozhenpeng www.miduo.com 2015-9-9
 * 
 */
public class MessageView extends View {
	private Paint paint;
	private String text;
	private RectF rectf;
	private int radius = 10;
	private TextPaint textpaint;
	private float width;
	private FontMetrics metrics;
	private int textsize = 24;
	private float bottom;

	public MessageView(Context context) {
		this(context, null);
	}

	public MessageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.message, 0, 0);
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.message_radius1:
				radius = (int) typedArray.getDimension(attr, 10);
				break;
			case R.styleable.message_text:
				text = typedArray.getString(attr);
				if(text.length()>=3)
				{
					this.text="···";
				}
				break;
			case R.styleable.message_textsize1:
				textsize = (int) typedArray.getDimension(attr, 10);
				break;
			default:
				break;
			}
		}
		typedArray.recycle();
		init();
	}

	private void init() {
		setVisibility(View.VISIBLE);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setColor(Color.RED);
		textpaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		textpaint.setColor(Color.parseColor("#ffffff"));
		textpaint.setTextSize(textsize);
		textpaint.setTextAlign(Align.CENTER);
		metrics = textpaint.getFontMetrics();
		bottom = Math.abs(metrics.ascent) + metrics.descent;
		if (!TextUtils.isEmpty(text)) {
			width = textpaint.measureText(text);
			width += bottom - (textpaint.measureText("1"));
			rectf = new RectF(0, 0, width + radius * 2, bottom + 2 * radius);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int specwidth=(int) (width + radius * 2);
		int spcemode=MeasureSpec.EXACTLY;
		int spechight=(int) (bottom + 2 * radius);
		setMeasuredDimension(MeasureSpec.makeMeasureSpec(specwidth, spcemode), MeasureSpec.makeMeasureSpec(spechight, spcemode));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(!TextUtils.isEmpty(text))
		{
//		canvas.drawOval(rectf, paint);
		canvas.drawRoundRect(rectf, getResources().getDimension(R.dimen.px2dp_16), getResources().getDimension(R.dimen.px2dp_16), paint);
		canvas.drawText(text, (width + radius * 2) / 2, (bottom + 2 * radius)
				/ 2 + Math.abs((metrics.ascent + metrics.descent) / 2),
				textpaint);
		}

	}

	public void setText(String text) {
		setVisibility(View.GONE);
		this.text = text;
		if(text.length()>=3)
		{
			this.text="···";
		}
		init();
		invalidate();
	}

	public void setTextSize(int textSize) {
		this.textsize = textSize;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
