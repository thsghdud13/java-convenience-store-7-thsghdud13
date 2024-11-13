package store.service;

import java.util.List;
import store.dto.request.OrderRequestDto;
import store.model.domain.OrderItem;
import store.model.domain.Orders;
import store.model.domain.discount.OrderDiscountPolicy;

public interface OrderService {
    Orders createOrder(List<OrderItem> orderItems, OrderDiscountPolicy orderDiscountPolicy);

    List<OrderItem> createOrderItems(OrderRequestDto orderRequestDto);
}
