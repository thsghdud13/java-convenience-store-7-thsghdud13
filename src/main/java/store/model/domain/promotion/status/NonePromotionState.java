package store.model.domain.promotion.status;

public class NonePromotionState implements PromotionState {
    @Override
    public boolean isApplied() {
        return false;
    }

    @Override
    public boolean isAdditionalQuantityNeeded() {
        return false;
    }

    @Override
    public boolean isAnyNonAppliedQuantity() {
        return false;
    }

    @Override
    public int getPromotionAppliedQuantity() {
        return 0;
    }

    @Override
    public int getAdditionalQuantity() {
        return 0;
    }

    @Override
    public int getNonAppliedQuantity() {
        return 0;
    }

    @Override
    public int getRegularQuantity() {
        return 0;
    }
}
