package store.service;

import java.util.List;
import store.dto.request.ProductInfoDto;
import store.dto.response.InventoryResponseDto;
import store.model.domain.Item;
import store.model.domain.Order;
import store.model.domain.promotion.PromotionPolicy;
import store.repository.InventoryRepository;
import store.repository.PromotionRepository;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final PromotionRepository promotionRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, PromotionRepository promotionRepository) {
        this.inventoryRepository = inventoryRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void save(List<ProductInfoDto> productInfoDtos) {
        for (ProductInfoDto productInfoDto : productInfoDtos) {
            PromotionPolicy promotionPolicy = promotionRepository.findByName(productInfoDto.getName());
            inventoryRepository.save(
                    new Item(productInfoDto.getName(), productInfoDto.getPrice(), productInfoDto.getQuantity(),
                            promotionPolicy));
        }
    }

    @Override
    public InventoryResponseDto findAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

}
