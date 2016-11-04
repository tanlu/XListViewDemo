package com.miduo.financialmanageclient.util;

import java.io.File;
import java.io.InputStream;

import com.miduo.financialmanageclient.ui.FindPsdActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;


public class PhotoCutUtils {
	public static final int CAMERA_RESQUEST = 1; // 拍照启动状态码
	public static final int PHOTO_RESQUEST = 2; // sdk<19相册启动状态码
	public static final int PHOTO_RESQUEST_SDK19 = 5;  //sdk>=19相册启动状态码
	public static String startPhotoZoom(Activity activity,String imgPath,boolean isCamera) {
		String myImagePath = imgPath;
		Uri imageUri = Uri.fromFile(new File(myImagePath));
		Intent intent = new Intent("com.android.camera.action.CROP");
//		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setDataAndType(imageUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", getWidth(activity));
		intent.putExtra("outputY", getWidth(activity));
//		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
//		if(isCamera) {
//			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//		} else {//如果是来自相册，从新裁剪后放到自制文件夹去
			String strImgPath = Environment.getExternalStorageDirectory().toString() + "/CONSDCGMPIC/";// 存放照片的文件夹
			String fileName = System.currentTimeMillis()+".jpg";
			//String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";// 照片命名
			File out = new File(strImgPath);
			if (!out.exists()) {// 判断存放照片的文件夹是否存在，如果不存在就创建
				out.mkdirs();
			}
			out = new File(strImgPath, fileName);
			myImagePath = strImgPath + fileName;// 该照片的绝对路径
			imageUri = Uri.fromFile(new File(myImagePath));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//		}
//		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//		intent.putExtra("noFaceDetection", true); // no face detection
		activity.startActivityForResult(intent, 3);
		return myImagePath;
	}
	
	public static String getOnResultAlbum(Intent data,Activity context) {
		if(data == null) {
			return null;
		}
		if(data.toString().contains("com.google.android.apps.docs")) { //如果来自google云盘，就不返回数据
			MToast.showToast(context, "无法从云端硬盘获得数据，请选择其他照片！");
			return null;
		}
		Uri uri = data.getData();
        return PhotoCutUtils.startPhotoZoom(context,PhotoCutUtils.getPath(context, uri),false);
	}
	
	public static int getWidth(Activity activity) {
		// 获得屏幕分辨率
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		// 屏幕高度（像素）
		return metric.widthPixels;
	}
	
	/**
	 * 保存裁剪之后的图片数据
	 * @param picdata
	 */
	public static void setPicToView(ImageView imgBg,String imgPath) {
		try {
			Bitmap bitmap = ImageUtils.getimage(imgPath);
			// 照片缩略图
			if (bitmap != null) {
				Drawable drawable = ImageUtils.bitmapToDrawable(bitmap);
				// layoutTop.setBackgroundDrawable(drawable);
				imgBg.setImageDrawable(drawable);
				imgBg.setTag(imgPath);  //保存裁剪的值
			}
		} catch (Exception e){
		}
	}
	
	public static void readPathBitMap(Context context,ImageView img,String imgPath) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;//设为true表示当系统要内存的时候，可以回收它
		 //如果图片当有需要可以被回收的话，这个opt.inInputShareable=true;可以让bitmap共享他人的数据
		//This field works in conjuction with inPurgeable. If inPurgeable is false, then this field is ignored. If inPurgeable is true, then this field determines whether the bitmap can share a reference to the input data (inputstream, array, etc.) or if it must make a deep copy.
		opt.inInputShareable = true;
		//获取资源图片
		img.setImageBitmap(BitmapFactory.decodeFile(imgPath, opt));
		
	}
	
	public static Bitmap readBitMap(Context context, ImageView img,int resId){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;//设为true表示当系统要内存的时候，可以回收它
		 //如果图片当有需要可以被回收的话，这个opt.inInputShareable=true;可以让bitmap共享他人的数据
		//This field works in conjuction with inPurgeable. If inPurgeable is false, then this field is ignored. If inPurgeable is true, then this field determines whether the bitmap can share a reference to the input data (inputstream, array, etc.) or if it must make a deep copy.
		opt.inInputShareable = true;
		//获取资源图片
		
		InputStream is = context.getResources().openRawResource(resId);
		img.setImageBitmap(BitmapFactory.decodeStream(is, null, opt));
		return BitmapFactory.decodeStream(is, null, opt);
	}
	public static Bitmap readBitMap2(Context context, ImageView img,int resId){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;//设为true表示当系统要内存的时候，可以回收它
		//如果图片当有需要可以被回收的话，这个opt.inInputShareable=true;可以让bitmap共享他人的数据
		//This field works in conjuction with inPurgeable. If inPurgeable is false, then this field is ignored. If inPurgeable is true, then this field determines whether the bitmap can share a reference to the input data (inputstream, array, etc.) or if it must make a deep copy.
		opt.inInputShareable = true;
		//获取资源图片
		
		InputStream is = context.getResources().openRawResource(resId);
		img.setImageBitmap(BitmapFactory.decodeStream(is, null, opt));
		return BitmapFactory.decodeStream(is, null, opt);
	}

	public static Bitmap readBitMapVoice(Context context, ImageView img,int resId){
		int oldId = (Integer) img.getTag();
		if(oldId == resId) {
			return null;
		}
		img.setTag(resId);
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;//设为true表示当系统要内存的时候，可以回收它
		 //如果图片当有需要可以被回收的话，这个opt.inInputShareable=true;可以让bitmap共享他人的数据
		//This field works in conjuction with inPurgeable. If inPurgeable is false, then this field is ignored. If inPurgeable is true, then this field determines whether the bitmap can share a reference to the input data (inputstream, array, etc.) or if it must make a deep copy.
		opt.inInputShareable = true;
		//获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		img.setImageBitmap(BitmapFactory.decodeStream(is,null,opt));
		return BitmapFactory.decodeStream(is,null,opt);
	}
	
	//http://blog.csdn.net/alovebtoc/article/details/17269565
	//android sdk 4.4.2更新
	@SuppressLint("InlinedApi")
	public static void getPhotoAlbum(Activity activity) {
		if (Build.VERSION.SDK_INT <19){
		    Intent intent = new Intent(); 
		    intent.setType("image/*");
		    intent.setAction(Intent.ACTION_GET_CONTENT);
		    activity.startActivityForResult(intent, PHOTO_RESQUEST);		
		} else {
		    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		    intent.addCategory(Intent.CATEGORY_OPENABLE);
		    intent.setType("image/*");
		    activity.startActivityForResult(intent, PHOTO_RESQUEST_SDK19);
		}
	}
	
	/**
	 * Get a file path from a Uri. This will get the the path for Storage Access
	 * Framework Documents, as well as the _data field for the MediaStore and
	 * other file-based ContentProviders.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @author paulburke
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	@SuppressLint("NewApi")
	public static String getPath(final Context context, final Uri uri) {

	    final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

	    // DocumentProvider
	    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
	        // ExternalStorageProvider
	        if (isExternalStorageDocument(uri)) {
	            final String docId = DocumentsContract.getDocumentId(uri);
	            final String[] split = docId.split(":");
	            final String type = split[0];

	            if ("primary".equalsIgnoreCase(type)) {
	                return Environment.getExternalStorageDirectory() + "/" + split[1];
	            }

	            // TODO handle non-primary volumes
	        }
	        // DownloadsProvider
	        else if (isDownloadsDocument(uri)) {

	            final String id = DocumentsContract.getDocumentId(uri);
	            final Uri contentUri = ContentUris.withAppendedId(
	                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

	            return getDataColumn(context, contentUri, null, null);
	        }
	        // MediaProvider
	        else if (isMediaDocument(uri)) {
	            final String docId = DocumentsContract.getDocumentId(uri);
	            final String[] split = docId.split(":");
	            final String type = split[0];

	            Uri contentUri = null;
	            if ("image".equals(type)) {
	                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	            } else if ("video".equals(type)) {
	                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	            } else if ("audio".equals(type)) {
	                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	            }

	            final String selection = "_id=?";
	            final String[] selectionArgs = new String[] {
	                    split[1]
	            };

	            return getDataColumn(context, contentUri, selection, selectionArgs);
	        }
	    }
	    // MediaStore (and general)
	    else if ("content".equalsIgnoreCase(uri.getScheme())) {

	        // Return the remote address
	        if (isGooglePhotosUri(uri))
	            return uri.getLastPathSegment();

	        return getDataColumn(context, uri, null, null);
	    }
	    // File
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @param selection (Optional) Filter used in the query.
	 * @param selectionArgs (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection,
	        String[] selectionArgs) {

	    Cursor cursor = null;
	    final String column = "_data";
	    final String[] projection = {
	            column
	    };

	    try {
	        cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
	                null);
	        if (cursor != null && cursor.moveToFirst()) {
	            final int index = cursor.getColumnIndexOrThrow(column);
	            return cursor.getString(index);
	        }
	    } finally {
	        if (cursor != null)
	            cursor.close();
	    }
	    return null;
	}


	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
	    return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
	    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
	    return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
	    return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
}