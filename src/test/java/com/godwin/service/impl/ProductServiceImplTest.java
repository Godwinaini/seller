package com.godwin.service.impl;


import com.godwin.dataobject.ProductInfo;
import com.godwin.service.ProductService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by admin on 2021/1/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testFindUpAll() throws Exception {
        List<ProductInfo> rel = productService.findUpAll();
        System.out.println(rel.toString());
    }

    @Test
    public void testFindByProductId() throws Exception {
        ProductInfo rel = productService.findByProductId("188101");
        System.out.println(rel.toString());
    }

    @Test
    public void testAdd() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("188106");
        productInfo.setProductName("大盆鸡");
        productInfo.setProductPrice(new BigDecimal(98));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("香辣可口");
        productInfo.setProductIcon("***.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        productService.add(productInfo);
    }


}