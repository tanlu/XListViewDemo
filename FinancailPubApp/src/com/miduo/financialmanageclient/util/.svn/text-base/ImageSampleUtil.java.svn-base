package com.miduo.financialmanageclient.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

/**
 * 图片的二次采样
 */
public class ImageSampleUtil {
	
	private ImageSampleUtil(){
		throw new Error("工具类ImageSampleUtil不可实例化");
	}
	
	/**
	 * 获得压缩处理后的位图
	 * 
	 * @param resources
	 * @param resID
	 *            图片ID
	 * @param reqWidth
	 *            需要的宽度
	 * @param reqHeight
	 *            需要的高度
	 * @return 压缩处理后的位图
	 */
	public static Bitmap decodeBitmapfromResource(Resources resources, int resID,
			int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;// 不会申请内存空间
		BitmapFactory.decodeResource(resources, resID, options);
		options.inSampleSize = calculateInsampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;// 压缩完成可以申请内存空间
		return BitmapFactory.decodeResource(resources, resID, options);
	}
	

	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		if (width <= 0) {
			return bitmap;
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}

	/**
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return 需要压缩的倍数
	 */
	public static int calculateInsampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {

		int width = options.outWidth;
		int height = options.outHeight;
		int inSampleSize = 1;
		if (width > reqWidth || height > reqHeight) {
			int heightRatio = Math.round((float) height / (float) reqHeight);
			int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	public Bitmap scaleBitMap(Resources resources, int resID,
			int reqWidth, int reqHeight) {
		Bitmap bitmap = BitmapFactory.decodeResource(resources, resID);
		float scaleW = reqWidth/bitmap.getWidth();
		float scaleH = reqHeight/bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(scaleW, scaleH); // 长和宽放大缩小的比例
		if(scaleW<=0)
			return bitmap;
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}
}
