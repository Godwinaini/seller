package com.godwin.service.impl;

import com.godwin.dataobject.ProductCategory;
import com.godwin.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Godwin on 2021/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService mapper;

    @Test
    public void testAdd() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(8);
        productCategory.setCategoryName("糖醋排骨");
        productCategory.setCategoryType(8);
        mapper.add(productCategory);
        Assert.assertNotNull(mapper);
    }

    @Test
    public void testFindById() throws Exception {
        ProductCategory result = mapper.findById(1);
        System.out.println(result.toString());
        Assert.assertEquals(new Integer(1),result.getCategoryId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<ProductCategory> categoryList = mapper.findAll();
        for (ProductCategory productCategory : categoryList) {
            System.out.println(productCategory);
        }
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void testFindByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList = mapper.findByCategoryType(Arrays.asList(1, 2, 3));
        Assert.assertNotEquals(0, productCategoryList.size());
        System.out.println(productCategoryList);
    }
}