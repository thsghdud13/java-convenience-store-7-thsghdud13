package store.service;

import java.util.List;
import store.dto.request.ProductInfoDto;
import store.dto.response.InventoryResponseDto;
import store.model.domain.Order;

public interface InventoryService {
    void save(List<ProductInfoDto> productInfoDtos);

    InventoryResponseDto findAll();

    void update(Order order);
}
