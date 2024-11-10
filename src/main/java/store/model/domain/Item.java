package store.model.domain;

import store.model.domain.promotion.PromotionPolicy;
import store.model.domain.promotion.status.PromotionState;

public class Item {
    private final String name;
    private final int price;
    private final int quantity;
    private final PromotionPolicy promotionPolicy;

    public Item(String name, int price, int quantity, PromotionPolicy promotionPolicy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionPolicy = promotionPolicy;
    }

    public PromotionState checkPromotionStatus(OrderItem orderItem) {
        return promotionPolicy.checkPromotionState(orderItem, quantity);
    }

    public String getName() {
        return name;
    }
}
