package store.service;

import java.util.List;
import store.dto.request.PromotionInfoDto;

public interface PromotionService {
    void save(List<PromotionInfoDto> promotionInfoDtos);
}
