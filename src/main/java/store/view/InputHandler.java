package store.view;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.dto.request.ProductInfoDto;
import store.dto.request.PromotionInfoDto;
import store.util.CSVData;
import store.util.CSVFileLoader;

public class InputHandler {
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";
    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.md";

    public static List<PromotionInfoDto> getPromotionInfos() {
        return toPromotionInfoDtos(CSVFileLoader.readCSV(PROMOTIONS_FILE_PATH));
    }

    public static List<ProductInfoDto> getProductInfos() {
        return toProductInfoDtos(CSVFileLoader.readCSV(PRODUCTS_FILE_PATH));
    }

    private static List<PromotionInfoDto> toPromotionInfoDtos(CSVData csvData) {
        List<PromotionInfoDto> promotionInfos = new ArrayList<>();
        List<List<String>> rows = csvData.getRows();
        for (List<String> row : rows) {
            promotionInfos.add(new PromotionInfoDto(row.get(0), parseInt(row.get(1)), parseInt(row.get(2)),
                    LocalDate.parse(row.get(3)), LocalDate.parse(row.get(4))));
        }
        return promotionInfos;
    }

    private static List<ProductInfoDto> toProductInfoDtos(CSVData csvData) {
        List<ProductInfoDto> productInfos = new ArrayList<>();
        List<List<String>> rows = csvData.getRows();
        for (List<String> row : rows) {
            productInfos.add(new ProductInfoDto(row.get(0), parseInt(row.get(1)), parseInt(row.get(2)),
                    row.get(3)));
        }
        return productInfos;
    }


}
