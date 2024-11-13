package store.model.domain.promotion.status;

public class BuyNGet1PromotionState implements PromotionState {
    private final boolean isApplied;
    private final int appliedQuantity;
    private final int additionalQuantityNeeded;
    private final int nonPromotionQuantity;
    private final int regularQuantity;

    public BuyNGet1PromotionState(boolean isApplied, int appliedQuantity, int additionalQuantityNeeded,
                                  int nonPromotionQuantity, int regularQuantity) {
        this.isApplied = isApplied;
        this.appliedQuantity = appliedQuantity;
        this.additionalQuantityNeeded = additionalQuantityNeeded;
        this.nonPromotionQuantity = nonPromotionQuantity;
        this.regularQuantity = regularQuantity;
    }

    @Override
    public boolean isApplied() {
        return isApplied;
    }

    @Override
    public boolean isAdditionalQuantityNeeded() {
        if (!isApplied) {
            return false;
        }
        return additionalQuantityNeeded > 0;
    }

    @Override
    public boolean isAnyNonAppliedQuantity() {
        if (!isApplied) {
            return false;
        }
        return nonPromotionQuantity > 0;
    }

    @Override
    public int getPromotionAppliedQuantity() {
        if (!isApplied) {
            return 0;
        }
        return appliedQuantity;
    }

    @Override
    public int getAdditionalQuantity() {
        if (!isApplied) {
            return 0;
        }
        return additionalQuantityNeeded;
    }

    @Override
    public int getNonAppliedQuantity() {
        if (!isApplied) {
            return 0;
        }
        return nonPromotionQuantity;
    }

    @Override
    public int getRegularQuantity() {
        return regularQuantity;
    }
}
