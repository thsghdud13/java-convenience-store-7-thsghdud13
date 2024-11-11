package store.model.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import store.model.domain.promotion.status.PromotionState;

public class OrderItem {
    private Item item;
    private int totalOrderQuantity;
    private int promotionOrderQuantity;
    private int regularOrderQuantity;
    private PromotionState promotionState;
    private final LocalDateTime orderDateTime;

    public OrderItem(Item item, int totalOrderQuantity) {
        this.item = item;
        this.totalOrderQuantity = totalOrderQuantity;
        orderDateTime = DateTimes.now();
        promotionState = promotionCheck();
        promotionOrderQuantity = getPromotionOrderQuantity(item);
        regularOrderQuantity = getRegularOrderQuantity(item);
    }

    /*private static vs private */
    private PromotionState promotionCheck() {
        return item.checkPromotionStatus(this);
    }

    int getPromotionOrderQuantity(Item item) {
        if (item.isPromotionItem() && item.getPromotionQuantity() > 0) {
            return totalOrderQuantity;
        }
        return 0;
    }

    int getRegularOrderQuantity(Item item) {
        if (!item.isPromotionItem()) {
            return totalOrderQuantity;
        }
        if (item.isPromotionItem() && item.getPromotionQuantity() <= 0) {
            return totalOrderQuantity;
        }
        return 0;
    }

    private boolean isApplied() {
        return promotionCheck().isApplied();
    }

    public boolean isAdditionalQuantityNeeded() {
        return promotionState.isAdditionalQuantityNeeded();
    }

    public void addAdditionalQuantity() {
        this.totalOrderQuantity += promotionState.getAdditionalQuantity();
        this.promotionOrderQuantity += promotionState.getAdditionalQuantity();
        this.promotionState = promotionCheck();
    }

    public boolean isAnyNonAppliedQuantity() {
        return promotionCheck().isAnyNonAppliedQuantity();
    }

    public void subNonAppliedQuantity() {
        this.totalOrderQuantity -= promotionState.getNonAppliedQuantity();
        this.promotionOrderQuantity -= promotionState.getNonAppliedQuantity();
        this.promotionState = promotionCheck();
    }

    public void confirmPurchaseWithoutDiscount() {
        this.regularOrderQuantity = promotionState.getRegularQuantity();
        this.promotionOrderQuantity -= this.regularOrderQuantity;
    }

    public int getTotalOrderQuantity() {
        return totalOrderQuantity;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getName() {
        return item.getName();
    }

    public int getAdditionalQuantity() {
        return promotionState.getAdditionalQuantity();
    }

    public int getNonDiscountQuantity() {
        return promotionState.getNonAppliedQuantity();
    }

    public int getFreeQuantity() {
        return promotionState.getPromotionAppliedQuantity();
    }

    public int getUnitPrice() {
        return item.getPrice();
    }

    public int getPromotionOrderQuantity() {
        return promotionOrderQuantity;
    }

    public int getRegularOrderQuantity() {
        return regularOrderQuantity;
    }
}
