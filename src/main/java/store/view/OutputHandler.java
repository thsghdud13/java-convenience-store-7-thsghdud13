package store.view;

import java.util.List;
import store.dto.response.InventoryResponseDto;
import store.dto.response.Receipt;
import store.dto.response.Receipt.FreeItem;
import store.dto.response.Receipt.PurchaseItem;

public class OutputHandler {
    public static void printInventory(List<InventoryResponseDto> inventoryResponseDtos) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        for (InventoryResponseDto inventoryResponseDto : inventoryResponseDtos) {
            if (inventoryResponseDto.isPromotionItem()) {
                String promotionOutput = String.format("- %s %,d원 %s %s", inventoryResponseDto.getItemName(),
                        inventoryResponseDto.getPrice(), inventoryResponseDto.getPromotionQuantity(),
                        inventoryResponseDto.getPromotionName());
                System.out.println(promotionOutput);
            }
            String regularOutput = String.format("- %s %,d원 %s", inventoryResponseDto.getItemName(),
                    inventoryResponseDto.getPrice(), inventoryResponseDto.getRegularQuantity());
            System.out.println(regularOutput);
        }
        System.out.println();
    }

    public static void printReceipt(Receipt receipt) {
        System.out.println("==============W 편의점================");
        System.out.printf("%-19s %-10s %-7s%n", "상품명", "수량", "금액");

        for (PurchaseItem item : receipt.getPurchaseItems()) {
            System.out.printf("%-19s %-10d %,7d%n", item.getItemName(), item.getQuantity(), item.getTotalPrice());
        }

        System.out.println("=============증\t정===============");
        for (FreeItem item : receipt.getFreeItems()) {
            System.out.printf("%-19s %-10d%n", item.getItemName(), item.getQuantity());
        }

        System.out.println("===================================");
        System.out.printf("%-19s %-10d %,7d%n", "총구매액", receipt.getTotalQuantity(), receipt.getTotalPrice());
        System.out.printf("%-19s %-10s %,7d%n", "행사할인", "", -receipt.getPromotionDiscount());
        System.out.printf("%-19s %-10s %,7d%n", "멤버십할인", "", -receipt.getMembershipDiscount());
        System.out.printf("%-19s %-10s %,7d%n\n", "내실돈", "", receipt.getSum());
    }

}
