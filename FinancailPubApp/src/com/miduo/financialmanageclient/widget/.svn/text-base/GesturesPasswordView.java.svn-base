package com.miduo.financialmanageclient.widget;

import java.util.ArrayList;
import java.util.List;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.listener.GesturesPasswordListener;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GesturesPasswordView extends View {

	private Boolean isDrawLine;// 是否绘制线
	private Paint initcp;// 初始灰色圆圈画笔
	private Paint bluecp;// 蓝色圆圈画笔
	private Paint watchetBluecp;// 浅蓝色圆环画笔
	private Paint darkBluecp;// 深蓝色画笔
	private Paint redcp;// 红色圆圈画笔
	private Paint watchetRedcp;// 浅红色圆环画笔
	private Paint darkRedcp;// 深红色画笔
	private Paint smllDarkBluecp;//小的手势view选中的深蓝色
	private int width, height;// 控件的宽和高
	private int area;// 绘图区域
	private List<Integer> list;
	private boolean right = true;
	private GesturesPasswordListener listener;
	private float storkeWidth = 0f;
	private boolean isBigView = true;

	public GesturesPasswordView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GesturePasswordView, 0, 0);
		isBigView = typedArray.getBoolean(R.styleable.GesturePasswordView_bigView, true);
		init();
	}

	public GesturesPasswordView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GesturesPasswordView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		width = getPaddingLeft() + getPaddingRight() + specSize;
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		height = getPaddingTop() + getPaddingBottom() + specSize;
		height = width;
		setarea();
		setMeasuredDimension(width, height);
	}

	private void setarea() {
		area = width > height ? height : width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawInit(canvas);
		if (isBigView) {
			drawInner(canvas);
			drawLine(canvas);
		} else {
			drawSmallViewValue(canvas);
		}
	}

	private void drawSmallViewValue(Canvas canvas) {
		// TODO Auto-generated method stub
		if (list.size() > 0) {
			int initw = (width - area) / 2;
			int inith = (height - area) / 2;
			float d = (area - storkeWidth * 6) / 8;
			// 第一横排第一个
			RectF oval1_1 = new RectF(initw + storkeWidth, inith + storkeWidth, initw + 2 * d + storkeWidth, inith + 2
					* d + storkeWidth);
			// 第一横排第二个
			RectF oval2_1 = new RectF(initw + 3 * d + storkeWidth * 3, inith + storkeWidth, initw + 5 * d + storkeWidth
					* 3, inith + 2 * d + storkeWidth);
			// 第一横排第三个
			RectF oval3_1 = new RectF(initw + 6 * d + storkeWidth * 5, inith + storkeWidth, initw + 8 * d + storkeWidth
					* 5, inith + 2 * d + storkeWidth);
			// 第二横排第一个
			RectF oval4_1 = new RectF(initw + storkeWidth, inith + 3 * d + storkeWidth * 3,
					initw + 2 * d + storkeWidth, inith + 5 * d + 3 * storkeWidth);
			// 第二横排第二个
			RectF oval5_1 = new RectF(initw + 3 * d + storkeWidth * 3, inith + 3 * d + storkeWidth * 3, initw + 5 * d
					+ storkeWidth * 3, inith + 5 * d + storkeWidth * 3);
			// 第二横排第三个
			RectF oval6_1 = new RectF(initw + 6 * d + storkeWidth * 5, inith + 3 * d + storkeWidth * 3, initw + 8 * d
					+ storkeWidth * 5, inith + 5 * d + storkeWidth * 3);
			// 第三横排第一个
			RectF oval7_1 = new RectF(initw + storkeWidth, inith + 6 * d + storkeWidth * 5,
					initw + 2 * d + storkeWidth, inith + 8 * d + storkeWidth * 5);
			// 第三横排第二个
			RectF oval8_1 = new RectF(initw + 3 * d + storkeWidth * 3, inith + 6 * d + storkeWidth * 5, initw + 5 * d
					+ storkeWidth * 3, inith + 8 * d + storkeWidth * 5);
			// 第三横排第三个
			RectF oval9_1 = new RectF(initw + 6 * d + storkeWidth * 5, inith + 6 * d + storkeWidth * 5, initw + 8 * d
					+ +storkeWidth * 5, inith + 8 * d + storkeWidth * 5);
			for (int i : list) {
				switch (i) {
				case 1:
					canvas.drawArc(oval1_1, 0, 360, true, smllDarkBluecp);
					break;
				case 2:
					canvas.drawArc(oval2_1, 0, 360, true, smllDarkBluecp);
					break;
				case 3:
					canvas.drawArc(oval3_1, 0, 360, true, smllDarkBluecp);
					break;
				case 4:
					canvas.drawArc(oval4_1, 0, 360, true, smllDarkBluecp);
					break;
				case 5:
					canvas.drawArc(oval5_1, 0, 360, true, smllDarkBluecp);
					break;
				case 6:
					canvas.drawArc(oval6_1, 0, 360, true, smllDarkBluecp);
					break;
				case 7:
					canvas.drawArc(oval7_1, 0, 360, true, smllDarkBluecp);
					break;
				case 8:
					canvas.drawArc(oval8_1, 0, 360, true, smllDarkBluecp);
					break;
				case 9:
					canvas.drawArc(oval9_1, 0, 360, true, smllDarkBluecp);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			right = true;
			float dx = event.getX();
			float dy = event.getY();
			int dlocation = getLocation(dx, dy);
			if (dlocation != 0) {
				list.add(dlocation);
				// if (listener != null)
				// listener.changeGesturesPassword(list);
				postInvalidate();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float mx = event.getX();
			float my = event.getY();
			int mlocation = getLocation(mx, my);
			if (mlocation != 0) {
				if (list.size() > 0) {
					if (mlocation != list.get(list.size() - 1)) {
						list.add(mlocation);
						// if (listener != null)
						// listener.changeGesturesPassword(list);
					}
				} else {
					list.add(mlocation);
					// if (listener != null)
					// listener.changeGesturesPassword(list);
				}
			}
			postInvalidate();
			break;
		case MotionEvent.ACTION_UP:
			if (list.size() > 0) {
				if (listener != null)
					listener.getGesturesPassword(list);
			}
			// list = new ArrayList<Integer>();
			postInvalidate();
			break;
		default:
			break;
		}
		return true;
	}

	private int getLocation(float x, float y) {
		int initw = (width - area) / 2;
		int inith = (height - area) / 2;
		float d = (area - storkeWidth * 6) / 8;
		if (x >= initw + storkeWidth + d / 2 && x <= initw + storkeWidth + 2 * d - d / 2) {
			if (y >= inith + storkeWidth + d / 2 && y <= inith + storkeWidth + d * 2 - d / 2) {
				return 1;
			} else if (y >= inith + storkeWidth * 3 + d / 2 + 3 * d && y <= inith + storkeWidth * 3 + 5 * d - d / 2) {
				return 4;
			} else if (y >= inith + storkeWidth * 5 + d / 2 + 6 * d && y <= inith + storkeWidth * 5 + 8 * d - d / 2) {
				return 7;
			}
		} else if (x >= initw + storkeWidth * 3 + d / 2 + 3 * d && x <= initw + storkeWidth * 3 + 5 * d - d / 2) {
			if (y >= inith + storkeWidth + d / 2 && y <= inith + storkeWidth + d * 2 - d / 2) {
				return 2;
			} else if (y >= inith + storkeWidth * 3 + d / 2 + 3 * d && y <= inith + storkeWidth * 3 + 5 * d - d / 2) {
				return 5;
			} else if (y >= inith + storkeWidth * 5 + d / 2 + 6 * d && y <= inith + storkeWidth * 5 + 8 * d - d / 2) {
				return 8;
			}
		} else if (x >= initw + storkeWidth * 5 + 6 * d + d / 2 && x <= initw + storkeWidth * 5 + 8 * d - d / 2) {
			if (y >= inith + storkeWidth + d / 2 && y <= inith + storkeWidth + d * 2 - d / 2) {
				return 3;
			} else if (y >= inith + storkeWidth * 3 + d / 2 + 3 * d && y <= inith + storkeWidth * 3 + 5 * d - d / 2) {
				return 6;
			} else if (y >= inith + storkeWidth * 5 + d / 2 + 6 * d && y <= inith + storkeWidth * 5 + 8 * d - d / 2) {
				return 9;
			}
		}
		return 0;
	}

	private void drawLine(Canvas canvas) {
		if (list.size() > 1 && isDrawLine) {
			int initw = (width - area) / 2;
			int inith = (height - area) / 2;
			float d = (area - storkeWidth * 6) / 8;
			int first = list.get(0);
			float startX = 0, startY = 0, stopX = 0, stopY = 0;
			for (int i = 1; i < list.size(); i++) {
				int second = list.get(i);
				switch (first) {
				case 1:
					startX = initw + storkeWidth + d;
					startY = inith + storkeWidth + d;
					break;
				case 2:
					startX = initw + storkeWidth * 3 + 4 * d;
					startY = inith + storkeWidth + d;
					break;
				case 3:
					startX = initw + storkeWidth * 5 + 7 * d;
					startY = inith + storkeWidth + d;
					break;
				case 4:
					startX = initw + storkeWidth + d;
					startY = inith + storkeWidth * 3 + 4 * d;
					break;
				case 5:
					startX = initw + storkeWidth * 3 + 4 * d;
					startY = inith + storkeWidth * 3 + 4 * d;
					break;
				case 6:
					startX = initw + storkeWidth * 5 + 7 * d;
					startY = inith + storkeWidth * 3 + 4 * d;
					break;
				case 7:
					startX = initw + storkeWidth + d;
					startY = inith + storkeWidth * 5 + 7 * d;
					break;
				case 8:
					startX = initw + storkeWidth * 3 + 4 * d;
					startY = inith + storkeWidth * 5 + 7 * d;
					break;
				case 9:
					startX = initw + storkeWidth * 5 + 7 * d;
					startY = inith + storkeWidth * 5 + 7 * d;
					break;
				default:
					break;
				}
				switch (second) {
				case 1:
					stopX = initw + storkeWidth + d;
					stopY = inith + storkeWidth + d;
					break;
				case 2:
					stopX = initw + storkeWidth * 3 + 4 * d;
					stopY = inith + storkeWidth + d;
					break;
				case 3:
					stopX = initw + storkeWidth * 5 + 7 * d;
					stopY = inith + storkeWidth + d;
					break;
				case 4:
					stopX = initw + storkeWidth + d;
					stopY = inith + storkeWidth * 3 + 4 * d;
					break;
				case 5:
					stopX = initw + storkeWidth * 3 + 4 * d;
					stopY = inith + storkeWidth * 3 + 4 * d;
					break;
				case 6:
					stopX = initw + storkeWidth * 5 + 7 * d;
					stopY = inith + storkeWidth * 3 + 4 * d;
					break;
				case 7:
					stopX = initw + storkeWidth + d;
					stopY = inith + storkeWidth * 5 + 7 * d;
					break;
				case 8:
					stopX = initw + storkeWidth * 3 + 4 * d;
					stopY = inith + storkeWidth * 5 + 7 * d;
					break;
				case 9:
					stopX = initw + storkeWidth * 5 + 7 * d;
					stopY = inith + storkeWidth * 5 + 7 * d;
					break;
				default:
					break;
				}
				darkBluecp.setStrokeWidth(5);
				Paint darkTempcp;
				if (right) {
					darkTempcp = darkBluecp;
				} else {
					darkTempcp = darkRedcp;
				}
				canvas.drawLine(startX, startY, stopX, stopY, darkTempcp);
				first = second;
			}
		}
	}

	/**
	 * 选中的带颜色的圆圈
	 * 
	 * @param canvas
	 */
	private void drawInner(Canvas canvas) {
		if (list.size() > 0) {
			int initw = (width - area) / 2;
			int inith = (height - area) / 2;
			float d = (area - storkeWidth * 6) / 8;
			// 第一横排第一个
			RectF oval1_1 = new RectF(initw + storkeWidth, inith + storkeWidth, initw + 2 * d + storkeWidth, inith + 2
					* d + storkeWidth);
			RectF oval1_2 = new RectF(initw + storkeWidth, inith + storkeWidth, initw + 2 * d + storkeWidth, inith + 2
					* d + storkeWidth);
			RectF oval1_3 = new RectF(initw + d / 2 + storkeWidth, inith + d / 2 + storkeWidth, initw + d / 2
					+ storkeWidth + d, inith + d / 2 + storkeWidth + d);
			// 第一横排第二个
			RectF oval2_1 = new RectF(initw + 3 * d + storkeWidth * 3, inith + storkeWidth, initw + 5 * d + storkeWidth
					* 3, inith + 2 * d + storkeWidth);
			RectF oval2_2 = new RectF(initw + 3 * d + storkeWidth * 3, inith + storkeWidth, initw + 5 * d + storkeWidth
					* 3, inith + 2 * d + storkeWidth);
			;
			RectF oval2_3 = new RectF(initw + 3 * d + storkeWidth * 3 + d / 2, inith + storkeWidth + d / 2, initw + 3
					* d + storkeWidth * 3 + d / 2 + d, inith + storkeWidth + d / 2 + d);
			// 第一横排第三个
			RectF oval3_1 = new RectF(initw + 6 * d + storkeWidth * 5, inith + storkeWidth, initw + 8 * d + storkeWidth
					* 5, inith + 2 * d + storkeWidth);
			RectF oval3_2 = new RectF(initw + 6 * d + storkeWidth * 5, inith + storkeWidth, initw + 8 * d + storkeWidth
					* 5, inith + 2 * d + storkeWidth);
			RectF oval3_3 = new RectF(initw + 6 * d + storkeWidth * 5 + d / 2, inith + storkeWidth + d / 2, initw + 6
					* d + storkeWidth * 5 + d / 2 + d, inith + storkeWidth + d / 2 + d);
			// 第二横排第一个
			RectF oval4_1 = new RectF(initw + storkeWidth, inith + 3 * d + storkeWidth * 3,
					initw + 2 * d + storkeWidth, inith + 5 * d + 3 * storkeWidth);
			RectF oval4_2 = new RectF(initw + storkeWidth, inith + 3 * d + storkeWidth * 3,
					initw + 2 * d + storkeWidth, inith + 5 * d + 3 * storkeWidth);
			RectF oval4_3 = new RectF(initw + storkeWidth + d / 2, inith + 3 * d + storkeWidth * 3 + d / 2, initw
					+ storkeWidth + d / 2 + d, inith + 3 * d + storkeWidth * 3 + d / 2 + d);
			// 第二横排第二个
			RectF oval5_1 = new RectF(initw + 3 * d + storkeWidth * 3, inith + 3 * d + storkeWidth * 3, initw + 5 * d
					+ storkeWidth * 3, inith + 5 * d + storkeWidth * 3);
			RectF oval5_2 = new RectF(initw + 3 * d + storkeWidth * 3, inith + 3 * d + storkeWidth * 3, initw + 5 * d
					+ storkeWidth * 3, inith + 5 * d + storkeWidth * 3);
			RectF oval5_3 = new RectF(initw + 3 * d + storkeWidth * 3 + d / 2, inith + 3 * d + storkeWidth * 3 + d / 2,
					initw + 3 * d + storkeWidth * 3 + d / 2 + d, inith + 3 * d + storkeWidth * 3 + d / 2 + d);
			// 第二横排第三个
			RectF oval6_1 = new RectF(initw + 6 * d + storkeWidth * 5, inith + 3 * d + storkeWidth * 3, initw + 8 * d
					+ storkeWidth * 5, inith + 5 * d + storkeWidth * 3);
			RectF oval6_2 = new RectF(initw + 6 * d + storkeWidth * 5, inith + 3 * d + storkeWidth * 3, initw + 8 * d
					+ storkeWidth * 5, inith + 5 * d + storkeWidth * 3);
			RectF oval6_3 = new RectF(initw + 6 * d + storkeWidth * 5 + d / 2, inith + 3 * d + storkeWidth * 3 + d / 2,
					initw + 6 * d + storkeWidth * 5 + d / 2 + d, inith + 3 * d + storkeWidth * 3 + d / 2 + d);
			// 第三横排第一个
			RectF oval7_1 = new RectF(initw + storkeWidth, inith + 6 * d + storkeWidth * 5,
					initw + 2 * d + storkeWidth, inith + 8 * d + storkeWidth * 5);
			RectF oval7_2 = new RectF(initw + storkeWidth, inith + 6 * d + storkeWidth * 5,
					initw + 2 * d + storkeWidth, inith + 8 * d + storkeWidth * 5);
			RectF oval7_3 = new RectF(initw + storkeWidth + d / 2, inith + 6 * d + storkeWidth * 5 + d / 2, initw
					+ storkeWidth + d / 2 + d, inith + 6 * d + storkeWidth * 5 + d / 2 + d);
			// 第三横排第二个
			RectF oval8_1 = new RectF(initw + 3 * d + storkeWidth * 3, inith + 6 * d + storkeWidth * 5, initw + 5 * d
					+ storkeWidth * 3, inith + 8 * d + storkeWidth * 5);
			RectF oval8_2 = new RectF(initw + 3 * d + storkeWidth * 3, inith + 6 * d + storkeWidth * 5, initw + 5 * d
					+ storkeWidth * 3, inith + 8 * d + storkeWidth * 5);
			RectF oval8_3 = new RectF(initw + 3 * d + storkeWidth * 3 + d / 2, inith + 6 * d + storkeWidth * 5 + d / 2,
					initw + 3 * d + storkeWidth * 3 + d / 2 + d, inith + 6 * d + storkeWidth * 5 + d / 2 + d);
			// 第三横排第三个
			RectF oval9_1 = new RectF(initw + 6 * d + storkeWidth * 5, inith + 6 * d + storkeWidth * 5, initw + 8 * d
					+ +storkeWidth * 5, inith + 8 * d + storkeWidth * 5);
			RectF oval9_2 = new RectF(initw + 6 * d + storkeWidth * 5, inith + 6 * d + storkeWidth * 5, initw + 8 * d
					+ +storkeWidth * 5, inith + 8 * d + storkeWidth * 5);
			RectF oval9_3 = new RectF(initw + 6 * d + storkeWidth * 5 + d / 2, inith + 6 * d + storkeWidth * 5 + d / 2,
					initw + 6 * d + storkeWidth * 5 + d / 2 + d, inith + 6 * d + storkeWidth * 5 + d / 2 + d);
			Paint tempcp, watchetTempcp, darkTempcp;
			if (right) {
				tempcp = bluecp;
				watchetTempcp = watchetBluecp;
				darkTempcp = darkBluecp;
			} else {
				tempcp = redcp;
				watchetTempcp = watchetRedcp;
				darkTempcp = darkRedcp;
			}
			for (int i : list) {
				switch (i) {
				case 1:
					canvas.drawArc(oval1_1, 0, 360, true, tempcp);
					canvas.drawArc(oval1_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval1_3, 0, 360, true, darkTempcp);
					break;
				case 2:
					canvas.drawArc(oval2_1, 0, 360, true, tempcp);
					canvas.drawArc(oval2_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval2_3, 0, 360, true, darkTempcp);
					break;
				case 3:
					canvas.drawArc(oval3_1, 0, 360, true, tempcp);
					canvas.drawArc(oval3_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval3_3, 0, 360, true, darkTempcp);
					break;
				case 4:
					canvas.drawArc(oval4_1, 0, 360, true, tempcp);
					canvas.drawArc(oval4_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval4_3, 0, 360, true, darkTempcp);
					break;
				case 5:
					canvas.drawArc(oval5_1, 0, 360, true, tempcp);
					canvas.drawArc(oval5_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval5_3, 0, 360, true, darkTempcp);
					break;
				case 6:
					canvas.drawArc(oval6_1, 0, 360, true, tempcp);
					canvas.drawArc(oval6_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval6_3, 0, 360, true, darkTempcp);
					break;
				case 7:
					canvas.drawArc(oval7_1, 0, 360, true, tempcp);
					canvas.drawArc(oval7_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval7_3, 0, 360, true, darkTempcp);
					break;
				case 8:
					canvas.drawArc(oval8_1, 0, 360, true, tempcp);
					canvas.drawArc(oval8_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval8_3, 0, 360, true, darkTempcp);
					break;
				case 9:
					canvas.drawArc(oval9_1, 0, 360, true, tempcp);
					canvas.drawArc(oval9_2, 0, 360, true, watchetTempcp);
					canvas.drawArc(oval9_3, 0, 360, true, darkTempcp);
					break;
				default:
					break;
				}
			}
		}
	}

	private void drawInit(Canvas canvas) {
		int initw = (width - area) / 2;
		int inith = (height - area) / 2;
		float d = (area - storkeWidth * 6) / 8;
		// 第一竖排第一个
		RectF oval = new RectF(initw + storkeWidth, inith + storkeWidth, initw + 2 * d + storkeWidth, inith + 2 * d
				+ storkeWidth);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第一竖排第二个
		oval = new RectF(initw + storkeWidth, inith + 3 * d + storkeWidth * 3, initw + 2 * d + storkeWidth, inith + 5
				* d + 3 * storkeWidth);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第一竖排第三个
		oval = new RectF(initw + storkeWidth, inith + 6 * d + storkeWidth * 5, initw + 2 * d + storkeWidth, inith + 8
				* d + storkeWidth * 5);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第二竖排第一个
		oval = new RectF(initw + 3 * d + storkeWidth * 3, inith + storkeWidth, initw + 5 * d + storkeWidth * 3, inith
				+ 2 * d + storkeWidth);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第二竖排第二个
		oval = new RectF(initw + 3 * d + storkeWidth * 3, inith + 3 * d + storkeWidth * 3, initw + 5 * d + storkeWidth
				* 3, inith + 5 * d + storkeWidth * 3);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第二竖排第三个
		oval = new RectF(initw + 3 * d + storkeWidth * 3, inith + 6 * d + storkeWidth * 5, initw + 5 * d + storkeWidth
				* 3, inith + 8 * d + storkeWidth * 5);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第三竖排第一个
		oval = new RectF(initw + 6 * d + storkeWidth * 5, inith + storkeWidth, initw + 8 * d + storkeWidth * 5, inith
				+ 2 * d + storkeWidth);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第三竖排第二个
		oval = new RectF(initw + 6 * d + storkeWidth * 5, inith + 3 * d + storkeWidth * 3, initw + 8 * d + storkeWidth
				* 5, inith + 5 * d + storkeWidth * 3);
		canvas.drawArc(oval, 0, 360, true, initcp);
		// 第三竖排第三个
		oval = new RectF(initw + 6 * d + storkeWidth * 5, inith + 6 * d + storkeWidth * 5, initw + 8 * d + +storkeWidth
				* 5, inith + 8 * d + storkeWidth * 5);
		canvas.drawArc(oval, 0, 360, true, initcp);
	}

	public void setIsDrawLine(Boolean isDrawLine) {
		this.isDrawLine = isDrawLine;
	}

	private void init() {
		storkeWidth = getResources().getDimension(R.dimen.px2dp_2);
		// 灰色的线
		initcp = new Paint();
		initcp.setStyle(Style.STROKE);
		initcp.setAntiAlias(true);
		initcp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_2));
		initcp.setColor(Color.parseColor("#dedede"));

		// 蓝色的边线
		bluecp = new Paint();
		bluecp.setAntiAlias(true);
		bluecp.setStyle(Style.STROKE);
		bluecp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_2));
		bluecp.setColor(Color.parseColor("#56c0ea"));
		// 蓝色外圆的内部颜色
		watchetBluecp = new Paint();
		watchetBluecp.setAntiAlias(true);
		watchetBluecp.setColor(Color.parseColor("#b0e5fd"));
		// 深蓝色内环颜色
		darkBluecp = new Paint();
		darkBluecp.setAntiAlias(true);
		darkBluecp.setColor(Color.parseColor("#0da3eb"));
		isDrawLine = true;
		list = new ArrayList<Integer>();

		// 红色的边线
		redcp = new Paint();
		redcp.setAntiAlias(true);
		redcp.setStyle(Style.STROKE);
		redcp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_2));
		redcp.setColor(Color.parseColor("#f28176"));
		// 红色外圆的内部颜色
		watchetRedcp = new Paint();
		watchetRedcp.setAntiAlias(true);
		watchetRedcp.setColor(Color.parseColor("#efa49c"));
		// 深红色内环颜色
		darkRedcp = new Paint();
		darkRedcp.setAntiAlias(true);
		darkRedcp.setColor(Color.parseColor("#f7574a"));
		
		// 小的手势view深蓝色颜色
		smllDarkBluecp = new Paint();
		smllDarkBluecp.setAntiAlias(true);
		smllDarkBluecp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_2));
		smllDarkBluecp.setColor(Color.parseColor("#0da3eb"));
		
		isDrawLine = true;
		list = new ArrayList<Integer>();		
	}

	public void setListener(GesturesPasswordListener listener) {
		this.listener = listener;
	}

	public void setList(List<Integer> list) {
		this.list = list;
		postInvalidate();
	}

	public void setList(List<Integer> list, boolean right) {
		this.list = list;
		this.right = right;
		postInvalidate();
	}

}
