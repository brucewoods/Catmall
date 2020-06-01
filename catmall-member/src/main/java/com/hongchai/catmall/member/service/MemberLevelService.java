package com.hongchai.catmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.catmall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 13:13:09
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

