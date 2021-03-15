package com.godwin.service;


import com.godwin.dto.CartDTO;
import com.godwin.dataobject.ProductInfo;


import java.util.List;

/**
 * 商品信息接口
 * Created by Godwin on 2021/1/27.
 */
public interface ProductService {
    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpAll();
    //根据商品Id查询一条数据
    ProductInfo findByProductId(String productId);
    //分页查询所有，后台管理系统用
    List<ProductInfo> findAll();
//    List<ProductInfo> getPages(int pageNum, int pageSize);
    //增加
    int add(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
