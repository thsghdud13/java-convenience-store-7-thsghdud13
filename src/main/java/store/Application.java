package store;

import camp.nextstep.edu.missionutils.Console;
import store.config.ConvenienceStoreConfig;
import store.repository.InMemoryInventoryRepository;
import store.repository.InMemoryPromotionRepository;

public class Application {
    public static void main(String[] args) {
        ConvenienceStoreConfig convenienceStoreConfig = new ConvenienceStoreConfig();
        convenienceStoreConfig.counterController().respond();
        Console.close();
        InMemoryPromotionRepository.getInstance().clear();
        InMemoryInventoryRepository.getInstance().clear();
    }
}
