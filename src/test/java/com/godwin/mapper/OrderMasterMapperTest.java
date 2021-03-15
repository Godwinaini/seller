package com.godwin.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.godwin.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Godwin on 2021/1/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterMapperTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    private final String BUYER_OPENID = "110110";
    @Test
    public void testFindByOrderId() throws Exception {
        OrderMaster rel = orderMasterMapper.findByOrderId("05318888");
        System.out.println(rel.toString());
    }

    @Test
    public void testAdd() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("05318888");
        orderMaster.setBuyerName("刘亦菲");
        orderMaster.setBuyerPhone("18888888");
        orderMaster.setBuyerAddress("China");
        orderMaster.setBuyerOpenid(BUYER_OPENID);
        orderMaster.setOrderAmount(new BigDecimal(1.8));
        orderMasterMapper.add(orderMaster);
    }

    @Test
    public void testFindByBuyerOpenid() throws Exception {
        //设置分页条件，Parameters:pageNum 页码pageSize 每页显示数量count 是否进行count查询
        PageHelper.startPage(1, 3,true);
        List<OrderMaster> orderMasters = orderMasterMapper.findByBuyerOpenid(BUYER_OPENID);

        PageInfo<OrderMaster> pageInfo = new PageInfo<OrderMaster>(orderMasters);

        //打印分页信息
        System.out.println("数据总数：" + pageInfo.getTotal());
        System.out.println("数据总页数：" + pageInfo.getPages());
//        System.out.println("最后一页：" + pageInfo.getLastPage());


        for (OrderMaster orderMaster : orderMasters) {
            System.out.println(orderMaster);
        }
    }

    @Test
    public void testFindAll() throws Exception {
        PageHelper.startPage(1,3);
        List<OrderMaster> list = orderMasterMapper.getQuery();
        PageInfo<OrderMaster> pageInfo = new PageInfo<OrderMaster>(list);

        //打印分页信息
        System.out.println("数据总数：" + pageInfo.getTotal());
        System.out.println("数据总页数：" + pageInfo.getPages());
        for (OrderMaster orderMaster : list){
            System.out.println(orderMaster);
        }
    }
}