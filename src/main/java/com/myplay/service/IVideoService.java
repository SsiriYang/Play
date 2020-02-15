package com.myplay.service;

import java.util.List;

import com.myplay.model.Category;
import com.myplay.model.Collection;
import com.myplay.model.MyCollection;
import com.myplay.model.Video;

public interface IVideoService {
	int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    Video selectByPrimaryKey(Integer id);

    List<Video> selectAllVideo();
    
    List<Video> selectVideosByUid(Integer userid);//查找该用户所以视频
    
    //查询用户的所有收藏的视频和作者
    List<MyCollection>selectMyCollection(Integer vid,Integer vuid, Integer uid);
    //查找给用户的所有收藏集合
    List<Collection> selectUserCollection (Integer userid);
    //删除收藏
    int deleteCollectionById(Integer id);
    
    int updateByPrimaryKey(Video record);
    
    List<Category> selectAllCategory();//找类型
}
