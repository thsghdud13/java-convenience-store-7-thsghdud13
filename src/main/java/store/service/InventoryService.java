package store.service;

import java.util.List;
import store.dto.request.ProductInfoDto;
import store.dto.response.InventoryResponseDto;
import store.model.domain.Item;

public interface InventoryService {
    void save(List<ProductInfoDto> productInfoDtos);

    List<InventoryResponseDto> findAll();

    Item findByItemName(String name);
}
