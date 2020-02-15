package com.myplay.service;

import java.util.List;

import com.myplay.model.Category;
import com.myplay.model.Video;


public interface ICategoryService {
	List<Category> selectAll();

	List<Video> selectVideosByType(Integer categoryid);

	Video selectByPrimaryKey(Integer itemID);

	List<Video> selectAllVideo();

	List<Video> selectByvivwcount();

}
