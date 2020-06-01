package com.hongchai.catmall.order.dao;

import com.hongchai.catmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 13:10:37
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
