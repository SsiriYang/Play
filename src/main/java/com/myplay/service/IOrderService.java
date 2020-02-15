package com.myplay.service;

import java.util.List;

import com.myplay.model.Order;


public interface IOrderService {

    void insert(Order order);
    
	void deleteByPrimaryKey(Integer id);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectOrders();

	
}
