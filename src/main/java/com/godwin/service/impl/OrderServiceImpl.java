package com.godwin.service.impl;

import com.github.pagehelper.PageHelper;
import com.godwin.converter.OrderMaster2OrderDTOConverter;
import com.godwin.dto.CartDTO;
import com.godwin.dto.OrderDTO;
import com.godwin.enums.OrderstatusEnum;
import com.godwin.enums.PayStatusEnum;
import com.godwin.enums.ResultEnum;
import com.godwin.exception.SellException;
import com.godwin.mapper.OrderDetailMapper;
import com.godwin.mapper.OrderMasterMapper;
import com.godwin.dataobject.OrderDetail;
import com.godwin.dataobject.OrderMaster;
import com.godwin.dataobject.ProductInfo;
import com.godwin.service.OrderService;
import com.godwin.service.ProductService;
import com.godwin.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Godwin on 2021/1/27.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //orderId应该是整个订单创建时就存在了
        String orderId = KeyUtil.genUniqueKey();
        //自定义总价为0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1.查询商品(价格)
        //查询商品首先拿到orderDTO的List,所以需要先遍历
        //orderDetail最先传进来，然后遍历
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findByProductId(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            //价格=单价*数量 但是BigDecimal类型要用multiply来写
            //总价还要加到orderAmount中,orderAmount初始为0，然后每一次循环
            //因为是一个LIst可能有很多商品，先计算（乘上multiply）某一件商品的总价，在加上原来的总价（orderAmount）,最终得到订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail
                            .getProductQuantity()))
                    .add(orderAmount);
            //订单详情(OrderDetail)入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailMapper.add(orderDetail);

//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }


        //3.写入订单数据库（OrderMaster和OrderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderstatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.add(orderMaster);
        //4.扣库存
        //下一个订单一次创建调一次，而不是写入for循环调用好几次,所以参数要定义，不用返回数据，有问题抛异常
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    //查询单个用户订单信息
    @Override
    public OrderDTO findOne(String orderId) {
        //查询订单主表信息
        OrderMaster orderMaster = orderMasterMapper.findByOrderId(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单详情信息
        List<OrderDetail> orderDetailList = orderDetailMapper.findByOrderId(orderId);
        if (orderDetailList == null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //组装orderDTO
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    //取消订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderstatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderstatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int updateResult = orderMasterMapper.update(orderMaster);
        if (updateResult == 0){
            log.error("【取消订单】 更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已经支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    //订单完结
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderstatusEnum.NEW.getCode())) {
            log.error("【完结订单】 订单状态不正确 orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderstatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int updateResult = orderMasterMapper.update(orderMaster);
        if (updateResult == 0){
            log.error("【完结订单】 更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    //支付订单
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderstatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】 订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getOrderStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】 订单支付状态不正确，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int updateResult = orderMasterMapper.update(orderMaster);
        if (updateResult == 0){
            log.error("【订单支付完成】 更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /*根据buyerOpenid查询订单列表*/
    @Override
    public List<OrderDTO> findList(String buyerOpenid, int pageNum , int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderMaster> orderMasterList = orderMasterMapper.findByBuyerOpenid(buyerOpenid);
        List<OrderDTO> list = OrderMaster2OrderDTOConverter.convert(orderMasterList);
        return list;
    }

    //查询所有订单（分页）
    @Override
    public List<OrderDTO> findAll(int pageNum , int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderMaster> orderMasterList = orderMasterMapper.getQuery();
        List<OrderDTO> list = OrderMaster2OrderDTOConverter.convert(orderMasterList);
        return list;
    }
}
