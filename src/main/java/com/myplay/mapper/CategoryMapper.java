package com.myplay.mapper;

import com.myplay.model.Category;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);
}