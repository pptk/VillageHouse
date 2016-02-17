package com.example.threefragment;

import com.example.villagehouse.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class HouseKeepingActivity extends Activity {

	public WebView webview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.housekeeping_activity);

		webview = (WebView) findViewById(R.id.webView);
		webview.loadUrl("http://www.iojoo.com/product/productsearch.html?key=%C4%CF%B2%FD");
		webview.getSettings().setBuiltInZoomControls(true);

	}
}
