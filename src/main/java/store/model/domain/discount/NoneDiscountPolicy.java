package store.model.domain.discount;

import store.model.domain.Order;

public class NoneDiscountPolicy implements OrderDiscountPolicy {

    @Override
    public double discount(Order order) {
        return 0;
    }
}
