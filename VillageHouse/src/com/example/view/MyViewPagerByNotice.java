package com.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.oneactivity.NoticeFirstActivity;
import com.example.villagehouse.MainActivity;
import com.example.villagehouse.R;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

/**
 * notice with viewpager
 * 
 * @author pxh
 * 
 */
public class MyViewPagerByNotice extends FrameLayout implements
		View.OnClickListener {
	private Context mcontext;
	private View v;
	private ViewPager viewpager;// Vp
	private int currentItem = 0;
	private ImageView dos_1, dos_2, dos_3, dos_4, dos_5;// change imageview
	private int[] image;
	private List<ImageView> list;
	private ScheduledExecutorService scheduledExecutorService;// 定时操作
	private String[] str_title;
	private TextView title;

	public MyViewPagerByNotice(Context context) {
		super(context);
	}

	public MyViewPagerByNotice(Context context, AttributeSet attrs) {
		super(context, attrs);
		mcontext = context;
		
		LayoutInflater inflater = LayoutInflater.from(context);
		v = inflater.inflate(R.layout.notice, null);
		addView(v);
		//给内容赋值
		image = new int[] { R.drawable.notice1, R.drawable.notice2,
				R.drawable.notice3, R.drawable.notice4, R.drawable.notice5 };
		str_title = new String[] { "", "", "",
				"", "" };
		list = new ArrayList<ImageView>();
		for (int i = 0; i < 5; i++) {
			ImageView imageview = new ImageView(context);
			imageview.setImageResource(image[i]);
			imageview.setScaleType(ScaleType.FIT_XY);
			list.add(imageview);
			list.get(i).setOnClickListener(this);
		}
		initView();
	}

	public MyViewPagerByNotice(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void initView() {
		viewpager = (ViewPager) v.findViewById(R.id.viewpager);
		dos_1 = (ImageView) v.findViewById(R.id.dos_1);
		dos_2 = (ImageView) v.findViewById(R.id.dos_2);
		dos_3 = (ImageView) v.findViewById(R.id.dos_3);
		dos_4 = (ImageView) v.findViewById(R.id.dos_4);
		dos_5 = (ImageView) v.findViewById(R.id.dos_5);

		viewpager.setAdapter(new MyAdapter());
		viewpager.setOnPageChangeListener(new MyOnPageChangeListener());

		title = (TextView) v.findViewById(R.id.title);// find title
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewpager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewpager) {// 线程锁、确保同时只运行一个
				currentItem = (currentItem + 1) % list.size();// 使得currentItem重复
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	// changeListener
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
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0:
				title.setText(str_title[arg0]);
				dos_1.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_normal));
				dos_2.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_3.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_4.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_5.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				break;
			case 1:
				title.setText(str_title[arg0]);
				dos_1.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_2.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_normal));
				dos_3.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_4.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_5.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				break;
			case 2:
				title.setText(str_title[arg0]);
				dos_1.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_2.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_3.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_normal));
				dos_4.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_5.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				break;
			case 3:
				title.setText(str_title[arg0]);
				dos_1.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_2.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_3.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_4.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_normal));
				dos_5.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				break;
			case 4:
				title.setText(str_title[arg0]);
				dos_1.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_2.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_3.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_4.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_focused));
				dos_5.setImageDrawable(getResources().getDrawable(
						R.drawable.dot_normal));
				break;

			}
		}

	}

	// pageAdapter
	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}

	}

	//if start
	public void StartCurrent() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 5, 6,
				TimeUnit.SECONDS);
	}

	//if stop
	public void StopCurrent() {
		scheduledExecutorService.shutdown();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (currentItem) {
		case 0:
			Intent intent = new Intent(mcontext, NoticeFirstActivity.class);
			mcontext.startActivity(intent);
			break;
		case 1:
			Toast.makeText(mcontext, "点击了图片2", 1000).show();
			break;
		case 2:
			Toast.makeText(mcontext, "点击了图片3", 1000).show();
			break;
		case 3:
			Toast.makeText(mcontext, "点击了图片4", 1000).show();
			break;
		case 4:
			Toast.makeText(mcontext, "点击了图片5", 1000).show();
			break;
		default:
			break;
		}
	}
}
