package com.miduo.financialmanageclient.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;

import org.apache.http.util.EncodingUtils;

import com.miduo.financialmanageclient.application.ConstantValues;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.Log;

public class FileUtil {
	private static final String Tag = FileUtil.class.getSimpleName();

	private FileUtil(){
		throw new Error("工具类FileUtil不可实例化");
	}
	
	/**
	 * @param fileName
	 *            文件名字
	 * @param type
	 *            文件类型
	 * @return
	 */
	public static File getFile(String fileName, int type) {
		File file = null;
		String path = Environment.getExternalStorageDirectory().getPath() + File.separator + ConstantValues.ROOTDIR;
		switch (type) {
		// 录音文件
		case ConstantValues.RECROD_SOUND_TYPE:
			path = path + File.separator + ConstantValues.RECROD_SOUND_DIR + File.separator + fileName;
			file = new File(path);

			break;

		// 图片
		case ConstantValues.IMAGE_TYPE:
			path = path + File.separator + ConstantValues.IMAGE_DIR + File.separator + fileName;
			file = new File(path);
			break;

		// 头像
		case ConstantValues.PHOTO_TYPE:
			path = path + File.separator + ConstantValues.PHOTO_DIR + File.separator + fileName;
			file = new File(path);
			break;

		// 缓存
		case ConstantValues.CACHE_TYPE:
			path = path + File.separator + ConstantValues.CACHE_DIR + File.separator + fileName;
			file = new File(path);
			break;
		// 缓存
		case ConstantValues.UPDATE_TYPE:
			path = path + File.separator + ConstantValues.UPDATE_DIR + File.separator + fileName;
			file = new File(path);
			break;
		default:
			break;
		}

		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * 获得文件路径
	 */
	public static String getPath(String fileName, int type) {
		String path = Environment.getExternalStorageDirectory().getPath() + File.separator + ConstantValues.ROOTDIR;
		switch (type) {
		// 录音文件
		case ConstantValues.RECROD_SOUND_TYPE:
			path = path + File.separator + ConstantValues.RECROD_SOUND_DIR + File.separator + fileName;
			break;

		// 图片
		case ConstantValues.IMAGE_TYPE:
			path = path + File.separator + ConstantValues.IMAGE_DIR + File.separator + fileName;
			break;

		// 头像
		case ConstantValues.PHOTO_TYPE:
			path = path + File.separator + ConstantValues.PHOTO_DIR + File.separator + fileName;
			break;

		// 缓存
		case ConstantValues.CACHE_TYPE:
			path = path + File.separator + ConstantValues.CACHE_DIR + File.separator + fileName;
			break;
		// apk
		case ConstantValues.UPDATE_TYPE:
			path = path + File.separator + ConstantValues.UPDATE_DIR + File.separator + fileName;
			break;
		default:
			break;
		}
		return path;
	}

	/*
	 * 创建所需路径
	 */
	public static void createFileDir() {
		File file = null;
		String path = Environment.getExternalStorageDirectory().getPath() + File.separator + ConstantValues.ROOTDIR;
		file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String subPath = null;
		// 录音文件
		subPath = path + File.separator + ConstantValues.RECROD_SOUND_DIR;
		file = new File(subPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 图片
		subPath = path + File.separator + ConstantValues.IMAGE_DIR;
		file = new File(subPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 头像
		subPath = path + File.separator + ConstantValues.PHOTO_DIR;
		file = new File(subPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 缓存
		subPath = path + File.separator + ConstantValues.CACHE_DIR;
		file = new File(subPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// apk文件
		subPath = path + File.separator + ConstantValues.UPDATE_DIR;
		file = new File(subPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		subPath = null;
		file = null;
	}

	public static String FileToBase64(String filepath) {
		File file = new File(filepath);
		FileInputStream fis = null;
		byte[] buffer = new byte[(int) file.length()];
		// int len = 0;
		String fileString = null;
		try {
			fis = new FileInputStream(file);
			fis.read(buffer);
			fileString = Base64.encodeToString(buffer, Base64.DEFAULT);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileString;
	}

	public static void Base64ToFile(String base64Data, String filepath) {
		byte[] buffer = Base64.decode(base64Data, Base64.DEFAULT);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filepath);
			out.write(buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取文件名，不带后缀的
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameNoFormat(String filePath) {
		if (StringUtil.isEmpty(filePath)) {
			return "";
		}
		int point = filePath.lastIndexOf('.');
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1, point);
	}

	/**
	 * 获取文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		long size = 0;

		File file = new File(filePath);
		if (file != null && file.exists()) {
			size = file.length();
		}
		return size;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileFormat(String fileName) {
		if (StringUtil.isEmpty(fileName))
			return "";

		int point = fileName.lastIndexOf('.');
		return fileName.substring(point + 1);
	}

	/**
	 * path:/data/data/package/files/fileName 文件是否存在（我的定制文件是否存在）
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkFileIsExists(Context context, String name) {
		boolean status;
		if (!name.equals("")) {
			// 获取File的绝对路径
			File newPath = new File(context.getFilesDir() + "/" + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;

	}

	/**
	 * path:/data/data/package/files/fileName
	 * 
	 * @param context
	 * @param msg
	 */
	public static void writeToFile(Context context, String fileName, String content) {
		if (content == null)
			content = "";
		FileOutputStream fos = null;
		try {
			// 写入一个私有的文件，此文件的权限仅本应用
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(content.getBytes("UTF-8"));
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 从文件中读取字符串
	public static String readString(Context context, String fileName) {
		String res = "";
		FileInputStream fin = null;
		try {
			fin = context.openFileInput(fileName);
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
		} catch (Exception ex) {
			Log.e(Tag, ex.getMessage());
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fin = null;
			}
		}
		return res;
	}

	/**
	 * path:/data/data/package/cache/fileName 缓存文件
	 * 
	 * @param context
	 * @param fileName
	 * @param content
	 */
	public static void writeToCache(Context context, String fileName, String content) {
		if (content == null)
			content = "";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(context.getCacheDir());
			fos.write(content.getBytes("UTF-8"));
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * path:/mnt/SDCard01/Android/data/package/file/fileName 写入文件
	 * 
	 * @param context
	 * @param fileName
	 * @param content
	 */
	public static void writeToSDFile(Context context, String fileName, String content) {
		if (content == null)
			content = "";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(FileUtil.getSDFileDir(context, fileName));
			fos.write(content.getBytes("UTF-8"));
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * path:/mnt/SDCard01/Android/data/package/cache/fileName 写入缓存
	 * 
	 * @param context
	 * @param fileName
	 * @param content
	 */
	public static void writeToSDCache(Context context, String fileName, String content) {
		if (content == null)
			content = "";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(FileUtil.getSDCacheDir(context, fileName));
			fos.write(content.getBytes("UTF-8"));
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * /data/data/package/cache/fileName 读取缓存
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String readFromCache(Context context, String fileName) {
		try {
			FileInputStream in = context.openFileInput(fileName);
			return readInStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}

	public static File createFile(String folderPath, String fileName) {
		File destDir = new File(folderPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(folderPath, fileName + fileName);
	}

	/**
	 * 写数据到SD卡
	 * 
	 * @param buffer
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFileToSDCard(byte[] buffer, String folder, String fileName) {
		boolean writeSucc = false;

		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

		String folderPath = "";
		if (sdCardExist) {
			folderPath = Environment.getExternalStorageDirectory() + File.separator + folder + File.separator;
		} else {
			writeSucc = false;
		}

		File fileDir = new File(folderPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(buffer);
			writeSucc = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return writeSucc;
	}

	/**
	 * 从路径获取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		if (StringUtil.isEmpty(filePath))
			return "";
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	}

	/**
	 * 格式化大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 获取目录大小
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file);
			}
		}
		return dirSize;
	}

	/**
	 * 获取文件列表个数
	 * 
	 * @param f
	 * @return
	 */
	public static long getFileList(File dir) {
		long count = 0;
		File[] files = dir.listFiles();
		count = files.length;
		for (File file : files) {
			if (file.isDirectory()) {
				count = count + getFileList(file);
				count--;
			}
		}
		return count;
	}

	public static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			out.write(ch);
		}
		byte buffer[] = out.toByteArray();
		out.close();
		return buffer;
	}

	/**
	 * 文件是否存在
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String name) {
		boolean status;
		if (!name.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;

	}

	/**
	 * 获取sd卡剩余空间大小
	 * 
	 * @return
	 */
	public static long getFreeDiskSpace() {
		String status = Environment.getExternalStorageState();
		long freeSpace = 0;
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				File path = Environment.getExternalStorageDirectory();
				StatFs stat = new StatFs(path.getPath());
				@SuppressWarnings("deprecation")
				long blockSize = stat.getBlockSize();
				@SuppressWarnings("deprecation")
				long availableBlocks = stat.getAvailableBlocks();
				freeSpace = availableBlocks * blockSize / 1024;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return -1;
		}
		return (freeSpace);
	}

	/**
	 * 创建目录
	 * 
	 * @param directoryName
	 * @return
	 */
	public static boolean createDirectory(String directoryName) {
		boolean status;
		if (!directoryName.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + directoryName);
			status = newPath.mkdir();
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * SD卡是否存在
	 * 
	 * @return
	 */
	public static boolean checkSDExists() {
		String sDCardStatus = Environment.getExternalStorageState();
		boolean status;
		if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * SD卡是否可写
	 * 
	 * @return
	 */
	public static boolean checkSDWritable() {
		return checkSDExists();
	}

	/**
	 * SD卡是否可读
	 * 
	 * @return
	 */
	public static boolean checkSDReadable() {
		String sDCardStatus = Environment.getExternalStorageState();
		if (sDCardStatus.equals(Environment.MEDIA_MOUNTED) || sDCardStatus.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除目录下的所有文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteDirectory(File directory) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (directory != null) {
			checker.checkDelete(directory.toString());
			if (directory.isDirectory()) {
				String[] listfile = directory.list();
				// delete all files within the specified directory and then
				// delete the directory
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(directory.toString() + "/" + listfile[i].toString());
						deletedFile.delete();
					}
					directory.delete();
					// Log.i("DirectoryManager deleteDirectory", directory);
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除目录下的所有文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteDirectory(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isDirectory()) {
				String[] listfile = newPath.list();
				// delete all files within the specified directory and then
				// delete the directory
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(newPath.toString() + "/" + listfile[i].toString());
						deletedFile.delete();
					}
					newPath.delete();
					Log.i("DirectoryManager deleteDirectory", fileName);
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				try {
					Log.i("DirectoryManager deleteFile", fileName);
					newPath.delete();
					status = true;
				} catch (SecurityException se) {
					se.printStackTrace();
					status = false;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * path:/Android/data/package/cache
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static File getSDCacheDir(Context context, String fileName) {
		String sdCacheDirPath = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			sdCacheDirPath = context.getExternalCacheDir().getPath();
		} else {
			sdCacheDirPath = "/Android/data/" + context.getPackageName() + "/cache";
		}
		return new File(sdCacheDirPath + File.separator + fileName);
	}

	/**
	 * path:/Android/data/package/files
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static File getSDFileDir(Context context, String fileName) {
		String sdFileDir = null;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			sdFileDir = context.getExternalFilesDir(null).getPath();
		} else {
			sdFileDir = "/Android/data/" + context.getPackageName() + "/files";
		}
		return new File(sdFileDir + File.separator + fileName);
	}

	/**
	 * path:/mnt/sdcard/type
	 * 
	 * @param type
	 * @return
	 */
	public static File getPublicFileDir(String type) {
		if (StringUtil.isEmpty(type)) {
			throw new IllegalArgumentException("type不能为空");
		}
		if (isFroyoOrMore()) {
			return Environment.getExternalStoragePublicDirectory(type);
		} else {
			String path = Environment.getExternalStorageDirectory().getPath();
			if (Environment.DIRECTORY_ALARMS.equals(type)) {
				path += "/Alarms";
			} else if (Environment.DIRECTORY_DCIM.equals(type)) {
				path += "/DCIM";
			} else if (Environment.DIRECTORY_DOWNLOADS.equals(type)) {
				path += "/Download";
			} else if (Environment.DIRECTORY_MOVIES.equals(type)) {
				path += "/Movies";
			} else if (Environment.DIRECTORY_MUSIC.equals(type)) {
				path += "/Music";
			} else if (Environment.DIRECTORY_PICTURES.equals(type)) {
				path += "/Pictures";
			} else if (Environment.DIRECTORY_NOTIFICATIONS.equals(type)) {
				path += "/Notifications";
			} else if (Environment.DIRECTORY_PODCASTS.equals(type)) {
				path += "/Podcasts";
			} else if (Environment.DIRECTORY_RINGTONES.equals(type)) {
				path += "/Ringtones";
			}
			return new File(path);
		}
	}

	private static boolean isFroyoOrMore() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	public static void writeObject(Context context, String fileName, Object o) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
		} catch (IOException ex) {
			Log.e(Tag, ex.getMessage());
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				oos = null;
			}
			fos = null;
		}
	}

	public static Object readObject(Context context, String fileName) {
		Object o = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			File f = new File(context.getFilesDir() + "/" + fileName);
			if (!f.exists()) {
				return null;
			}
			fis = context.openFileInput(fileName);
			ois = new ObjectInputStream(fis);
			o = ois.readObject();
		} catch (Exception ex) {
			Log.e(Tag, ex.getMessage());
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ois = null;
			}
			fis = null;
		}
		return o;
	}
	
	/**
	 * copy 图片
	 */
	public static void copyFile(File sourceFile, File desFile) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(sourceFile);
			fo = new FileOutputStream(desFile);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
