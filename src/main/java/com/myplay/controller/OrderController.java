package com.myplay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.model.Order;
import com.myplay.service.IOrderService;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService service;
	
	@GetMapping("/add")
	public void addOrder(Order order) {
		order.setId((int)((Math.random()*9+1)*1000));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式		
		order.setTime(df.format(new Date()));		
		service.insert(order);
	}
	
	@GetMapping("/selectAll")
	public List<Order> selectOrders(){
		return service.selectOrders();
	}
	
	@PostMapping("/selectById")
	public Order selectById(Integer id) {
		return service.selectByPrimaryKey(id);
	}
	
	@PostMapping("/delete")
	public void deleteOrder() {
		
	}
}
