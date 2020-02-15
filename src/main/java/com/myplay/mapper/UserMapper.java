package com.myplay.mapper;

import com.myplay.model.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    
    public User login(User record);
    
    int phoneExit(User record);
}