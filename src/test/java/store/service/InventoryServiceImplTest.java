package store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.request.ProductInfoDto;
import store.model.domain.Item;
import store.model.domain.promotion.BuyNGet1Promotion;
import store.model.domain.promotion.NonePromotion;
import store.model.domain.promotion.PromotionPolicy;
import store.repository.InventoryRepository;
import store.repository.PromotionRepository;

class InventoryServiceImplTest {

    InventoryServiceImpl inventoryService;
    InventoryRepository inventoryRepository;
    PromotionRepository promotionRepository;

    @BeforeEach
    void setUp() {
        inventoryRepository = new InventoryRepositoryMock();
        promotionRepository = new PromotionRepositoryMock();
        inventoryService = new InventoryServiceImpl(inventoryRepository, promotionRepository);
    }

    @Test
    void save() {
        //given
        promotionRepository.save(new BuyNGet1Promotion("반짝할인", 1, 1, LocalDate.now(), LocalDate.now()));
        List<ProductInfoDto> productInfoDtos = List.of(new ProductInfoDto("물", 1000, 1, "반짝할인"));

        //when
        inventoryService.save(productInfoDtos);

        //then
        assertThat("물")
                .isEqualTo(inventoryRepository.findAll().get(0).getName());
    }

    static class PromotionRepositoryMock implements PromotionRepository {
        private final Map<String, PromotionPolicy> promotions = new HashMap<>();

        @Override
        public void save(PromotionPolicy promotionPolicy) {
            promotions.put(promotionPolicy.getPromotionName(), promotionPolicy);
        }

        @Override
        public PromotionPolicy findByName(String name) {
            return promotions.getOrDefault(name, new NonePromotion());
        }
    }

    static class InventoryRepositoryMock implements InventoryRepository {
        private final List<Item> items = new ArrayList<>();

        @Override
        public void save(Item item) {
            items.add(item);
        }

        @Override
        public List<Item> findAll() {
            return new ArrayList<>(items);
        }
    }
}