package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.OrderMapper;
import com.myplay.model.Order;
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Override
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		orderMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void insert(Order order) {
		// TODO Auto-generated method stub
		orderMapper.insert(order);
	}

	@Override
	public Order selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> selectOrders() {
		// TODO Auto-generated method stub
		return orderMapper.selectAll();
	}

	

}
