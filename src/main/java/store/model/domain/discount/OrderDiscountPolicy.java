package store.model.domain.discount;

import store.model.domain.Order;

public interface OrderDiscountPolicy {
    double discount(Order order);
}
