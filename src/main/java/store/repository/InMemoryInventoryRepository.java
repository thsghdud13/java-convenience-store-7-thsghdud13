package store.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.exception.ErrorStatus;
import store.exception.handler.InventoryErrorHandler;
import store.model.domain.Item;
import store.model.domain.Order;

public class InMemoryInventoryRepository implements InventoryRepository {
    private final Map<String, Item> items = new LinkedHashMap<>();
    private static final InMemoryInventoryRepository INSTANCE = new InMemoryInventoryRepository();

    private InMemoryInventoryRepository() {

    }

    public static InMemoryInventoryRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Item item) {
        if (items.containsKey(item.getName())) {
            Item existingItem = items.get(item.getName());
            existingItem.addPromotionQuantity(item.getPromotionQuantity());
            existingItem.addRegularQuantity(item.getRegularQuantity());
            return;
        }
        items.put(item.getName(), item);
    }

    @Override
    public void update(Order order) {
        Item item = items.get(order.getItemName());
        item.subPromotionQuantity(order.getPromotionQuantity());
        item.subRegularQuantity(order.getRegularQuantity());
        items.put(item.getName(), item);
    }

    @Override
    public Item findByName(String name) {
        Item item = items.get(name);
        if (item == null) {
            throw new InventoryErrorHandler(ErrorStatus.ITEM_NOT_FOUND);
        }
        return new Item(item);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public void clear() {
        items.clear();
    }

}
