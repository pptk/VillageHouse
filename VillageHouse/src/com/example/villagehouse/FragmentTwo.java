package com.example.villagehouse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.threefragment.ConvenienceActivity;
import com.example.threefragment.HouseKeepingActivity;
import com.example.threefragment.SecondHandActivity;
import com.example.tools.Weather;
import com.lidroid.xutils.BitmapUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This inside С��֪ͨ���ɷѡ�������񡢼������񡢶��ֽ��ף���ҵ������
 * 
 * @author pxh
 */
public class FragmentTwo extends Fragment {
	private ImageView weather_img;
	private TextView weather_;
	private Handler handler;
	private String now, temperature;
	private BitmapUtils bitmapUtils;
	private LinearLayout ershou,bianmin,jiazheng;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("FragmentTwo", "����FragmentTwo");
		return inflater.inflate(R.layout.fragment_2, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		initWeather();//weather

	}

	public void initView() {
		weather_img = (ImageView) getView().findViewById(R.id.weather_img);
		weather_ = (TextView) getView().findViewById(R.id.weather_);
		ershou=(LinearLayout) getView().findViewById(R.id.ershoujiaoyi);
		bianmin=(LinearLayout) getView().findViewById(R.id.bianminfuwu);
		jiazheng=(LinearLayout)getView().findViewById(R.id.jiazhengfuwu);
		
		MyOnClickListener mOnClickListener=new MyOnClickListener();
		ershou.setOnClickListener(mOnClickListener);
		bianmin.setOnClickListener(mOnClickListener);
		jiazheng.setOnClickListener(mOnClickListener);
		
	}
	
	private class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int mID=v.getId();
			switch(mID){
			case R.id.ershoujiaoyi:
				Intent intent=new Intent(getActivity(),SecondHandActivity.class);
				startActivity(intent);
				break;
			case R.id.bianminfuwu:
				Intent intent1=new Intent(getActivity(),ConvenienceActivity.class);
				startActivity(intent1);
				break;
			case R.id.jiazhengfuwu:
				Intent intent2=new Intent(getActivity(),HouseKeepingActivity.class);
				startActivity(intent2);
				break;
			}
			
		}
		
		
		
	}

	public void initWeather() {
		bitmapUtils = new BitmapUtils(getActivity());
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String result = Weather.send("�ϲ�");
				Message m = handler.obtainMessage(0, result);
				handler.sendMessage(m);
			}
		}).start();
		handler = new Handler() {
			public void handleMessage(Message msg) {
				String s = (String) msg.obj;
				WeatherJxJson(s);//����
				bitmapUtils.display(weather_img, now);
				weather_.setText(temperature);
				super.handleMessage(msg);
			}
		};
	}

	// Analytical Weather
	public void WeatherJxJson(String result) {
		try {
			JSONObject datajson = new JSONObject(result);// ��һ������string��ʽת����json��ʽ
			JSONArray results = datajson.getJSONArray("results");// ��ȡresults����
			JSONObject city = results.getJSONObject(0);
			JSONArray weather_data = city.getJSONArray("weather_data");
			JSONObject today = weather_data.getJSONObject(0);
			now = today.getString("dayPictureUrl");
			temperature = today.getString("temperature");// �¶�
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
