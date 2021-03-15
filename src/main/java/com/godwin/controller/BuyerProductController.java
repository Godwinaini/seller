package com.godwin.controller;

import com.godwin.dataobject.ProductCategory;
import com.godwin.dataobject.ProductInfo;
import com.godwin.service.CategoryService;
import com.godwin.service.ProductService;
import com.godwin.utils.ResultVoUtil;
import com.godwin.vo.CategoryVo;
import com.godwin.vo.ProductVo;
import com.godwin.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by Godwin on 2021/1/27.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResultVo list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询我们需要类目，一次性查出
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryType(categoryTypeList);
        //3.数据拼装
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setCategoryName(productCategory.getCategoryName());
            categoryVo.setCategoryType(productCategory.getCategoryType());


            List<ProductVo> productVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(categoryVo.getCategoryType())){
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(productInfo,productVo);
                    productVoList.add(productVo);
                }
            }
            categoryVo.setProductVoList(productVoList);
            categoryVoList.add(categoryVo);
        }
        return ResultVoUtil.success(categoryVoList);
    }
}
