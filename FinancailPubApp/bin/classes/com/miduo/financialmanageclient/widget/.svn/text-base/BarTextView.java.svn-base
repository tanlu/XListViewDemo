package com.miduo.financialmanageclient.widget;

import java.util.ArrayList;
import java.util.List;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BarBean;
import com.miduo.financialmanageclient.bean.PerBarBean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 柱状图下面的提示文字
 * 
 * @author huozhenpeng
 * 
 */
public class BarTextView extends View {
	private int leftPadding = 0;// 自己定义的，跟系统无关,描述文字的左边距
	private int perBarDelta = 20;// 组内柱子之间的距离
	private int barPadding = 100;// 柱子的左边距,与右边距相等
	private int barDelta = 80;// 组间柱子之间的距离
	private int perBarWidth;// 每组柱子的宽度
	private Paint paint;// 灰色画笔
	private int barWidth;// 意外险宽度
	private int width;// 控件宽度
	private int height;// 控件高度
	private int reservedHeight;// 预留高度（都是0的时候显示的柱状图）
	private TextPaint textPaint;
	private BarBean datas;
	private List<RectF> rects = new ArrayList<RectF>();;
	private int textSize = 50;
	private FontMetrics fontMetrics;
	private int textHeight;
	private int textpadding=10;
	private int baseline;// 文字的baseline

	public BarTextView(Context context) {
		this(context, null);
	}

	public BarTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BarTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void init() {
		//初始化，读取dimens文件的值
		perBarDelta=(int) getResources().getDimension(R.dimen.px2dp_7);
		barPadding=(int) getResources().getDimension(R.dimen.px2dp_85);
		barDelta=(int) getResources().getDimension(R.dimen.px2dp_80);
		textSize=(int) getResources().getDimension(R.dimen.px2sp_24);
		textpadding=(int) getResources().getDimension(R.dimen.px2dp_6);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setColor(Color.parseColor("#0888b7"));
		textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		textPaint.setColor(Color.parseColor("#ffffff"));
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(textSize);
		fontMetrics = textPaint.getFontMetrics();
		textHeight = (int) (fontMetrics.descent - fontMetrics.ascent);
		baseline = (int) (Math.abs(fontMetrics.ascent + fontMetrics.descent)/2);
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		heightMeasureSpec=MeasureSpec.makeMeasureSpec(textHeight+textpadding*2, MeasureSpec.EXACTLY);
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
		if (datas != null) {
			for (int i = 0; i < datas.getDatas().size(); i++) {
				PerBarBean perBarBean = datas.getDatas().get(i);
				perBarBean.setWhiteHeight(height * perBarBean.getWhiteBar()
						/ datas.getTotal());
				perBarBean.setBlueHeight(height * perBarBean.getBlueBar()
						/ datas.getTotal());
			}
		}
		

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.parseColor("#000ca7d7"));
		if (datas != null) {
			for (int i = 0; i < datas.getDatas().size(); i++) {
				canvas.drawRoundRect(rects.get(i), textHeight/2, textHeight/2,
						paint);
				canvas.drawText(datas.getDatas().get(i).getDes(), rects.get(i).left+(rects.get(i).right-rects.get(i).left)/2, textHeight-baseline+textpadding, textPaint);
			}
		}
		super.onDraw(canvas);
	}

	/**
	 * 外部设置数据
	 */
	public void setDatas(BarBean datas) {
		if (datas==null || datas.getDatas() == null || datas.getDatas().size() != 4) {
			throw new IllegalArgumentException("数据内部集合长度应为4");
		}
		this.datas = datas;
		if (datas != null) {
			int startX = leftPadding + barPadding + barWidth / 2;
			for (int i = 0; i < datas.getDatas().size(); i++) {
				rects.add(new RectF(startX + i * (barDelta + barWidth) + i
						* (perBarDelta + barWidth) - barWidth / 2 - textHeight
						/ 2, 0,
						startX + (perBarDelta + barWidth) * (i + 1) + i
								* (barWidth + barDelta) + barWidth / 2
								+ textHeight / 2, textHeight + textpadding));
			}
		}
		init();
	}

}
