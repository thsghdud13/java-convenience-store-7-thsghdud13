package store.exception;

public enum ErrorStatus implements BaseErrorCode {
    //문자열 파싱 관련 예외
    PARSE_ERROR("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INPUT_ERROR("잘못된 입력입니다. 다시 입력해 주세요."),
    //재고 관련 예외
    ITEM_NOT_FOUND("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INSUFFICIENT_STOCK_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    ;


    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorStatus() {
        return ERROR_PREFIX + message;
    }
}
