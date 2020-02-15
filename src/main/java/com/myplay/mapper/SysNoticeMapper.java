package com.myplay.mapper;

import com.myplay.model.SysNotice;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNotice record);

    SysNotice selectByPrimaryKey(Integer id);

    List<SysNotice> selectAll();

    int updateByPrimaryKey(SysNotice record);
}