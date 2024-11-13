package store.dto.response;

import java.util.List;
import store.dto.response.Receipt.FreeItem;
import store.dto.response.Receipt.PurchaseItem;
import store.model.domain.Order;
import store.model.domain.Orders;

public class ReceiptFactory {
    private static ReceiptFactory INSTANCE = new ReceiptFactory();

    private ReceiptFactory() {

    }

    public static ReceiptFactory getINSTANCE() {
        return INSTANCE;
    }


    public Receipt createReceipt(Orders orders) {
        List<PurchaseItem> PurchaseItems = orders.getOrders().stream()
                .map(PurchaseItem::new)
                .toList();
        List<FreeItem> FreeItems = orders.getOrders().stream()
                .filter(Order::isPromotionApplied)
                .map(FreeItem::new)
                .toList();
        return new Receipt(PurchaseItems, FreeItems, orders.getTotalPrice(), orders.getTotalPromotionDiscount(),
                (int) orders.getMembershipDiscount());
    }
}
