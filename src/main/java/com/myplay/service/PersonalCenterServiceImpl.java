package com.myplay.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.CollectionMapper;
import com.myplay.mapper.DynamicCommentMapper;
import com.myplay.mapper.DynamicMapper;
import com.myplay.mapper.FollowMapper;
import com.myplay.mapper.UserMapper;
import com.myplay.model.Collection;
import com.myplay.model.Dynamic;
import com.myplay.model.DynamicComment;
import com.myplay.model.Follow;
import com.myplay.model.MyDynamicComment;
import com.myplay.model.User;
@Service
public class PersonalCenterServiceImpl implements IPersonalCenterService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	@Autowired
	private FollowMapper followMapper;
	@Autowired
	private DynamicMapper dynamicMapper;
	@Autowired
	private DynamicCommentMapper dynamicCommentMapper;
	@Override 
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	 
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(User record) {		
		return userMapper.updateByPrimaryKey(record);
	}

	//根据用户id查找所有的关注 
	@Override
	public List<Follow> selectMyFollow(Integer from_uid) {
		// TODO Auto-generated method stub
		return followMapper.selectMyFollow(from_uid);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteFollow(Follow follow) {
		// TODO Auto-generated method stub
		return followMapper.deleteFollow(follow);
	}
	
	
	//根据userid查找用户的动态
	@Override
	public List<Dynamic> selectDynamicByUserId(Integer uid) {
		// TODO Auto-generated method stub
		return dynamicMapper.selectDynamicByUserId(uid);
	}

	@Override
	public int deleteDynamicById(Integer id) {
		// TODO Auto-generated method stub
		return dynamicMapper.deleteDynamicById(id);
	}

	@Override
	public Dynamic selectDynamicById(Integer id) {
		// TODO Auto-generated method stub
		return dynamicMapper.selectDynamicById(id);
	}

    //查找我的动态的评论
	@Override
	public List<MyDynamicComment> selectMyDynamicComment(Integer did) {
		return dynamicMapper.selectMyDynamicComment(did);
	}
	// 根据did查找我的动态评论 
	@Override
	public List<DynamicComment> selectCommentById(Integer did) {
		// TODO Auto-generated method stub
		return dynamicCommentMapper.selectCommentById(did);
	}

	@Override
	public int deleteDynamicComment(Integer id) {
		// TODO Auto-generated method stub
		return dynamicCommentMapper.deleteDynamicComment(id);
	}

}
