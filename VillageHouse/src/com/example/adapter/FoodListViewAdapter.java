package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.amap.api.location.b;
import com.example.model.FoodModel;

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

public class FoodListViewAdapter extends BaseAdapter {

	private Context context;
	private List<FoodModel> list = new ArrayList<FoodModel>();
	public BitmapUtils bitmapUtils;

	public FoodListViewAdapter(Context context, List<FoodModel> list) {
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
		FoodModel model = list.get(position);

		// 将xml布局转换为view对象
		convertView = inflater.inflate(R.layout.foodlist_item, null);
		// 利用view对象，找到布局中的组件

		ImageView picture = (ImageView) convertView
				.findViewById(R.id.food_image);
		TextView name = (TextView) convertView.findViewById(R.id.food_name);
		TextView jianjie = (TextView) convertView
				.findViewById(R.id.food_jianjie);
		TextView send = (TextView) convertView.findViewById(R.id.food_send);

		name.setText(model.getFname());
		jianjie.setText(model.getFprofile());
		send.setText(model.getFsend());
		// Bitmap bit = BitmapFactory.decodeByteArray(model.getImage(), 0,
		// model.getImage().length); //将图片流转化为bitmap类型
		// picture.setImageBitmap(bit);
		Log.i("url", model.getFimage());
		bitmapUtils.display(picture, model.getFimage());
		return convertView;
	}

}
