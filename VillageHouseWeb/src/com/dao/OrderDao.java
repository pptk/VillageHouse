package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.model.OrderModel;

public interface OrderDao {

	Serializable add_order(OrderModel order);

	ArrayList<OrderModel> getOrderList(String name);

	String order_deal(String username, String shopname, String ordermenu,
			String telphone, String ordertime); // —È÷§
}
