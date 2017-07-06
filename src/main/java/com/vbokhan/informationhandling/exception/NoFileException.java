package com.vbokhan.informationhandling.exception;

public class NoFileException extends Exception {

    public NoFileException() {
        super();
    }

    public NoFileException(String message) {
        super(message);
    }

    public NoFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFileException(Throwable cause) {
        super(cause);
    }
}
