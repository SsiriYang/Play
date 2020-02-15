package com.myplay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myplay.model.MyCollection;
import com.myplay.model.Video;
import com.myplay.model.VideoAuthor;
@Mapper
public interface VideoMapper {
	List<Video> selectAll();
	List<Video> selectAllDesc();
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    Video selectByPrimaryKey(Integer id);

    List<Video> selectAllVideo();
    
    List<Video> selectVideosByUid(Integer userid);//根据用户查找所以视频
    //查询用户的所有收藏
    List<MyCollection>selectMyCollection(@Param("vid")Integer vid,@Param("aid")Integer aid, @Param("uid")Integer uid);
    
    int updateByPrimaryKey(Video record);
    
	List<Video> selectVideosByType(Integer categoryid);

	List<VideoAuthor> recommend(Integer cid);
	
    //审核通过
    int updateforpass(Integer videoid);
    //审核退回
    int updatefornotpass(Integer videoid);
    
    //模糊查询
    List<Video> searchByWord(String searchword);
    //类型查询
    List<Video> searchByCategory(Integer categoryid);
    //推荐查询
	Video selectByVideoId(Long itemID);

	void updateVideoCount(int id);

	void updateVideoCollectionnum(Integer vid);

	 List<Video>  selectByvivwcount();
}