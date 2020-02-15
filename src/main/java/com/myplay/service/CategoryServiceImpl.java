package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.CategoryMapper;
import com.myplay.mapper.VideoCommentMapper;
import com.myplay.mapper.VideoMapper;
import com.myplay.model.Category;
import com.myplay.model.Video;


@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private VideoMapper videoMapper;
	@Override
	public List<Category> selectAll() {		
		return categoryMapper.selectAll();
	}
	@Override
	public List<Video> selectVideosByType(Integer categoryid) {
		
		return videoMapper.selectVideosByType(categoryid);
	}
	@Override
	public Video selectByPrimaryKey(Integer itemID) {
		return videoMapper.selectByPrimaryKey(itemID);
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Video> selectAllVideo() {
		// TODO Auto-generated method stub
		return videoMapper.selectAll();
	}
	@Override
	public List<Video> selectByvivwcount() {
		// TODO Auto-generated method stub
		return videoMapper.selectByvivwcount();
	}
	
}
