package store.config;

import store.controller.CounterController;
import store.controller.InventoryController;
import store.controller.OrderController;
import store.controller.ReadyController;
import store.repository.InMemoryInventoryRepository;
import store.repository.InMemoryPromotionRepository;
import store.repository.InventoryRepository;
import store.repository.PromotionRepository;
import store.service.InventoryService;
import store.service.InventoryServiceImpl;
import store.service.OrderService;
import store.service.OrderServiceImpl;
import store.service.PromotionService;
import store.service.PromotionServiceImpl;

public class ConvenienceStoreConfig {
    public CounterController counterController() {
        return new CounterController(orderController(), inventoryController(), readyController());
    }

    private OrderController orderController() {
        return new OrderController(orderService());
    }

    private InventoryController inventoryController() {
        return new InventoryController(inventoryService());
    }

    private ReadyController readyController() {
        return new ReadyController(inventoryService(), promotionService());
    }

    private InventoryService inventoryService() {
        return new InventoryServiceImpl(inventoryRepository(), promotionRepository());
    }

    private OrderService orderService() {
        return new OrderServiceImpl(inventoryRepository());
    }

    private PromotionService promotionService() {
        return new PromotionServiceImpl(promotionRepository());
    }

    private PromotionRepository promotionRepository() {
        return InMemoryPromotionRepository.getInstance();
    }

    private InventoryRepository inventoryRepository() {
        return InMemoryInventoryRepository.getInstance();
    }
}
