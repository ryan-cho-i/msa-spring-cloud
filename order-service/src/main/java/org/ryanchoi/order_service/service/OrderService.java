package org.ryanchoi.order_service.service;

import org.ryanchoi.order_service.dto.OrderDto;
import org.ryanchoi.order_service.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
