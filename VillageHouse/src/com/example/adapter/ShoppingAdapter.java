package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.model.ShoppingModel;
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

public class ShoppingAdapter extends BaseAdapter{
    
	private Context context;
	private List<ShoppingModel> list = new ArrayList<ShoppingModel>();
	public BitmapUtils bitmapUtils;
	
	public ShoppingAdapter(Context context, List<ShoppingModel> list) {
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
		//加载布局管理器
		ShoppingModel model = list.get(position);
		
			//将xml布局转换为view对象
			convertView = inflater.inflate(R.layout.shoppinglist_item,
					null);
			//利用view对象，找到布局中的组件
	  
			ImageView picture = (ImageView)convertView.findViewById(R.id.shopping_image);
		    TextView name=(TextView) convertView.findViewById(R.id.shopping_name);
		    TextView phone=(TextView) convertView.findViewById(R.id.shopping_phone);
		    TextView price=(TextView) convertView.findViewById(R.id.shopping_price);
		    
			name.setText(model.getSname());
			phone.setText(model.getSphone());
			price.setText(model.getSprice());
			Log.i("url",model.getSimage());
			bitmapUtils.display(picture,model.getSimage());
			return convertView;
		}
	
}
