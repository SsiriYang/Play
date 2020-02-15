package com.myplay.mapper;

import com.myplay.model.Admin;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}