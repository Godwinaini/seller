package com.godwin.service.impl;

import com.github.pagehelper.PageInfo;
import com.godwin.dto.OrderDTO;
import com.godwin.enums.OrderstatusEnum;
import com.godwin.enums.PayStatusEnum;
import com.godwin.dataobject.OrderDetail;
import com.godwin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Godwin on 2021/1/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "110110";

    private final String ORDERID = "1611742908921447989";

    //创建订单
    @Test
    public void testCreate() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("万茜");
        orderDTO.setBuyerAddress("China");
        orderDTO.setBuyerPhone("15566666");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("188105");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

//        OrderDetail o2 = new OrderDetail();
//        o2.setProductId("188102");
//        o2.setProductQuantity(2);
//        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}");
        Assert.assertNotNull(result);
    }

    //查询一个订单
    @Test
    public void TestfindOne() throws Exception{
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        log.info("【查询单个订单】 result={}",orderDTO);
        Assert.assertEquals(ORDERID,orderDTO.getOrderId());
    }

    //取消订单
    @Test
    public void testCancel() throws Exception{
        OrderDTO orderDTO = orderService.findOne("1614668274269543899");
        OrderDTO rel = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderstatusEnum.CANCEL.getCode(),rel.getOrderStatus());
    }

    //完结订单
    @Test
    public void testFinish() throws Exception{
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO rel = orderService.finish(orderDTO);
        Assert.assertEquals(OrderstatusEnum.FINISHED.getCode(),rel.getOrderStatus());
    }

    //支付订单
    @Test
    public void testPaid() throws Exception{
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO rel = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),rel.getPayStatus());
    }

    /*根据buyerOpenid查询订单列表*/
    @Test
    public void testFindList() throws Exception {
        List<OrderDTO> orderDTOList = orderService.findList(BUYER_OPENID,1,3);

//        PageInfo<OrderDTO> pageInfo = new PageInfo<>(orderDTOList);
//        //打印分页信息
//        System.out.println("数据总数：" + pageInfo.getTotal());
//        System.out.println("数据总页数：" + pageInfo.getPages());

        for (OrderDTO orderDTO : orderDTOList){
            System.out.println(orderDTOList);
        }

    }

    //查询所有订单（分页）
    @Test
    public void testFindAll() throws Exception {
        List<OrderDTO> list = orderService.findAll(1,3);
        PageInfo<OrderDTO> pageInfo = new PageInfo<OrderDTO>(list);

        //打印分页信息
        System.out.println("数据总数：" + pageInfo.getTotal());
        System.out.println("数据总页数：" + pageInfo.getPages());
        for (OrderDTO orderDTO : list){
            System.out.println(orderDTO);
        }

    }
}