package com.hongchai.catmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.hongchai.common.valid.AddGroup;
import com.hongchai.common.valid.ListValue;
import com.hongchai.common.valid.UpdateGroup;
import com.hongchai.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author brucewoods
 * @email hi.brucewoo@hotmail.com
 * @date 2020-04-24 10:13:18
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@NotNull(groups = {UpdateGroup.class},message = "修改必须指定品牌ID")
	@Null(groups = {AddGroup.class},message = "新增无需Id")
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */

	@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(groups = {AddGroup.class})
	@URL(message = "必须是一个URL地址",groups = {AddGroup.class,UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(groups = {AddGroup.class})
	//@Range(min = 0,max = 1,message = "状态必须为0或1",groups = {AddGroup.class,UpdateGroup.class})
	 @ListValue(vals={0,1},groups = {AddGroup.class, UpdateStatusGroup.class,UpdateGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(groups = {AddGroup.class})
	@Pattern(regexp="^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups={AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(groups = {AddGroup.class})
	@Min(value = 0,message = "排序必须大于0的整数",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
