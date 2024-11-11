package store.service;

import java.util.List;
import java.util.stream.Collectors;
import store.dto.request.ProductInfoDto;
import store.dto.response.InventoryResponseDto;
import store.model.domain.Item;
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
            PromotionPolicy promotionPolicy = promotionRepository.findByName(productInfoDto.getPromotionName());
            inventoryRepository.save(
                    new Item(productInfoDto.getName(), productInfoDto.getPrice(), productInfoDto.getRegularQuantity(),
                            productInfoDto.getPromotionQuantity(), promotionPolicy));
        }
    }

    @Override
    public List<InventoryResponseDto> findAll() {
        List<Item> items = inventoryRepository.findAll();
        return items.stream()
                .map(InventoryResponseDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public Item findByItemName(String name) {
        Item item = inventoryRepository.findByName(name);
        if (item == null) {
            throw new IllegalArgumentException("그런 상품은 없음");
        }
        return item;
    }

}
