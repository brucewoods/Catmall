package com.hongchai.catmall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.Query;

import com.hongchai.catmall.ware.dao.WareSkuDao;
import com.hongchai.catmall.ware.entity.WareSkuEntity;
import com.hongchai.catmall.ware.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<WareSkuEntity>();
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmptyOrWhitespaceOnly(wareId))
            queryWrapper.eq("ware_id", wareId);

        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmptyOrWhitespaceOnly(skuId))
            queryWrapper.eq("sku_id", skuId);
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        List<WareSkuEntity> entities = baseMapper.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (entities == null || entities.size() == 0) {
            WareSkuEntity skuEntity=new WareSkuEntity();
            skuEntity.setSkuId(skuId);
            skuEntity.setWareId(wareId);
            skuEntity.setStock(skuNum);
            baseMapper.insert(skuEntity);
        }else{

            baseMapper.addStock(skuId,wareId,skuNum);
        }




    }


}