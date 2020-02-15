package com.myplay.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myplay.model.Collection;
import com.myplay.model.Dynamic;
import com.myplay.model.DynamicComment;
import com.myplay.model.Follow;
import com.myplay.model.MyDynamicComment;
import com.myplay.model.User;

public interface IPersonalCenterService {
	//根据userid查找用户的动态
		List<Dynamic> selectDynamicByUserId(Integer uid);
		//删除我的动态
		int deleteDynamicById(Integer id);
		 //通过did查找动态
	    Dynamic selectDynamicById(Integer id);
	  //查找我的动态的评论
	    List<MyDynamicComment> selectMyDynamicComment(Integer did);
	  //删除动态评论
	    int deleteDynamicComment(Integer id);

	    // 根据did查找我的动态详情评论 
	    List<DynamicComment> selectCommentById(Integer did);
	    
	    User selectByPrimaryKey(Integer id);

	    List<User> selectAll();
	    	
	    List<Follow> selectMyFollow(Integer from_uid);//根据用户id查找所有的关注
	    
	    Integer deleteFollow(Follow follow);//删除关注
	    
	    int updateByPrimaryKey(User record);
	    
	    Integer deleteByPrimaryKey(Integer id);//删除关注
		
	    
	    
	    
	    
}
