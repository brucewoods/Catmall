package com.hongchai.catmall.product.service.impl;

import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.Query;

import com.hongchai.catmall.product.dao.AttrGroupDao;
import com.hongchai.catmall.product.entity.AttrGroupEntity;
import com.hongchai.catmall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long cateId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();

        String key= (String) params.get("key");
        if(!StringUtils.isNullOrEmpty(key)){
            wrapper.and(o->{
                o.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }
        if(cateId>0) wrapper.eq("catelog_id",cateId);

        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                wrapper
        );
        return  new PageUtils(page);
    }

}