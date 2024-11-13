package store.model.domain.promotion;

import store.model.domain.OrderItem;
import store.model.domain.promotion.status.PromotionState;

public interface PromotionPolicy {
    boolean isPromotion();

    String getPromotionName();

    PromotionState checkPromotionState(OrderItem orderItem, int availablePromotionQuantity);
}
