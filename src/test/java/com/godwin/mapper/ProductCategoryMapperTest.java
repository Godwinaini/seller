package com.godwin.mapper;


import com.godwin.dataobject.ProductCategory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;

/**
 * 商品类目测试
 * Created by admin on 2021/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void testAdd() throws Exception {
//        int rel = mapper.add(new ProductCategory(7, "把子肉", 7));


    }

    @Test
    public void testFindById() throws Exception {
        ProductCategory rel = mapper.findByCategoryId(1);
        Assert.assertNotNull(rel);
        System.out.println(rel.toString());
    }

    @Test
    public void testFindAll() throws Exception {
        List<ProductCategory> list = mapper.findAll();
        for (ProductCategory productCategory : list) {
            System.out.println(productCategory);
        }

    }

    @Test
    public void testFindByCategoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> categoryList = mapper.findByCategoryType(list);
        Assert.assertNotEquals(0,categoryList.size());
        System.out.println(categoryList);

    }
}