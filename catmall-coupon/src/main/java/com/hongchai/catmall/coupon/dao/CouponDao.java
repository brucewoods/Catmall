package com.hongchai.catmall.coupon.dao;

import com.hongchai.catmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 13:03:30
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
