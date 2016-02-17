package com.example.villagehouse;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.FragmentAdapter;
import com.example.oneactivity.Bill;
import com.example.oneactivity.HeadLinear;
import com.example.oneactivity.News;
import com.example.oneactivity.Numbers;
import com.example.threefragment.SuggestActivity;
import com.example.view.MyViewPager;
import com.example.view.SlidingPaneLayout;
import com.example.view.SlidingPaneLayout.PanelSlideListener;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private MyViewPager mainViewPager;
	private TextView suggestTextView;
	private List<Fragment> fragmentList;
	private SlidingPaneLayout slidingPanelayout;
	private FragmentOne fragmentone;// my house inside 医疗/安保
	private FragmentTwo fragmenttwo;// my village inside 物业、服务
	private FragmentThree fragmentthree;// around inside 购物
	// bottom
	private ImageView myHouseImageView, villageImageView, aroundImageView,
			headImageView;
	private TextView myHouseTextView, villageTextView, aroundTextView,
			nameTextView;
	private LinearLayout myHouseLinearLayout, villageLinearLayout,
			aroundLinearLayout;
	private LinearLayout headLinearLayout, setLinearLayout;
	private RelativeLayout news, bill, numbers;
	private String headUrl, name;
	public BitmapUtils bitmapUtils = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences sp = getSharedPreferences("user_tab",
				Context.MODE_PRIVATE);
		name = sp.getString("name", "");
		headUrl = sp.getString("head", "");
		Log.i(">>>>>>>>", headUrl);
		initView();

	}

	/**
	 * all initView mainView init
	 */
	public void initView() {
		// FindView
		mainViewPager = (MyViewPager) findViewById(R.id.viewpager);
		suggestTextView = (TextView) findViewById(R.id.suggestTextView);
		suggestTextView.setOnClickListener(this);
		slidingPanelayout = (SlidingPaneLayout) findViewById(R.id.pane);
		myHouseImageView = (ImageView) findViewById(R.id.myHouseImageView);
		villageImageView = (ImageView) findViewById(R.id.villageImageView);
		aroundImageView = (ImageView) findViewById(R.id.aroundImageView);
		myHouseTextView = (TextView) findViewById(R.id.myHouseTextView);
		villageTextView = (TextView) findViewById(R.id.villageTextView);
		aroundTextView = (TextView) findViewById(R.id.aroundTextView);
		myHouseLinearLayout = (LinearLayout) findViewById(R.id.myHouseLinearLayout);
		villageLinearLayout = (LinearLayout) findViewById(R.id.villageLinearLayout);
		aroundLinearLayout = (LinearLayout) findViewById(R.id.aroundLinearLayout);
		initViewLeft();// initView left
		initViewVp();// initView ViewPager
		// bottom onclick Listener
		myHouseLinearLayout.setOnClickListener(new myBottomOnClickListener(0));
		villageLinearLayout.setOnClickListener(new myBottomOnClickListener(1));
		aroundLinearLayout.setOnClickListener(new myBottomOnClickListener(2));
		// slidingPanelayout onclicklistener
		slidingPanelayout.setPanelSlideListener(new myPanelSlideListener());
	}

	// initView Left
	public void initViewLeft() {
		bitmapUtils = new BitmapUtils(this);
		nameTextView = (TextView) findViewById(R.id.nameTextView);
		headImageView = (ImageView) findViewById(R.id.headImageView);
		headLinearLayout = (LinearLayout) findViewById(R.id.headLinearLayout);
		news = (RelativeLayout) findViewById(R.id.news);
		bill = (RelativeLayout) findViewById(R.id.bill);
		numbers = (RelativeLayout) findViewById(R.id.numbers);
//		setLinearLayout = (LinearLayout) findViewById(R.id.setLinearLayout);
		nameTextView.setText(name);
		bitmapUtils.display(headImageView, headUrl);
//		setLinearLayout.setOnClickListener(this);
		headLinearLayout.setOnClickListener(this);
		news.setOnClickListener(this);
		bill.setOnClickListener(this);
		numbers.setOnClickListener(this);

	}

	// ViewPager初始化
	public void initViewVp() {
		fragmentList = new ArrayList<Fragment>();
		fragmentone = new FragmentOne();// my house inside 医疗/安保
		fragmenttwo = new FragmentTwo();// my village inside 物业、服务
		fragmentthree = new FragmentThree();// around inside 购物

		fragmentList.add(fragmentone);
		fragmentList.add(fragmenttwo);
		fragmentList.add(fragmentthree);

		mainViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
				fragmentList, this));// set FragmentAdapter
		mainViewPager.setOnPageChangeListener(new MyOnPageChangeListener());// set
																			// pagechangeListener
	}

	// onclick
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.suggestTextView:
			Intent intent = new Intent(this, SuggestActivity.class);
			startActivity(intent);
			break;
		// head
		case R.id.headLinearLayout:
			Intent intent1 = new Intent(this, HeadLinear.class);
			startActivity(intent1);
			break;
		// news
		case R.id.news:
			Intent intent2 = new Intent(this, News.class);
			startActivity(intent2);
			break;
		// bill
		case R.id.bill:
			Intent intent3 = new Intent(this, Bill.class);
			startActivity(intent3);
			break;
		// numbers
		case R.id.numbers:
			Intent intent4 = new Intent(this, Numbers.class);
			startActivity(intent4);
			break;
		
		}

	}

	/**
	 * 三个内部类、都是实现接口、方便调用。
	 * 
	 * @author Administrator
	 * 
	 */
	// set PanelSlideListener in inner class
	class myPanelSlideListener implements PanelSlideListener {

		@Override
		public void onPanelSlide(View panel, float slideOffset) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPanelOpened(View panel) {
			// TODO Auto-generated method stub
			mainViewPager.setNoScroll(true);
			fragmentone.stopNotice();
		}

		@Override
		public void onPanelClosed(View panel) {
			// TODO Auto-generated method
			mainViewPager.setNoScroll(false);
			fragmentone.startNotice();
		}

	}

	// set OnPageChangeListener in inner class
	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// last
			switch (arg0) {
			case 0:
				myHouseImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.in_me));
				myHouseTextView.setTextColor(0xff18b4ed);
				villageTextView.setTextColor(0xffa09b9b);
				villageImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.house));
				aroundTextView.setTextColor(0xffa09b9b);
				aroundImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.around));
				break;
			case 1:
				villageImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.in_house));
				villageTextView.setTextColor(0xff18b4ed);

				myHouseTextView.setTextColor(0xffa09b9b);
				aroundTextView.setTextColor(0xffa09b9b);

				aroundImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.around));
				myHouseImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.me));
				break;
			case 2:
				aroundImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.in_around));
				aroundTextView.setTextColor(0xff18b4ed);

				myHouseTextView.setTextColor(0xffa09b9b);
				myHouseImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.me));
				villageTextView.setTextColor(0xffa09b9b);
				villageImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.house));
				break;
			}
		}

	}

	// set onclicklistener in myBottomOnClickListener inner
	// change viewpager by bottom with setCurrentItem(index)
	class myBottomOnClickListener implements OnClickListener {
		private int index = 0;

		myBottomOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mainViewPager.setCurrentItem(index);
		}

	}

}
