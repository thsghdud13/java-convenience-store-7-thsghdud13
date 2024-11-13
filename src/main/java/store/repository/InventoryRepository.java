package store.repository;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Order;

public interface InventoryRepository {
    void save(Item item);

    void update(Order order);

    Item findByName(String name);

    List<Item> findAll();

    void clear();
}
