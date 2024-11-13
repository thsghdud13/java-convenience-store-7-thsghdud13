package store.controller;

import store.service.InventoryService;
import store.view.OutputHandler;

public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void display() {
        OutputHandler.printInventory(inventoryService.findAll());
    }
}
