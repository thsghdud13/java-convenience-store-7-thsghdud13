package store.repository;

import java.util.HashMap;
import java.util.Map;
import store.model.domain.promotion.NonePromotion;
import store.model.domain.promotion.PromotionPolicy;

public class InMemoryPromotionRepository implements PromotionRepository {
    private final Map<String, PromotionPolicy> promotions = new HashMap<>();
    private static final InMemoryPromotionRepository INSTANCE = new InMemoryPromotionRepository();

    private InMemoryPromotionRepository() {

    }

    public static InMemoryPromotionRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(PromotionPolicy promotionPolicy) {
        promotions.put(promotionPolicy.getPromotionName(), promotionPolicy);
    }

    @Override
    public PromotionPolicy findByName(String name) {
        return promotions.getOrDefault(name, new NonePromotion());
    }

    @Override
    public void clear() {
        promotions.clear();
    }
}
