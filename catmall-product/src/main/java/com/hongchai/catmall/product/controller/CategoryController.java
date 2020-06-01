package com.hongchai.catmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hongchai.catmall.product.entity.CategoryEntity;
import com.hongchai.catmall.product.service.CategoryService;
import com.hongchai.common.utils.PageUtils;
import com.hongchai.common.utils.R;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * 商品三级分类
 *
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 10:50:24
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    private RedisTemplate  redisTemplate;
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    //@RequiresPermissions("product:category:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = categoryService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:category:list")
    public R list(){
       //  DispatcherServlet
//        var tmp= redisTemplate.opsForValue().get("plist");
//        if(tmp!=null){
//            return R.ok().put("data",tmp);
//        }else {
            List<CategoryEntity> list = categoryService.listWithTree();
            redisTemplate.opsForValue().set("plist", list);
            return R.ok().put("data", list);
//        }
    }
    @RequestMapping("/delete2")
    //@RequiresPermissions("product:category:list")
    public R delete2(){

        return R.ok().put("data",redisTemplate.delete("plist"));


    }

    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
		//categoryService.removeByIds(Arrays.asList(catIds));
         categoryService.removeAppropriateIds(Arrays.asList(catIds));
        return R.ok();
    }

}
