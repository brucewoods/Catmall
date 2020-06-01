package com.hongchai.catmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.catmall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 13:13:09
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

