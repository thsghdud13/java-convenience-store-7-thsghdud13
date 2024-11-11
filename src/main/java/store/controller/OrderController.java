package store.controller;

import java.util.List;
import store.dto.request.OrderRequestDto;
import store.dto.response.ReceiptFactory;
import store.enums.UserInput;
import store.model.domain.OrderItem;
import store.model.domain.discount.Membership;
import store.model.domain.discount.NoneDiscountPolicy;
import store.model.domain.discount.OrderDiscountPolicy;
import store.service.OrderService;
import store.view.InputHandler;
import store.view.OutputHandler;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void order(OrderRequestDto orderRequestDto) {
        List<OrderItem> orderItems = orderService.createOrderItems(orderRequestDto);
        for (OrderItem orderItem : orderItems) {
            checkPromotion(orderItem);
        }
        OutputHandler.printReceipt(ReceiptFactory.getINSTANCE()
                .createReceipt(orderService.createOrder(orderItems, checkMembership())));
    }

    private void checkPromotion(OrderItem orderItem) {
        checkAdditionalQuantityNeeded(orderItem);
        checkNonAppliedQuantity(orderItem);
    }

    private void checkAdditionalQuantityNeeded(OrderItem orderItem) {
        if (orderItem.isAdditionalQuantityNeeded()) {
            if (InputHandler.getUserInputForAdditionalPromotionQuantity(orderItem).equals(UserInput.Y)) {
                orderItem.addAdditionalQuantity();
            }
        }
    }

    private void checkNonAppliedQuantity(OrderItem orderItem) {
        if (orderItem.isAnyNonAppliedQuantity()) {
            UserInput userInput = InputHandler.getUserInputForNonPromotionalQuantity(orderItem);
            if (userInput.equals(UserInput.N)) {
                orderItem.subNonAppliedQuantity();
            }
            if (userInput.equals(UserInput.Y)) {
                orderItem.confirmPurchaseWithoutDiscount();
            }
        }
    }

    private OrderDiscountPolicy checkMembership() {
        UserInput userInputForMembership = InputHandler.getUserInputForMembership();
        if (userInputForMembership.equals(UserInput.Y)) {
            return new Membership();
        }
        return new NoneDiscountPolicy();
    }
}
