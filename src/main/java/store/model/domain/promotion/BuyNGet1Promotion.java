package store.model.domain.promotion;

import java.time.LocalDateTime;
import store.model.domain.OrderItem;
import store.model.domain.promotion.status.BuyNGet1PromotionState;
import store.model.domain.promotion.status.PromotionState;

public class BuyNGet1Promotion implements PromotionPolicy {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDateTime starDateTime;
    private final LocalDateTime endDateTime;

    public BuyNGet1Promotion(String name, int buy, int get, LocalDateTime starDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.starDateTime = starDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean isPromotion() {
        return true;
    }

    @Override
    public String getPromotionName() {
        return name;
    }

    @Override
    public PromotionState checkPromotionState(OrderItem orderItem, int availablePromotionQuantity) {
        return new BuyNGet1PromotionState(isApplied(orderItem),
                calculateAppliedQuantity(orderItem, availablePromotionQuantity),
                calculateAdditionalQuantityNeeded(orderItem, availablePromotionQuantity),
                calculateNonAppliedQuantity(orderItem, availablePromotionQuantity),
                calculateRegularQuantity(orderItem, availablePromotionQuantity));
    }

    private boolean isApplied(OrderItem orderItem) {
        return isBetweenDateTimes(orderItem.getOrderDateTime(), starDateTime, endDateTime) && isOverMinimumQuantity(
                orderItem.getTotalOrderQuantity(), buy);
    }

    // 증정수량
    private int calculateAppliedQuantity(OrderItem orderItem, int availablePromotionQuantity) {
        if (orderItem.getTotalOrderQuantity() > availablePromotionQuantity) {
            return (availablePromotionQuantity) / (buy + get); //한도 내에서 적용
        }
        return orderItem.getTotalOrderQuantity() / (buy + get);
    }

    // 추가 수량
    private int calculateAdditionalQuantityNeeded(OrderItem orderItem, int availablePromotionQuantity) {
        int remainder = orderItem.getTotalOrderQuantity() % (buy + get);
        if (remainder == buy && availablePromotionQuantity >= orderItem.getTotalOrderQuantity() + get) {
            return get;
        }
        return 0;
    }

    private int calculateNonAppliedQuantity(OrderItem orderItem, int availablePromotionQuantity) {
        if (calculateAppliedQuantity(orderItem, availablePromotionQuantity) == 0) {
            return 0;
        }
        if (orderItem.getTotalOrderQuantity() > availablePromotionQuantity && availablePromotionQuantity != 0) {
            int promotionCount = availablePromotionQuantity / (buy + get);
            return orderItem.getTotalOrderQuantity() - promotionCount * (buy + get);
        }
        return 0;
    }

    private int calculateRegularQuantity(OrderItem orderItem, int availablePromotionQuantity) {
        if (orderItem.getTotalOrderQuantity() > availablePromotionQuantity) {
            return orderItem.getTotalOrderQuantity() - availablePromotionQuantity;
        }
        return 0;
    }

    private static boolean isBetweenDateTimes(LocalDateTime dateTime, LocalDateTime startDateTime,
                                              LocalDateTime endDateTime) {
        return !dateTime.isBefore(startDateTime) && !dateTime.isAfter(endDateTime);
    }

    private static boolean isOverMinimumQuantity(int orderQuantity, int buy) {
        return orderQuantity >= buy;
    }
}
