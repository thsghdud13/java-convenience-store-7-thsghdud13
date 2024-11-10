package store.repository;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Item;

public class InMemoryInventoryRepository implements InventoryRepository {
    private final List<Item> items = new ArrayList<>();
    private static final InMemoryInventoryRepository INSTANCE = new InMemoryInventoryRepository();

    private InMemoryInventoryRepository() {

    }

    public static InMemoryInventoryRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Item item) {
        items.add(item);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);

    }
}
