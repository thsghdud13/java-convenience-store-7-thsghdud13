package store.model.domain.promotion;

import java.time.LocalDate;
import store.model.domain.OrderItem;
import store.model.domain.promotion.status.BuyNGet1PromotionState;
import store.model.domain.promotion.status.PromotionState;

public class BuyNGet1Promotion implements PromotionPolicy {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate starDate;
    private final LocalDate endDate;
    private final PromotionCondition promotionCondition;

    public BuyNGet1Promotion(String name, int buy, int get, LocalDate starDate, LocalDate endDate,
                             PromotionCondition promotionCondition) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.starDate = starDate;
        this.endDate = endDate;
        this.promotionCondition = promotionCondition;
    }

    @Override
    public String getPromotionName() {
        return name;
    }

    @Override
    public PromotionState checkPromotionState(OrderItem orderItem, int availablePromotionQuantity) {
        return new BuyNGet1PromotionState(true, calculateAppliedQuantity(orderItem, 0),
                calculateAdditionalQuantityNeeded(orderItem, 0),
                calculateNonAppliedQuantity(orderItem, 0));
    }

    private boolean isApplied(OrderItem orderItem, int availablePromotionQuantity) {
        return promotionCondition.isSatisfiedBy(orderItem);

    }

    private int calculateAppliedQuantity(OrderItem orderItem, int availablePromotionQuantity) {
        return 0;
    }

    private int calculateAdditionalQuantityNeeded(OrderItem orderItem, int availablePromotionQuantity) {
        return 0;
    }

    private int calculateNonAppliedQuantity(OrderItem orderItem, int availablePromotionQuantity) {
        return 0;
    }
}
