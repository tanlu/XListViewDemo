package com.miduo.financialmanageclient.widget;

import com.miduo.financialmanageclient.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PercentageView extends View {

	private int bgColor;
	private int topColor;
	private int stroke;
	private int width, height;
	private Paint bgPaint, topPaint, paint;
	private float percentage, animprogress;
	private int radius;
	private RectF rectF;

	public PercentageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.percentage, 0, 0);
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.percentage_bgcolor:
				bgColor = typedArray.getColor(i, Color.GRAY);
				break;
			case R.styleable.percentage_topcolor:
				topColor = typedArray.getColor(i, Color.BLUE);
				break;
			case R.styleable.percentage_stroke:
				stroke = typedArray.getDimensionPixelSize(i, 2);
				break;
			case R.styleable.percentage_radius:
				radius = typedArray.getDimensionPixelOffset(i, 6);
				break;
			default:
				break;
			}
		}
		init();
	}

	public PercentageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PercentageView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		width = 0;
		height = 0;
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		width = getPaddingLeft() + getPaddingRight() + specSize;
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		height = getPaddingBottom() + getPaddingTop() + specSize;
		setMeasuredDimension(width, height);
	}

	private void init() {
		bgPaint = new Paint();
		bgPaint.setColor(bgColor);
		bgPaint.setAntiAlias(true);
		percentage = 0;
		animprogress = 0;
		topPaint = new Paint();
		topPaint.setColor(topColor);
		topPaint.setAntiAlias(true);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		rectF = new RectF();
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
		if (percentage >= 100) {
			topPaint.setColor(bgColor);
			animprogress = 100;
			postInvalidate();
		} else {
			startAnim();
		}
	}

	private void startAnim() {
		new Thread() {
			public void run() {
				for (int i = 0; i <= 120; i += 2) {
					if (i >= percentage) {
						animprogress = percentage;
						break;
					}
					animprogress = i;
					postInvalidate();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
//		canvas.drawRect(0, height / 2, width, height, paint);
		//背景灰色线
		canvas.drawRect(0, (height - stroke) / 2, width,
				(height + stroke) / 2, bgPaint);
		//进度蓝色线
		canvas.drawRect(0, (height - stroke) / 2, width * (animprogress / 100),
				(height + stroke) / 2, topPaint);
		int left = (int) (width * (animprogress / 100));
		if (animprogress == 100)
			left -= ((radius * 2) + 2);
		rectF.left = left;
		rectF.top = 0;
//		radius = 5;
		rectF.right = left + radius * 2;
		rectF.bottom = height;
		canvas.drawArc(rectF, 0, 360, true, topPaint);
	}

}
