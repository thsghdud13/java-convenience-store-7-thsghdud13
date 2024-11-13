package store.controller;

import java.util.List;
import store.dto.request.ProductInfoDto;
import store.dto.request.PromotionInfoDto;
import store.service.InventoryService;
import store.service.PromotionService;
import store.view.InputHandler;

public class ReadyController {
    private final InventoryService inventoryService;
    private final PromotionService promotionService;

    public ReadyController(InventoryService inventoryService, PromotionService promotionService) {
        this.inventoryService = inventoryService;
        this.promotionService = promotionService;
    }

    public void ready() {
        savePromotionInfo();
        saveProductInfo();
    }

    private void savePromotionInfo() {
        List<PromotionInfoDto> promotionInfos = InputHandler.getPromotionInfos();
        promotionService.save(promotionInfos);
    }

    private void saveProductInfo() {
        List<ProductInfoDto> productInfos = InputHandler.getProductInfos();
        inventoryService.save(productInfos);
    }
}
