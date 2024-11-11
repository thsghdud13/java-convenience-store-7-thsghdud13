package store.model.domain;

public class Order {
    private final String itemName;
    private final int orderQuantity;
    private final int freeQuantity;
    private final int promotionQuantity;
    private final int regularQuantity;
    private final int unitPrice;

    public Order(OrderItem orderItem) {
        this.itemName = orderItem.getName();
        this.orderQuantity = orderItem.getTotalOrderQuantity();
        this.freeQuantity = orderItem.getFreeQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.promotionQuantity = orderItem.getPromotionOrderQuantity();
        this.regularQuantity = orderItem.getRegularOrderQuantity();
    }

    //TODO 돈계산
    public int getTotalAmount() {
        return orderQuantity * unitPrice;
    }

    public int getDiscountAmount() {
        return freeQuantity * unitPrice;
    }

    public boolean isPromotionApplied() {
        return freeQuantity > 0;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getRegularQuantity() {
        return regularQuantity;
    }

    public int getTotalQuantity() {
        return orderQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }
}
