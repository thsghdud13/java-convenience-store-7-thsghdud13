package store.exception.handler;


import store.exception.BaseErrorCode;
import store.exception.GeneralException;

public class InventoryErrorHandler extends GeneralException {
    public InventoryErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
