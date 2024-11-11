package store.model.domain;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.discount.OrderDiscountPolicy;

public class Orders {
    private final List<Order> orders;
    private final OrderDiscountPolicy orderDiscountPolicy;

    public Orders(List<Order> orders, OrderDiscountPolicy orderDiscountPolicy) {
        this.orders = orders;
        this.orderDiscountPolicy = orderDiscountPolicy;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public int getTotalPrice() {
        int total = 0;
        for (Order order : orders) {
            total += order.getTotalAmount();
        }
        return total;
    }

    public int getTotalPromotionDiscount() {
        int total = 0;
        for (Order order : orders) {
            total += order.getDiscountAmount();
        }
        return total;
    }

    public double getMembershipDiscount() {
        double total = 0;
        for (Order order : orders) {
            total += orderDiscountPolicy.discount(order);
        }
        return total;
    }


}
