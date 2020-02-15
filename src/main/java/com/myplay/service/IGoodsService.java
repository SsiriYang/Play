package com.myplay.service;

import java.util.List;

import com.myplay.model.Goods;

public interface IGoodsService {
	 public List<Goods> selectAll();
	 public Goods selectById(Integer id);
	 
	 public List<Goods> selectAllGoodsByType(Integer typeId);
}
