package store.model.domain.promotion.status;

public interface PromotionState {
    boolean isApplied();

    boolean isAdditionalQuantityNeeded();

    boolean isAnyNonAppliedQuantity();

    int getPromotionAppliedQuantity();

    int getAdditionalQuantity();

    int getNonAppliedQuantity();

    int getRegularQuantity();
}
