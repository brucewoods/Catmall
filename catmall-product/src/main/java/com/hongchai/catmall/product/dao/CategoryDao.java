package com.hongchai.catmall.product.dao;

import com.hongchai.catmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 10:13:18
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
