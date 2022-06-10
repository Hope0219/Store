package com.example.store.service.ex;

public class NoproductException extends RuntimeException{

    public NoproductException() {
        super();
    }

    public NoproductException(String message) {
        super(message);
    }

    public NoproductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoproductException(Throwable cause) {
        super(cause);
    }

    protected NoproductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

