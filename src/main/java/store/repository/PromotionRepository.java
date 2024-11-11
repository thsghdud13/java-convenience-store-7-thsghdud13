package store.repository;

import store.model.domain.promotion.PromotionPolicy;

public interface PromotionRepository {
    void save(PromotionPolicy promotionPolicy);

    PromotionPolicy findByName(String name);

    void clear();
}
