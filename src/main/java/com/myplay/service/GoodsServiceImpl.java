package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.GoodsMapper;
import com.myplay.model.Goods;

@Service
public class GoodsServiceImpl implements IGoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<Goods> selectAll() {
		// TODO Auto-generated method stub
		return goodsMapper.selectAll();
	}

	@Override
	public Goods selectById(Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Goods> selectAllGoodsByType(Integer typeId) {		
		return goodsMapper.selectAllGoodsByType(typeId);
	}


}
