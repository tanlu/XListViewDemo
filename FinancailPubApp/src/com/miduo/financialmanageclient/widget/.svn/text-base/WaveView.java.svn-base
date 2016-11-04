package com.miduo.financialmanageclient.widget;


import com.miduo.financialmanageclient.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @author huozhenpeng
 * 
 */
public class WaveView extends View implements Runnable {
	private Paint paint;
	private Rect rectSrc;
	private Rect rectbitmap1Des1;
	private Rect rectbitmap1Des2;
	private Rect rectbitmap2Des1;
	private Rect rectbitmap2Des2;
	private int width;
	private int height;
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private int step;

	public WaveView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public WaveView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public WaveView(Context context) {
		this(context, null);
	}

	public void init() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		bitmap1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.line_bg1);
		bitmap2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.line_bg2);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
			int maxHeight = Math.max(bitmap1.getHeight(), bitmap2.getHeight());
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight,
					MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		rectSrc = new Rect(0, 0, width, height);
		rectbitmap1Des1 = new Rect(0, 0, width, height);
		rectbitmap1Des2 = new Rect(0, 0, width, height);
		rectbitmap2Des1 = new Rect(0, 0, width, height);
		rectbitmap2Des2 = new Rect(0, 0, width, height);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		step = step % width;
		rectbitmap1Des1.left = step;
		rectbitmap1Des1.right = step + width;
		canvas.drawBitmap(bitmap1, rectSrc, rectbitmap1Des1, paint);
		rectbitmap1Des2.left = step - width;
		rectbitmap1Des2.right = step;
		canvas.drawBitmap(bitmap1, rectSrc, rectbitmap1Des2, paint);

		rectbitmap2Des1.left = -1 * step;
		rectbitmap2Des1.right = -1 * step + width;
		canvas.drawBitmap(bitmap2, rectSrc, rectbitmap2Des1, paint);
		rectbitmap2Des2.left = -1 * step + width;
		rectbitmap2Des2.right = -1 * step + width * 2;
		canvas.drawBitmap(bitmap2, rectSrc, rectbitmap2Des2, paint);
		super.onDraw(canvas);
	}

	@Override
	public void run() {
		int delta = 5;
		while (true) {
			try {
				step += delta;
				postInvalidate();
				Thread.sleep(50);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
