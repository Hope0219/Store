package com.example.store.service.ex;

public class DeleteaddressException extends ServiceException{
    public DeleteaddressException() {
    }

    public DeleteaddressException(String message) {
        super(message);
    }

    public DeleteaddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteaddressException(Throwable cause) {
        super(cause);
    }

    public DeleteaddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
