package com.myplay.service;

import java.util.List;

import com.myplay.model.GoodsType;

public interface IGoodsTypeService {
	public List<GoodsType> selectType();
	public GoodsType selectByPrimaryKey(Integer id);

}
