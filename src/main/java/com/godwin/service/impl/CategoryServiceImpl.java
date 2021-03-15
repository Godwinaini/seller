package com.godwin.service.impl;

import com.godwin.mapper.ProductCategoryMapper;
import com.godwin.dataobject.ProductCategory;
import com.godwin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目接口实现类
 * Created by Godwin on 2021/1/26.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public int add(ProductCategory productCategory) {
        return productCategoryMapper.add(productCategory);
    }

    @Override
    public ProductCategory findById(Integer categoryId) {
        return productCategoryMapper.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList) {
        return productCategoryMapper.findByCategoryType(categoryTypeList);
    }
}
