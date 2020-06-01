package com.hongchai.catmall.ware.vo;

import lombok.Data;

@Data
public class PurchaseItemDoneVo {
  private  Long itemid;
  private  Integer status;
  private  String  reason;
}
