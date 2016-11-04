package com.miduo.financialmanageclient.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.listener.GesturesPasswordListener;

public class GesturesPasswordViewNew extends View {
	private Paint initcp;// // 白色边线画笔
	private Paint bluecp;// 蓝色的内环颜色
	private Paint blueLinecp;// 蓝色连线
	private Paint blackcp;// 黑色圆圈画笔
	private Paint watchetBlackcp;// 浅黑色圆环画笔
	private Paint blackLinecp;// 黑色连线
	private int width, height;// 控件的宽和高
	private int area;// 绘图区域
	private List<Integer> list;
	private boolean right = true;
	private GesturesPasswordListener listener;
	private float storkeWidth = 0f;

	public GesturesPasswordViewNew(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public GesturesPasswordViewNew(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GesturesPasswordViewNew(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		width = getPaddingLeft() + getPaddingRight() + specSize;
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		height = getPaddingTop() + getPaddingBottom() + specSize;
		setarea();
		width = area;
		height = area;
		setMeasuredDimension(width, height);
	}

	private void setarea() {
		area = width > height ? height : width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawLine(canvas);
		drawInit(canvas);
		drawInner(canvas);
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
		float smallRadius = (getResources().getDimension(R.dimen.px2dp_34) - storkeWidth * 2) / 2;
		float bigRadius = (getResources().getDimension(R.dimen.px2dp_120) - storkeWidth * 2) / 2;
		float center = getResources().getDimension(R.dimen.px2dp_120) / 2;
		if (x >= center - bigRadius && x <= center + bigRadius) {
			if (y >= center - bigRadius && y <= center + bigRadius) {
				return 1;
			} else if (y >= area / 2 - bigRadius && y <= area / 2 + bigRadius) {
				return 4;
			} else if (y >= area - center - bigRadius && y <= area - center + bigRadius) {
				return 7;
			}
		} else if (x >= area / 2 - bigRadius && x <= area / 2 + bigRadius) {
			if (y >= center - bigRadius && y <= center + bigRadius) {
				return 2;
			} else if (y >= area / 2 - bigRadius && y <= area / 2 + bigRadius) {
				return 5;
			} else if (y >= area - center - bigRadius && y <= area - center + bigRadius) {
				return 8;
			}
		} else if (x >= area - center - bigRadius && x <= area - center + bigRadius) {
			if (y >= center - bigRadius && y <= center + bigRadius) {
				return 3;
			} else if (y >= area / 2 - bigRadius && y <= area / 2 + bigRadius) {
				return 6;
			} else if (y >= area - center - bigRadius && y <= area - center + bigRadius) {
				return 9;
			}
		}
		return 0;
	}

	private void drawLine(Canvas canvas) {
		if (list.size() > 1) {
			float smallRadius = (getResources().getDimension(R.dimen.px2dp_34) - storkeWidth * 2) / 2;
			float bigRadius = (getResources().getDimension(R.dimen.px2dp_120) - storkeWidth * 2) / 2;
			float center = getResources().getDimension(R.dimen.px2dp_120) / 2;

			int first = list.get(0);
			float startX = 0, startY = 0, stopX = 0, stopY = 0;
			for (int i = 1; i < list.size(); i++) {
				int second = list.get(i);
				switch (first) {
				case 1:
					startX = center;
					startY = center;
					break;
				case 2:
					startX = area / 2;
					startY = center;
					break;
				case 3:
					startX = area - center;
					startY = center;
					break;
				case 4:
					startX = center;
					startY = area / 2;
					break;
				case 5:
					startX = area / 2;
					startY = area / 2;
					break;
				case 6:
					startX = area - center;
					startY = area / 2;
					break;
				case 7:
					startX = center;
					startY = area - center;
					break;
				case 8:
					startX = area / 2;
					startY = area - center;
					break;
				case 9:
					startX = area - center;
					startY = area - center;
					break;
				default:
					break;
				}
				switch (second) {
				case 1:
					stopX = center;
					stopY = center;
					break;
				case 2:
					stopX = area / 2;
					stopY = center;
					break;
				case 3:
					stopX = area - center;
					stopY = center;
					break;
				case 4:
					stopX = center;
					stopY = area / 2;
					break;
				case 5:
					stopX = area / 2;
					stopY = area / 2;
					break;
				case 6:
					stopX = area - center;
					stopY = area / 2;
					break;
				case 7:
					stopX = center;
					stopY = area - center;
					break;
				case 8:
					stopX = area / 2;
					stopY = area - center;
					break;
				case 9:
					stopX = area - center;
					stopY = area - center;
					break;
				default:
					break;
				}
				Paint darkTempcp;
				if (right) {
					darkTempcp = blueLinecp;
				} else {
					darkTempcp = blackLinecp;
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
			float smallRadius = (getResources().getDimension(R.dimen.px2dp_34) - storkeWidth * 2) / 2;
			float bigRadius = (getResources().getDimension(R.dimen.px2dp_120) - storkeWidth * 2) / 2;
			float center = getResources().getDimension(R.dimen.px2dp_120) / 2;
			// 第一横排第一个
			RectF oval1_1 = new RectF(center - bigRadius, center - bigRadius, center + bigRadius, center + bigRadius);
			RectF oval1_2 = new RectF(center - smallRadius, center - smallRadius, center + smallRadius, center
					+ smallRadius);
			// 第一横排第二个
			RectF oval2_1 = new RectF(area / 2 - bigRadius, center - bigRadius, area / 2 + bigRadius, center
					+ bigRadius);
			RectF oval2_2 = new RectF(area / 2 - smallRadius, center - smallRadius, area / 2 + smallRadius, center
					+ smallRadius);
			// 第一横排第三个
			RectF oval3_1 = new RectF(area - center - bigRadius, center - bigRadius, area - center + bigRadius, center
					+ bigRadius);
			RectF oval3_2 = new RectF(area - center - smallRadius, center - smallRadius, area - center + smallRadius,
					center + smallRadius);
			// 第二横排第一个
			RectF oval4_1 = new RectF(center - bigRadius, area / 2 - bigRadius, center + bigRadius, area / 2
					+ bigRadius);
			RectF oval4_2 = new RectF(center - smallRadius, area / 2 - smallRadius, center + smallRadius, area / 2
					+ smallRadius);
			// 第二横排第二个
			RectF oval5_1 = new RectF(area / 2 - bigRadius, area / 2 - bigRadius, area / 2 + bigRadius, area / 2
					+ bigRadius);
			RectF oval5_2 = new RectF(area / 2 - smallRadius, area / 2 - smallRadius, area / 2 + smallRadius, area / 2
					+ smallRadius);
			// 第二横排第三个
			RectF oval6_1 = new RectF(area - center - bigRadius, area / 2 - bigRadius, area - center + bigRadius, area
					/ 2 + bigRadius);
			RectF oval6_2 = new RectF(area - center - smallRadius, area / 2 - smallRadius, area - center + smallRadius,
					area / 2 + smallRadius);
			// 第三横排第一个
			RectF oval7_1 = new RectF(center - bigRadius, area - center - bigRadius, center + bigRadius, area - center
					+ bigRadius);
			RectF oval7_2 = new RectF(center - smallRadius, area - center - smallRadius, center + smallRadius, area
					- center + smallRadius);
			// 第三横排第二个
			RectF oval8_1 = new RectF(area / 2 - bigRadius, area - center - bigRadius, area / 2 + bigRadius, area
					- center + bigRadius);
			RectF oval8_2 = new RectF(area / 2 - smallRadius, area - center - smallRadius, area / 2 + smallRadius, area
					- center + smallRadius);
			// 第三横排第三个
			RectF oval9_1 = new RectF(area - center - bigRadius, area - center - bigRadius, area - center + bigRadius,
					area - center + bigRadius);
			RectF oval9_2 = new RectF(area - center - smallRadius, area - center - smallRadius, area - center
					+ smallRadius, area - center + smallRadius);
			Paint tempcp, watchetTempcp;
			if (right) {
				tempcp = initcp;
				watchetTempcp = bluecp;
			} else {
				tempcp = blackcp;
				watchetTempcp = watchetBlackcp;
			}
			for (int i : list) {
				switch (i) {
				case 1:
					canvas.drawArc(oval1_1, 0, 360, true, tempcp);
					canvas.drawArc(oval1_2, 0, 360, true, watchetTempcp);
					break;
				case 2:
					canvas.drawArc(oval2_1, 0, 360, true, tempcp);
					canvas.drawArc(oval2_2, 0, 360, true, watchetTempcp);
					break;
				case 3:
					canvas.drawArc(oval3_1, 0, 360, true, tempcp);
					canvas.drawArc(oval3_2, 0, 360, true, watchetTempcp);
					break;
				case 4:
					canvas.drawArc(oval4_1, 0, 360, true, tempcp);
					canvas.drawArc(oval4_2, 0, 360, true, watchetTempcp);
					break;
				case 5:
					canvas.drawArc(oval5_1, 0, 360, true, tempcp);
					canvas.drawArc(oval5_2, 0, 360, true, watchetTempcp);
					break;
				case 6:
					canvas.drawArc(oval6_1, 0, 360, true, tempcp);
					canvas.drawArc(oval6_2, 0, 360, true, watchetTempcp);
					break;
				case 7:
					canvas.drawArc(oval7_1, 0, 360, true, tempcp);
					canvas.drawArc(oval7_2, 0, 360, true, watchetTempcp);
					break;
				case 8:
					canvas.drawArc(oval8_1, 0, 360, true, tempcp);
					canvas.drawArc(oval8_2, 0, 360, true, watchetTempcp);
					break;
				case 9:
					canvas.drawArc(oval9_1, 0, 360, true, tempcp);
					canvas.drawArc(oval9_2, 0, 360, true, watchetTempcp);
					break;
				default:
					break;
				}
			}
		}
	}

	private void drawInit(Canvas canvas) {
		float smallRadius = (getResources().getDimension(R.dimen.px2dp_34) - storkeWidth * 2) / 2;
		float bigRadius = (getResources().getDimension(R.dimen.px2dp_120) - storkeWidth * 2) / 2;
		float center = getResources().getDimension(R.dimen.px2dp_120) / 2;
		Paint tempcp;
		if (right) {
			tempcp = initcp;
		} else {
			tempcp = blackcp;
		}
		// 第一竖排第一个
		RectF oval = new RectF(center - smallRadius, center - smallRadius, center + smallRadius, center + smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第一竖排第二个
		oval = new RectF(center - smallRadius, area / 2 - smallRadius, center + smallRadius, area / 2 + smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第一竖排第三个
		oval = new RectF(center - smallRadius, area - center - smallRadius, center + smallRadius, area - center
				+ smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第二竖排第一个
		oval = new RectF(area / 2 - smallRadius, center - smallRadius, area / 2 + smallRadius, center + smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第二竖排第二个
		oval = new RectF(area / 2 - smallRadius, area / 2 - smallRadius, area / 2 + smallRadius, area / 2 + smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第二竖排第三个
		oval = new RectF(area / 2 - smallRadius, area - center - smallRadius, area / 2 + smallRadius, area - center
				+ smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第三竖排第一个
		oval = new RectF(area - center - smallRadius, center - smallRadius, area - center + smallRadius, center
				+ smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第三竖排第二个
		oval = new RectF(area - center - smallRadius, area / 2 - smallRadius, area - center + smallRadius, area / 2
				+ smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
		// 第三竖排第三个
		oval = new RectF(area - center - smallRadius, area - center - smallRadius, area - center + smallRadius, area
				- center + smallRadius);
		canvas.drawArc(oval, 0, 360, true, tempcp);
	}

	private void init() {
		storkeWidth = getResources().getDimension(R.dimen.px2dp_2);
		// 白色边线
		initcp = new Paint();
		initcp.setStyle(Style.STROKE);
		initcp.setAntiAlias(true);
		initcp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_2));
		initcp.setColor(Color.parseColor("#ffffff"));
		// 蓝色的内环颜色
		bluecp = new Paint();
		bluecp.setAntiAlias(true);
		bluecp.setColor(Color.parseColor("#34b3d9"));
		// 蓝色连线
		blueLinecp = new Paint();
		blueLinecp.setStyle(Style.STROKE);
		blueLinecp.setAntiAlias(true);
		blueLinecp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_4));
		blueLinecp.setColor(Color.parseColor("#60b7d6"));

		// 黑色的边线
		blackcp = new Paint();
		blackcp.setAntiAlias(true);
		blackcp.setStyle(Style.STROKE);
		blackcp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_2));
		blackcp.setColor(Color.parseColor("#000000"));
		// 黑色的内部颜色
		watchetBlackcp = new Paint();
		watchetBlackcp.setAntiAlias(true);
		watchetBlackcp.setColor(Color.parseColor("#004E65"));
		// 黑色连线
		blackLinecp = new Paint();
		blackLinecp.setStyle(Style.STROKE);
		blackLinecp.setAntiAlias(true);
		blackLinecp.setStrokeWidth(getResources().getDimension(R.dimen.px2dp_4));
		blackLinecp.setColor(Color.parseColor("#004E65"));

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
