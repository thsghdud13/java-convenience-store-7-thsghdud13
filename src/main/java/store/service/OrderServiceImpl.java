package store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import store.dto.request.OrderRequestDto;
import store.exception.ErrorStatus;
import store.exception.handler.InventoryErrorHandler;
import store.model.domain.Item;
import store.model.domain.Order;
import store.model.domain.OrderItem;
import store.model.domain.Orders;
import store.model.domain.discount.OrderDiscountPolicy;
import store.repository.InventoryRepository;

public class OrderServiceImpl implements OrderService {
    private final InventoryRepository inventoryRepository;

    public OrderServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Orders createOrder(List<OrderItem> orderItems, OrderDiscountPolicy orderDiscountPolicy) {
        List<Order> orders = orderItems.stream()
                .map(Order::new)
                .collect(Collectors.toList());

        orders.forEach(inventoryRepository::update);
        return new Orders(orders, orderDiscountPolicy);
    }

    @Override
    public List<OrderItem> createOrderItems(OrderRequestDto orderRequestDto) {
        List<OrderItem> orderItems = new ArrayList<>();
        Map<String, Integer> orderList = orderRequestDto.getOrderList();
        for (String itemName : orderList.keySet()) {
            Item byItemName = inventoryRepository.findByName(itemName);
            if (!byItemName.isOrderable(orderList.get(itemName))) {
                throw new InventoryErrorHandler(ErrorStatus.INSUFFICIENT_STOCK_QUANTITY);
            }
            orderItems.add(new OrderItem(inventoryRepository.findByName(itemName), orderList.get(itemName)));
        }
        return orderItems;
    }
}
