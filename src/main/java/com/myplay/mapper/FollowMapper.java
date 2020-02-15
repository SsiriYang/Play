package com.myplay.mapper;

import com.myplay.model.Follow;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FollowMapper {
	Integer deleteByPrimaryKey(Integer id);

    Integer insert(Follow record);

    List<Follow> selectMyFollow(Integer from_uid);//根据用户id查找所有的关注 
    
    Follow selectByPrimaryKey(Integer id);

    List<Follow> selectAll();

    Integer updateByPrimaryKey(Follow record);

    Integer loadFollow(@Param("toUid") Integer toUid,@Param("fromUid") Integer fromUid);

	Integer deleteFollow(Follow follow);//删除关注
}