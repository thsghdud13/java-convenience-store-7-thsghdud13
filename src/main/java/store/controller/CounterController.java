package store.controller;

import store.enums.UserInput;
import store.exception.handler.InventoryErrorHandler;
import store.view.InputHandler;
import store.view.OutputView;

public class CounterController {
    private final OrderController orderController;
    private final InventoryController inventoryController;
    private final ReadyController readyController;

    public CounterController(OrderController orderController, InventoryController inventoryController,
                             ReadyController readyController) {
        this.orderController = orderController;
        this.inventoryController = inventoryController;
        this.readyController = readyController;
    }

    public void respond() {
        readyController.ready();
        do {
            inventoryController.display();
            order();
        } while (UserInput.Y == InputHandler.getUserInputForAnotherOrder());

    }

    public void order() {
        try {
            orderController.order(InputHandler.getOrderDto());
        } catch (InventoryErrorHandler e) {
            OutputView.printError(e);
            order();
        }
    }
}
