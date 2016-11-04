package com.example.day07_listview_;



import java.io.File;
import java.io.IOException;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LargestLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LimitedAgeMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;

import android.app.Application;
import android.widget.Toast;

/**
 * @author WJL
 * 
 */
public class MyApplication extends Application {
	private int maxMemory;

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("MyApplication");
		
		maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024 / 1024;
                  System.out.println("最大内存:"+maxMemory);
                  Toast.makeText(this, "   "+maxMemory, 0).show();
                  
         //初始ImageLoader
		initImageLoader();
                  
//         Runtime.getRuntime().maxMemory();获取最大内存
	}

	/**
	 *           
         //初始ImageLoader
	 */
	@SuppressWarnings("deprecation")
	private void initImageLoader() {
		
		
		//得到ImageLoader实例
		ImageLoader imageLoader=ImageLoader.getInstance();
		
		//得到ImageLoader配置的建筑者对象
		ImageLoaderConfiguration.Builder builder=new Builder(this);
		
		//设置磁盘(sd卡)缓存的大小限制
//		builder.diskCacheSize(maxCacheSize);
		try {
			//设置磁盘(sd卡)缓存策略,参数一:缓存文件的路径,参数二:文件名的命名方法,有MD5,HashCode,参数三:缓存的最大内存
			builder.discCache(new LruDiskCache(new File("/storage/sdcard0/cache/"), new Md5FileNameGenerator(), 2*1024*1204));
			//设置磁盘(sd卡)缓存策略,限制缓存时间；参数一:缓存文件的路径,参数二:缓存文件的存活时间,系统当前时间-文件的最新修改时间 > maxAge
//			builder.discCache(new LimitedAgeDiskCache(cacheDir, maxAge) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//设置内存缓存的大小限制,默认是最大内存的1/8
		builder.memoryCacheSize(maxMemory/8);
		
		//设置内存缓存策略                根据先进先出的原则上删除多余对象  LinkedList  参数一:设置的缓存内存大小
//		builder.memoryCache(new FIFOLimitedMemoryCache(sizeLimit));
		
		//设置内存缓存策略          先删除占内存最大的图片      HashMap    参数一:设置的缓存内存大小
//  	builder.memoryCache(new LargestLimitedMemoryCache(sizeLimit));
		
         //	   设置内存缓存策略    Lru算法   LinkedHashMap  最大的缓存大小
		builder.memoryCache(new LruMemoryCache(maxMemory/8));
		
		//设置内存缓存策略        最少被用到的对象会被删除      HashMap    参数一:设置的缓存内存大小
//		builder.memoryCache(new UsingFreqLimitedMemoryCache(sizeLimit))
		
		//设置内存缓存策略        最早被添加的对象会被删除     HashMap  参数一:MemoryCache对象   参数二:缓存失效的最大时间
//		builder.memoryCache(new LimitedAgeMemoryCache(cache, maxAge));
		
		
		//初始化imageLoader配置
		imageLoader.init(builder.build());
		
		
		
	/*	
		使用默认的缓存策略
		ImageLoader imageLoader2=ImageLoader.getInstance();
		ImageLoaderConfiguration.Builder configuration=new ImageLoaderConfiguration.Builder(this);
		imageLoader2.init(configuration.build());
		*/
		
	
	}

}
