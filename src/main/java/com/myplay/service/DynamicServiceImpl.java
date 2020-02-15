package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.DynamicMapper;
import com.myplay.model.Dynamic;
import com.myplay.model.dynamicAndUser;

@Service
public class DynamicServiceImpl implements IDynamicService{
	@Autowired
	DynamicMapper dynamicMapper;
	
	@Override
	public void insert(Dynamic dynamic) {
		// TODO Auto-generated method stub
		dynamicMapper.insert(dynamic);
	}
	//根据你关注的人来查找动态
	@Override
	public List<dynamicAndUser> selectdynamibycuser(Integer fromid) {
		// TODO Auto-generated method stub
		return dynamicMapper.selectdynamibycuser(fromid);
	}

	
}
