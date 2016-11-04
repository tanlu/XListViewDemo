package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.InVesteResult;
import com.miduo.financialmanageclient.bean.InvestResultBean;

/**
 * 柱形图（投资结果页面）
 * 
 * @author huozhenpeng
 * 
 */
public class InvestmentBarPic extends View {
	private int leftPadding;// 左侧间距,与右侧相等
	private int perBarWidth;// 每个柱子的宽度
	private int perBarPadding;// 柱子之间的宽度
	private int width;
	private int height;
	private InVesteResult inVesteResult;
	private InvestResultBean tempInvestResultBean;
	private Context context;
	private Paint paint;
	private TextPaint textPaint;
	private int textsize;
	private FontMetrics metrics;
	private OnBarClickListener listener;
	private int surplusHeight;

	public InvestmentBarPic(Context context) {
		this(context, null);
	}

	public InvestmentBarPic(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public InvestmentBarPic(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.investmentBarPic, 0, 0);
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.investmentBarPic_leftPadding:
				leftPadding = (int) typedArray.getDimension(i, getResources()
						.getDimension(R.dimen.px2dp_80));
				break;
			case R.styleable.investmentBarPic_perBarPadding:
				perBarPadding = (int) typedArray.getDimension(i, getResources()
						.getDimension(R.dimen.px2dp_54));
				break;
			default:
				break;
			}
		}
		typedArray.recycle();
		init();
	}

	public void init() {
//		leftPadding = (int) context.getResources().getDimension(
//				R.dimen.px2dp_80);
//		perBarPadding = (int) context.getResources().getDimension(
//				R.dimen.px2dp_54);
		textsize = (int) context.getResources().getDimension(R.dimen.px2sp_24);
		surplusHeight = (int) context.getResources().getDimension(
				R.dimen.px2dp_4);
		if (inVesteResult != null) {
			if(inVesteResult.getLists().size()==0)
				return;
			perBarWidth = (width - leftPadding * 2 - (inVesteResult.getLists()
					.size() - 1) * perBarPadding)
					/ inVesteResult.getLists().size();
		}
		
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setStrokeWidth(perBarWidth);
		textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		textPaint.setTextSize(textsize);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setColor(Color.parseColor("#ffffff"));
		metrics = textPaint.getFontMetrics();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 宽度直接在配置文件中写match_parent,这儿就不处理了
		int height = MeasureSpec.getSize(heightMeasureSpec) + getPaddingLeft()
				+ getPaddingRight();
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
				MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (inVesteResult != null) {
			for (int i = 0; i < inVesteResult.getLists().size(); i++) {
				tempInvestResultBean = inVesteResult.getLists().get(i);
				paint.setColor(Color.parseColor(tempInvestResultBean.getColor()));
				canvas.drawLine(
						leftPadding + perBarWidth / 2
								+ (perBarWidth + perBarPadding) * i,
						height,
						leftPadding + perBarWidth / 2
								+ (perBarWidth + perBarPadding) * i,
						tempInvestResultBean.getActualHeight() == 0 ? height
								- surplusHeight : height
								- tempInvestResultBean.getActualHeight(), paint);
				tempInvestResultBean.setLeftX(leftPadding
						+ (perBarPadding + perBarWidth) * i);
				tempInvestResultBean.setRightX(leftPadding
						+ (perBarPadding + perBarWidth) * i + perBarWidth);
				tempInvestResultBean.setTopY(height
						- tempInvestResultBean.getActualHeight());
				canvas.drawText(
						tempInvestResultBean.getDescripion(),
						leftPadding + perBarWidth / 2
								+ (perBarWidth + perBarPadding) * i,
						tempInvestResultBean.getActualHeight() == 0 ? height
								- surplusHeight
								- (metrics.descent - metrics.ascent) / 2
								: height
										- tempInvestResultBean
												.getActualHeight()
										- (metrics.descent - metrics.ascent)
										/ 2, textPaint);
				tempInvestResultBean.setLeftX(leftPadding
						+ (perBarWidth + perBarPadding) * i);
				tempInvestResultBean.setRightX(leftPadding
						+ (perBarWidth + perBarPadding) * i + perBarWidth);
				tempInvestResultBean.setTopY(height
						- tempInvestResultBean.getActualHeight());
			}
		}
		super.onDraw(canvas);
	}

	public void setData(InVesteResult inVesteResult) {
		this.inVesteResult = inVesteResult;
		InvestResultBean investResultBean = null;
		for (int i = 0; i < inVesteResult.getLists().size(); i++) {
			investResultBean = inVesteResult.getLists().get(i);
			// 留下十分之一的高度显示柱子顶端的文字
			investResultBean.setActualHeight((int) (9 * height / 10
					* investResultBean.getActualData() / inVesteResult
					.getTotal()));
		}
		init();
		invalidate();
	}

	public interface OnBarClickListener {
		void barClick(int index);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int) event.getX();
			int y = (int) event.getY();
			InvestResultBean investResultBean = null;
			if (inVesteResult != null) {
				for (int i = 0; i < inVesteResult.getLists().size(); i++) {
					investResultBean = inVesteResult.getLists().get(i);
					if (x > investResultBean.getLeftX()
							&& investResultBean.getRightX() > x
							//&& y > investResultBean.getTopY()
							) {
						if (listener != null) {
							listener.barClick(i);
						}
					}
				}
			}

			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	public void setOnBarClickListerer(OnBarClickListener listener) {
		this.listener = listener;
	}

}
