package store.dto.request;

public class ProductInfoDto {
    private final String name;
    private final int price;
    private final int quantity;
    private final String promotionName;

    public ProductInfoDto(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isNonPromotion() {
        return promotionName.equals("null");
    }

    public int getRegularQuantity() {
        if (isNonPromotion()) {
            return quantity;
        }
        return 0;
    }

    public int getPromotionQuantity() {
        if (!isNonPromotion()) {
            return quantity;
        }
        return 0;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
