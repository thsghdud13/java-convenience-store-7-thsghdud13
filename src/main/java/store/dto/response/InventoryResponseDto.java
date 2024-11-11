package store.dto.response;

import store.model.domain.Item;

public class InventoryResponseDto {
    private final static String NO_QUANTITY = "재고 없음";
    private final static String UNIT = "개";
    private final boolean isPromotion;
    private final String itemName;
    private final int price;
    private final String promotionQuantity;
    private final String regularQuantity;
    private final String promotionName;

    public InventoryResponseDto(Item item) {
        this.isPromotion = item.isPromotionItem();
        this.itemName = item.getName();
        this.price = item.getPrice();
        this.promotionQuantity = getPromotionQuantity(item);
        this.regularQuantity = getRegularQuantity(item);
        this.promotionName = item.getPromotionPolicy().getPromotionName();
    }

    private String getPromotionQuantity(Item item) {
        if (item.getPromotionQuantity() < 1) {
            return NO_QUANTITY;
        }
        return item.getPromotionQuantity() + UNIT;
    }

    private String getRegularQuantity(Item item) {
        if (item.getRegularQuantity() < 1) {
            return NO_QUANTITY;
        }
        return item.getRegularQuantity() + UNIT;
    }

    public boolean isPromotionItem() {
        return isPromotion;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getPromotionQuantity() {
        return promotionQuantity;
    }

    public String getRegularQuantity() {
        return regularQuantity;
    }
}
