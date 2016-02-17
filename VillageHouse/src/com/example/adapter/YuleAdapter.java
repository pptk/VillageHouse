package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.model.YuleModel;
import com.example.tools.AsynctaskImageLoad;
import com.example.tools.CallbackImpl;
import com.example.villagehouse.R;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YuleAdapter extends BaseAdapter {

	private Context context;
	private List<YuleModel> list = new ArrayList<YuleModel>();
	public BitmapUtils bitmapUtils;

	public YuleAdapter(Context context, List<YuleModel> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		bitmapUtils = new BitmapUtils(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		// 加载布局管理器
		YuleModel model = list.get(position);

		// 将xml布局转换为view对象
		convertView = inflater.inflate(R.layout.yulelist_item, null);
		// 利用view对象，找到布局中的组件

		ImageView picture = (ImageView) convertView
				.findViewById(R.id.yule_image);
		TextView name = (TextView) convertView.findViewById(R.id.yule_name);
		TextView profile = (TextView) convertView
				.findViewById(R.id.yule_jianjie);

		name.setText(model.getYtitle());
		profile.setText(model.getYprofile());
		Log.i("url", model.getYimage());
		bitmapUtils.display(picture, model.getYimage());
		return convertView;
	}
}
