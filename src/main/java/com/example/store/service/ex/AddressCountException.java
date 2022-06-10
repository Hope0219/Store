package com.example.store.service.ex;

public class AddressCountException extends ServiceException{
    public AddressCountException() {
        super();
    }

    public AddressCountException(String message) {
        super(message);
    }

    public AddressCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountException(Throwable cause) {
        super(cause);
    }

    protected AddressCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
