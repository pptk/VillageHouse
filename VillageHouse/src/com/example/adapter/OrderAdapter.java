package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amap.api.location.c;
import com.example.model.OrderModel;
import com.example.model.orderMenuModel;
import com.example.villagehouse.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderAdapter extends BaseAdapter {
	private Context context;
	private List<OrderModel> list = new ArrayList<OrderModel>();
	private List<orderMenuModel> menulist;

	public OrderAdapter(Context context, List<OrderModel> list) {
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
		menulist = new ArrayList<orderMenuModel>();
		LayoutInflater inflater = LayoutInflater.from(context);
		// 加载布局管理器
		OrderModel model = list.get(position);
		float all = 0;
		// 将xml布局转换为view对象
		convertView = inflater.inflate(R.layout.order_item, null);
		// 利用view对象，找到布局中的组件
		ListView orderMenuListView = (ListView) convertView
				.findViewById(R.id.orderMenuListView);// listView
		String x = list.get(position).getOrderMenu();// 获取orderMenu
		// 解析出来
		menulist = jxOrderMenu(x);
		// 适配到orderMenuListView中
		List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < menulist.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("foodName", menulist.get(i).getFoodName());
			map.put("foodPrice", menulist.get(i).getFoodPrice());
			Log.i("78  FoodName", menulist.get(i).getFoodName());
			listitems.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(context, listitems,
				R.layout.order_ok_item,
				new String[] { "foodName", "foodPrice" }, new int[] {
						R.id.foodNameTextView, R.id.foodPriceTextView });

		// 动态调整listview大小。 ---------由于嵌套ListView会导致无法计算大小。所以需要自己计算。
		ViewGroup.LayoutParams params = orderMenuListView.getLayoutParams();
		params.height = 50 * (menulist.size() + 1);
		orderMenuListView.setLayoutParams(params);
		orderMenuListView.setAdapter(adapter);
		for (int i = 0; i < menulist.size(); i++) {
			Float count = Float.valueOf(menulist.get(i).getFoodPrice());
			all += count;
		}
		TextView addPriceTextView = (TextView) convertView
				.findViewById(R.id.addPriceTextView);// 共计
		TextView shopName = (TextView) convertView.findViewById(R.id.shopName);
		TextView orderTime = (TextView) convertView
				.findViewById(R.id.orderTime);
		shopName.setText(model.getShopName());// 店名字
		orderTime.setText(model.getOrderTime());// 时间
		addPriceTextView.setText(String.valueOf(all));
		return convertView;
	}

	public List<orderMenuModel> jxOrderMenu(String str) {
		List<orderMenuModel> list = new ArrayList<orderMenuModel>();
		if (str.equals("")) {
			Toast.makeText(context, "orderMenu null", Toast.LENGTH_LONG).show();
		} else {
			try {
				JSONArray jsy = new JSONArray(str);
				for (int i = 0; i < jsy.length(); i++) {
					JSONObject job = jsy.getJSONObject(i);
					String foodName = job.getString("mname");
					String foodPrice = job.getString("mprice");
					orderMenuModel model = new orderMenuModel(foodName,
							foodPrice);
					list.add(model);
				}
				return list;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}