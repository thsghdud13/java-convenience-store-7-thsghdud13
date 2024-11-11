package store.model.domain;

import store.model.domain.promotion.PromotionPolicy;
import store.model.domain.promotion.status.PromotionState;

public class Item {
    private final String name;
    private final int price;
    private int regularQuantity = 0;
    private int promotionQuantity = 0;
    private final PromotionPolicy promotionPolicy;

    public Item(String name, int price, int regularQuantity, int promotionQuantity, PromotionPolicy promotionPolicy) {
        this.name = name;
        this.price = price;
        this.regularQuantity = regularQuantity;
        this.promotionQuantity = promotionQuantity;
        this.promotionPolicy = promotionPolicy;
    }

    public Item(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.regularQuantity = item.getRegularQuantity();
        this.promotionQuantity = item.getPromotionQuantity();
        this.promotionPolicy = item.getPromotionPolicy();
    }

    public PromotionState checkPromotionStatus(OrderItem orderItem) {
        return promotionPolicy.checkPromotionState(orderItem, promotionQuantity);
    }

    public boolean isOrderable(int orderQuantity) {
        return regularQuantity + promotionQuantity > orderQuantity;
    }

    public String getName() {
        return name;
    }

    public void addRegularQuantity(int quantity) {
        this.regularQuantity += quantity;
    }

    public void subRegularQuantity(int quantity) {
        this.regularQuantity -= quantity;
    }

    public void addPromotionQuantity(int quantity) {
        this.promotionQuantity += quantity;
    }

    public void subPromotionQuantity(int quantity) {
        this.promotionQuantity -= quantity;
    }

    public int getRegularQuantity() {
        return regularQuantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getPrice() {
        return price;
    }

    public PromotionPolicy getPromotionPolicy() {
        return promotionPolicy;
    }

    public boolean isPromotionItem() {
        return promotionPolicy.isPromotion();
    }
}
