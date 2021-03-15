package com.godwin.mapper;

import com.godwin.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by Godwin on 2021/1/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailMapperTest {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Test
    public void testFindByOrderId() throws Exception {

    }
    @Test
    public void testAdd() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111002");
        orderDetail.setOrderId("05318888");
        orderDetail.setProductId("188101");
        orderDetail.setProductName("把子肉");
        orderDetail.setProductPrice(new BigDecimal(8));
        orderDetail.setProductQuantity(100);
        orderDetail.setProductIcon("***.jpg");
        orderDetailMapper.add(orderDetail);
    }
}