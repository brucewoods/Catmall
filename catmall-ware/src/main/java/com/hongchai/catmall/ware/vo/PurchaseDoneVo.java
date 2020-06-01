package com.hongchai.catmall.ware.vo;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PurchaseDoneVo {

  @NonNull
  private  long id;
  private List<PurchaseItemDoneVo> items;

}
