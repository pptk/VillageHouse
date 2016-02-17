package com.example.threefragment;

import com.example.tools.AsynctaskImageLoad;
import com.example.tools.CallbackImpl;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


public class BigImageActivity2 extends Activity{
   
	public ImageView image;
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	public String url1,url2,url3;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.big_view);
		image=(ImageView)findViewById(R.id.big_img);
		Intent intent = getIntent();//ªÒ»°intent
		String log=intent.getStringExtra("log");
		url1= intent.getStringExtra("s_image1");
		url2= intent.getStringExtra("s_image2");
		url3= intent.getStringExtra("s_image3");
		Log.i("liog",log);
		if("one".equals(log))
		{
			Log.i("image1",url1);
	       loadImage(url1,image);
		}
        if("two".equals(log))
		{
			loadImage(url2,image);
		}
        if("three".equals(log))
        {
			loadImage(url3,image);
		}
     }
	
	private void loadImage(String url,ImageView img){
		CallbackImpl callbackImpl = new CallbackImpl(img);
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);
		if(cacheImage != null){
			img.setImageDrawable(cacheImage);
		}
	}
}
