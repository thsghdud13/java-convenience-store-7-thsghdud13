package store.dto.request;

import java.util.HashMap;
import java.util.Map;

public class OrderRequestDto {
    private final Map<String, Integer> orderList;

    public OrderRequestDto(Map<String, Integer> orderList) {
        this.orderList = orderList;
    }

    public Map<String, Integer> getOrderList() {
        return new HashMap<>(orderList);
    }
}
