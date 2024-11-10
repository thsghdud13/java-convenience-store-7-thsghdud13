package store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.dto.request.PromotionInfoDto;
import store.model.domain.promotion.NonePromotion;
import store.model.domain.promotion.PromotionPolicy;
import store.repository.PromotionRepository;

class PromotionServiceImplTest {
    PromotionServiceImpl promotionService;
    PromotionRepository promotionRepository;

    @BeforeEach
    void setUp() {
        promotionRepository = new PromotionRepositoryMock();
        promotionService = new PromotionServiceImpl(promotionRepository);
    }

    @Test
    void 저장_기능_테스트() {
        //given
        List<PromotionInfoDto> 반짝할인 = List.of(
                new PromotionInfoDto("반짝할인", 1, 1, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 30)));

        //when
        promotionService.save(반짝할인);
        PromotionPolicy 반짝할인1 = promotionRepository.findByName("반짝할인");

        //then
        assertThat("반짝할인")
                .isEqualTo(promotionRepository.findByName("반짝할인").getPromotionName());
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
}
