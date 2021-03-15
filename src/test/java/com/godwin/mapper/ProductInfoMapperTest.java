package com.godwin.mapper;


import com.godwin.dataobject.ProductInfo;
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
public class ProductInfoMapperTest {

    @Autowired
    private ProductInfoMapper mapper;

    @Test
    public void testFindByProductStatus() throws Exception {
        List<ProductInfo> infoList = mapper.findByProductStatus(0);
        System.out.println(infoList.toString());
    }

    @Test
    public void testFindByProductId() throws Exception {
        ProductInfo rel = mapper.findByProductId("188101");
        System.out.println(rel.toString());
    }

    @Test
    public void testFinAll() throws Exception {
        List<ProductInfo> list = mapper.findAll();
        for (ProductInfo productInfo : list) {
            System.out.println(productInfo);
        }

    }
    @Test
    public void testAdd() throws Exception{
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("188105");
        productInfo.setProductName("糖醋里脊");
        productInfo.setProductPrice(new BigDecimal(42));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("酸甜可口");
        productInfo.setProductIcon("***.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        mapper.add(productInfo);
    }
}