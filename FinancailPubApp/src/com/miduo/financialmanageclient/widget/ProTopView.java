package com.miduo.financialmanageclient.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.listener.ProductSelectChangeListener;

/**
 * 
 * @author sunrui 2015/09/23
 * 
 */
public class ProTopView extends View {

	private int width, height;
	// 中间圆的长宽
	private int inheight, inwidth;
	// 两个文字选项中间的间隔长度，文字大小，文字和图片间隔，图片大小
	private int specing, textSize, div, innercell;
	// 自定义控件宽度的一半
	private int centerx;
	private int cell_c, cell_l, cell_r;
	private int startX;
	// 文字左边图片+间隔+文字的宽度
	private int innerlength;
	// 背景图片，文字选项的深蓝背景
	private Bitmap bgBitmap, circle_center;
	// 背景图片大小的矩形,文字选项深蓝背景大小的矩形
	private Rect bgSrc, circleSrc;
	// 整个自定义控件大小的矩形
	private Rect bgDes;
	private Rect circleDes1, circleDes2, circleDes5, innerSrc2, innerDes1,
			innerDes2, innerDes4;
	// 背景图片，文字，文字选项背景的Paint对象
	private Paint bgPaint, textP, circleP, leftP;
	private List<String> listStr;
	private List<Bitmap> listBit1;
	private Bitmap fdsyBit1, gldxBit1, gdsyBit1, llsyBit1, mdrxBit1, assign,
			insurance;
	private Rect textRect;
	private FontMetricsInt fontMetrics;
	private int baseline;
	private ProductSelectChangeListener listener;
	private int select = 0;

	public void setProductSelectChangeListener(
			ProductSelectChangeListener listener) {
		this.listener = listener;
	}

	public ProTopView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ProTopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.protopview, 0, 0);
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.protopview_inheight:
				inheight = typedArray.getDimensionPixelSize(i, 80);
				break;
			case R.styleable.protopview_inwidth:
				inwidth = typedArray.getDimensionPixelSize(i, 480);
				break;
			case R.styleable.protopview_specing:
				specing = typedArray.getDimensionPixelSize(i, 40);
				break;
			case R.styleable.protopview_textsize:
				textSize = typedArray.getDimensionPixelSize(i, 34);
				break;
			case R.styleable.protopview_div_:
				div = typedArray.getDimensionPixelSize(i, 30);
				break;
			case R.styleable.protopview_innercell:
				innercell = typedArray.getDimensionPixelSize(i, 38);
				break;
			default:
				break;
			}
		}
		init();
	}

	public ProTopView(Context context) {
		super(context);
	}

	public void init() {
		bgBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.top_bg);
		circle_center = BitmapFactory.decodeResource(getResources(),
				R.drawable.circle_center);
		bgSrc = new Rect(0, 0, bgBitmap.getWidth(), bgBitmap.getHeight());
		circleSrc = new Rect(0, 0, circle_center.getWidth(),
				circle_center.getHeight());
		circleDes1 = new Rect();
		circleDes2 = new Rect();
		// new Rect();
		// new Rect();
		circleDes5 = new Rect();
		bgDes = new Rect(0, 0, width, height);
		bgPaint = new Paint();
		bgPaint.setAntiAlias(true);
		startX = 0;
		listStr = new ArrayList<String>();
		listBit1 = new ArrayList<Bitmap>();
		initData();
		textP = new Paint();
		textP.setTextSize(textSize);
		textP.setAntiAlias(true);
		textRect = new Rect();
		textP.setColor(Color.WHITE);
		textP.getTextBounds(listStr.get(0), 0, listStr.get(0).length(),
				textRect);
		innerDes1 = new Rect();
		innerDes2 = new Rect();
		// new Rect();
		innerDes4 = new Rect();
		innerlength = innercell + div + textRect.width();
		circleP = new Paint();
		circleP.setAntiAlias(true);
		leftP = new Paint();
		leftP.setAntiAlias(true);
		circleP.setTextSize(textSize);
		leftP.setTextSize(textSize);
		leftP.setColor(Color.WHITE);
		circleP.setColor(Color.WHITE);
	}

	private void initData() {
		listStr.add("米多热销");
		listStr.add("高流动性");
		listStr.add("固定收益");
		listStr.add("浮动收益");
		listStr.add("另类投资");
		listStr.add("保险产品");
		listStr.add("转让专区");
		mdrxBit1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.hand_icon);
		fdsyBit1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.fdsy_icon1);
		innerSrc2 = new Rect(0, 0, fdsyBit1.getWidth(), fdsyBit1.getHeight());
		gldxBit1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.gldx_icon1);
		gdsyBit1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.gdsy_icon1);
		llsyBit1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.llsy_icon1);
		insurance = BitmapFactory.decodeResource(getResources(),
				R.drawable.insurance);
		assign = BitmapFactory
				.decodeResource(getResources(), R.drawable.assign);
		listBit1.add(mdrxBit1);
		listBit1.add(gldxBit1);
		listBit1.add(gdsyBit1);
		listBit1.add(fdsyBit1);
		listBit1.add(llsyBit1);
		listBit1.add(insurance);
		listBit1.add(assign);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		width = 0;
		height = 0;
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		width = getPaddingLeft() + getPaddingRight() + specSize;
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		height = getPaddingTop() + getPaddingBottom() + specSize;
		bgDes.right = width;
		bgDes.bottom = height;
		centerx = width / 2;
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.parseColor("#2da8df"));
		// Rect bgSrc: 是对图片进行裁截，若是空null则显示整个图片
		// RectF
		// bgDes：是图片在Canvas画布中显示的区域(大于bgSrc则把bgSrc的裁截区放大，小于bgSrc则把bgSrc的裁截区缩小。)
		// 中间那个条目选项
		circleDes1.left = (width - inwidth) / 2 + startX;
		circleDes1.top = (height - inheight) / 2;
		circleDes1.right = circleDes1.left + inwidth;
		circleDes1.bottom = circleDes1.top + inheight;
		// 左边边那个条目选项
		circleDes5.right = circleDes1.left - specing;
		circleDes5.top = circleDes1.top;
		circleDes5.bottom = circleDes1.bottom;
		circleDes5.left = circleDes5.right - inwidth;
		// 右边那个条目选项
		circleDes2.bottom = circleDes1.bottom;
		circleDes2.top = circleDes1.top;
		circleDes2.left = circleDes1.right + specing;
		circleDes2.right = circleDes2.left + inwidth;
		// 中间文字选项上，开始画图片的位置，相对于文字选项左边(所说的文字选项是每一个item)
		cell_c = (inwidth - innerlength) / 2;
		// 左边文字选项上，开始画图片的左边位置(相对于左边那个item)
		cell_l = inwidth - ((width - inwidth) / 2 - specing - innercell) / 2
				- innercell;
		// 右边文字选项上，开始画图片的左边位置
		cell_r = ((width - inwidth) / 2 - specing - innercell) / 2;

		int lenth = width - ((width - inwidth - 2 * specing) / 2 + specing);// (其实位置时中间item相对屏幕左边的长度)
		int delta_c = 0;
		int delta_l = 0;
		int delta_r = 0;
		if (startX >= 0) {// 向右边滑动
			delta_c = cell_c
					- Math.abs((int) ((startX * (cell_c - cell_r)) / (float) lenth));
			delta_l = cell_l
					- Math.abs((int) ((startX * (cell_l - cell_r)) / (float) lenth));
			delta_r = cell_r;

		} else {
			delta_c = cell_c
					+ Math.abs((int) ((startX * (cell_l - cell_c)) / (float) lenth));

			delta_r = cell_r
					+ Math.abs((int) ((startX * (cell_c - cell_r)) / (float) lenth));
		}
		innerDes1.left = circleDes1.left + delta_c;
		innerDes1.right = innerDes1.left + innercell;
		innerDes1.top = (height - innercell) / 2;
		innerDes1.bottom = (height + innercell) / 2;

		innerDes4.left = circleDes5.left + delta_l;
		innerDes4.right = innerDes4.left + innercell;
		innerDes4.top = innerDes1.top;
		innerDes4.bottom = innerDes1.bottom;

		innerDes2.left = circleDes2.left + delta_r;
		innerDes2.right = innerDes2.left + innercell;
		innerDes2.top = innerDes1.top;
		innerDes2.bottom = innerDes1.bottom;
		float fenmu = (circleDes1.right - circleDes1.left) + specing;
		float distance1 = (circleDes1.right + circleDes1.left) / 2 - centerx;
		int a1 = (int) (150 - Math.abs((distance1 / fenmu) * 150)) + 100;
		circleP.setAlpha(a1);
		canvas.drawBitmap(circle_center, circleSrc, circleDes1, circleP);
		canvas.drawBitmap(listBit1.get(0), innerSrc2, innerDes1, circleP);
		fontMetrics = textP.getFontMetricsInt();
		baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2
				- fontMetrics.top;
		canvas.drawText(listStr.get(0), circleDes1.left + cell_c + innercell
				+ div, baseline, circleP);
		float distance2 = (circleDes5.right + circleDes5.left) / 2 - centerx;
		int a2 = (int) (150 - Math.abs((distance2 / fenmu) * 150)) + 100;
		leftP.setAlpha(a2);
		canvas.drawBitmap(listBit1.get(6), innerSrc2, innerDes4, leftP);
		canvas.drawBitmap(circle_center, circleSrc, circleDes5, leftP);
		canvas.drawText(listStr.get(6), circleDes5.left + cell_c + innercell
				+ div, baseline, leftP);
		float distance3 = (circleDes2.right + circleDes2.left) / 2 - centerx;
		int a3 = (int) (150 - Math.abs((distance3 / fenmu) * 150)) + 100;
		circleP.setAlpha(a3);
		canvas.drawBitmap(circle_center, circleSrc, circleDes2, circleP);
		canvas.drawBitmap(listBit1.get(1), innerSrc2, innerDes2, circleP);
		canvas.drawText(listStr.get(1), circleDes2.left + cell_c + innercell
				+ div, baseline, circleP);
	}

	private float delta, initx;

	private int downPosition, upPosition;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			initx = event.getX();
			downPosition = onClickPosition(event.getX());
			break;
		case MotionEvent.ACTION_MOVE:
			delta = event.getX() - initx;
			startX = (int) delta;
			postInvalidate();
			break;
		case MotionEvent.ACTION_UP:
			upPosition = onClickPosition(event.getX());
			if (upPosition == downPosition && downPosition != -1) {
				if (upPosition == 4) {
					toMoveRight();
				}
				if (upPosition == 1) {
					toMoveLeft();
				}
			} else if (delta < (width / 4) && delta > (-1) * (width / 4)) {
				startX = 0;
				postInvalidate();
			} else if (delta >= (width / 4)) {
				toRight();
			} else {
				toLeft();
			}

			break;
		default:
			break;
		}
		return true;
	}

	private void toMoveRight() {
		startX = 0;
		new Thread() {
			public void run() {
				while (true) {
					startX += 30;
					try {
						Thread.sleep(1000 / 16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (startX >= inwidth) {
						startX = inwidth;
						handler.sendEmptyMessage(1);
						break;
					}
					postInvalidate();
				}
			}
		}.start();

	}

	private void toMoveLeft() {
		startX = 0;
		new Thread() {
			public void run() {
				while (true) {
					startX -= 30;
					try {
						Thread.sleep(1000 / 16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (startX <= (-1) * inwidth) {
						startX = (-1) * inwidth;
						handler.sendEmptyMessage(2);
						break;
					}
					postInvalidate();
				}
			}
		}.start();

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				System.out.println("====");
				toRight();
			}
			if (msg.what == 2) {
				toLeft();
			}
			invalidate();
		};
	};

	private void toRight() {
		String str = listStr.get(6);
		listStr.add(0, str);
		listStr.remove(7);
		startX = 0;
		Bitmap bitmap1 = listBit1.get(6);
		listBit1.add(0, bitmap1);
		listBit1.remove(7);
		if (listener != null) {
			select -= 10;
			if (select == -10) {
				select = 60;
			}
			listener.onIndexChange(select);
		}
	}

	private void toLeft() {
		String str = listStr.get(0);
		listStr.add(str);
		listStr.remove(0);
		startX = 0;
		Bitmap bitmap1 = listBit1.get(0);
		listBit1.add(bitmap1);
		listBit1.remove(0);

		if (listener != null) {
			select += 10;
			if (select == 70) {
				select = 0;
			}
			listener.onIndexChange(select);
		}
	}

	/**
	 * 判断点击位置
	 * 
	 * @return
	 */
	private int onClickPosition(float x) {
		if (x >= innerDes4.left && x <= innerDes4.right) {
			return 4;
		}
		if (x >= innerDes2.left && x <= innerDes2.right) {
			return 1;
		}
		return -1;
	}

	public void setIndex(int index) {
		if (index == 0)
			return;
		for (int i = 0; i < index; i++) {
			toLeft2();
		}
		if (listener != null) {
			listener.onIndexChange(select);
		}
	}

	private void toLeft2() {
		String str = listStr.get(0);
		listStr.add(str);
		listStr.remove(0);
		startX = 0;
		Bitmap bitmap1 = listBit1.get(0);
		listBit1.add(bitmap1);
		listBit1.remove(0);

		if (listener != null) {
			select += 10;
			if (select == 70) {
				select = 0;
			}

		}
	}

}
