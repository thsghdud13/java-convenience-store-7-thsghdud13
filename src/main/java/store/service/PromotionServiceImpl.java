package store.service;

import java.util.List;
import store.dto.request.PromotionInfoDto;
import store.model.domain.promotion.BuyNGet1Promotion;
import store.model.domain.promotion.DatePromotionCondition;
import store.repository.PromotionRepository;

public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void save(List<PromotionInfoDto> promotionInfoDtos) {
        for (PromotionInfoDto promotionInfoDto : promotionInfoDtos) {
            promotionRepository.save(
                    new BuyNGet1Promotion(promotionInfoDto.getName(), promotionInfoDto.getBuyQuantity(),
                            promotionInfoDto.getGetQuantity(), promotionInfoDto.getStartDate(),
                            promotionInfoDto.getEndDate(), new DatePromotionCondition()));
        }
    }
}
