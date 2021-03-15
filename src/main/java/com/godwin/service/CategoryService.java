package com.godwin.service;

import com.godwin.dataobject.ProductCategory;

import java.util.List;

/**
 * 商品类目接口
 * Created by Godwin on 2021/1/26.
 */
public interface CategoryService {
    /*新增*/
    int add(ProductCategory productCategory);
    /*查询一条数据*/
    ProductCategory findById(Integer categoryId);
    /*查询所有数据*/
    List<ProductCategory> findAll();
    /*通过类目编号来查询所有*/
    List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList);
}
