package com.hongchai.catmall.coupon.dao;

import com.hongchai.catmall.coupon.entity.HomeSubjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 * 
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 13:03:30
 */
@Mapper
public interface HomeSubjectDao extends BaseMapper<HomeSubjectEntity> {
	
}
