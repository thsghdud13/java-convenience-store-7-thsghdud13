package store.model.domain.promotion;

import store.model.domain.OrderItem;
import store.model.domain.promotion.status.NonePromotionState;
import store.model.domain.promotion.status.PromotionState;

public class NonePromotion implements PromotionPolicy {
    private final String name = "none";

    @Override
    public String getPromotionName() {
        return name;
    }

    @Override
    public PromotionState checkPromotionState(OrderItem orderItem, int availablePromotionQuantity) {
        return new NonePromotionState();
    }
}
