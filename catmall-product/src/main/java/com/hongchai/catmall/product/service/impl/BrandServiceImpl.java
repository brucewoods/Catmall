package com.hongchai.catmall.product.service.impl;

import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.Query;

import com.hongchai.catmall.product.dao.BrandDao;
import com.hongchai.catmall.product.entity.BrandEntity;
import com.hongchai.catmall.product.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isNullOrEmpty(key)){
            wrapper.eq("brand_id",key)
                    .or().like("name",key)
                    .or().like("descript",key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
              wrapper
        );

        return new PageUtils(page);
    }

}