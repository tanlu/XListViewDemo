package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.miduo.financialmanageclient.R;

public class RingView extends View {
	// 现有
	private float valueLst1[];
	// 推荐
	private float valueLst2[];
	// 字体颜色
	private int ring_text_color;
	// 小字号字体大小
	private float small_text_size;
	// 大字号字体大小
	private float big_text_size;
	// 大环宽度
	private float small_ring_width;
	// 小环宽度
	private float big_ring_width;
	// 间隔宽度
	private float interval_ring_width;
	// 高流动性画笔
	private Paint highFlowPaint;
	// 灰色的画笔
	private Paint greyPaint;
	// 固定收益画笔
	private Paint fixProfitPaint;
	// 浮动收益画笔
	private Paint floatProfitPaint;
	// 另类画笔
	private Paint otherTypePaint;
	// 小文字画笔
	private Paint smallTextPaint;
	// 大文字画笔
	private Paint bigTextPaint;
	// 角度
	private float angle1[], angle2[];

	public void setValueLst(float[] valueLst1, float[] valueLst2) {
		isAction = true;
		this.valueLst1 = valueLst1;
		this.valueLst2 = valueLst2;
		new Thread() {
			public void run() {
				currentAngle = 0;
				for (int i = 20; i <= ringSumAngle; i = i + 20) {
					currentAngle = i;
					postInvalidate();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	// 文字和图片间隔
	private float drawPaddting = 0;
	// 总共角度
	private float ringSumAngle = 300;
	// 现在的角度
	private float currentAngle = 0;
	// 绘制时控制文本绘制的范围
	private Rect smallBound;
	private Rect bigBound;
	private String content3 = "资产配置";
	private String content1 = "现有";
	private String content2 = "推荐";
	private int width, height;// 控件的宽和高
	// 是否需要动画显示
	private boolean isAction = true;

	public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ring_view, 0, 0);
		// 字体颜色
		ring_text_color = typedArray.getColor(R.styleable.ring_view_ring_text_color, Color.parseColor("#ffffff"));
		// 小字号字体大小
		small_text_size = typedArray.getDimension(R.styleable.ring_view_small_text_size,
				getResources().getDimension(R.dimen.px2sp_22));
		// 大字号字体大小
		big_text_size = typedArray.getDimension(R.styleable.ring_view_big_text_size,
				getResources().getDimension(R.dimen.px2sp_30));
		// 大环宽度
		small_ring_width = typedArray.getDimension(R.styleable.ring_view_small_ring_width,
				getResources().getDimension(R.dimen.px2dp_40));
		// 小环宽度
		big_ring_width = typedArray.getDimension(R.styleable.ring_view_big_ring_width,
				getResources().getDimension(R.dimen.px2dp_10));
		// 间隔宽度
		interval_ring_width = typedArray.getDimension(R.styleable.ring_view_interval_ring_width, getResources()
				.getDimension(R.dimen.px2dp_20));
		// 是否显示文字
		// isShow = typedArray.getBoolean(R.styleable.ring_view_is_show, true);
		typedArray.recycle();// 一定要调用，否则这次的设定会对下次的使用造成影响
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		// 灰色画笔 greyPaint greyPaint.setStrokeCap(Cap.ROUND);
		greyPaint = new Paint();
		greyPaint.setStyle(Style.STROKE);
		greyPaint.setAntiAlias(true);
		greyPaint.setColor(Color.parseColor("#008ecf"));
		// 高流动性画笔
		highFlowPaint = new Paint();
		highFlowPaint.setStyle(Style.STROKE);
		highFlowPaint.setAntiAlias(true);
		highFlowPaint.setColor(Color.parseColor("#ffffff"));
		// 固定收益画笔
		fixProfitPaint = new Paint();
		fixProfitPaint.setStyle(Style.STROKE);
		fixProfitPaint.setAntiAlias(true);
		fixProfitPaint.setColor(Color.parseColor("#b7eeff"));
		// 浮动收益画笔
		floatProfitPaint = new Paint();
		floatProfitPaint.setStyle(Style.STROKE);
		floatProfitPaint.setAntiAlias(true);
		floatProfitPaint.setColor(Color.parseColor("#67dbff"));
		// 另类画笔
		otherTypePaint = new Paint();
		otherTypePaint.setStyle(Style.STROKE);
		otherTypePaint.setAntiAlias(true);
		otherTypePaint.setColor(Color.parseColor("#13c7fe"));

		drawPaddting = getResources().getDimension(R.dimen.px2dp_16);

		smallTextPaint = new Paint();
		smallTextPaint.setStyle(Style.STROKE);
		smallTextPaint.setAntiAlias(true);
		smallTextPaint.setColor(ring_text_color);
		smallTextPaint.setTextSize(small_text_size);
		smallBound = new Rect();
		// 获取到文字的宽度和高度，然后调用了drawText()方法去进行绘制
		smallTextPaint.getTextBounds(content1, 0, content1.length(), smallBound);

		bigTextPaint = new Paint();
		bigTextPaint.setStyle(Style.STROKE);
		bigTextPaint.setAntiAlias(true);
		bigTextPaint.setColor(ring_text_color);
		bigTextPaint.setTextSize(big_text_size);
		bigBound = new Rect();
		// 获取到文字的宽度和高度，然后调用了drawText()方法去进行绘制
		bigTextPaint.getTextBounds(content3, 0, content3.length(), bigBound);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		width = getPaddingLeft() + getPaddingRight() + specSize;
		// specSize = MeasureSpec.getSize(heightMeasureSpec);
		// height = getPaddingTop() + getPaddingBottom() + specSize;
		height = width;
		setMeasuredDimension(width, height);
	}

	public RingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RingView(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if ((valueLst1 == null || valueLst1.length == 0) && (valueLst2 == null || valueLst2.length == 0)) {
			return;
		}
		angle1 = getAngle(valueLst1);
		angle2 = getAngle(valueLst2);
		// 外环大圆
		RectF ovalBig = new RectF(big_ring_width + (smallBound.height() - big_ring_width) / 2, big_ring_width
				+ (smallBound.height() - big_ring_width) / 2, width - big_ring_width
				- (smallBound.height() - big_ring_width) / 2, height - big_ring_width
				- (smallBound.height() - big_ring_width) / 2);
		// 内环小圆
		RectF ovalSmall = new RectF(big_ring_width + small_ring_width + interval_ring_width
				+ (smallBound.height() - big_ring_width) / 2, big_ring_width + small_ring_width + interval_ring_width
				+ (smallBound.height() - big_ring_width) / 2, width - big_ring_width - small_ring_width
				- interval_ring_width - (smallBound.height() - big_ring_width) / 2, height - big_ring_width
				- small_ring_width - interval_ring_width - (smallBound.height() - big_ring_width) / 2);
		// Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.asset_1);
		float startAngle = 0 - 90;
		if (isAction) {
			startAngle = 0 - 90;
			if (currentAngle <= angle1[0]) {
				highFlowPaint.setStrokeWidth(big_ring_width);
				greyPaint.setStrokeWidth(big_ring_width);
				if (angle1[0] == ringSumAngle) {
					canvas.drawArc(ovalBig, startAngle, currentAngle, false, greyPaint);
				} else {
					canvas.drawArc(ovalBig, startAngle, currentAngle, false, highFlowPaint);
				}
			} else if (currentAngle <= (angle1[0] + angle1[1])) {
				highFlowPaint.setStrokeWidth(big_ring_width);
				canvas.drawArc(ovalBig, startAngle, angle1[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[0];
				canvas.drawArc(ovalBig, startAngle, currentAngle - angle1[0], false, fixProfitPaint);
			} else if (currentAngle <= (angle1[0] + angle1[1] + angle1[2])) {
				highFlowPaint.setStrokeWidth(big_ring_width);
				canvas.drawArc(ovalBig, startAngle, angle1[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[0];
				canvas.drawArc(ovalBig, startAngle, angle1[1], false, fixProfitPaint);
				floatProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[1];
				canvas.drawArc(ovalBig, startAngle, currentAngle - angle1[0] - angle1[1], false, floatProfitPaint);
			} else {
				highFlowPaint.setStrokeWidth(big_ring_width);
				canvas.drawArc(ovalBig, startAngle, angle1[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[0];
				canvas.drawArc(ovalBig, startAngle, angle1[1], false, fixProfitPaint);
				floatProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[1];
				canvas.drawArc(ovalBig, startAngle, angle1[2], false, floatProfitPaint);
				otherTypePaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[2];
				canvas.drawArc(ovalBig, startAngle, currentAngle - angle1[0] - angle1[1] - angle1[2], false,
						otherTypePaint);
			}
			startAngle = 0 - 90;
			if (currentAngle <= angle2[0]) {
				highFlowPaint.setStrokeWidth(small_ring_width);
				greyPaint.setStrokeWidth(small_ring_width);
				if (angle2[0] == ringSumAngle) {
					canvas.drawArc(ovalSmall, startAngle, currentAngle, false, greyPaint);
				} else {
					canvas.drawArc(ovalSmall, startAngle, currentAngle, false, highFlowPaint);
				}
			} else if (currentAngle <= (angle2[0] + angle2[1])) {
				highFlowPaint.setStrokeWidth(small_ring_width);
				canvas.drawArc(ovalSmall, startAngle, angle2[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[0];
				canvas.drawArc(ovalSmall, startAngle, currentAngle - angle2[0], false, fixProfitPaint);
			} else if (currentAngle <= (angle2[0] + angle2[1] + angle2[2])) {
				highFlowPaint.setStrokeWidth(small_ring_width);
				canvas.drawArc(ovalSmall, startAngle, angle2[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[0];
				canvas.drawArc(ovalSmall, startAngle, angle2[1], false, fixProfitPaint);
				floatProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[1];
				canvas.drawArc(ovalSmall, startAngle, currentAngle - angle2[0] - angle2[1], false, floatProfitPaint);
			} else {
				highFlowPaint.setStrokeWidth(small_ring_width);
				canvas.drawArc(ovalSmall, startAngle, angle2[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[0];
				canvas.drawArc(ovalSmall, startAngle, angle2[1], false, fixProfitPaint);
				floatProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[1];
				canvas.drawArc(ovalSmall, startAngle, angle2[2], false, floatProfitPaint);
				otherTypePaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[2];
				canvas.drawArc(ovalSmall, startAngle, currentAngle - angle2[0] - angle2[1] - angle2[2], false,
						otherTypePaint);
			}
			if (currentAngle == ringSumAngle) {
				isAction = false;
			}
		} else {
			startAngle = 0 - 90;
			highFlowPaint.setStrokeWidth(big_ring_width);
			greyPaint.setStrokeWidth(big_ring_width);
			if (angle1[0] == ringSumAngle) {
				canvas.drawArc(ovalBig, startAngle, angle1[0], false, greyPaint);
			} else {
				canvas.drawArc(ovalBig, startAngle, angle1[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[0];
				canvas.drawArc(ovalBig, startAngle, angle1[1], false, fixProfitPaint);
				floatProfitPaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[1];
				canvas.drawArc(ovalBig, startAngle, angle1[2], false, floatProfitPaint);
				otherTypePaint.setStrokeWidth(big_ring_width);
				startAngle = startAngle + angle1[2];
				canvas.drawArc(ovalBig, startAngle, angle1[3], false, otherTypePaint);
			}

			startAngle = 0 - 90;
			highFlowPaint.setStrokeWidth(small_ring_width);
			greyPaint.setStrokeWidth(small_ring_width);
			if (angle2[0] == ringSumAngle) {
				canvas.drawArc(ovalSmall, startAngle, angle2[0], false, greyPaint);
			} else {
				canvas.drawArc(ovalSmall, startAngle, angle2[0], false, highFlowPaint);
				fixProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[0];
				canvas.drawArc(ovalSmall, startAngle, angle2[1], false, fixProfitPaint);
				floatProfitPaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[1];
				canvas.drawArc(ovalSmall, startAngle, angle2[2], false, floatProfitPaint);
				otherTypePaint.setStrokeWidth(small_ring_width);
				startAngle = startAngle + angle2[2];
				canvas.drawArc(ovalSmall, startAngle, angle2[3], false, otherTypePaint);
			}
		}
	}

	private float[] getAngle(float valueLst[]) {
		float temp[] = new float[4];
		float sumValue = 0.0f;
		for (int i = 0; i < valueLst.length; i++) {
			sumValue = sumValue + valueLst[i];
		}
		if (sumValue == 0) {
			temp[0] = ringSumAngle;
			temp[1] = 0;
			temp[2] = 0;
			temp[3] = 0;
			return temp;
		}
		float sumAangle = 0.0f;
		for (int i = 0; i < valueLst.length; i++) {
			temp[i] = (float) Math.floor(ringSumAngle * valueLst[i] / 100);
			sumAangle = sumAangle + temp[i];
		}
		float surplusAangle = (ringSumAngle - sumAangle) / valueLst.length;
		if (surplusAangle != 0.0f) {
			sumAangle = 0.0f;
			for (int i = 0; i < valueLst.length; i++) {
				temp[i] = (float) Math.floor(temp[i] + surplusAangle);
				sumAangle = sumAangle + temp[i];
				if (i == valueLst.length - 1) {
					if ((ringSumAngle - sumAangle) != 0.0f) {
						temp[i] = temp[i] + ringSumAngle - sumAangle;
					}
				}
			}
		}
		return temp;
	}

	// private float[] getAngle(float valueLst[]) {
	// float temp[] = new float[4];
	// float sumValue = 0.0f;
	// for (int i = 0; i < valueLst.length; i++) {
	// sumValue = sumValue + valueLst[i];
	// }
	// if (sumValue == 0) {
	// temp[0] = ringSumAngle;
	// temp[1] = 0;
	// temp[2] = 0;
	// temp[3] = 0;
	// return temp;
	// }
	// float sumAangle = 0.0f;
	// for (int i = 0; i < valueLst.length; i++) {
	// temp[i] = (float) Math.floor(ringSumAngle * valueLst[i] / sumValue);
	// sumAangle = sumAangle + temp[i];
	// }
	// float surplusAangle = (ringSumAngle - sumAangle) / valueLst.length;
	// if (surplusAangle != 0.0f) {
	// sumAangle = 0.0f;
	// for (int i = 0; i < valueLst.length; i++) {
	// temp[i] = (float) Math.floor(temp[i] + surplusAangle);
	// sumAangle = sumAangle + temp[i];
	// if (i == valueLst.length - 1) {
	// if ((ringSumAngle - sumAangle) != 0.0f) {
	// temp[i] = temp[i] + ringSumAngle - sumAangle;
	// }
	// }
	// }
	// }
	// return temp;
	// }

}
