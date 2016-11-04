package com.miduo.financialmanageclient.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SharePrefUtil {
	//share文件名 存储当前用户的信息
	public final static String CUR_USER_INFO = "cur_user_info";
	//share文件名 存储用米多大众APP的用户的共有信息
	public final static String MIDUO_PUB_INFO = "miduo_pub_info";
	
	//判断是不是第一次登录APP
	public final static String IS_FIRST = "is_first";
	//判断是否过了最长有效时间，重新启动APP
	public final static String OUT_TIME = "out_time";
	public final static String DIALOG_OUT_TIME = "dialog_out_time";
	public final static String MSG_ON_OFF = "msg_on_off";
	/**
	 * 产品来源统计周期时间
	 */
	public final static String START_TIME = "pro_start";
	public final static String END_TIME = "pro_end";
	public final static String IS_SAVEGESTURE = "save_gesture";
	public final static String GESTURE = "gesture";
	// 是否已经登录
	public static final String IS_LOGIN = "is_login";
	public final static String EVER_LOGIN = "ever_login";
	public final static String ACCOUNT_INFO = "account_info";
	public final static String ACCOUNT_MOBILE = "account_mobile";
	public final static String ACCOUNT_PSD = "account_psd";
	public final static String ACCOUNT_HEADER = "account_header";
//	public final static String IFAID = "ifaid";
	public final static String MSG_DATE = "msg_date";
	public final static String MSG_CLICKED = "msg_clicked";
	public final static String FA_CODE = "fa_code";
	public final static String LOGIN_STATE = "login_state";
	
	public final static String GETUI_REGISTER = "GETUI";
	
	public final static String FA_DIALOG = "fa_dialog";
	public final static String IFA_DIALOG = "ifa_dialog";
	public final static String SFA_DIALOG = "sfa_dialog";
	
	public final static String USER_INFO = "user_info";
	

	public static final String IFA_ID = "ifa_id";

	public static void saveString(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		sp.edit().putString(key, value).commit();
	}

	public static SharedPreferences getSharePre_Name(Context context) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		return sp;
	}

	public static String getString(Context context, String key, String defValue) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		return sp.getString(key, defValue);
	}

	public static void clear(Context context) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		sp.edit().clear().commit();
	}

	public static int getInt(Context context, String key, int defValue) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		return sp.getInt(key, defValue);
	}

	public static void saveInt(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 将对象进行bitmap编码后保存到SharePref�?
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void saveBitmap(Context context, String key, Bitmap bitmap) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		String imageBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
		sp.edit().putString(key, imageBase64).commit();
	}

	/**
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static Bitmap getBitmap(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		String objBase64 = sp.getString(key, null);
		if (StringUtil.isEmpty(objBase64))
			return null;
		byte[] base64Bytes = Base64.decodeBase64(objBase64.getBytes());
		ByteArrayInputStream baos = new ByteArrayInputStream(base64Bytes);
		return BitmapFactory.decodeStream(baos);
	}

	public static void saveObj(Context context, String key, Object object) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.close();
			String objBase64 = new String(Base64.encodeBase64(baos.toByteArray()));

			sp.edit().putString(key, objBase64).commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveObj(Context context, String name, String key, Object object) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.close();
			String objBase64 = new String(Base64.encodeBase64(baos.toByteArray()));

			sp.edit().putString(key, objBase64).commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object getObj(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		String objBase64 = sp.getString(key, null);
		if (StringUtil.isEmpty(objBase64))
			return null;
		byte[] base64Bytes = Base64.decodeBase64(objBase64.getBytes());
		ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);

		ObjectInputStream ois;
		Object obj = null;
		try {
			ois = new ObjectInputStream(bais);
			obj = (Object) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static Object getObj(Context context, String name, String key) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		String objBase64 = sp.getString(key, null);
		if (StringUtil.isEmpty(objBase64))
			return null;
		byte[] base64Bytes = Base64.decodeBase64(objBase64.getBytes());
		ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);

		ObjectInputStream ois;
		Object obj = null;
		try {
			ois = new ObjectInputStream(bais);
			obj = (Object) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static boolean removeKey(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, Context.MODE_MULTI_PROCESS);
		return sp.edit().remove(key).commit();
	}

	public static boolean removeKey(Context context, String name, String key) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		return sp.edit().remove(key).commit();
	}

	public static void saveBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key, boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, 0);
		return sp.getBoolean(key, defValue);
	}

	public static void saveBoolean(Context context, String name, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String name, String key, boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		return sp.getBoolean(key, defValue);
	}

	public static void saveString(Context context, String name, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context context, String name, String key, String defValue) {
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		return sp.getString(key, defValue);
	}

	public static boolean clearKey(Context context) {
		SharedPreferences sp = context.getSharedPreferences(CUR_USER_INFO, Context.MODE_MULTI_PROCESS);
		return sp.edit().clear().commit();
	}
}
