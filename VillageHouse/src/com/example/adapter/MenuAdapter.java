package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.model.MenuModel;
import com.example.villagehouse.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter{
	private List<MenuModel> list;
	private Context context;
	private List<MenuModel> lists;
	public MenuAdapter(Context context,List<MenuModel> list)
	{
		super();
		this.list = list;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public List<MenuModel> getLists(){
		return lists;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int x = position;
		lists = new ArrayList<MenuModel>();
		if (convertView == null) {
			LayoutInflater iflater = LayoutInflater.from(context);
			convertView = iflater.inflate(R.layout.menu_item, null);
		}
		TextView mname = (TextView) convertView.findViewById(R.id.food_mennu_name);
		TextView mprice = (TextView) convertView.findViewById(R.id.food_number);
		ImageView add_btn = (ImageView) convertView.findViewById(R.id.food_touch);
//        TextView touch=(TextView)convertView.findViewById(R.id.food_touch);
//        TextView price=(TextView)convertView.findViewById(R.id.food_price);
//        TextView fuhao=(TextView)convertView.findViewById(R.id.food_fuhao);
		add_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MenuModel model = new MenuModel();
				model.setMname(list.get(x).getMname());
				model.setMprice(list.get(x).getMprice());
				lists.add(model);
			}
		});
		mname.setText(list.get(position).getMname());
		mprice.setText(list.get(position).getMprice());
		return convertView;
	}

}
