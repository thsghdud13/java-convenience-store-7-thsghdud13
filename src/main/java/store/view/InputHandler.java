package store.view;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.dto.request.OrderRequestDto;
import store.dto.request.ProductInfoDto;
import store.dto.request.PromotionInfoDto;
import store.enums.UserInput;
import store.exception.ErrorStatus;
import store.exception.handler.ParseErrorHandler;
import store.model.domain.OrderItem;
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

    public static OrderRequestDto getOrderDto() {
        OutputView.print("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        try {
            String input = InputView.read();
            validateOrderInput(input);
            OrderRequestDto orderRequestDto = toOrderRequestDto(input);
            return orderRequestDto;
        } catch (ParseErrorHandler e) {
            OutputView.printError(e);
            return getOrderDto();
        }
    }

    private static void validateOrderInput(String input) {
        Matcher matcher = Pattern.compile("\\[[가-힣a-zA-Z]+-\\d+\\](,[\\[가-힣a-zA-Z]+-\\d+])*").matcher(input);
        if (!matcher.matches()) {
            throw new ParseErrorHandler(ErrorStatus.PARSE_ERROR);
        }
    }

    private static OrderRequestDto toOrderRequestDto(String input) {
        List<String> orderList = List.of(input.split(","));
        Map<String, Integer> orders = new HashMap<>();
        for (String s : orderList) {
            s = s.substring(1, s.length() - 1);
            List<String> split = List.of(s.split("-"));
            orders.put(split.get(0), parseInt(split.get(1)));
        }
        return new OrderRequestDto(orders);
    }

    private static List<String> parse(String input) {
        return List.of(input.split(","));
    }

    public static UserInput getUserInputForAdditionalPromotionQuantity(OrderItem orderItem) {
        try {
            String infoMessage = String.format("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", orderItem.getName(),
                    orderItem.getAdditionalQuantity());
            OutputView.print(infoMessage);
            return toUserInput(InputView.read());
        } catch (ParseErrorHandler e) {
            OutputView.printError(e);
            return getUserInputForAdditionalPromotionQuantity(orderItem);
        }
    }

    public static UserInput getUserInputForNonPromotionalQuantity(OrderItem orderItem) {
        try {
            String infoMessage = String.format("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)",
                    orderItem.getName(),
                    orderItem.getNonDiscountQuantity());
            OutputView.print(infoMessage);
            return toUserInput(InputView.read());
        } catch (ParseErrorHandler e) {
            OutputView.printError(e);
            return getUserInputForNonPromotionalQuantity(orderItem);
        }
    }

    public static UserInput getUserInputForMembership() {
        try {
            OutputView.print("멤버십 할인을 받으시겠습니까? (Y/N)");
            return toUserInput(InputView.read());
        } catch (ParseErrorHandler e) {
            OutputView.printError(e);
            return getUserInputForMembership();
        }
    }

    public static UserInput getUserInputForAnotherOrder() {
        try {
            OutputView.print("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
            return toUserInput(InputView.read());
        } catch (ParseErrorHandler e) {
            OutputView.printError(e);
            return getUserInputForMembership();
        }
    }

    private static UserInput toUserInput(String input) {
        if (input.equals("Y")) {
            return UserInput.Y;
        }
        if (input.equals("N")) {
            return UserInput.N;
        }
        throw new ParseErrorHandler(ErrorStatus.PARSE_ERROR);
    }

}
