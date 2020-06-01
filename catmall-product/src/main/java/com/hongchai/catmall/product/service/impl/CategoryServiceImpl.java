package com.hongchai.catmall.product.service.impl;

import lombok.var;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.Query;

import com.hongchai.catmall.product.dao.CategoryDao;
import com.hongchai.catmall.product.entity.CategoryEntity;
import com.hongchai.catmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> list = baseMapper.selectList(null);
        var rootMenus=
                list.stream().filter(o->o.getParentCid()==0)
                .map(i->{i.setChildren(makeTree(i,list)) ;
                  return i;
                })
                .sorted(Comparator.comparingInt(s -> (s.getSort() == null ? 0 : s.getSort())))
                .collect(Collectors.toList());
        return rootMenus;
    }

    @Override
    public void removeAppropriateIds(List<Long> asList) {
        //TODO  handle relationships of ids
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] getCatpath(Long catId) {
       CategoryEntity entity= getById(catId);

       List<Long> longs=new ArrayList<>();
       longs.add(catId);
       findPath(catId,longs);
        Collections.reverse(longs);

        return (Long[]) longs.toArray(new Long[longs.size()]);
    }
    private  void findPath(long catId,List<Long> result){

       CategoryEntity cat= this.getById(catId);
       long pid=cat.getParentCid();
       if(pid!=0){
          result.add(pid);
          findPath(pid,result);
       }
    }

    private List<CategoryEntity> makeTree(CategoryEntity cate, List<CategoryEntity> list) {

       var result=list.stream().filter(o->o.getParentCid()==cate.getCatId())
                .map(o-> {o.setChildren(makeTree(o,list));
                   return o;
                 })
                .sorted(Comparator.comparingInt(s -> (s.getSort() == null ? 0 : s.getSort())))
                .collect(Collectors.toList());
       return  result;
    }


//    @Override
//    public List<CategoryEntity> listWithTree() {
//      List<CategoryEntity> list = baseMapper.selectList(null);
//      var firstMenus= list.stream().filter(o->o.getParentCid()==0).collect(Collectors.toList());
//      makeTreeMenus(list,firstMenus);
//      return firstMenus;
//    }
//
//    private   void  makeTreeMenus(List<CategoryEntity> list,List<CategoryEntity> list2){
//
//        for (CategoryEntity c: list2) {
//            var tmp=  list.stream()
//                    .filter(o->o.getParentCid()==c.getCatId())
//                    .collect(Collectors.toList());
//            c.setChildren(tmp);
//             makeTreeMenus(list,tmp);
//
//
//
//            }
//
//        }




    }
