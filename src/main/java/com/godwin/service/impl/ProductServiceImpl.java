package com.godwin.service.impl;

import com.godwin.dto.CartDTO;
import com.godwin.enums.ProductStatusEnum;
import com.godwin.enums.ResultEnum;
import com.godwin.exception.SellException;
import com.godwin.mapper.ProductInfoMapper;
import com.godwin.dataobject.ProductInfo;
import com.godwin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息接口实现类
 * Created by Godwin on 2021/1/27.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoMapper.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo findByProductId(String productId) {
        return productInfoMapper.findByProductId(productId);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoMapper.findAll();
    }

//    @Override
//    public List<ProductInfo> getPages(int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<ProductInfo> productInfoList = productInfoMapper.findAll();
//        return productInfoList;
//    }

    @Override
    public int add(ProductInfo productInfo) {
        return productInfoMapper.add(productInfo);
    }

    //加库存
    @Override
    //使用事务，抛出异常，事务会回滚（不进行任何操作）,事务的意思，要么就全成功，要么就全失败不操作
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO :cartDTOList){
            ProductInfo productInfo = productInfoMapper.findByProductId(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(result);

            productInfoMapper.update(productInfo);
        }
    }

    //减库存
    @Override
    //使用事务，抛出异常，事务会回滚（不进行任何操作）
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoMapper.findByProductId(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (result < 0){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productInfo.setProductStock(result);

            productInfoMapper.update(productInfo);
        }
    }
}
