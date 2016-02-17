package com.example.tools;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsynctaskImageLoad {
	/**
	 * 图片缓存对象
	 * url对应softReference管理的图片对象。
	 */
	private Map<String,SoftReference<Drawable>> imageCache = 
			new HashMap<String,SoftReference<Drawable>>();
	/**
	 * 实现图片异步加载
	 */
	public Drawable loadDrawable(final String imageUrl,final ImageCallback callback ){
		if(imageCache.containsKey(imageUrl)){
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			if(softReference.get() != null){
				return softReference.get();//不为空、返回soft指向的图片对象
			}
		}
		final Handler handler = new Handler(){
			public void handleMessage(Message msg) {
				callback.imageLoaded((Drawable) msg.obj);
			};
		};
		new Thread(){
			public void run(){
				Drawable drawable = loadImageFromUrl(imageUrl);//用来下载一张图片、
				imageCache.put(imageUrl,new SoftReference<Drawable>(drawable));//放到缓存。
				Message message = handler.obtainMessage(0,drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}
	
	/**
	 * 图片下载方法。
	 * @param imageUrl
	 * @return
	 */
	protected Drawable loadImageFromUrl(String imageUrl){
		try{
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");//返回一个图片流
		}catch (Exception e) {
			throw new RuntimeException();//抛出执行时间。
		}
	}
	/**
	 * 回调方法
	 * @author Administrator
	 */
	public interface ImageCallback{
		public void imageLoaded(Drawable imageDrawable);
	}
}
