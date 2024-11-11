package store;

import camp.nextstep.edu.missionutils.Console;
import store.config.ConvenienceStoreConfig;

public class Application {
    public static void main(String[] args) {
        ConvenienceStoreConfig convenienceStoreConfig = new ConvenienceStoreConfig();
        convenienceStoreConfig.counterController().respond();
        Console.close();
    }
}
