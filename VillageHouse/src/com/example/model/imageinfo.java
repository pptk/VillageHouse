package com.example.model;

import android.net.Uri;

public class imageinfo {
	private Uri imgUri;

	public imageinfo() {
		super();
	}

	public imageinfo(Uri imgUri) {
		super();
		this.imgUri = imgUri;
	}

	public Uri getImgUri() {
		return imgUri;
	}

	public void setImgUri(Uri imgUri) {
		this.imgUri = imgUri;
	}
	
}
