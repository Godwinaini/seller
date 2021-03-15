package com.godwin.mapper;

import com.godwin.dataobject.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品信息DAO层
 * Created by Godwin on 2021/1/26.
 */
@Repository
public interface ProductInfoMapper {
    //通过商品的状态查询所有上架的商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
    //通过商品id查询
    ProductInfo findByProductId(String productId);
    //查询所有商品
    List<ProductInfo> findAll();
    //增加
    int add(ProductInfo productInfo);
    //修改
    int update(ProductInfo productInfo);

}
