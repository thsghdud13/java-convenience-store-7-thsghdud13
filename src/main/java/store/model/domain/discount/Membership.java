package store.model.domain.discount;

import store.model.domain.Order;

public class Membership implements OrderDiscountPolicy {
    private final static double DISCOUNT_RATIO = 0.3;

    private final static int MAX_DISCOUNT_AMOUNT = 8000;

    @Override
    public double discount(Order order) {
        if (order.isPromotionApplied()) {
            return 0;
        }
        double discountAmount = order.getTotalAmount() * DISCOUNT_RATIO;
        if (discountAmount > MAX_DISCOUNT_AMOUNT) {
            return MAX_DISCOUNT_AMOUNT;
        }
        return discountAmount;
    }
}
