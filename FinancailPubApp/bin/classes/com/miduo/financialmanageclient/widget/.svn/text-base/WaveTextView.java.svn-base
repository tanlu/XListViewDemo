package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.miduo.financialmanageclient.R;

/**
 * 水波纹数字
 * 
 * @author huozhenpeng
 * 
 */
public class WaveTextView extends View implements Runnable {
	private TextPaint textPaint;
	private TextPaint textPaint2;
	private int textSize = 200;
	private int textPaintWidth = 50;
	private int symbolSize;
	private int width;
	private int height;
	private int Delta;
	private int step;
	private Path path;
	private FontMetrics fontMetrics;
	private String text = "0";
	private int baseline;
	private int symbolbaseline;
	private int max;
	private float controlY1;// 两个控制点的纵坐标
	private float controlY2;
	private int fraction = 1;// 1或者-1
	private Rect bounds;
	private TextPaint symbolTextPaint;
	private FontMetrics symbelMetrics;
	private int symbolWidth;
	private int symbolMargin;

	public WaveTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray types = context.obtainStyledAttributes(attrs,
				R.styleable.waveTextView);
		textSize=(int) types.getDimension(R.styleable.waveTextView_textSize, getResources().getDimension(R.dimen.px2dp_80));
		textPaintWidth=(int) types.getDimension(R.styleable.waveTextView_paintWidth, getResources().getDimension(R.dimen.px2dp_50));
		symbolSize=(int) types.getDimension(R.styleable.waveTextView_symbolSize, getResources().getDimension(R.dimen.px2dp_30));
		types.recycle();
		init();
	}

	public WaveTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public WaveTextView(Context context) {
		this(context, null);
	}

	public void init() {
		textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		textPaint.setColor(Color.parseColor("#0e92ca"));
		textPaint.setTextSize(textSize);
		textPaint.setStrokeWidth(textPaintWidth);
		textPaint.setTextAlign(Align.CENTER);
		fontMetrics = textPaint.getFontMetrics();

		textPaint2 = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		textPaint2.setColor(Color.parseColor("#efefef"));
		textPaint2.setTextSize(textSize);
		textPaint2.setStrokeWidth(textPaintWidth);
		textPaint2.setTextAlign(Align.CENTER);
		path = new Path();
		bounds = new Rect();
		textPaint.getTextBounds(text, 0, text.length(), bounds);
		
		symbolTextPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		symbolTextPaint.setTextSize(symbolSize);
		symbelMetrics=symbolTextPaint.getFontMetrics();
		symbolMargin=(int) getResources().getDimension(R.dimen.px2dp_2);
		symbolWidth=(int) (symbolTextPaint.measureText("%")+symbolMargin);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(
					(int) textPaint.measureText(text)+symbolWidth, MeasureSpec.EXACTLY);
		}
		if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(
					(int)( (bounds.height())+getResources().getDimension(R.dimen.px2dp_4)), MeasureSpec.EXACTLY);

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		baseline =  (int) (((fontMetrics.descent + fontMetrics.ascent) / 2)-getResources().getDimension(R.dimen.px2dp_2));//有点误差，再减点，囧
		// max = (int) (Integer.parseInt(text) / 100.0f * height);
		symbolbaseline=baseline+height/4;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Delta += 1;
		step += 1 * fraction;

		max = (int) (Integer.parseInt(text) / 100.0f * height);
		if (Delta >= max) {
			Delta = max;
		}
		path.reset();
		path.moveTo(0, 0);
		path.lineTo(width, 0);
		path.lineTo(width, height - Delta);
		controlY1 = step + (height - Delta);
		controlY2 = -step + (height - Delta);
		if (controlY1 - (height - Delta) >= height * 1.0f / 4) {
			fraction = -1;
		}
		if (controlY1 - (height - Delta) <= -height * 1.0f / 4) {
			fraction = 1;
		}
		path.cubicTo(width * 3.0f / 4, controlY1, width * 1.0f / 4, controlY2,
				0, height - Delta);
		path.close();
		canvas.drawColor(Color.parseColor("#ffffff"));
		canvas.drawText(text,( width-symbolWidth )/ 2, height / 2 - baseline, textPaint);
		symbolTextPaint.setColor(Color.parseColor("#0e92ca"));
		canvas.drawText("%", (width-symbolWidth+symbolMargin), height*3.0f/4-symbolbaseline, symbolTextPaint);
		canvas.save();
		canvas.clipPath(path);
		canvas.drawText(text, ( width-symbolWidth ) / 2, height / 2 - baseline, textPaint2);
		symbolTextPaint.setColor(Color.parseColor("#efefef"));
		canvas.drawText("%", (width-symbolWidth+symbolMargin), height*3.0f/4-symbolbaseline, symbolTextPaint);
		canvas.restore();
		super.onDraw(canvas);
	}

	public void setText(int text) {
		setVisibility(View.GONE);
		this.text = text + "";
		setVisibility(View.VISIBLE);
		Delta = 0;
		step = 0;
		fraction = 1;

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(30);
				// 这两行代码一定要放到onDraw中，要不然手机屏幕睡眠之后，线程还会走，但是不执行重绘操作，会导致Delta和step无限大和小
				// Delta += 1;
				// step += 1 * fraction;
				postInvalidate();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
