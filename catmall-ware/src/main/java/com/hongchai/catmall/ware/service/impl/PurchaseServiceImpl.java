package com.hongchai.catmall.ware.service.impl;

import com.hongchai.catmall.ware.entity.PurchaseDetailEntity;
import com.hongchai.catmall.ware.service.PurchaseDetailService;
import com.hongchai.catmall.ware.service.WareSkuService;
import com.hongchai.catmall.ware.vo.PurchaseDoneVo;
import com.hongchai.catmall.ware.vo.PurchaseItemDoneVo;
import com.hongchai.common.constant.WareConstant;
import io.prometheus.client.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.Query;

import com.hongchai.catmall.ware.dao.PurchaseDao;
import com.hongchai.catmall.ware.entity.PurchaseEntity;
import com.hongchai.catmall.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {
    @Autowired
    PurchaseDetailService  purchaseDetailService;
    @Autowired
    WareSkuService wareSkuService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void received(List<Long> ids) {
         List<PurchaseEntity> collect=ids.stream().map(id->{
             PurchaseEntity entity=this.getById(id);
             return  entity; })
                 .filter(item->{return   (item.getStatus()== WareConstant.PurchaseStatusEnum.CREATED.getCode()||item.getStatus()
                         ==WareConstant.PurchaseStatusEnum.ASSIGNED.getCode());
                 })
                 .map(item->{
                     item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVED.getCode());
                     item.setUpdateTime(new Date());
                     return item;
                             })
                 .collect(Collectors.toList());
          this.updateBatchById(collect);

          collect.forEach((item)->{
              List<PurchaseDetailEntity> entities=purchaseDetailService.listDetailByPurchaseId(item.getId());
              List<PurchaseDetailEntity> detailEntities=entities.stream()
                      .map(entity->{
                     PurchaseDetailEntity entity1=new PurchaseDetailEntity();
                     entity1.setId(entity.getId());
                     entity1.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                     return  entity1;
              }).collect(Collectors.toList());
             purchaseDetailService.updateBatchById(detailEntities);
          });
    }
    @Transactional
    @Override
    public void done(PurchaseDoneVo vo) {
        Long id=vo.getId();
        List<PurchaseItemDoneVo> items = vo.getItems();
        int flag=0;

        List<PurchaseDetailEntity> updates= new ArrayList<>();

        for(PurchaseItemDoneVo item:items){
            PurchaseDetailEntity entity=new PurchaseDetailEntity();
            if(item.getStatus()==WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()){
              flag = item.getStatus();
              entity.setStatus(item.getStatus());
            }else{

              entity.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
              flag=entity.getStatus();
                PurchaseDetailEntity byId = purchaseDetailService.getById(item.getItemid());
                wareSkuService.addStock(byId.getSkuId(),byId.getWareId(),byId.getSkuNum());
            }
            entity.setId(item.getItemid());
            updates.add(entity);
            purchaseDetailService.updateBatchById(updates);


            PurchaseEntity pEntity=new PurchaseEntity();
            pEntity.setId(id);
            pEntity.setStatus(flag);
            pEntity.setUpdateTime(new Date());
            this.updateById(pEntity);




        }
    }

}