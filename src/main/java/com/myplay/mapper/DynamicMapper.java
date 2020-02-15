package com.myplay.mapper;

import com.myplay.model.Dynamic;
import com.myplay.model.MyDynamicComment;
import com.myplay.model.dynamicAndUser;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DynamicMapper {
	//根据userid查找用户的动态
	List<Dynamic> selectDynamicByUserId(Integer uid);
	//删除我的动态
    int deleteDynamicById(Integer id);
    //查找我的动态的评论
    List<MyDynamicComment> selectMyDynamicComment(Integer did);
    int insert(Dynamic record);
    //通过did查找动态
    Dynamic selectDynamicById(Integer id);

    List<Dynamic> selectAll();

    int updateByPrimaryKey(Dynamic record);

	List<dynamicAndUser> selectdynamibycuser(Integer fromid);
}