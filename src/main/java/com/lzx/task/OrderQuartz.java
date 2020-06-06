package com.lzx.task;

import com.lzx.entity.Order;
import com.lzx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderQuartz {

    @Autowired
    private OrderService orderService;

    public void closeOrder(String str){
        System.out.println("--------------run-------------------"+str);
        orderService.closeOrder();
    }
}
