package store.exception.handler;


import store.exception.BaseErrorCode;
import store.exception.GeneralException;

public class ParseErrorHandler extends GeneralException {
    public ParseErrorHandler(BaseErrorCode code) {
        super(code);
    }
}
