package store.model.domain.promotion;

import store.model.domain.OrderItem;

public class DatePromotionCondition implements PromotionCondition {
    @Override
    public boolean isSatisfiedBy(OrderItem orderItem) {
        return false;
    }
}
