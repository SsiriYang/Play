package com.myplay.mapper;

import com.myplay.model.GoodsType;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsType record);

    GoodsType selectByPrimaryKey(Integer id);

    List<GoodsType> selectType();

    int updateByPrimaryKey(GoodsType record);
    
    List<GoodsType> selectAll();
}