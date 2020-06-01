package com.hongchai.catmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.catmall.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 10:13:18
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

