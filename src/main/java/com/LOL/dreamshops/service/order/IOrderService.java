package com.LOL.dreamshops.service.order;

import com.LOL.dreamshops.dto.OrderDto;
import com.LOL.dreamshops.model.Order;

import java.util.List;

public interface IOrderService
{

  Order placeOrder(Long userId);

  OrderDto getOrder(Long orderId);

  List<OrderDto> getUserOrders(Long userId);

  OrderDto convertToDto(Order order);
}
