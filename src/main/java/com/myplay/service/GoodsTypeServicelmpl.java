package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.GoodsTypeMapper;
import com.myplay.model.GoodsType;

@Service
public class GoodsTypeServicelmpl implements IGoodsTypeService{
	@Autowired
	private GoodsTypeMapper goodsType;
	
	@Override
	public List<GoodsType> selectType(){
		return goodsType.selectType();
	}

	@Override
	public GoodsType selectByPrimaryKey(Integer id) {
		return goodsType.selectByPrimaryKey(id);
	}
	

}
