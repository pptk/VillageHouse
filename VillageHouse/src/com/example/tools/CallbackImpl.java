package com.example.tools;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CallbackImpl implements AsynctaskImageLoad.ImageCallback{
	private ImageView imageView;
	public CallbackImpl (ImageView imageView){
		super();
		this.imageView = imageView;
	}
	@Override
	public void imageLoaded(Drawable imageDrawable) {
		// TODO Auto-generated method stub
		imageView.setImageDrawable(imageDrawable);
	}

}
