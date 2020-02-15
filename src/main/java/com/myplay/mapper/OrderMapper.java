package com.myplay.mapper;

import com.myplay.model.Order;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OrderMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Order order);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);
}