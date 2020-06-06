package com.lzx.service.impl;

import com.lzx.entity.Order;
import com.lzx.repository.OrderRepository;
import com.lzx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void closeOrder() {
        log.info("关闭订单:{}",orderRepository.findById(5L));
    }
}
