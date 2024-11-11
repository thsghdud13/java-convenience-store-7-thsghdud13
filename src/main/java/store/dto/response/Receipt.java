package store.dto.response;

import java.util.List;
import store.model.domain.Order;

public class Receipt {
    private final List<PurchaseItem> purchaseItems;
    private final List<FreeItem> freeItems;
    private final int totalPrice;
    private final int promotionDiscount;
    private final int membershipDiscount;
    private final int sum;

    public Receipt(List<PurchaseItem> PurchaseItems, List<FreeItem> FreeItems, int totalPrice, int promotionDiscount,
                   int membershipDiscount) {
        this.purchaseItems = PurchaseItems;
        this.freeItems = FreeItems;
        this.totalPrice = totalPrice;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
        this.sum = totalPrice - promotionDiscount - membershipDiscount;
    }

    public int getTotalQuantity() {
        return purchaseItems.stream().mapToInt(PurchaseItem::getQuantity).sum();
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public List<FreeItem> getFreeItems() {
        return freeItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getPromotionDiscount() {
        return "-" + String.format("%,d", promotionDiscount);
    }

    public String getMembershipDiscount() {
        return "-" + String.format("%,d", membershipDiscount);
    }

    public int getSum() {
        return sum;
    }

    public static class PurchaseItem {
        private final String itemName;
        private final int quantity;
        private final int totalPrice;

        public PurchaseItem(Order order) {
            this.itemName = order.getItemName();
            this.quantity = order.getTotalQuantity();
            this.totalPrice = order.getTotalAmount();
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getTotalPrice() {
            return totalPrice;
        }
    }

    public static class FreeItem {
        private final String itemName;
        private final int quantity;

        public FreeItem(Order order) {
            this.itemName = order.getItemName();
            this.quantity = order.getFreeQuantity();
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }
    }

}

