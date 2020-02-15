package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.CategoryMapper;
import com.myplay.mapper.CollectionMapper;
import com.myplay.mapper.VideoMapper;
import com.myplay.model.Category;
import com.myplay.model.Collection;
import com.myplay.model.MyCollection;
import com.myplay.model.Video;
@Service
public class VideoServiceImpl implements IVideoService{
	
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	
	
	@Override
	//删除视频
	public int deleteByPrimaryKey(Integer id) {	
		return videoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Video record) {
		return videoMapper.insert(record);
	}

	@Override
	public Video selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return videoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Video record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Video> selectAllVideo() {
		// TODO Auto-generated method stub
		return null;
	}
	//查找所以视频类型
	@Override
	public List<Category> selectAllCategory() {
		return categoryMapper.selectAll();
	}
	@Override
	public List<Video> selectVideosByUid(Integer userid) {
		
		return videoMapper.selectVideosByUid(userid);
	}
	//查询用户所以收藏
	@Override
	public List<MyCollection> selectMyCollection(Integer vid,Integer aid,Integer uid) {
		return videoMapper.selectMyCollection(vid, aid,uid);
	}

	@Override
	public List<Collection> selectUserCollection(Integer userid) {
		// TODO Auto-generated method stub
		return collectionMapper.selectByUserId(userid);
	}
	//删除收藏
	@Override
	public int deleteCollectionById(Integer id) {
		// TODO Auto-generated method stub
		return collectionMapper.deleteCollectionById(id);
	}
	
	
	
	

}
