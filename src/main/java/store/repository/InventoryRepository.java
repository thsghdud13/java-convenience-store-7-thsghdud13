package store.repository;

import java.util.List;
import store.model.domain.Item;

public interface InventoryRepository {
    void save(Item item);

    List<Item> findAll();
}
