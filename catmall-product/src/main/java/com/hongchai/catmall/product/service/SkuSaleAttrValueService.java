package com.hongchai.catmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.catmall.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 10:13:18
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

