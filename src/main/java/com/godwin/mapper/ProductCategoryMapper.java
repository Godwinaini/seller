package com.godwin.mapper;

import com.godwin.dataobject.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品类目DAO层
 * Created by Godwin on 2021/1/25.
 */
@Repository//表示这个类是持久层，操作数据库的
public interface ProductCategoryMapper {
    /*新增*/
    int add(ProductCategory productCategory);
    /*查询一条数据*/
    ProductCategory findByCategoryId(Integer categoryId);
    /*查询所有数据*/
    List<ProductCategory> findAll();
    /*通过类目编号的集合来查询所有*/
    List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList);
}
