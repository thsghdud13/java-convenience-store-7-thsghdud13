package store.exception.handler;


import store.exception.BaseErrorCode;
import store.exception.GeneralException;

public class InputErrorHandler extends GeneralException {
    public InputErrorHandler(BaseErrorCode code) {
        super(code);
    }
}
