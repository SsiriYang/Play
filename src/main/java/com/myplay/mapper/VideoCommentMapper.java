package com.myplay.mapper;

import com.myplay.model.UserComment;
import com.myplay.model.VideoComment;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VideoCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoComment record);

    VideoComment selectByPrimaryKey(Integer id);

    List<UserComment> selectAll();

    int updateByPrimaryKey(VideoComment record);

	List<UserComment> selectNewComment(Integer vid);

	List<UserComment> getMyComment(@Param("id")int id,@Param("vid") Integer vid);

	List<UserComment> selectAllCommentsByVid(Integer vid);
}