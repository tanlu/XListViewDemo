package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BarBean;
import com.miduo.financialmanageclient.bean.PerBarBean;

/**
 * 柱状图
 * 
 * @author huozhenpeng
 * 
 */
public class BarPictrue extends View implements Runnable {
	private int leftPadding = 0;// 自己定义的，跟系统无关,描述文字的左边距
	private int barPadding = 100;// 柱子的左边距,与右边距相等
	private int barDelta = 80;// 组间柱子之间的距离
	private int perBarDelta = 20;// 组内柱子之间的距离
	private int perBarWidth;// 每组柱子的宽度
	private Paint paint;// 柱子画笔
	private int barWidth;// 柱状图宽度
	private int width;// 控件宽度
	private int height;// 控件高度
	private int reservedHeight;// 预留高度（都是0的时候显示的柱状图）
	private BarBean datas;
	private int whiteDelta = 0;
	private int blueDelta = -100;
	private boolean flag = false;

	public BarPictrue(Context context) {
		this(context, null);
	}

	public BarPictrue(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BarPictrue(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void init() {
		perBarDelta = (int) getResources().getDimension(R.dimen.px2dp_7);
		barPadding = (int) getResources().getDimension(R.dimen.px2dp_85);
		barDelta = (int) getResources().getDimension(R.dimen.px2dp_80);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h - reservedHeight;
		perBarWidth = (width - leftPadding - 2 * barPadding - 3 * barDelta) / 4;
		barWidth = (perBarWidth - perBarDelta) / 2;
		paint.setStrokeWidth(barWidth);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.parseColor("#000ca7d7"));
		if (datas != null) {
			int startX = leftPadding + barPadding + barWidth / 2;
			for (int i = 0; i < datas.getDatas().size(); i++) {
				paint.setColor(Color.parseColor("#ffffff"));
				canvas.drawLine(
						startX + i * (barDelta + barWidth) + i
								* (perBarDelta + barWidth),
						height,
						startX + i * (barDelta + barWidth) + i
								* (perBarDelta + barWidth),datas.getDatas().get(i).getWhiteHeight()!=0?(//如果为0，则按宽度720像素显示10像素的高度
						whiteDelta <= datas.getDatas().get(i).getWhiteHeight() ? height
								- whiteDelta
								: height
										- datas.getDatas().get(i)
												.getWhiteHeight()):height-getResources().getDimension(R.dimen.px2dp_10), paint);
				paint.setColor(Color.parseColor("#61ccff"));
				canvas.drawLine(startX + (perBarDelta + barWidth) * (i + 1) + i
						* (barWidth + barDelta), height, startX
						+ (perBarDelta + barWidth) * (i + 1) + i
						* (barWidth + barDelta), datas.getDatas().get(i).getBlueHeight()!=0?(blueDelta <= datas.getDatas()
						.get(i).getBlueHeight() ? height - blueDelta : height
						- datas.getDatas().get(i).getBlueHeight()):height-getResources().getDimension(R.dimen.px2dp_10), paint);
			}
		}
		super.onDraw(canvas);
	}

	/**
	 * 外部设置数据
	 */
	public void setDatas(BarBean datas) {
		this.datas = datas;
		if (datas == null) {
			invalidate();
			return;
		}
		if(datas.getDatas()==null)
			return;
		if (datas.getDatas().size() != 4) {
			throw new IllegalArgumentException("数据内部集合长度应为4");
		}
		if (datas != null) {
			for (int i = 0; i < datas.getDatas().size(); i++) {
				PerBarBean perBarBean = datas.getDatas().get(i);
				perBarBean.setWhiteHeight(height * perBarBean.getWhiteBar()
						/ datas.getTotal());
				perBarBean.setBlueHeight(height * perBarBean.getBlueBar()
						/ datas.getTotal());
			}
		}
		invalidate();
	}

	@Override
	public void run() {
		while (true) {
			if (flag) {
				whiteDelta += 2;
				blueDelta += 2;
			}

			try {
				postInvalidate();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 清除数据
	 */
	public void clearData() {
		flag = true;
		whiteDelta = 0;
		blueDelta = -100;

	}

}
