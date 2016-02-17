package com.example.ip;

import com.lidroid.xutils.BitmapUtils;

import android.app.Activity;
import android.widget.ImageView;

public class getImageForUrl {
	public static BitmapUtils bitmap;

	public static void getImage(ImageView iv, String url, Activity activity) {
		bitmap = new BitmapUtils(activity);
		bitmap.display(iv, url);
	}
}
