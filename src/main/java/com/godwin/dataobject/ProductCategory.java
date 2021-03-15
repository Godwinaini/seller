package com.godwin.dataobject;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * 商品类目实体类
 * Created by Godwin on 2021/1/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    private Integer categoryId;
    /*类目名字*/
    private String categoryName;
    /*类目编号*/
    private Integer categoryType;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
}
