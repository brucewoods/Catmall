package com.hongchai.catmall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.Query;

import com.hongchai.catmall.ware.dao.WareInfoDao;
import com.hongchai.catmall.ware.entity.WareInfoEntity;
import com.hongchai.catmall.ware.service.WareInfoService;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key=(String)params.get("key");
       QueryWrapper<WareInfoEntity>  queryWrapper=  new QueryWrapper<WareInfoEntity>();
       queryWrapper.eq("id",key).or()
               .like("name",key).or()
               .like("address",key);
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                queryWrapper

        );

        return new PageUtils(page);
    }

}