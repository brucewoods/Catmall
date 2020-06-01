package com.hongchai.catmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.catmall.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 10:13:18
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

