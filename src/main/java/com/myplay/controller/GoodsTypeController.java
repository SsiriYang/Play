package com.myplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.model.GoodsType;
import com.myplay.service.IGoodsTypeService;

@CrossOrigin
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
	@Autowired
	private IGoodsTypeService goodsType;
	
	@GetMapping("/selectType")
	public List<GoodsType> selectType(){
		return goodsType.selectType();
	}
	
	@GetMapping("/selectTypeById")
	public GoodsType selectByPrimaryKey(Integer id){
		return goodsType.selectByPrimaryKey(id);
	}

}
